FROM eclipse-temurin:21-jdk AS BUILD

WORKDIR /app

COPY .mvn .mvn/

COPY mvnw mvnw.cmd ./

RUN chmod +x mvnw

COPY pom.xml .

RUN --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/spring-boot-crud.jar"]