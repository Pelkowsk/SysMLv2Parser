import os
import sys
import json

# Liste der verbotenen Lizenzen in Kleinbuchstaben
PROHIBITED_LICENSES = {
    "agpl-1.0-only", "agpl-1.0-or-later", "agpl-3.0-only", "agpl-3.0-or-later",
    "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0", "cc-by-nc-nd-4.0",
    "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5", "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0",
    "cpal-1.0", "epl-1.0", "epl-2.0", "eupl-1.1", "eupl-1.2",
    "ipl-1.0", "ms-pl", "mpl-1.0", "mpl-1.1", "mpl-2.0",
    "osl-3.0", "sspl-1.0",
    "unlicense", "wtfpl", "zlib-acknowledgement"
}

# Erwarteter Lizenzheader (Platzhalter)
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
# Sicherstellen, dass die Umgebungsvariable geladen wird
scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')

if not scancode_results_dir:
    print("Error: Environment variable 'SCANCODE_RESULTS_DIR' not set.")
    sys.exit(1)

# Sicherstellen, dass die Umgebungsvariable geladen wird
g4_files_list_path = os.getenv('G4_FILES_LIST')




def load_scancode_results_as_string(scancode_results_dir):
    """
    Lädt den gesamten Inhalt aller ScanCode-Ergebnisdateien als einen einzigen String in Kleinbuchstaben.
    :param scancode_results_dir: Verzeichnis mit ScanCode-Ergebnisdateien.
    :return: Gesamter Inhalt der Dateien als String in Kleinbuchstaben.
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
    Sucht nach verbotenen Lizenzen im gegebenen Inhalt.
    :param content: Inhalt als String in Kleinbuchstaben.
    :return: Liste der gefundenen verbotenen Lizenzen.
    """
    found_licenses = []
    for license in PROHIBITED_LICENSES:
        if license in content and license not in found_licenses:
            found_licenses.append(license)
    return found_licenses

def check_g4_files_for_license_header(g4_files_list_path, required_header):
    """
    Überprüft, ob die in der Liste angegebenen .g4-Dateien den erforderlichen Lizenzheader enthalten.
    :param g4_files_list_path: Pfad zur Datei mit der Liste der .g4-Dateien.
    :param required_header: Erwarteter Lizenzheader als Zeichenkette.
    :return: Liste der Dateien ohne den erforderlichen Lizenzheader.
    """
    if not os.path.exists(g4_files_list_path):
        print(f"Error: File list '{g4_files_list_path}' not found.")
        sys.exit(1)

    missing_headers = []
    with open(g4_files_list_path, "r") as file_list:
        for filepath in file_list:
            filepath = filepath.strip()
            if filepath and os.path.exists(filepath):
                try:
                    with open(filepath, "r", encoding="utf-8") as file:
                        content = file.read()
                        if required_header not in content:
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
    Schreibt den Report mit verbotenen Lizenzen und fehlenden Lizenzheaders in eine JSON-Datei.
    :param prohibited_files: Liste der gefundenen verbotenen Lizenzen.
    :param missing_headers: Liste der Dateien ohne Projektlizenzheader.
    :param output_report_path: Pfad zur Ausgabedatei für den Bericht.
    """
    report = {
        "These files contain prohibited licenses": prohibited_files,
        "These files do not contain the project license header; please insert missing headers": missing_headers,
        "status": "success" if not prohibited_files and not missing_headers else "failure"
    }

    with open(output_report_path, "w", encoding="utf-8") as report_file:
        json.dump(report, report_file, indent=2)
    print(f"Report written to {output_report_path}")

def main():
    scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')
    g4_files_list_path = os.getenv('G4_FILES_LIST')
    output_report_path = os.getenv('OUTPUT_REPORT_PATH', 'license_and_header_check_report.json')

    if not scancode_results_dir or not g4_files_list_path:
        print("Error: Environment variables 'SCANCODE_RESULTS_DIR' or 'G4_FILES_LIST_PATH' not set.")
        sys.exit(1)

    # Gesamten Inhalt der ScanCode-Ergebnisdateien einlesen und in Kleinbuchstaben umwandeln
    combined_content = load_scancode_results_as_string(scancode_results_dir)

    # Nach verbotenen Lizenzen im kombinierten Inhalt suchen
    prohibited_files = find_prohibited_licenses_in_content(combined_content)

    # Überprüfen, ob die .g4-Dateien den erforderlichen Lizenzheader enthalten
    header_missing_files = check_g4_files_for_license_header(g4_files_list_path, REQUIRED_HEADER)

    # Report erstellen
    report = {
        "These files contain prohibited licenses": prohibited_files,
        "These files do not contain the project license header; please insert missing headers": header_missing_files,
        "status": "success" if not prohibited_files and not header_missing_files else "failure"
    }

    # Report speichern
    with open(output_report_path, "w", encoding="utf-8") as f:
        json.dump(report, f, indent=2)

    # Pipeline-Status basierend auf den Ergebnissen setzen
    if report["status"] == "failure":
        print(f"Failure: Prohibited licenses or missing headers found. See '{output_report_path}' for details.")
        sys.exit(1)
    else:
        print("Success: No prohibited licenses and all required headers found.")

if __name__ == "__main__":
    main()

