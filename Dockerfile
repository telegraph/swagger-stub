FROM anapsix/alpine-java
MAINTAINER toorap
COPY ./target/scala-2.11/facebookauthStub-assembly-0.1.19.jar /home/
COPY ./src/main/resources/contract/*.json /home/
COPY ./src/main/resources/cannedJson/*.json /home/
CMD ["java","-jar", "/home/facebookauthStub-assembly-0.1.19.jar", "8081", "/home", "/home/openApi.json", "/home/stateModel.json"]