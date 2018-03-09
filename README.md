# Stub Pipeline

## Getting started

### Prerequisites
* Access to Amazon S3 (You will need S3 keys to access the maven artifacts)
* Intellij with scala support
* Access to Telegraph Github

### Installation

Install sbt using the link `http://www.scala-sbt.org/release/docs/Installing-sbt-on-Mac.html`

Install docker from the URL `https://docs.docker.com/docker-for-mac/install/`

Install awscli (You can install this using `brew install awscli` on Mac)

### Build instructions 


### Deployment

docker build -t swagger-stub --build-arg PORT=8081 --build-arg SWAGGER_FILE=/Users/toorap/identityStub/identity-stub/src/main/resources/contract/openApi.json --build-arg MAPPINGS_LOCATION=/Users/toorap/identityStub/identity-stub/src/main/resources/mappings/ --no-cache .


### Testing


### Test the stub by hitting the environment


## Errors
