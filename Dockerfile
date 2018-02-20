FROM anapsix/alpine-java
MAINTAINER toorap
COPY ./target/scala-2.11/cruisestub-assembly-0.0.8.jar /home/
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/cruisestub-assembly-0.0.8.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]
RUN usermod -a -G docker jenkins
USER jenkins
