# Stub Pipeline

[![Build Status](https://img.shields.io/travis/thephpleague/pipeline/master.svg?style=flat-square)](https://jenkins-preprod.aws-preprod.telegraph.co.uk/job/travel-stubs/)

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

* For Cruise stub -


    sbt reload clean assembly
    
    docker build -t cruisestub .
    
    docker run -p 8080:8081 cruisestub
    
* For Hotel stub - 
    
    
    sbt reload clean assembly
    
    docker build -t hotel-stub .
    
    docker run -p 8080:8081 hotel-stub
    
    docker save hotel-stub > hotel-stub.tar (if you want to save as a tar)
    
### Deployment

Before deploying the changes, update version of JAR in build.sbt and Dockerfile and rebuild the docker image as per build instructions

Note: Deployment commands to tag, push and pull images for both Cruise and hotel stub have been included, please execute the commands corresponding to the stub being updated.  

Upload JAR to S3 

    sbt publish

Tag docker image 
   
    docker tag cruisestub  docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub 
    docker tag hotel-stub  docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub 
    
Push to Docker registry
    
    docker push docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub
    docker push docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub
    
    
Pull from Docker registry
    
    docker pull docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub
    docker pull docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:hotel-stub

### Testing

In order to verify your stub, set the Header as `Content-Type:application/json` (in postman) and perform a GET using the following details. 

Cruise URL - 
   
    http://localhost:8080/travel-products/cruises/pvjqdBFp0sD 
    
Cruise listing URL - 

    http://localhost:8080/travel-products/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4
    
Hotelstub URL - 
    
    http://localhost:8080/travel-products/hotel-stub/kfxl6SzzYPB or kfxl6S6C32l or mkbj9GHrC76 or mbY85y0JBL2 or k3kWmhGH9Sm

### Test the stub by hitting the environment

To verify the changes in the stubbed environment (such as QA15), respin the environment so that the latest docker image is pulled.

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
