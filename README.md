# Auto
Crud su entity Auto


Per il database usare i seguenti comandi:
docker pull postgres
docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=postgres -p 5432:5432 -d postgres


Per Swagger:
collegarsi su localhost:8080/swagger-ui.html e copiare e importa il link dato su postman

Per kafka Docker:
docker-compose -f docker-compose.yml up -d
