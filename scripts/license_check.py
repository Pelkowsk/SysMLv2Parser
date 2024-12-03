import os
import json
import sys

# Liste der mit der LGPLv3 inkompatiblen Lizenzen
PROHIBITED_LICENSES = {
    "afl-3.0", "apache-2.0", "artistic-1.0", "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0",
    "cc-by-nc-nd-4.0", "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5",
    "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0"
}

# Erwarteter Header (als String)
REQUIRED_HEADER = """[Ihr erwarteter Header-Text hier]"""

scancode_results_dir = os.getenv('SCANCODE_RESULTS_DIR')
if not os.path.exists(scancode_results_dir):
    print(f"Fehler: Verzeichnis '{scancode_results_dir}' wurde nicht gefunden.")
    sys.exit(1)

if not os.listdir(scancode_results_dir):
    print(f"Warnung: Verzeichnis '{scancode_results_dir}' ist leer. Keine Scan-Ergebnisse vorhanden.")
    sys.exit(0)

def load_scancode_results(scancode_results_dir):
    """
    Lädt alle ScanCode-Ergebnisse aus dem angegebenen Verzeichnis.
    :param scancode_results_dir: Verzeichnis mit den ScanCode-Ergebnisdateien.
    :return: Liste der Scan-Ergebnisse.
    """
    if not os.path.exists(scancode_results_dir):
        print(f"Fehler: Verzeichnis '{scancode_results_dir}' wurde nicht gefunden.")
        sys.exit(1)

    results = []
    for filename in os.listdir(scancode_results_dir):
        if filename.endswith(".json"):
            with open(os.path.join(scancode_results_dir, filename), "r") as f:
                results.append(json.load(f))
    return results

def check_licenses(scan_results):
    """
    Überprüft die Scan-Ergebnisse auf verbotene Lizenzen.
    :param scan_results: Liste der Scan-Ergebnisse.
    :return: Liste der Dateien mit verbotenen Lizenzen.
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
    Überprüft, ob die Datei den erforderlichen Header enthält.
    :param file_path: Pfad zur Datei.
    :param required_header: Erwarteter Header-Text.
    :return: True, wenn der Header gefunden wurde, sonst False.
    """
    with open(file_path, "r") as f:
        content = f.read()
        return required_header in content

def main():
    scancode_results_dir = os.getenv("SCANCODE_RESULTS_DIR")
    g4_files_list_path = os.getenv("G4_FILES_LIST")

    if not scancode_results_dir or not g4_files_list_path:
        print("Fehler: Umgebungsvariablen 'SCANCODE_RESULTS_DIR' oder 'G4_FILES_LIST' nicht gesetzt.")
        sys.exit(1)

    print(f"Debug: SCANCODE_RESULTS_DIR={scancode_results_dir}")
    print(f"Debug: G4_FILES_LIST={g4_files_list_path}")

    # Lade ScanCode-Ergebnisse
    scan_results = load_scancode_results(scancode_results_dir)

    # Überprüfe auf verbotene Lizenzen
    prohibited_files = check_licenses(scan_results)

    # Überprüfe Header in .g4-Dateien
    header_missing_files = []
    with open(g4_files_list_path, "r") as f:
        for line in f:
            g4_file_path = line.strip()
            if not check_header(g4_file_path, REQUIRED_HEADER):
                header_missing_files.append(g4_file_path)

    # Ergebnisbericht erstellen
    report = {
        "prohibited_licenses": prohibited_files,
        "missing_headers": header_missing_files,
        "status": "success" if not prohibited_files and not header_missing_files else "failure"
    }

    # Ergebnisbericht speichern
    with open("license_and_header_check_report.json", "w") as f:
        json.dump(report, f, indent=2)

    # Pipeline-Status basierend auf den Ergebnissen setzen
    if report["status"] == "failure":
        print("Fehler: Verbotene Lizenzen oder fehlende Header gefunden. Details siehe 'license_and_header_check_report.json'.")
        sys.exit(1)
    else:
        print("Erfolg: Keine verbotenen Lizenzen und alle erforderlichen Header gefunden.")

if __name__ == "__main__":
    main()
