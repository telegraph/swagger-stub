# Cruise Stub
Repository with Cruise stubs for Travel Automation testing

## Getting started

### Prerequisites
* Access to Amazon S3 (You will need S3 keys to access the maven artifacts)
* Intellij with scala support
* Access to Telegraph Github

### Installation

Install sbt using the link `http://www.scala-sbt.org/release/docs/Installing-sbt-on-Mac.html`

Install docker from the URL `https://docs.docker.com/docker-for-mac/install/`

### Build instructions

    sbt reload clean assembly
    
    docker build -t cruisestub .
    
    docker run -p 8080:8081 cruisestub
    
### Deployment

Before deploying the changes, update version of JAR in build.sbt and Dockerfile and rebuild the docker image as per build instructions

Upload JAR to S3 

    sbt publish

Tag docker image 

    docker tag cruisestub  docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub
    
Push to Docker registry
    
    docker push docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub
    
Pull from Docker registry
    
    docker pull docker.awspreprod.telegraph.co.uk/adobe/tmg-service-stubs:cruisestub

### Testing

In order to verify your stub, set the Header as `Content-Type:application/json` (in postman) and perform a GET using the following details. 

Cruise URL - 
   
    http://localhost:8080/travel-products/cruises/pvjqdBFp0sD 
    
Cruise listing URL - 

    http://localhost:8080/travel-products/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4

To verify the changes in the stubbed environment (such as QA15), respin the environment so that the latest docker image is pulled.
And verify the below URLs using the QA15 domain.

    http://aem-docker-qa15.aws-preprod.telegraph.co.uk:8082/travel-products/cruises/pvjqdBFp0sD    
    http://aem-docker-qa15.aws-preprod.telegraph.co.uk:8082/travel-products/cruises?flakeIds=ng5xKjcstfs,mlMWvMk8tGh,ng5xFhmBC33,mlMWvMxfHXq,ng5w8LfZ8Wl,ng5w7ZGDGzZ,mlMWvMdxpST,ng5xKs6Mz5C,pClvcx3M5N4


### Issues
