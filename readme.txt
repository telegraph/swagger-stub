Build instructions
------------------
sbt reload clean assembly
docker build -t stubserver .
docker run -p 8080:8081 stubserver


Example
-------
http://localhost:8080/identity/tokens
with body:
{
    "grant_type": "password",
    "credential_type": "EMAIL_PASSWORD",
    "identifier": "user.email@telegraph.co.uk",
    "auth_key": "user.password",
    "client_id": "tcuk",
    "remember_me": false
}

Usage
-----
The stub accepts:
- json swagger file location (openAPI)
- port
- location of canned responses
- opening state

Engineers should only need to change MyStub.scala and add canned json files

N.B. You should run your acceptance tests against the stub as well as the real service
to further improve the validity of the stub, as well as validating the test requests
against swagger.

Errors
------
An invalid request payload will return a 500 with an error
An invalid response (from the stub) will also result in a 500 with an error
An invalid state transition will (you guessed it) return a 500 with an error


