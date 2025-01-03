# Менеджер Личных Финансов
## Описание
Система управления личными финансами на базе Java, которая помогает пользователям отслеживать доходы, расходы и бюджеты.

## Основные возможности
- Аутентификация пользователя
  - Регистрация новых аккаунтов
  - Безопасная система входа
  - Управление финансами

- Отслеживание доходов и расходов
  - Категоризация транзакций
  - Установка и контроль бюджетов по категориям
  - Просмотр текущего баланса
  - Статистика

- Просмотр доходов по категориям
  - Отслеживание расходов по категориям
  - Контроль лимитов бюджета
  - Уведомления о превышении бюджета

## Технические характеристики
- Разработано на Java 11
- Используется Maven для управления зависимостями
- JUnit 5 для тестирования
- GSON для сохранения данных в формате JSON
- Модульная архитектура с отдельными компонентами для:
  - Управления пользователями
  - Операций с кошельком
  - Обработки транзакций
  - Финансовой статистики
- Данные пользователей автоматически сохраняются в файлы JSON в каталоге userdata и загружаются оттуда при необходимости.

## Сборка и запуск
- Сборка и запуск
```bash
mvn clean install
mvn compile exec:java -DskipTests -Dexec.mainClass=Main
```
- Запуск тестов
```bash
mvn test
```

## Использование
```
Главное меню:
1. Login
2. Register
3. Exit

После успешного логина:
1. Add Income
2. Add Expense
3. View Balance
4. Set Budget
5. View Statistics
6. Logout
```
