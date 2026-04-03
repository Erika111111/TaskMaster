curl -X PATCH http://localhost:8082/tasks/34a11a81-e46b-4c45-90a4-07146576455e \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Обновленное название",
    "description": "Обновленное описание"
  }'