# Stub Pipeline

[![Build Status](https://img.shields.io/travis/thephpleague/pipeline/master.svg?style=flat-square)](https://jenkins-preprod.aws-preprod.telegraph.co.uk/job/travel-stubs/)

# Manual Instructions to Build a Stub

Note: You need to have awscli,docker and stb installed in your local machine
```
sbt reload clean assembly

sbt publish

docker build -t hotel-stub .

docker run -p 8080:8081 hotel-stub

docker save hotel-stub > hotel-stub.tar (if you want to save as a tar)

docker tag hotel-stub  docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub

docker push docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub (to push to docker registry)

docker pull docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub (to pull)

## Example
In order to verify your stub, perform a GET using the following details:

URL: for hotelstub - http://localhost:8080/travel-products/hotel-stub/kfxl6SzzYPB or kfxl6S6C32l or mkbj9GHrC76 or mbY85y0JBL2 or k3kWmhGH9Sm
Header: Content-Type:application/json

## Errors
## Test the stub by hitting the environment
```
eg: http://aem-docker-${environment}.aws-preprod.telegraph.co.uk:$port/$dir/${flakeid}
$dir - could be anything you define in your stub jar, in above hotels example it is /travel-products/hotels
```
environments: qa15,ci6,ci7,ci10 
ports:

| Stub Name | Port Number |
| --- | --- |
| cruisestub    | 8081 |
| hotelstub     | 8082 |
| holidaystub   | 8083 |
| identitystub  | 8084 |
| productlistingstub | 8085 |
| suggestionapistub | 8086 |

Note: We have all the above stubs in qa15 environment and ci6,ci7 & ci10 only have cruisestub,hotelstub,holidaystub

## Errors
