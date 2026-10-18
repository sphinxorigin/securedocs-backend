# SecureDocs

SecureDocs ist ein geplantes Dokumentenportal, mit dem registrierte
Nutzer PDF-Dokumente hochladen, verwalten und gezielt anderen
Nutzern zum Download freigeben können.

## Geplante Funktionen

- Registrierung und Anmeldung
- PDF-Dokumente hochladen
- Eigene Dokumente suchen und anzeigen
- Dokumente für andere Nutzer freigeben
- Freigegebene Dokumente herunterladen
- Freigaben widerrufen
- Eigene Dokumente löschen

## Technologien

- Backend: Java 25 und Spring Boot
- Geplantes Frontend: Vue.js
- Geplante Datenbank: PostgreSQL
- Geplantes Deployment: Render

## Stand: Milestone 1

- Datenmodell Document mit ID, Dateiname und Beschreibung
- GET /documents liefert zwei Beispieldokumente als JSON
- Noch keine Dateispeicherung, Anmeldung oder Zugriffskontrolle

## Lokal starten

Voraussetzung: JDK 25

Start auf Port 8081:

./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"

Anschließend im Browser öffnen:

http://localhost:8081/documents