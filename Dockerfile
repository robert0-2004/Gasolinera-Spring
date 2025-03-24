# Usa una imagen de JDK como base
FROM eclipse-temurin:17-jdk AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo de construcción de Gradle/Maven
COPY ./ ./

# Si usas Maven, ejecuta:
RUN ./mvnw clean package -DskipTests

# Si usas Gradle, ejecuta:
# RUN ./gradlew build -x test

# Segunda etapa: Imagen más liviana con JRE
FROM eclipse-temurin:17-jre

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado en la primera etapa
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto de la aplicación (ajústalo si es diferente)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
