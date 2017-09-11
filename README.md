# Build instructions
sbt reload clean assembly

docker build -t facebookauth-stub .

docker run -p 8080:8081 facebookauth-stub


## Example
http://localhost:8080/tmgauth

with body:
```
{
"accountLinkingToken": "abcdef123456",
"emailId": "subscribed@telegraph.co.uk",
"password": "password"
}
```

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