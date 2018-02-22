FROM anapsix/alpine-java
MAINTAINER toorap

ARG APP_VERSION

ADD ./target/scala-2.11/cruisestub-assembly-${APP_VERSION}.jar /home/app.jar
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/app.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]
