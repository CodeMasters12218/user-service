# ============================
#   Etapa 1: Build con Maven
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos todo el proyecto (dentro del build context)
COPY . .

# Ahora, dentro del contenedor, la estructura será:
# /app/
#   ├── common-dto/
#   └── user-service/

# Instalamos common-dto
RUN cd common-dto && mvn clean install -DskipTests

# Construimos user-service
RUN cd user-service && mvn clean package -DskipTests

# ============================
#   Etapa 2: Imagen final
# ============================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el JAR generado de user-service
COPY --from=build /app/user-service/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
