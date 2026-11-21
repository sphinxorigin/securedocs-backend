# SecureDocs Backend

I'm building SecureDocs for my Web Technologies course at HTW Berlin. The idea is a document portal where users can upload PDFs and share them with other users.

This repo contains the backend, built with Java 25, Spring Boot and Maven. The Vue frontend has its own repository.

## Links

- [Frontend repository](https://github.com/sphinxorigin/securedocs-frontend)
- [Live app](https://securedocs-dh61.onrender.com)
- [Backend API](https://securedocs-api-dh61.onrender.com/documents) — this is the submission link for Milestone 3.

## Current status — Milestone 3

The backend returns two sample documents from `GET /documents`. Each document has an ID, a filename and a description. The frontend fetches this list and displays it.

The backend also has:

- CORS configuration for the frontend URL.
- A Dockerfile for deployment on Render.
- Tests for the JSON response and CORS behaviour.

There is no database, file storage, login or access control yet. I plan to add PostgreSQL, PDF uploads, downloads, sharing, revoking access and deleting documents in later milestones. The documents returned by the API are still sample data.

## Project structure

| Path | Contents |
|---|---|
| `src/main/java/` | Spring Boot app, document model, REST controller and CORS configuration |
| `src/main/resources/application.properties` | Port and environment configuration |
| `src/test/` | Application and API tests |
| `pom.xml` | Maven configuration |
| `Dockerfile` | Multi-stage build using Java 25 |

The frontend code is in `securedocs-frontend`, not in this repo.

## Run locally

You need JDK 25. From the repo root, run:

```sh
./mvnw spring-boot:run
```

The API is available at [http://localhost:8081/documents](http://localhost:8081/documents).

Start the frontend separately from its own repo. The backend allows requests from `http://localhost:5173` by default.

## Configuration

| Variable | Purpose | Local default |
|---|---|---|
| `PORT` | Backend port | `8081` |
| `FRONTEND_URL` | Allowed frontend origin; multiple origins can be separated by commas | `http://localhost:5173` |

On Render, `FRONTEND_URL` is set to `https://securedocs-dh61.onrender.com`. Use the full origin without a trailing slash.

## Render deployment

The backend runs as the free web service `securedocs-api-dh61`. It uses this repo and the `main` branch.

| Setting | Value |
|---|---|
| Runtime | Docker |
| Root directory | Leave empty — repo root |
| Dockerfile path | `./Dockerfile` |
| Health check path | `/documents` |
| Plan | Free |
| `FRONTEND_URL` | `https://securedocs-dh61.onrender.com` |

Render sets `PORT`. The Docker build runs the tests and creates the executable JAR.

The service is connected through the public Git URL, so I use **Manual Deploy** in Render after pushing changes. The free instance can go to sleep when inactive, which makes the first request take longer.

## Tests and build

```sh
./mvnw test
./mvnw package
```

The API tests start the backend on a random port and check the HTTP status, JSON response and CORS headers for allowed and other origins.

## Repository history

This repo was originally called `securedocs` and contained both parts of the app. I renamed it to `securedocs-backend` and moved the frontend into its own repo. The old commits are still here, and the frontend repo also keeps its earlier commit history.
