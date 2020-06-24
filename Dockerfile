FROM docker.io/adoptopenjdk/openjdk11:alpine-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]