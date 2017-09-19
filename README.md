# Build instructions

update version

sbt reload clean assembly

sbt publish

docker build -t facebookauth-stub .

docker run -p 8080:8081 facebookauth-stub

docker save facebookauth-stub > facebookauth-stub.tar (if you want to save as a tar)

docker tag facebookauth-stub  385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub

docker push 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub (to push to docker registry)

docker pull 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub (to pull)

## Example
http://localhost:8080/tmgauth

with body:
```
{
"accountLinkingToken": "abcdef123456",
"emailId": "user@telegraph.co.uk",
"password": "subscribed@telegraph.co.uk"
}
```

the password or the oauthtoken will identify the response (see http://localhost:8080/__admin)

## Usage
Engineers should only need to change MyStub.scala which sets up the mocks and configures:
- port for stub
- canned responses location
- swagger file
- state model file
- opening state

N.B. You should run your acceptance tests against the stub as well as the real service
to further improve the validity of the stub, as well as validating the test requests
against swagger.

## Errors
An invalid request payload will return a 500 with an error
An invalid response (from the stub) will also result in a 500 with an error
An invalid state transition will (you guessed it) return a 500 with an error