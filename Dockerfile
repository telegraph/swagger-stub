FROM anapsix/alpine-java
MAINTAINER toorap

COPY ./target/scala-2.11/swagger-stub-assembly-0.0.1.jar /home/

CMD ["java","-jar","/home/swagger-stub-assembly-0.0.1.jar","8081","/mnt/contract/openApi.json","/mnt"]