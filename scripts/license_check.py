import os
import subprocess
import json

# Liste der mit der LGPLv3 inkompatiblen Lizenzen
PROHIBITED_LICENSES = {
    "afl-3.0", "apache-2.0", "artistic-1.0", "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0",
    "cc-by-nc-nd-4.0", "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5",
    "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0"
}

# Erwarteter Header, der in den .g4-Dateien vorhanden sein soll
EXPECTED_HEADER = """
/*****************************************************************************
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
 *****************************************************************************/
"""

# Debugging-Datei
DEBUG_FILE = "debug_log.txt"

def write_debug_log(message):
    """
    Schreibt eine Debug-Meldung in die Datei.
    :param message: Die zu schreibende Meldung.
    """
    with open(DEBUG_FILE, "a") as log_file:
        log_file.write(message + "\n")

def find_g4_files(directory):
    """
    Sucht rekursiv nach .g4-Dateien im angegebenen Verzeichnis.
    :param directory: Verzeichnis, in dem nach .g4-Dateien gesucht wird.
    :return: Liste der gefundenen .g4-Dateien mit absoluten Pfaden.
    """
    g4_files = []
    for root, _, files in os.walk(directory):
        for file in files:
            if file.endswith(".g4"):
                g4_files.append(os.path.join(root, file))
    return g4_files

def run_license_scan(files):
    """
    Führt einen Lizenzscan mit scancode-toolkit auf den angegebenen Dateien durch.
    :param files: Liste der zu scannenden Dateien.
    :return: Ergebnisse des Scans als Dictionary.
    """
    try:
        write_debug_log(f"Starte Lizenzscan für {len(files)} .g4-Dateien.")
        result = subprocess.run(
            ["scancode-toolkit/scancode"] + files + ["--json", "result.json"],
            capture_output=True,
            text=True
        )

        write_debug_log(f"Scan-Ausgabe: {result.stdout}")
        if result.returncode != 0:
            write_debug_log(f"Scan fehlgeschlagen: {result.stderr}")
            return None

        with open("result.json", "r") as result_file:
            data = json.load(result_file)
        write_debug_log("Scan abgeschlossen. Ergebnisse in 'result.json' gespeichert.")
        return data
    except Exception as e:
        write_debug_log(f"Fehler beim Lizenzscan: {str(e)}")
        return None
# eingefügt 1*
# Pfad zur 'result.json'-Datei
result_file_path = "result.json"

# Überprüfen, ob die Datei existiert
if os.path.exists(result_file_path):
    # Pfad zur 'GITHUB_OUTPUT'-Datei aus den Umgebungsvariablen abrufen
    github_output = os.getenv('GITHUB_OUTPUT')
    if github_output:
        # Ausgabeparameter 'scancode_result' mit dem Pfad zur 'result.json'-Datei festlegen
        with open(github_output, 'a') as output_file:
            print(f"scancode_result={result_file_path}", file=output_file)
    else:
        print("Fehler: 'GITHUB_OUTPUT' Umgebungsvariable ist nicht gesetzt.")
else:
    print(f"Fehler: Datei '{result_file_path}' wurde nicht gefunden.")
    # Nach dem Ausführen des ScanCode-Scans und dem Speichern in 'result.json'

if os.path.exists("result.json"):
    write_debug_log("Die Datei 'result.json' wurde erfolgreich erstellt.")
else:
    write_debug_log("Fehler: Die Datei 'result.json' wurde nicht gefunden.")

# eingefügt 1*


def check_licenses(scan_results):
    """
    Überprüft die Scan-Ergebnisse auf verbotene Lizenzen.
    :param scan_results: Ergebnisse des Scans als Dictionary.
    """
    if not scan_results:
        write_debug_log("Keine Scan-Ergebnisse vorhanden.")
        return

    write_debug_log("Starte Lizenzüberprüfung.")
    for file in scan_results.get("files", []):
        for license_info in file.get("licenses", []):
            license_id = license_info.get("spdx_license_key", "")
            if license_id in PROHIBITED_LICENSES:
                message = f"Verbotene Lizenz entdeckt: {license_id} in Datei {file['path']}."
                write_debug_log(message)
                print(message)
    write_debug_log("Lizenzüberprüfung abgeschlossen.")

def check_header_in_g4_files(files):
    """
    Überprüft, ob die angegebenen .g4-Dateien den erwarteten Header enthalten.
    :param files: Liste der zu überprüfenden .g4-Dateien.
    """
    write_debug_log(f"Überprüfe Header in {len(files)} .g4-Dateien.")
    for file_path in files:
        try:
            with open(file_path, 'r') as f:
                content = f.read()
                if EXPECTED_HEADER in content:
                    message = f"Header gefunden in {file_path}."
                else:
                    message = f"Header nicht gefunden in {file_path}."
                write_debug_log(message)
                print(message)
        except Exception as e:
            write_debug_log(f"Fehler beim Lesen der Datei {file_path}: {str(e)}")

def main():
    # Aktuelles Arbeitsverzeichnis als Ausgangspunkt
    scan_path = os.getcwd()
    write_debug_log(f"Programm gestartet im Verzeichnis: {scan_path}")

    # Finde alle .g4-Dateien im Repository
    g4_files = find_g4_files(scan_path)
    write_debug_log(f"Gefundene .g4-Dateien: {g4_files}")

    if not g4_files:
        write_debug_log("Keine .g4-Dateien gefunden.")
        print("Keine .g4-Dateien gefunden.")
        return

    # Führe Lizenzscan auf den gefundenen .g4-Dateien durch
    scan_results = run_license_scan(g4_files)
    check_licenses(scan_results)

    # Überprüfe Header in den .g4-Dateien
    check_header_in_g4_files(g4_files)

    write_debug_log("Programm abgeschlossen.")

if __name__ == "__main__":
    # Debug-Log initialisieren
    with open(DEBUG_FILE, "w") as log_file:
        log_file.write("Debug-Log gestartet\n")
    main()

# Nach dem ersten Aufruf von write_debug_log
if os.path.exists("debug_log.txt"):
    write_debug_log("Die Datei 'debug_log.txt' wurde erfolgreich erstellt.")
else:
    write_debug_log("Fehler: Die Datei 'debug_log.txt' wurde nicht gefunden.")
