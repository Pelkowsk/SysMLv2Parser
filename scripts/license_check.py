import os
import json
import sys

# List of prohibited licenses
PROHIBITED_LICENSES = ["AGPL-1.0-only", "AGPL-1.0-or-later", "AGPL-3.0-only", "AGPL-3.0-or-later",
                       "BitTorrent-1.0", "BitTorrent-1.1",
                       "CC-BY-NC-1.0", "CC-BY-NC-2.0", "CC-BY-NC-2.5", "CC-BY-NC-3.0", "CC-BY-NC-4.0",
                       "CC-BY-NC-ND-1.0", "CC-BY-NC-ND-2.0", "CC-BY-NC-ND-2.5", "CC-BY-NC-ND-3.0", "CC-BY-NC-ND-4.0",
                       "CC-BY-NC-SA-1.0", "CC-BY-NC-SA-2.0", "CC-BY-NC-SA-2.5", "CC-BY-NC-SA-3.0", "CC-BY-NC-SA-4.0",
                       "CPAL-1.0", "EPL-1.0", "EPL-2.0", "EUPL-1.1", "EUPL-1.2",
                       "IPL-1.0", "MS-PL", "MPL-1.0", "MPL-1.1", "MPL-2.0",
                       "OSL-3.0", "SSPL-1.0",
                       "Unlicense", "WTFPL", "Zlib-acknowledgement"]


# project license header
PROJECT_LICENSE_HEADER = """/*****************************************************************************
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
output_report_path = os.getenv('OUTPUT_REPORT_PATH', 'license_check_report.json')
file_list_path = os.getenv('FILE_LIST_PATH', 'file_list.txt')  # Liste der zu prüfenden Dateien
if not os.path.exists(scancode_results_dir):
    print(f"Error: Directory '{scancode_results_dir}' not found.")
    sys.exit(1)

if not os.listdir(scancode_results_dir):
    print(f"Warning: Directory '{scancode_results_dir}' is empty. No scan results available.")
    sys.exit(0)

def load_file_list(file_list_path):
    """
    Lädt die Liste der Dateien, die geprüft werden sollen.
    :param file_list_path: Pfad zur Datei mit der Liste der Dateien.
    :return: Liste der Dateipfade.
    """
    if not os.path.exists(file_list_path):
        print(f"Error: File list '{file_list_path}' not found.")
        sys.exit(1)

    with open(file_list_path, "r") as file_list:
        files = [line.strip() for line in file_list if line.strip()]
    return files

def load_scancode_results(scancode_results_dir):
    """
    Lädt alle ScanCode-Ergebnisse aus dem angegebenen Verzeichnis.
    :param scancode_results_dir: Verzeichnis mit ScanCode-Ergebnisdateien.
    :return: Liste der Scan-Ergebnisse.
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

def check_licenses_and_headers(scan_results, file_list):
    """
    Prüft die Scan-Ergebnisse auf verbotene Lizenzen und fehlende Projektlizenzheader.
    :param scan_results: Liste der Scan-Ergebnisse.
    :param file_list: Liste der zu prüfenden Dateien.
    :return: Drei Listen: Dateien mit verbotenen Lizenzen, Dateien ohne Projektlizenzheader, alle überprüften Lizenzen.
    """
    prohibited_files = []  # Dateien mit verbotenen Lizenzen sammeln.
    missing_headers = []   # Dateien ohne Projektlizenzheader sammeln.
    all_checked_licenses = []  # Alle überprüften Lizenzen speichern.

    for result in scan_results:
        for file in result.get("files", []):
            for license_info in file.get("licenses", []):
                license_id = license_info.get("spdx_license_key", "").lower()
                license_expression = license_info.get("license_expression_spdx", "").lower()

                # Hinzufügen der überprüften Lizenz zur Liste
                all_checked_licenses.append({
                    "file": file["path"],
                    "license_id": license_id,
                    "license_expression": license_expression
                })

                # Lizenz in einfacher oder kombinierter Form prüfen
                if license_id in [x.lower() for x in PROHIBITED_LICENSES] or \
                        any(prohibited in license_expression for prohibited in [x.lower() for x in PROHIBITED_LICENSES]):
                    prohibited_files.append({
                        "file": file["path"],
                        "license": license_id or license_expression
                    })

    # Prüfen auf fehlende Lizenzheader basierend auf der Dateiliste
    for filepath in file_list:
        try:
            with open(filepath, "r") as file:
                content = file.read()
                if PROJECT_LICENSE_HEADER not in content:
                    missing_headers.append(filepath)
        except Exception as e:
            print(f"Error reading file '{filepath}': {e}")
            missing_headers.append(filepath)

    return prohibited_files, missing_headers, all_checked_licenses

def write_report(prohibited_files, missing_headers, all_checked_licenses):
    """
    Schreibt den Report mit verbotenen Lizenzen, fehlenden Lizenzheaders und überprüften Lizenzen in eine JSON-Datei.
    :param prohibited_files: Liste der Dateien mit verbotenen Lizenzen.
    :param missing_headers: Liste der Dateien ohne Projektlizenzheader.
    :param all_checked_licenses: Liste aller überprüften Lizenzen.
    """
    report = {
        "prohibited_files": prohibited_files,
        "missing_headers": missing_headers,
        "all_checked_licenses": all_checked_licenses
    }

    with open(output_report_path, "w") as report_file:
        json.dump(report, report_file, indent=2)
    print(f"Report written to {output_report_path}")

def main():
    # Datei-Liste laden
    file_list = load_file_list(file_list_path)

    # ScanCode-Ergebnisse laden
    scan_results = load_scancode_results(scancode_results_dir)

    # Verbotene Lizenzen und fehlende Header prüfen
    prohibited_files, missing_headers, all_checked_licenses = check_licenses_and_headers(scan_results, file_list)

    # Report schreiben
    write_report(prohibited_files, missing_headers, all_checked_licenses)

    # Ergebnisse ausgeben
    if prohibited_files or missing_headers:
        print("Issues found:")
        if prohibited_files:
            print("Prohibited licenses:")
            for entry in prohibited_files:
                print(f"- File: {entry['file']}, License: {entry['license']}")
        if missing_headers:
            print("Missing project license headers:")
            for file in missing_headers:
                print(f"- File: {file}")
        sys.exit(1)  # Exit mit Statuscode 1 bei gefundenen Problemen
    else:
        print("No issues found.")
        sys.exit(0)  # Exit mit Statuscode 0, wenn keine Probleme gefunden wurden

if __name__ == "__main__":
    main()
