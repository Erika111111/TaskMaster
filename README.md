# 📋 TaskManager - Система управления задачами

![Java](https://img.shields.io/badge/Java-21-blue.svg)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.4-brightgreen.svg)

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)

![Docker](https://img.shields.io/badge/Docker-26.1-blue.svg)

![Maven](https://img.shields.io/badge/Maven-3.9.6-red.svg)



## 📖 О проекте

**TaskManager** - это современное REST API приложение для управления задачами, разработанное на Java с использованием Spring Boot. Проект демонстрирует лучшие практики разработки микросервисных приложений: чистая архитектура, работа с базой данных, обработка ошибок, валидация данных и контейнеризация.


### 🎯 Для чего нужен этот проект?

- 📝 Управление задачами (CRUD операции)
- 🏷️ Категоризация задач по статусам и приоритетам
- 🔍 Фильтрация задач по статусу
- 🐳 Легкий запуск через Docker Compose
- 📊 Готов к масштабированию и интеграции

## 🛠️ Технологический стек

| Технология | Назначение |
|------------|------------|
| **Java 21** | Язык программирования |
| **Spring Boot 3** | Фреймворк |
| **Spring Data JPA** | Работа с БД |
| **PostgreSQL 16** | База данных |
| **Liquibase** | Миграции БД |
| **Docker / Docker Compose** | Контейнеризация |
| **Maven Wrapper** | Сборка проекта |
| **GitHub Actions** | CI/CD |
| **Checkstyle** | Статический анализ кода |
| **Lombok** | Уменьшение boilerplate кода |
| **JUnit / Mockito** | Тестирование |

## Возможности

- ✅ CRUD операции с задачами
- ✅ Приоритеты задач (LOW, MEDIUM, HIGH)
- ✅ Валидация входных данных
- ✅ Глобальная обработка ошибок
- ✅ Логирование (INFO / DEBUG уровни)
- ✅ DTO (Data Transfer Object) — отдельно для запросов и ответов
- ✅ Unit тесты
- ✅ Checkstyle — проверка стиля кода
- ✅ CI/CD через GitHub Actions
- ✅ Контейнеризация через Docker

## 📈 Планы по развитию

- Добавить аутентификацию и авторизацию (Spring Security)
- Реализовать пагинацию и сортировку
- Добавить кэширование (Redis)
- Написать интеграционные тесты
- Добавить Swagger/OpenAPI документацию



## API Endpoints

| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/tasks` | Получить все задачи |
| GET | `/tasks/{id}` | Получить задачу по ID |
| GET | `/tasks/status/{status}` | Получить задачи по статусу |
| POST | `/tasks` | Создать новую задачу |
| PUT | `/tasks/{id}` | Обновить задачу |
| PATCH | `/tasks/{id}/status` | Обновить статус задачи |
| DELETE | `/tasks/{id}` | Удалить задачу |

### Модели данных


**TaskRequest (запрос):**
```json

{
  "name": "Buy milk",
  "description": "Go to store"

}
```

**TaskResponse (ответ):**
```json
{

  "name": "Buy milk",
  "description": "Go to store",
  
}
```
**Статусы задач**

- NOT_STARTED — не начата
- IN_PROGRESS — в процессе
- COMPLETED — завершена

**Приоритеты**

- LOW — низкий
- MEDIUM — средний
- HIGH — высокий

**Быстрый старт**

**Требования**

- Java 21
- Docker Desktop

**Запуск через Docker**

#### 1. Клонировать репозиторий
git clone https://github.com/Erika111111/TaskMaster

cd TaskMaster

#### 2. Собрать JAR файл
./mvnw clean package -DskipTests

#### 3. Запустить все сервисы
docker-compose up -d


##### После запуска будут доступны:
| Сервис | URL                                                                  |
|-------------|----------------------------------------------------------------------|
|Приложение   | http://localhost:8080                                                | GET all tasks    |
|pgAdmin    | http://localhost:8081 (логин: admin@email.com / пароль: admin)       |
|PostgreSQL   | http://localhost:5432 (логин: admin / пароль: secret / БД: tasks-db) |


**Локальный запуск (без Docker)**

#### 1. Запустить PostgreSQL
docker-compose up -d postgres

#### 2. Запустить приложение
./mvnw spring-boot:run

**Примеры запросов**

**Создать задачу**


curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{
"name": "Buy milk",
"description": "Go to store",
"priority": "HIGH"
}'
**Получить все задачи**


curl -X GET http://localhost:8080/tasks

**Получить задачу по ID**


curl -X GET http://localhost:8080/tasks/1

**Получить задачи по статусу**


curl -X GET http://localhost:8080/tasks/status/IN_PROGRESS

**Обновить статус задачи**


curl -X PATCH "http://localhost:8080/tasks/1/status?status=COMPLETED"

**Обновить задачу**


curl -X PUT http://localhost:8080/tasks/1 \
-H "Content-Type: application/json" \
-d '{
"name": "Buy milk and bread",
"description": "Go to store",
"status": "COMPLETED",
"priority": "MEDIUM"
}'

**Удалить задачу**


curl -X DELETE http://localhost:8080/tasks/1

**Логирование**

| Уровень | Когда используется | Пример |
|-------------|-------------|-------------|
| INFO    | Вход в контроллеры, основные операции   | GET all tasks    |
|DEBUG    | Детали выполнения, результаты       | Found 5 tasks    |


**Настройка уровней в application.yml**

    logging:
        level:
            root: INFO
                org.springframework.security: INFO
                ru.erikaMit.TaskManager: DEBUG
                org.springframework.web: INFO
                org.springframework.data: INFO

**Тестирование**

#### Запустить все тесты
./mvnw test

#### Запустить только Checkstyle
./mvnw checkstyle:check

**Архитектура проекта**


    TaskMaster/
    ├── .github/workflows/
    │   └── build.yaml                    # GitHub Actions CI/CD
    ├── src/
    │   ├── main/
    │   │   ├── java/ru/erikaMit/TaskManager/
    │   │   │   ├── controller/           # REST контроллеры (логирование)
    │   │   │   ├── service/              # Бизнес-логика
    │   │   │   ├── repository/           # JPA репозитории
    │   │   │   ├── model/                # Entity (Task, enums)
    │   │   │   ├── dto/                  # TaskRequest, TaskResponse, Mapper
    │   │   │   └── exception/            # Глобальный обработчик ошибок
    │   │   └── resources/
    │   │       ├── application.yml       # Конфигурация Spring Boot
    │   │       └── db/
    │   │           └── changelog/        # Liquibase миграции
    │   │               ├── db.changelog-master.yml      # Главный файл миграций
    │   │               ├── v1/
    │   │               │   └── db-changelog-create-task-table.yaml
    │   │               └── v2/
    │   │                   └── db-changelog-alter-task-table-add-column.yaml
    │   └── test/
    │       └── java/                     # Unit тесты
    ├── docker-compose.yml                # Docker Compose конфигурация
    ├── Dockerfile                        # Сборка Docker образа
    ├── checkstyle.xml                    # Checkstyle конфигурация
    ├── liquibase.properties              # Конфигурация Liquibase CLI (опционально)
    ├── pom.xml                           # Maven конфигурация
    └── README.md                         # Документация

**CI/CD (GitHub Actions)**

При каждом push и pull request в ветку main автоматически запускается:

- ✅ Checkstyle — проверка стиля кода
- ✅ Test — запуск unit тестов
- ✅ Package — сборка JAR файла

**Структура БД**

    CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL
);

**🧩 Управление миграциями базы данных (Liquibase)**

Проект использует Liquibase для версионирования схемы PostgreSQL.
Все изменения БД хранятся в виде миграций (changesets) в папке:

src/main/resources/db/changelog/

*📁 Структура миграций*


    db/changelog/
      ├── db.changelog-master.yml          # главный файл (точка входа)
      ├── v1/
      │   └── db-changelog-create-task-table.yaml
      ├── v2/
      │   └── db-changelog-alter-task-table-add-column.yaml

*🏷️ Версионирование через теги*

В миграциях используются теги для маркировки версий БД:

| Тег | Состояние схемы |
|-----|-------------|
| v0  | Пустая база данных   |
| v1  | Создана таблица tasks       |
| v2  | Добавлена колонка created_at       |

*⚙️ Как это работает*

- При запуске приложения (`docker-compose up`) миграции применяются автоматически
- Таблица `DATABASECHANGELOG` хранит историю выполнения
- Hibernate не управляет схемой (`ddl-auto: none`)

*🛠️ Ручное управление миграциями (Liquibase CLI)*
#### Установка
brew install liquibase

Основные команды

#### Статус миграций
liquibase status

#### История выполнения
liquibase history

#### Откат до версии v1
liquibase rollback --tag=v1

#### Применить все новые миграции
liquibase update

**Обработка ошибок**

При ошибках API возвращает единый формат:

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "errorType": "NOT_FOUND",
  "message": "Task not found with id: 1",
  "timestamp": "2026-04-03T10:30:00Z"
}
```b Actions
- ✅ Контейнеризация через Docker

## 📈 Планы по развитию

- Внедрить Liquibase для управления версиями схемы базы данных
- Добавить аутентификацию и авторизацию (Spring Security)
- Реализовать пагинацию и сортировку
- Добавить кэширование (Redis)
- Написать интеграционные тесты
- Добавить Swagger/OpenAPI документацию



## API Endpoints

| Метод | URL | Описание |
|-------|-----|----------|
| GET | `/tasks` | Получить все задачи |
| GET | `/tasks/{id}` | Получить задачу по ID |
| GET | `/tasks/status/{status}` | Получить задачи по статусу |
| POST | `/tasks` | Создать новую задачу |
| PUT | `/tasks/{id}` | Обновить задачу |
| PATCH | `/tasks/{id}/status` | Обновить статус задачи |
| DELETE | `/tasks/{id}` | Удалить задачу |

### Модели данных


**TaskRequest (запрос):**
```json

{
  "name": "Buy milk",
  "description": "Go to store"

}
```

**TaskResponse (ответ):**
```json
{

  "name": "Buy milk",
  "description": "Go to store",
  
}
```
**Статусы задач**

- NOT_STARTED — не начата
- IN_PROGRESS — в процессе
- COMPLETED — завершена

**Приоритеты**

- LOW — низкий
- MEDIUM — средний
- HIGH — высокий

**Быстрый старт**

**Требования**

- Java 21
- Docker Desktop 

**Запуск через Docker**

#### 1. Клонировать репозиторий
git clone https://github.com/Erika111111/TaskMaster

cd TaskMaster

#### 2. Собрать JAR файл
./mvnw clean package -DskipTests

#### 3. Запустить все сервисы
docker-compose up -d


##### После запуска будут доступны:
| Сервис | URL                                                                  |
|-------------|----------------------------------------------------------------------|
|Приложение   | http://localhost:8080                                                | GET all tasks    |
|pgAdmin    | http://localhost:8081 (логин: admin@email.com / пароль: admin)       |
|PostgreSQL   | http://localhost:5432 (логин: admin / пароль: secret / БД: tasks-db) |


**Локальный запуск (без Docker)**

#### 1. Запустить PostgreSQL
docker-compose up -d postgres

#### 2. Запустить приложение
./mvnw spring-boot:run

**Примеры запросов**

**Создать задачу**


curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Buy milk",
    "description": "Go to store",
    "priority": "HIGH"
  }'
**Получить все задачи**


curl -X GET http://localhost:8080/tasks

**Получить задачу по ID**


curl -X GET http://localhost:8080/tasks/1

**Получить задачи по статусу**


curl -X GET http://localhost:8080/tasks/status/IN_PROGRESS

**Обновить статус задачи**


curl -X PATCH "http://localhost:8080/tasks/1/status?status=COMPLETED"

**Обновить задачу**


curl -X PUT http://localhost:8080/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Buy milk and bread",
    "description": "Go to store",
    "status": "COMPLETED",
    "priority": "MEDIUM"
  }'

**Удалить задачу**


curl -X DELETE http://localhost:8080/tasks/1

**Логирование**

| Уровень | Когда используется | Пример |
|-------------|-------------|-------------|
| INFO    | Вход в контроллеры, основные операции   | GET all tasks    |
|DEBUG    | Детали выполнения, результаты       | Found 5 tasks    |


**Настройка уровней в application.yml**

    logging:
        level:
            root: INFO
                org.springframework.security: INFO
                ru.erikaMit.TaskManager: DEBUG
                org.springframework.web: INFO
                org.springframework.data: INFO

**Тестирование**

#### Запустить все тесты
./mvnw test

#### Запустить только Checkstyle
./mvnw checkstyle:check

**Архитектура проекта**

   
    TaskMaster/
    ├── .github/workflows/
    │   └── build.yaml           # GitHub Actions CI/CD
    ├── src/
    │   ├── main/java/ru/erikaMit/TaskManager/
    │   │   ├── controller/       # REST контроллеры (логирование)
    │   │   ├── service/          # Бизнес-логика
    │   │   ├── repository/       # JPA репозитории
    │   │   ├── model/            # Entity (Task, enums)
    │   │   ├── dto/              # TaskRequest, TaskResponse, Mapper
    │   │   └── exception/        # Глобальный обработчик ошибок
    │   └── test/java/            # Unit тесты
    ├── docker-compose.yml        # Docker Compose конфигурация
    ├── Dockerfile                # Сборка Docker образа
    ├── checkstyle.xml            # Checkstyle конфигурация
    ├── pom.xml                   # Maven конфигурация
    └── README.md                 # Документация
    
**CI/CD (GitHub Actions)**

При каждом push и pull request в ветку main автоматически запускается:

- ✅ Checkstyle — проверка стиля кода
- ✅ Test — запуск unit тестов
- ✅ Package — сборка JAR файла

**Структура БД**

    CREATE TABLE tasks (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL
);

**Обработка ошибок**

При ошибках API возвращает единый формат:

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "errorType": "NOT_FOUND",
  "message": "Task not found with id: 1",
  "timestamp": "2026-04-03T10:30:00Z"
}
```