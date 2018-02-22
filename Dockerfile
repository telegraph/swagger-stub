FROM anapsix/alpine-java
MAINTAINER toorap

ARG APP_ENV
#Define environment global variables for container
#ENV APP_ENV=${APP_ENV}
ENV APP_ENV=${APP_ENV}

COPY ./target/scala-2.11/cruisestub-assembly-${APP_ENV}-SNAPSHOT.jar /home/
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/cruisestub-assembly-${APP_ENV}-SNAPSHOT.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]
