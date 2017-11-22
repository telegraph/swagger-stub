# Build instructions

update version

sbt reload clean assembly

sbt publish

docker build -t cruisestub .

docker run -p 8080:8081 cruisestub

docker save cruisestub > cruisestub.tar (if you want to save as a tar)

docker tag cruisestub  385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:cruisestub

docker push 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:cruisestub (to push to docker registry)

docker pull 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:cruisestub (to pull)

## Example


## Errors
