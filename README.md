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
- Deployment: Render (Docker-Webservice und statische Vue-Site)

## Stand: Milestone 3

- Datenmodell `Document` mit ID, Dateiname und Beschreibung
- `GET /documents` liefert zwei Beispieldokumente als JSON
- Vue-Frontend im Unterordner `frontend`
- Eigene Unterkomponente `DocumentList.vue`, die Beispieldokumente mit `v-for` darstellt
- Suchfunktion nach Dateiname und Beschreibung
- Frontend lädt Dokumente mit `fetch` über `GET /documents` vom Backend
- Ladezustand, Fehleranzeige und erneuter Ladeversuch
- CORS erlaubt die über `FRONTEND_URL` konfigurierte Frontend-Adresse
- Dockerfile für Java 25 und Maven; Port über die Render-Variable `PORT`
- API-Tests für JSON-Ausgabe und erlaubte/verbotene Browser-Ursprünge
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

Voraussetzung: Node.js 22.12+ oder Node.js 24 LTS.

In einem weiteren Terminal vom Hauptordner aus starten:

```sh
cd frontend
npm ci
npm run dev
```

Anschließend die im Terminal angezeigte lokale Adresse im Browser öffnen. Das Backend muss ebenfalls laufen. Ohne weitere Konfiguration ruft das Frontend `http://localhost:8081/documents` auf. Für ein anderes Backend kann im Frontend-Ordner eine `.env.local` mit `VITE_BACKEND_URL=https://<backend>.onrender.com` angelegt werden (Vorlage: `.env.example`). Danach den Frontend-Server neu starten.

Zum Prüfen der TypeScript-Typen und Erstellen des Produktionsbuilds im Ordner `frontend`:

```sh
npm run build
```

## Live-Links für Milestone 3

- Frontend: https://securedocs-dh61.onrender.com
- Backend-API und **Moodle-Abgabelink**: https://securedocs-api-dh61.onrender.com/documents

Beide Dienste wurden auf Render im kostenlosen Tarif eingerichtet. Das Frontend lädt seine Daten über die öffentliche Backend-API.

## Render-Deployment für Milestone 3

Beide Dienste verwenden dieses Repository und den Branch `main`.

| Einstellung | Backend | Frontend |
|---|---|---|
| Diensttyp | Web Service | Static Site |
| Sprache | Docker | – |
| Root Directory | leer (Repository-Hauptordner) | `frontend` |
| Dockerfile Path | `./Dockerfile` | – |
| Build Command | aus Dockerfile | `npm ci && npm run build` |
| Publish Directory | – | `dist` |
| Tarif | Free | Free |
| Health Check Path | `/documents` | – |

Umgebungsvariablen:

- Backend: `FRONTEND_URL` = vollständige HTTPS-Adresse der Frontend-Site, ohne abschließenden Slash. Mehrere erlaubte Origins sind kommasepariert möglich. `PORT` wird von Render gesetzt.
- Frontend: `VITE_BACKEND_URL` = vollständige HTTPS-Adresse des Backend-Dienstes, ohne `/documents`. `NODE_VERSION` = `24`.
- Nach Änderungen an `VITE_BACKEND_URL` das Frontend neu bauen/deployen, da Vite diesen Wert beim Build übernimmt.
- Nach Änderungen am Code beide betroffenen Dienste erneut deployen. Bei Verbindung über eine öffentliche Git-URL erfolgt das manuell über „Manual Deploy“.

Der kostenlose Backend-Dienst kann nach Inaktivität schlafen. Der erste Aufruf dauert dann länger; die Oberfläche zeigt währenddessen einen Ladehinweis.

### Überprüfen und abgeben

1. Backend-Link `https://<backend>.onrender.com/documents` öffnen: JSON-Liste mit zwei Dokumenten muss erscheinen.
2. Frontend-Link öffnen: Beide Dokumente müssen geladen werden; im Browser-Netzwerk-Tab ist der GET-Aufruf an das Render-Backend sichtbar.
3. Suchfeld prüfen, z. B. mit `projekt`.
4. In Moodle für M3 den vollständigen Backend-Link **einschließlich `/documents`** einreichen.

Eine Datenbank, Upload und Anmeldung gehören noch nicht zu diesem Projektstand. Die Dokumente werden im Backend als Beispieldaten erzeugt.

## Tests

Im Hauptordner:

```sh
./mvnw test
```

Die API-Tests starten das echte Backend auf einem zufälligen Port und prüfen HTTP-Status, JSON-Daten sowie CORS für erlaubte und fremde Origins.
