FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY Web/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080