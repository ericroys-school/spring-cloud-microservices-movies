FROM alpine:3.19.1

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk
ENV MAVEN_HOME=/usr/share/maven
ADD settings.xml ${HOME}

RUN apk add --no-cache openjdk21 maven && mkdir -p /opt/.m2/repository

ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

LABEL maintainer="Seriously Bob"
LABEL version="1.0"
LABEL description="Dockerfile for JDK 21 and Maven"
ARG '-v /opt/.m2:/opt/.m2'
CMD ["mvn"]