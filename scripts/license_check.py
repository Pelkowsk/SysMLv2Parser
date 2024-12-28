import os
import sys
import json

# Liste der verbotenen Lizenzen in Kleinbuchstaben
PROHIBITED_LICENSES = {license.casefold() for license in {
    "GPL-2.0-only", "MPL-1.1", "EPL-1.0", "CDDL-1.0", "Apache-1.1",
    "MS-PL", "APSL-2.0", "Artistic-1.0", "SPL-1.0", "NPL-1.1"
}}



# Erwarteter Lizenzheader (Platzhalter)
REQUIRED_HEADER = """/*****************************************************************************
 * SysML 2 Pilot Implementation (Modifizierte Version)
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018 IncQuery Labs Ltd.
 * Copyright (c) 2019 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019 Mgnite Inc.
 * Copyright (c) 2024 [Dein Name / Deine Organisation] (für Modifikationen)
 *
 * Dieses Programm ist freie Software: Sie können es unter den Bedingungen
 * der GNU Lesser General Public License, wie von der Free Software Foundation
 * veröffentlicht, weiterverteilen und/oder modifizieren, entweder gemäß Version 3
 * der Lizenz oder (nach Ihrer Wahl) jeder späteren Version.
 *
 * Dieses Programm wird in der Hoffnung verbreitet, dass es nützlich sein wird,
 * jedoch ohne jegliche Garantie; sogar ohne die implizite Garantie der
 * Marktgängigkeit oder Eignung für einen bestimmten Zweck. Siehe die
 * GNU Lesser General Public License für weitere Details.
 *
 * Sie sollten eine Kopie der GNU Lesser General Public License zusammen mit
 * diesem Programm erhalten haben. Wenn nicht, siehe <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 * Ursprüngliche Beitragende:
 *  - Ed Seidewitz, MDS
 *  - Zoltan Kiss, IncQuery
 *  - Balazs Grill, IncQuery
 *  - Hisashi Miyashita, Maplesoft/Mgnite
 *
 * Beitragende für Modifikationen:
 *  - [Dein Name / Deine Organisation] (2024)
 *****************************************************************************/"""


# Sicherstellen, dass die Umgebungsvariable geladen wird
scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')

if not scancode_results_dir:
    print("Error: Environment variable 'SCANCODE_RESULTS_DIR' not set.")
    sys.exit(1)

# Sicherstellen, dass die Umgebungsvariable geladen wird
g4_files_list_path = os.getenv('G4_FILES_LIST')

if not g4_files_list_path:
    print("Error: Environment variable 'g4_files_list_path' not set.")
    sys.exit(1)


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

def check_g4_files_for_license_header(g4_files_list_path, header_variable_name="REQUIRED_HEADER"):
    """
    Überprüft, ob die in der Liste angegebenen .g4-Dateien den erforderlichen Lizenzheader enthalten.
    :param g4_files_list_path: Pfad zur Datei mit der Liste der .g4-Dateien.
    :param header_variable_name: Name der Variablen, die den erwarteten Lizenzheader enthält.
    :return: Liste der Dateien ohne den erforderlichen Lizenzheader.
    """
    if not os.path.exists(g4_files_list_path):
        print(f"Error: File list '{g4_files_list_path}' not found.")
        sys.exit(1)

    # Header aus der globalen Namespace-Variable holen
    required_header = globals().get(header_variable_name, None)
    if required_header is None:
        raise ValueError(f"Die Header-Variable '{header_variable_name}' ist nicht definiert.")

    # Normalisierung des Headers
    normalized_required_header = " ".join(required_header.split())

    missing_headers = []
    with open(g4_files_list_path, "r") as file_list:
        for filepath in file_list:
            filepath = filepath.strip()
            if filepath and os.path.exists(filepath):
                try:
                    with open(filepath, "r", encoding="utf-8") as file:
                        content = file.read()
                        # Normalisierung des Dateiinhalts
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
    output_report_path = os.getenv('OUTPUT_REPORT_PATH', '$GITHUB_WORKSPACE/license_reports/license_and_header_check_report.json')


    if not scancode_results_dir or not g4_files_list_path:
        print("Error: Environment variables 'SCANCODE_RESULTS_DIR' or 'G4_FILES_LIST_PATH' not set.")
        sys.exit(1)

    # Gesamten Inhalt der ScanCode-Ergebnisdateien einlesen und in Kleinbuchstaben umwandeln
    combined_content = load_scancode_results_as_string(scancode_results_dir)

    # Nach verbotenen Lizenzen im kombinierten Inhalt suchen
    prohibited_files = find_prohibited_licenses_in_content(combined_content)

    # Überprüfen, ob die .g4-Dateien den erforderlichen Lizenzheader enthalten
    header_missing_files = check_g4_files_for_license_header(g4_files_list_path, "REQUIRED_HEADER")

    # Report erstellen
    report = {
        "These files contain prohibited licenses": prohibited_files,
        "These files do not contain the project license header; please insert missing headers": header_missing_files,
        "status": "success" if not prohibited_files and not header_missing_files else "failure"
    }

    # Report speichern
    write_report(prohibited_files, header_missing_files, output_report_path)

    # Pipeline-Status basierend auf den Ergebnissen setzen
    if report["status"] == "failure":
        print(f"Failure: Prohibited licenses or missing headers found. See '{output_report_path}' for details.")
        sys.exit(1)
    else:
        print("Success: No prohibited licenses and all required headers found.")


if __name__ == "__main__":
    main()

