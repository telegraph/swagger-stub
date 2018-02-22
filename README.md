# Stub Pipeline

[![Build Status](https://img.shields.io/travis/thephpleague/pipeline/master.svg?style=flat-square)](https://jenkins-preprod.aws-preprod.telegraph.co.uk/job/travel-stubs/)

# Manual Instructions to Build a Stub

update version

sbt reload clean assembly

sbt publish

docker build -t cruisestub .

docker run -p 8080:8081 cruisestub

docker save cruisestub > cruisestub.tar (if you want to save as a tar)

docker tag cruisestub  docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub

docker push docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub (to push to docker registry)

docker pull docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub (to pull)

## Example
In order to verify your stub, perform a GET using the following details:

URL: for cruise - http://localhost:8080/travel-products/cruises/pvjqdBFp0sD or
for cruise listing - http://localhost:8080/travel-products/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4
Header: Content-Type:application/json

## Errors
