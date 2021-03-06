FROM openjdk:8-jdk-alpine as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./mvnw clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=builder /app/source/target/*.jar /app/app.jar
EXPOSE 5101
CMD ["java", "-jar", "/app/app.jar"]