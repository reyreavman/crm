# Shift_Lab Java
#### Упрощенная CRM-система.

Дает возможность управлять информацией о продавцах и их транзакциях. 
Включает в себя возможности создания, чтения, обновления и удаления данных о продавцах и транзакциях. 
Также включает в себя функции аналитики для обработки и анализа данных.

## Содержание
- [Технологии](#технологии)
- [Требования](#требования)
- [Запуск](#запуск)
- [Тестирование](#тестирование)
- [Документация к API. Примеры Запросов](#эндпоинты)

## Технологии
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [Spring Validation](https://docs.spring.io/spring-framework/reference/core/validation/beanvalidation.html)
- [Flyway](https://www.red-gate.com/products/flyway/community/)
- [Lombok](https://projectlombok.org/)
- [Spring Boot Test](https://spring.io/guides/gs/testing-web)
- [AssertJ](https://assertj.github.io/doc/)
- [TestContainers](https://testcontainers.com/)

### Требования
Для установки и запуска проекта необходим Docker.

### Запуск
Для запуска выполните команду:
```sh
$ docker compose up
```
## Тестирование
Для тестирования в проекте были использованы:

- [Spring Boot Test](https://spring.io/guides/gs/testing-web)
- [AssertJ](https://assertj.github.io/doc/)
- [TestContainers](https://testcontainers.com/)

Проект покрыт юнит-тестами. Написаны юнит тесты для слоя контроллеров и сервисов. 
Также написаны интеграционные тесты с помощью TestContainers для тестирования слоя репозиториев.
```sh
./gradlew test
```

## Документация к API. Примеры Запросов
Для генерации документации к API были использованы:

- [Spring Doc](https://spring.io/projects/spring-restdocs)
- [Swagger](https://swagger.io/)

Документация к API доступна по URL: [/api-docs](/api-docs)  
UI для комфортного просмотра доступного API: [/swagger-ui](/swagger-ui)

Реализованный API имеет следующие методы:
- Получение списка продавцов
- Информация о конкретном продавце 
- Создание нового продавца 
- Обновить информации о продавце 
- Удаление продавца
- Получение списка всех транзакций 
- Получение информации о конкретной транзакции
- Создание новой транзакции
- Получение всех транзакций продавца 
- Получение самого продуктивного продавца 
- Получение список продавцов с суммой меньше указанной

Пример Post запроса для создания продавца: