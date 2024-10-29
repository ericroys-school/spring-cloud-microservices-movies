FROM eclipse-temurin:21-alpine
VOLUME /tmp
LABEL author = "eric"
LABEL project=${project.build.finalName}
ADD maven/${project.build.finalName}.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]