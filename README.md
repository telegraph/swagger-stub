# Stub Service

This application runs a Docker image whihc you pass your swagger file and swagger mappings for.
It will then load those mappings and validate the swagger contract

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
* docker build -t swagger-stub --no-cache .

### Deployment

Place swagger contract under /somewherelocal/contract/ and wiremock mappings directory under /somewherelocal

Run as follows:

* docker run -p 8080:8081 -v /somewherelocal:/mnt swagger-stub:latest

### Testing


### Test the stub by hitting the environment


## Errors
