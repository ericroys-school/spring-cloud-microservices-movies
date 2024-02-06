FROM eclipse-temurin:21-jdk

ENV MAVEN_HOME /usr/share/maven
RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn
ARG MAVEN_VERSION=3.9.6
CMD ["mvn"]