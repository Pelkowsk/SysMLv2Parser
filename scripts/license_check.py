import os
import sys
import subprocess

# Liste der verbotenen Lizenzen
PROHIBITED_LICENSES = {"apache-2.0","original-bsd","openssl","cddl-1.0","epl-1.0","epl-2.0","cpl-1.0","ms-pl","apsl-2.0","artistic-perl-1.0","artistic-perl-2.0","qpl-1.0","mpl-1.1","mpl-2.0","eudatagrid","cecill-2.0","cecill-b","cecill-c"}

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
        subprocess.run(["scancode", "--license", "--json-pp", result_file, g4_file], check=True)

        # Überprüfen der Scan-Ergebnisse
        with open(result_file, "r") as f:
            scan_data = f.read()

        # Überprüfen auf verbotene Lizenzen
        for license_key in PROHIBITED_LICENSES:
            if license_key in scan_data:
                print(f"Verbotene Lizenz {license_key} in {g4_file} gefunden.")
                sys.exit(1)

        # Überprüfen des Lizenzheaders
        with open(g4_file, "r") as f:
            file_header = "".join([f.readline() for _ in range(10)])
            if REQUIRED_LICENSE_HEADER not in file_header:
                print(f"Erforderlicher Lizenzheader in {g4_file} nicht gefunden.")
                sys.exit(1)

    print("Alle .g4-Dateien entsprechen den Lizenzanforderungen.")
    sys.exit(0)

if __name__ == "__main__":
    main()
