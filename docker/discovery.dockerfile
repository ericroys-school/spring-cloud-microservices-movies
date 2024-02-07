FROM eclipse-temurin:21-alpine
VOLUME /tmp
LABEL author = "eric"
ADD discovery-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]