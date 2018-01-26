# Build instructions

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

URL: http://localhost:8080/travel-products/cruises/pvjqdBFp0sD
Header: Content-Type:application/json

## Errors
