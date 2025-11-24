# --- Этап сборки ---
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Копируем gradle wrapper и скрипты
COPY gradlew ./
COPY gradle ./gradle

# Делаем gradlew исполняемым
RUN chmod +x gradlew

# Копируем остальные файлы проекта
COPY . .

# Собираем jar (bootJar для Spring Boot)
RUN ./gradlew clean bootJar --no-daemon

# --- Этап рантайма ---
FROM eclipse-temurin:17-jre-alpine
LABEL authors="Loogika"
WORKDIR /app

# Копируем собранный jar из предыдущего этапа
COPY --from=build /app/build/libs/*.jar app.jar

# Порт, на котором работает Spring Boot (по умолчанию 8080)
EXPOSE 8080

# Старт приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
