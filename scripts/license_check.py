import os
import json

#konfiguration dependend on projects license:

PROHIBITED_LICENSES = ["afl-3.0", "apache-2.0", "artistic-1.0", "bittorrent-1.0", "bittorrent-1.1",
                       "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
                       "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0",
                       "cc-by-nc-nd-4.0", "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5",
                       "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0"]  # here list of prohibited licenses
REQUIRED_LICENSE_HEADER = """/*****************************************************************************
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
 *****************************************************************************/""" # here project license header

# end konfiguration


def check_licenses_and_headers():
    # Pfad zur ScanCode-Ergebnisdatei
    scancode_results_path = os.path.join(os.environ.get('SCANCODE_RESULTS_DIR', '.'), 'scancode_results.json')
    # Pfad zur Berichtsausgabedatei
    report_path = os.path.join(os.environ.get('GITHUB_WORKSPACE', '.'), 'license_and_header_check_report.json')

    prohibited_licenses = []
    missing_headers = []

    # Laden der ScanCode-Ergebnisse
    with open(scancode_results_path, 'r') as f:
        scancode_data = json.load(f)
        for file in scancode_data.get('files', []):
            # Überprüfen auf verbotene Lizenzen
            for license in file.get('licenses', []):
                if license.get('key') in PROHIBITED_LICENSES:
                    prohibited_licenses.append({
                        'file': file.get('path'),
                        'license': license.get('key')
                    })
            # Überprüfen auf fehlende Lizenz-Header in .g4-Dateien
            if file.get('type') == 'file' and file.get('path', '').endswith('.g4'):
                if not any(header for header in file.get('headers', []) if 'License' in header.get('value', '')):
                    missing_headers.append(file.get('path'))

    report = {}

    if prohibited_licenses:
        report['files_with_prohibited_licenses'] = {
            "message": "The following files contain prohibited licenses:",
            "details": prohibited_licenses
        }

    if missing_headers:
        report['files_with_missing_headers'] = {
            "message": "The following .g4 files are missing the required license header:",
            "files": missing_headers,
            "instruction": "Please add the following license header to these files:",
            "header": REQUIRED_LICENSE_HEADER
        }

    # Speichern des Berichts als JSON-Datei
    with open(report_path, 'w') as f:
        json.dump(report, f, indent=4)

    # Ausgabe einer Fehlermeldung und Beenden mit Fehlercode, wenn Probleme gefunden wurden
    if prohibited_licenses or missing_headers:
        print(f"Errors found. Details in {report_path}.")
        exit(1)

if __name__ == '__main__':
    check_licenses_and_headers()
