FROM anapsix/alpine-java
MAINTAINER toorap
COPY ./target/scala-2.11/IdentityStub-assembly-0.1.0-SNAPSHOT.jar /home/
COPY ./src/main/resources/*.json /home/
CMD ["java","-jar", "/home/IdentityStub-assembly-0.1.0-SNAPSHOT.jar", "/home/openApi.json", "8081", "/home"]