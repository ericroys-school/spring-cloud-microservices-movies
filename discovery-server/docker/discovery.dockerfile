FROM eclipse-temurin:21-alpine
VOLUME /tmp
LABEL author = "eric"
LABEL project="discovery server"
ADD maven/discovery-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]