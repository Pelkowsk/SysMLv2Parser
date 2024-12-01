import os
import sys
import subprocess
import json

# Liste der verbotenen Lizenzen
PROHIBITED_LICENSES = {"gpl-3.0", "gpl-3.0+", "gpl-3.0-only", "gpl-3.0-or-later"}

# Erwarteter Lizenzheader
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
 *****************************************************************************/"""

def main():
    # Suche nach .g4-Dateien
    g4_files = []
    for root, _, files in os.walk("."):
        for file in files:
            if file.endswith(".g4"):
                g4_files.append(os.path.join(root, file))

    if not g4_files:
        print("Keine .g4-Dateien gefunden.")
        sys.exit(1)

    for g4_file in g4_files:
        # Ausführen des ScanCode-Tools
        result_file = f"{g4_file}_license_scan.json"
        try:
            subprocess.run(
                ["scancode", "--license", "--license-text", "--json-pp", result_file, g4_file],
                check=True
            )
        except subprocess.CalledProcessError as e:
            print(f"Fehler beim Ausführen von ScanCode für {g4_file}: {e}")
            sys.exit(1)

        # Laden der Scan-Ergebnisse
        try:
            with open(result_file, "r", encoding="utf-8") as f:
                scan_data = json.load(f)
        except Exception as e:
            print(f"Fehler beim Laden der Scan-Ergebnisse aus {result_file}: {e}")
            sys.exit(1)

        # Speichern der Scan-Ergebnisse als Textdatei für Debugging-Zwecke
        debug_text_file = f"{g4_file}_license_scan.txt"
        try:
            with open(debug_text_file, "w", encoding="utf-8") as f:
                json.dump(scan_data, f, ensure_ascii=False, indent=4)
        except Exception as e:
            print(f"Fehler beim Speichern der Debug-Textdatei {debug_text_file}: {e}")
            sys.exit(1)

        # Überprüfen auf verbotene Lizenzen
        for file_data in scan_data.get("files", []):
            for license_detection in file_data.get("license_detections", []):
                license_expression = license_detection.get("license_expression", "")
                if any(prohibited in license_expression for prohibited in PROHIBITED_LICENSES):
                    print(f"Verbotene Lizenz in {g4_file} gefunden: {license_expression}")
                    sys.exit(1)

        # Überprüfen des Lizenzheaders
        try:
            with open(g4_file, "r", encoding="utf-8") as f:
                file_content = f.read()
                if REQUIRED_LICENSE_HEADER not in file_content:
                    print(f"Erforderlicher Lizenzheader in {g4_file} nicht gefunden.")
                    sys.exit(1)
        except Exception as e:
            print(f"Fehler beim Lesen der Datei {g4_file}: {e}")
            sys.exit(1)

    print("Alle .g4-Dateien entsprechen den Lizenzanforderungen.")
    sys.exit(0)

if __name__ == "__main__":
    main()
