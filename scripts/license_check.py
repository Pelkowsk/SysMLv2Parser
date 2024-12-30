import os
import sys
import json

# List of prohibited licenses
PROHIBITED_LICENSES = {license.casefold() for license in {
    "GPL-2.0-only", "MPL-1.1", "EPL-1.0", "CDDL-1.0", "Apache-1.1",
    "MS-PL", "APSL-2.0", "Artistic-1.0", "SPL-1.0", "QPL-1.0",
    "ZPL-2.0", "NPL-1.1", "CPAL-1.0", "Sleepycat", "SSPL-1.0", "EPL-2.0",
    "CC-BY-SA-4.0", "OFL-1.1", "PHP-3.01", "OpenSSL", "NCSA",
    "Reciprocal Public License 1.5"

}}



# required project license header
REQUIRED_HEADER = """/*****************************************************************************
 * SysML 2 Pilot Implementation
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018-2024 IncQuery Labs Ltd.
 * Copyright (c) 2019-2023 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019-2023 Mgnite Inc.
 * Copyright (c) 2024-2025 [Your Organization]
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


# make sure variable will be loaded
scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')

if not scancode_results_dir:
    print("Error: Environment variable 'SCANCODE_RESULTS_DIR' not set.")
    sys.exit(1)

# make sure variable will be loaded
g4_files_list_path = os.getenv('G4_FILES_LIST')

if not g4_files_list_path:
    print("Error: Environment variable 'g4_files_list_path' not set.")
    sys.exit(1)


def load_scancode_results_as_string(scancode_results_dir):
    """
    loads all informations of of Scancode report as String in small letters.
    :param scancode_results_dir: directory with scancode report.
    :return: All Informations in String in small letters.
    """
    if not os.path.exists(scancode_results_dir):
        print(f"Error: Directory '{scancode_results_dir}' not found.")
        sys.exit(1)

    combined_content = ""
    for filename in os.listdir(scancode_results_dir):
        if filename.endswith(".json"):
            with open(os.path.join(scancode_results_dir, filename), "r", encoding="utf-8") as f:
                combined_content += f.read().casefold()
    return combined_content

def find_prohibited_licenses_in_content(content):
    """
    Search for forbidden licenses.
    :param content: String all small letters
    :return: List of prohibited licenses
    """
    found_licenses = []
    for license in PROHIBITED_LICENSES:
        if license in content and license not in found_licenses:
            found_licenses.append(license)
    return found_licenses

def check_g4_files_for_license_header(g4_files_list_path, header_variable_name="REQUIRED_HEADER"):
    """
    Checks if the files (.g4 and .java) include the license header.
    :param g4_files_list_path: path to all .java and .g4-files.
    :param header_variable_name: name of the variable which includes the project license header.
    :return: List of files which do not include the project license header.
    """
    if not os.path.exists(g4_files_list_path):
        print(f"Error: File list '{g4_files_list_path}' not found.")
        sys.exit(1)

    # get header from global variable required_header
    required_header = globals().get(header_variable_name, None)
    if required_header is None:
        raise ValueError(f"Die Header-Variable '{header_variable_name}' ist nicht definiert.")

    # normalize header (better handling for small mistakes like extra spaces)
    normalized_required_header = " ".join(required_header.split())

    missing_headers = []
    with open(g4_files_list_path, "r") as file_list:
        for filepath in file_list:
            filepath = filepath.strip()
            if filepath and os.path.exists(filepath):
                try:
                    with open(filepath, "r", encoding="utf-8") as file:
                        content = file.read()
                        # Normalize Data
                        normalized_content = " ".join(content.split())
                        if normalized_required_header not in normalized_content:
                            missing_headers.append(filepath)
                except Exception as e:
                    print(f"Error reading file '{filepath}': {e}")
                    missing_headers.append(filepath)
            else:
                print(f"Warning: File '{filepath}' does not exist.")
                missing_headers.append(filepath)
    return missing_headers


def write_report(prohibited_files, missing_headers, output_report_path):
    """
    Writes Report of the prohibited licenses found and all files which are missing header informations.
    :param prohibited_files: List of prohibited licenses.
    :param missing_headers: List of files with missing header informations.
    :param output_report_path: path to report file.
    """
    report = {
        "These files contain prohibited licenses": prohibited_files,
        "These files do not contain the project license header; please insert missing headers": missing_headers,
        "status": "success" if not prohibited_files and not missing_headers else "failure"
    }

    # make sure path exists
    os.makedirs(os.path.dirname(output_report_path), exist_ok=True)

    with open(output_report_path, "w", encoding="utf-8") as report_file:
        json.dump(report, report_file, indent=2)
    print(f"Report written to {output_report_path}")


def main():
    scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')
    g4_files_list_path = os.getenv('G4_FILES_LIST')
    output_report_path = os.getenv('OUTPUT_REPORT_PATH', '$GITHUB_WORKSPACE/license_reports/license_and_header_check_report.json')



    if not scancode_results_dir or not g4_files_list_path:
        print("Error: Environment variables 'SCANCODE_RESULTS_DIR' or 'G4_FILES_LIST_PATH' not set.")
        sys.exit(1)

    # Loads whole content of scancode result
    combined_content = load_scancode_results_as_string(scancode_results_dir)

    # Searching for prohibited files in combined content
    prohibited_files = find_prohibited_licenses_in_content(combined_content)

    # Check if all files (.java and .g4) include the project license header
    header_missing_files = check_g4_files_for_license_header(g4_files_list_path, "REQUIRED_HEADER")

    # set up report
    report = {
        "These files contain prohibited licenses": prohibited_files,
        "These files do not contain the project license header; please insert missing headers": header_missing_files,
        "status": "success" if not prohibited_files and not header_missing_files else "failure"
    }

    # save report
    write_report(prohibited_files, header_missing_files, output_report_path)

    # set up state for pipeline feedback depending on report
    if report["status"] == "failure":
        print(f"Failure: Prohibited licenses or missing headers found. See '{output_report_path}' for details.")
        sys.exit(1)
    else:
        print("Success: No prohibited licenses and all required headers found.")


if __name__ == "__main__":
    main()

