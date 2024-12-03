import os
import json

# Liste der mit der LGPLv3 inkompatiblen Lizenzen
PROHIBITED_LICENSES = {
    "afl-3.0", "apache-2.0", "artistic-1.0", "bittorrent-1.0", "bittorrent-1.1",
    "cc-by-nc-1.0", "cc-by-nc-2.0", "cc-by-nc-2.5", "cc-by-nc-3.0", "cc-by-nc-4.0",
    "cc-by-nc-nd-1.0", "cc-by-nc-nd-2.0", "cc-by-nc-nd-2.5", "cc-by-nc-nd-3.0",
    "cc-by-nc-nd-4.0", "cc-by-nc-sa-1.0", "cc-by-nc-sa-2.0", "cc-by-nc-sa-2.5",
    "cc-by-nc-sa-3.0", "cc-by-nc-sa-4.0"
}

# Erwarteter Header
REQUIRED_HEADER = """[Ihr erwarteter Header-Text hier]"""

def load_scancode_results(scancode_results_dir):
    """
    Lädt alle ScanCode-Ergebnisse aus dem angegebenen Verzeichnis.
    :param scancode_results_dir: Verzeichnis mit den ScanCode-Ergebnisdateien.
    :return: Liste der Scan-Ergebnisse.
    """
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
    """
    for result in scan_results:
        for file in result.get("files", []):
            for license_info in file.get("licenses", []):
                license_id = license_info.get("spdx_license_key", "")
                if license_id in PROHIBITED_LICENSES:
                    print(f"Verbotene Lizenz entdeckt: {license_id} in Datei {file['path']}.")

def check_header(file_path, required_header):
    """
    Überprüft, ob die Datei den erforderlichen Header enthält.
    :param file_path: Pfad zur Datei.
    :param required_header: Erwarteter Header-Text.
    """
    with open(file_path, "r") as f:
        content = f.read()
        if required_header in content:
            print(f"Header gefunden in {file_path}.")
        else:
            print(f"Header nicht gefunden in {file_path}.")

def main():
    scancode_results_dir = os.getenv("SCANCODE_RESULTS_DIR")
    g4_files_list = os.getenv("
    ::contentReference[oaicite:0]{index=0}

