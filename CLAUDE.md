# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

OCI DevOps full-stack application — a task/todo management system with AI integration, team management, and DevOps monitoring. Designed for deployment on Oracle Cloud Infrastructure (OCI) using Kubernetes and Terraform.

## Build Commands

All build activity happens from `MtdrSpring/backend/`:

```bash
# Build backend + frontend together (single command builds Java JAR and React app)
cd MtdrSpring/backend
mvn clean package spring-boot:repackage

# Run tests only
mvn test

# Run a single test class
mvn test -Dtest=ClassName

# Build Docker image and push to OCI Container Registry
./build.sh   # requires DOCKER_REGISTRY env var

# Deploy to Oracle Kubernetes Engine
./deploy.sh  # requires kubeconfig and OCI env vars

# Set up environment aliases and PATH
source MtdrSpring/env.sh
```

The frontend React app is built automatically during `mvn package` via `frontend-maven-plugin` — no separate frontend build step is needed.

## Architecture

### Stack
- **Backend**: Java 11, Spring Boot 3.5.6, Spring Data JPA, Oracle DB (OJDBC11 + UCP)
- **Frontend**: React 17, TypeScript 4.6, MUI 5, bundled by Maven into the JAR
- **AI**: DeepSeek LLM API for analysis features
- **Bot**: Telegram Bot API (9.1.0) for task management via chat
- **Infra**: Terraform → OCI (VCN, OKE, Oracle Autonomous DB, OCIR, API Gateway)
- **CI/CD**: Oracle DevOps Build Service (`build_spec.yaml`)

### Backend Package Layout (`src/main/java/com/springboot/MyTodoList/`)

Standard MVC layering:

| Package | Contents |
|---|---|
| `config/` | BotProps, DeepSeekConfig, CorsConfig, DbSettings, OracleConfiguration |
| `controller/` | 2 REST controllers: `ToDoItemBotController`, `UserController` |
| `model/` | 21 JPA entities (Task, Sprint, Project, AppUser, Deployment, Incident, KPI, Team, etc.) |
| `repository/` | 18 Spring Data JPA repositories |
| `service/` | 19 service classes containing core business logic |
| `util/` | Telegram bot integration: `MyTodoListBot.java`, `BotActions.java` |

Entry point: `MyTodoListApplication.java`

### Frontend (`src/main/frontend/`)

Minimal React app with four main files: `App.js`, `API.js`, `NewItem.js`, `index.js`. `API.js` is the backend HTTP client layer.

### Database

Oracle Autonomous Database accessed via a connection wallet (`backend/wallet/`). The wallet and TNS configuration are required for any local run that touches the database.

### Telegram Bot Flow

The bot is wired directly into the Spring Boot application. `MyTodoListBot` (util) handles Telegram updates and delegates to `BotActions`. `ToDoItemBotController` exposes the webhook endpoint.

### Infrastructure (`MtdrSpring/terraform/`)

Each `.tf` file maps to one OCI resource group: `core.tf` (VCN/networking), `containerengine.tf` (OKE), `database.tf`, `apigateway.tf`, `object_storage.tf`, `repositories.tf`. State is persisted across shell scripts via `utils/state-functions.sh`.

## Key Configuration Files

| File | Purpose |
|---|---|
| `MtdrSpring/backend/src/main/resources/application.properties` | DB pool, logging, Telegram token, DeepSeek API key |
| `MtdrSpring/backend/pom.xml` | Full Maven build including Node/npm versions |
| `build_spec.yaml` | Oracle DevOps CI/CD pipeline (GraalVM Java 22) |
| `MtdrSpring/env.sh` | Local environment variables and kubectl/Docker aliases |

## Local Development Notes

- Secrets (Telegram token, DeepSeek key, DB wallet) are required to run the app locally — these are not committed.
- Deployment scripts (`build.sh`, `deploy.sh`, all `utils/*.sh`) are bash scripts written for Linux/macOS; on Windows use WSL or Git Bash.
- The CI/CD pipeline uses GraalVM Java 22; local builds can use any JDK 11+ compatible with Spring Boot 3.
