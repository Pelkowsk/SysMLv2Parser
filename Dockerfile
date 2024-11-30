FROM openjdk:17-jdk-slim

COPY build/libs/Test2-1.0-SNAPSHOT.jar app1.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar","app1.jar" ]