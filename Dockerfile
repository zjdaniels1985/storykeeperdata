FROM maven:3.8.5-eclipse-temurin-17-alpine AS builder
WORKDIR /tmp
COPY pom.xml .
COPY src ./src
RUN mvn clean -e -B package
RUN mv /tmp/target/*.jar /tmp/target/storykeeper.jar                                      

FROM openjdk:17
WORKDIR /app                              
COPY --from=builder /tmp/target/storykeeper.jar .         
ENTRYPOINT ["java", "-jar", "storykeeper.jar"]



