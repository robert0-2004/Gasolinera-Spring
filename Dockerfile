# Usa una imagen de JDK como base
FROM eclipse-temurin:17-jdk AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia el código fuente al contenedor
COPY . .

# Da permisos de ejecución a mvnw
RUN chmod +x mvnw

# Ejecuta el proceso de construcción con Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Segunda etapa: Imagen más liviana con JRE
FROM eclipse-temurin:17-jre

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado en la primera etapa
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
