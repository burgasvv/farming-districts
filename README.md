# Приложение районы-фермеры

## Версии
* JDK 23
* SpringBoot 3.3.5

## Запуск приложения
* (В проекте) Синхронизировать Maven проекты
* Подключить и настроить базу данных Postgres (В корне находится файл docker-compose.yml), либо иным способом.
* Запустить все приложения через Services
* Для проверки запросов нужно перейти по ссылке http://localhost:8765/swagger-ui.html, либо файл request-test.http в корне