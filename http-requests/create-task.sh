TIMESTAMP=$(date +"%Y-%m-%d_%H-%M-%S")

curl -X POST http://localhost:8082/tasks \
  -H "Content-Type: application/json" \
  -d "{
    \"name\": \"Новая задача $TIMESTAMP\",
    \"description\": \"Описание задачи от $TIMESTAMP\",
    \"priority\": \"HIGH\"
  }"
