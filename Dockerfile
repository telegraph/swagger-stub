FROM anapsix/alpine-java
MAINTAINER toorap

ARG APP_VERSION
#Define environment global variables for container
ENV APP_VERSION=${APP_VERSION}

COPY ./target/scala-2.11/cruisestub-assembly-${APP_VERSION}.jar /home/
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/cruisestub-assembly-${APP_VERSION}.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]
