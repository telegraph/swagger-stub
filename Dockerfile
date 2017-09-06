FROM anapsix/alpine-java
MAINTAINER toorap
COPY ./target/scala-2.11/IdentityStub-assembly-0.1.0-SNAPSHOT.jar /home/
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/IdentityStub-assembly-0.1.0-SNAPSHOT.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]