# Middle Service приложения Мини-банк

[![Java Gradle Build & Test](https://github.com/gpb-it-factory/gladskoy-middle-service/actions/workflows/gradle-ci.yml/badge.svg)](https://github.com/gpb-it-factory/gladskoy-middle-service/actions/workflows/gradle-ci.yml)

Middle Service - это один из компонентов "Мини-банка", приложения разрабатываемого в рамках программы [GPB IT Factory Backend 2024](https://gpb.fut.ru/itfactory/backend).
Сервис принимает запросы от Telegram-бота, выполняет валидацию и бизнес-логику, а так же маршрутизирует запросы в Backend Service.


## Содержание

1. [Архитектура системы](#архитектура-системы)
2. [Интеграции](#интеграции)


### Архитектура системы

![](src/main/resources/project/architecture.png)

<details>

```plantuml
@startuml architecture
skinparam sequenceMessageAlign center
skinparam ParticipantPadding 20

participant TelegramBot
participant MiddleService
participant BackendService

TelegramBot -> MiddleService: HTTP request
activate MiddleService

MiddleService --> MiddleService: Validation
TelegramBot <-- MiddleService: HTTP error response, if not valid

MiddleService --> MiddleService: Business logic
activate MiddleService

MiddleService -> BackendService: HTTP request
activate BackendService

BackendService --> MiddleService: HTTP response
deactivate BackendService
deactivate MiddleService

MiddleService --> TelegramBot: HTTP response
deactivate MiddleService

@enduml
```
</details>


### Интеграции

- [Telegram Bot](https://github.com/gpb-it-factory/gladskoy-telegram-bot)
- [Backend Service]() // TBD
