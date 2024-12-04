import os
import json
import sys

# List of licenses incompatible with LGPLv3
PROHIBITED_LICENSES = {
    "afl-3.0", "apache-2.0", "artistic-1.0", "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0",
    "cc-by-nc-nd-4.0", "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5",
    "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0"
}

# Expected header (as string)
REQUIRED_HEADER = """/*****************************************************************************
 * SysML 2 Pilot Implementation
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018 IncQuery Labs Ltd.
 * Copyright (c) 2019 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019 Mgnite Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 * Contributors:
 *  Ed Seidewitz, MDS
 *  Zoltan Kiss, IncQuery
 *  Balazs Grill, IncQuery
 *  Hisashi Miyashita, Maplesoft/Mgnite
 *
 *****************************************************************************/"""

scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')
if not os.path.exists(scancode_results_dir):
    print(f"Error: Directory '{scancode_results_dir}' not found.")
    sys.exit(1)

if not os.listdir(scancode_results_dir):
    print(f"Warning: Directory '{scancode_results_dir}' is empty. No scan results available.")
    sys.exit(0)

def load_scancode_results(scancode_results_dir):
    """
    Loads all ScanCode results from the specified directory.
    :param scancode_results_dir: Directory containing ScanCode result files.
    :return: List of scan results.
    """
    if not os.path.exists(scancode_results_dir):
        print(f"Error: Directory '{scancode_results_dir}' not found.")
        sys.exit(1)

    results = []
    for filename in os.listdir(scancode_results_dir):
        if filename.endswith(".json"):
            with open(os.path.join(scancode_results_dir, filename), "r") as f:
                results.append(json.load(f))
    return results

def check_licenses(scan_results):
    """
    Checks the scan results for prohibited licenses.
    :param scan_results: List of scan results.
    :return: List of files with prohibited licenses.
    """
    prohibited_files = []
    for result in scan_results:
        for file in result.get("files", []):
            for license_info in file.get("licenses", []):
                license_id = license_info.get("spdx_license_key", "")
                if license_id in PROHIBITED_LICENSES:
                    prohibited_files.append({
                        "file": file["path"],
                        "license": license_id
                    })
    return prohibited_files

def check_header(file_path, required_header):
    """
    Checks if the file contains the required header.
    :param file_path: Path to the file.
    :param required_header: Expected header text.
    :return: True if the header is found, otherwise False.
    """
    with open(file_path, "r") as f:
        content = f.read()
        return required_header in content

def main():
    scancode_results_dir = os.getenv("SCANCODE_RESULTS_DIR")
    g4_files_list_path = os.getenv("G4_FILES_LIST")

    if not scancode_results_dir or not g4_files_list_path:
        print("Error: Environment variables 'SCANCODE_RESULTS_DIR' or 'G4_FILES_LIST' not set.")
        sys.exit(1)

    print(f"Debug: SCANCODE_RESULTS_DIR={scancode_results_dir}")
    print(f"Debug: G4_FILES_LIST={g4_files_list_path}")

    # Load ScanCode results
    scan_results = load_scancode_results(scancode_results_dir)

    # Check for prohibited licenses
    prohibited_files = check_licenses(scan_results)

    # Check headers in .g4 files
    header_missing_files = []
    with open(g4_files_list_path, "r") as f:
        for line in f:
            g4_file_path = line.strip()
            if not check_header(g4_file_path, REQUIRED_HEADER):
                header_missing_files.append(g4_file_path)

    # Create report
    report = {
        "file contains prohibited_licenses": prohibited_files,
        "file does not contain project license header, please insert missing_headers": header_missing_files,
        "status": "success" if not prohibited_files and not header_missing_files else "failure"
    }

    # Save report
    with open("license_and_header_check_report.json", "w") as f:
        json.dump(report, f, indent=2)

    # Set pipeline status based on results
    if report["status"] == "failure":
        print("Failure: Prohibited licenses or missing headers found. See 'license_and_header_check_report.json' for details.")
        sys.exit(1)
    else:
        print("Success: No prohibited licenses and all required headers found.")

if __name__ == "__main__":
    main()
