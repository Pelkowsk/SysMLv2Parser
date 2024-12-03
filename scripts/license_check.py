import os
import json

# Konfigurationsparameter
PROHIBITED_LICENSES = ['prohibited_license_1', 'prohibited_license_2']  # Ersetzen Sie diese Platzhalter durch die tatsächlichen Lizenzschlüssel
REQUIRED_LICENSE_HEADER = """[Ihr Lizenz-Header hier]"""  # Ersetzen Sie diesen Platzhalter durch den tatsächlichen Lizenz-Header

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
