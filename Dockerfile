FROM alpine:3.19.1

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV MAVEN_HOME=/usr/share/maven

RUN apk add --no-cache openjdk21 maven

ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

LABEL maintainer="Seriously Bob"
LABEL version="1.0"
LABEL description="Dockerfile for JDK 21 and Maven"
CMD ["mvn"]