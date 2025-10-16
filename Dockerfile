# Usamos Java 21 base
FROM eclipse-temurin:21-jdk-alpine

# Creamos directorio para la app
WORKDIR /app

# Copiamos el pom y el código fuente
COPY pom.xml .
COPY src ./src

# Build del JAR usando Maven Wrapper
COPY .mvn/ .mvn/
COPY mvnw .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Copiamos el JAR generado
RUN cp target/GuesMonsterApi-0.0.1-SNAPSHOT.jar

# Puerto que expondrá Render
EXPOSE 10000

# Comando para correr la app
ENTRYPOINT ["sh","-c","java -jar /app/app.jar --server.port=${PORT}"]