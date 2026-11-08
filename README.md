# SecureDocs

SecureDocs ist ein geplantes Dokumentenportal, mit dem registrierte Nutzer PDF-Dokumente hochladen, verwalten und gezielt anderen Nutzern zum Download freigeben können.

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
- Frontend: Vue 3, TypeScript und Vite
- Geplante Datenbank: PostgreSQL
- Geplantes Deployment: Render

## Stand: Milestone 2

- Datenmodell `Document` mit ID, Dateiname und Beschreibung
- `GET /documents` liefert zwei Beispieldokumente als JSON
- Vue-Frontend im Unterordner `frontend`
- Eigene Unterkomponente `DocumentList.vue`, die Beispieldokumente mit `v-for` darstellt
- Suchfunktion nach Dateiname und Beschreibung
- Frontend verwendet derzeit lokale Beispieldaten, noch keine Backend-Anbindung
- Noch keine Dateispeicherung, Anmeldung oder Zugriffskontrolle

## Projektstruktur

| Pfad | Inhalt |
|---|---|
| `src/` | Spring-Boot-Backend |
| `pom.xml` | Maven-Konfiguration |
| `frontend/` | Vue-Frontend |

## Lokal starten

### Backend

Voraussetzung: JDK 25.

Im Hauptordner auf Port 8081 starten:

```sh
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

Anschließend im Browser öffnen:

http://localhost:8081/documents

### Frontend

Voraussetzung: Node.js 24 LTS.

In einem weiteren Terminal vom Hauptordner aus starten:

```sh
cd frontend
npm ci
npm run dev
```

Anschließend die im Terminal angezeigte lokale Adresse im Browser öffnen. Das Frontend kann für Milestone 2 unabhängig vom Backend gestartet werden.

Zum Prüfen der TypeScript-Typen und Erstellen des Produktionsbuilds im Ordner `frontend`:

```sh
npm run build
```