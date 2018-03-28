# Stub Service

This application creates a Docker image to which you pass your swagger file and swagger mappings.
The Docker container will load those mappings and validate requests/responses against the swagger contract

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

* sbt reload clean assembly
* docker build -f Dockerfile -t swagger-stub --no-cache .
* docker build -f DockerfileYml -t swagger-stub-yml --no-cache .

### Deployment

* docker push 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:swagger-stub
* docker push 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:swagger-stub-yml


### Run (using json swagger)

n.b. Will need AWC ECR access

* Save swagger file and Wiremock mappings as follows:

      /<somewherelocal>/contract/openApi.json   (swagger file, can be .yml)
      /<somewherelocal>/mappings/mappings.json  (Wiremock mappings)

* docker pull 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:swagger-stub
* docker run -p 8080:8081 -v /<somewherelocal>:/mnt 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:swagger-stub