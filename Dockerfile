FROM anapsix/alpine-java
MAINTAINER toorap

ARG PORT
ARG SWAGGER_FILE
ARG MAPPINGS_LOCATION

RUN echo "Using port:${PORT} Swagger file: ${SWAGGER_FILE} Mappings location under:${MAPPINGS_LOCATION}"
RUN mkdir /home/mappings
COPY ./target/scala-2.11/swagger-stub-assembly-0.0.1.jar /home/
COPY ${SWAGGER_FILE} /home/openApi.json
COPY ${MAPPINGS_LOCATION}/*.json /home/mappings
CMD ["java","-jar", "/home/IdentityStub-assembly-0.1.0-SNAPSHOT.jar", ${PORT}, "/home/openApi.json", "/home/mappings"]