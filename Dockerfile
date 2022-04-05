FROM maven:3.6.3-jdk-8 AS builder
WORKDIR /tmp
COPY pom.xml .
COPY src ./src
RUN mvn clean -e -B package
RUN mv /tmp/target/*.jar /tmp/target/storykeeper.jar                                      

FROM openjdk:8-jdk-slim
WORKDIR /app                              
COPY --from=builder /tmp/target/storykeeper.jar .         
ENTRYPOINT ["java", "-jar", "storykeeper.jar"]



