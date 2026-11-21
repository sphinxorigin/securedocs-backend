# SecureDocs Backend

Spring-Boot-Backend des SecureDocs-Dokumentenportals im Modul Webtechnologien (Java 25, Maven).

## Repositories und Live-Links

- Backend-Repository: https://github.com/sphinxorigin/securedocs-backend
- Frontend-Repository: https://github.com/sphinxorigin/securedocs-frontend
- Frontend auf Render: https://securedocs-dh61.onrender.com
- Backend-API und **Moodle-Abgabelink für M3**: https://securedocs-api-dh61.onrender.com/documents

Frontend und Backend werden unabhängig entwickelt und auf Render bereitgestellt. Die Render-Adressen bleiben nach der Repository-Trennung unverändert.

## Stand: Milestone 3

- `Document` mit ID, Dateiname und Beschreibung.
- `GET /documents` liefert zwei Beispieldokumente als JSON.
- Das separate Vue-Frontend lädt die Daten von dieser API.
- Konfigurierbare CORS-Freigabe für die Frontend-Adresse.
- Dockerfile für den Render-Webservice, Port über die Umgebungsvariable `PORT`.
- API-Tests für JSON-Ausgabe und erlaubte/verbotene Browser-Ursprünge.

Noch keine Dateispeicherung, Datenbank, Anmeldung oder Zugriffskontrolle. Für spätere Milestones sind PDF-Uploads, Suche, Freigaben, Downloads, Widerruf und Löschen sowie PostgreSQL vorgesehen.

## Projektstruktur

| Pfad | Inhalt |
|---|---|
| `src/main/java/` | Spring-Boot-Anwendung, Dokumentenmodell, REST-Controller und CORS-Konfiguration |
| `src/main/resources/application.properties` | Port und Umgebungsvariablen |
| `src/test/` | Anwendungs- und API-Tests |
| `pom.xml` | Maven-Konfiguration |
| `Dockerfile` | Mehrstufiger Docker-Build mit Java 25 |

Das Frontend liegt ausschließlich im separaten Frontend-Repository.

## Lokal starten

Voraussetzung: JDK 25. Im Repository-Hauptordner:

```sh
./mvnw spring-boot:run
```

Die API ist standardmäßig unter http://localhost:8081/documents erreichbar. Das Frontend separat aus `securedocs-frontend` starten; dessen lokale Adresse `http://localhost:5173` ist standardmäßig erlaubt.

## Konfiguration

| Variable | Bedeutung | Lokaler Standard |
|---|---|---|
| `PORT` | Port des Backend-Servers | `8081` |
| `FRONTEND_URL` | Erlaubter Browser-Origin; mehrere Werte kommasepariert | `http://localhost:5173` |

Auf Render ist `FRONTEND_URL=https://securedocs-dh61.onrender.com` gesetzt. Die Origin-Adresse enthält keinen abschließenden Slash.

## Render-Deployment

Der bestehende Web Service `securedocs-api-dh61` nutzt dieses Backend-Repository, Branch `main`:

| Einstellung | Wert |
|---|---|
| Sprache | Docker |
| Root Directory | leer (Repository-Hauptordner) |
| Dockerfile Path | `./Dockerfile` |
| Health Check Path | `/documents` |
| Tarif | Free |
| `FRONTEND_URL` | `https://securedocs-dh61.onrender.com` |

`PORT` wird von Render gesetzt. Der Docker-Build führt die Tests aus und erstellt das ausführbare JAR.
Bei Einbindung über die öffentliche Git-URL Änderungen mit „Manual Deploy“ bereitstellen.
Der kostenlose Dienst kann nach Inaktivität schlafen; der erste Aufruf dauert dann länger.

## Tests und Build

```sh
./mvnw test
./mvnw package
```

Die API-Tests starten das Backend auf einem zufälligen Port und prüfen HTTP-Status, JSON-Ausgabe sowie CORS für erlaubte und fremde Origins.

## Historie

Das bisherige Repository `securedocs` wurde in `securedocs-backend` umbenannt. Die bisherige Commit-Historie bleibt erhalten; der ausgegliederte Frontend-Code ist in älteren Commits weiterhin nachvollziehbar. Die Frontend-Historie wurde zusätzlich in das eigene Repository übernommen.
