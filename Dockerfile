FROM openjdk:17-jdk-slim

COPY build/libs/TestMitGradle2.jar app1.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar","app1.jar" ]