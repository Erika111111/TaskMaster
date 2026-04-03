# TaskMaster

TaskMaster — это REST API приложение для управления задачами с полным CI/CD пайплайном, контейнеризацией и профессиональным логированием.

## Технологии

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