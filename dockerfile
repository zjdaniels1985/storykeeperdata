FROM maven:3.5.2-jdk-8-alpine AS build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
RUN mv /tmp/target/*.jar /tmp/target/storykeeper.jar                                      

FROM openjdk:8-jdk-alpine                              
COPY --from=build /tmp/target/storykeeper.jar .         
ENTRYPOINT ["java", "-jar", "storykeeper.jar"]



