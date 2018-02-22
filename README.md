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

```
URL: for cruise - http://localhost:8080/travel-products/cruises/pvjqdBFp0sD or
for cruise listing - http://localhost:8080/travel-products/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4
Header: Content-Type:application/json
```

## Test the stub by hitting the environment
```
eg: http://aem-docker-qa15.aws-preprod.telegraph.co.uk:8083/travel-products/holidays/m204v5bP9XV
    http://aem-docker-${environment}.aws-preprod.telegraph.co.uk:$port/$dir/${flakeid}
$dir - could be anything you define in your stub jar, in above holidays example it is /travel-products/holidays
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
