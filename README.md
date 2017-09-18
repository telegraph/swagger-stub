# Build instructions
Run Scala SBT

    $ sbt reload clean assembly
    # if the above fails see 'trouble shooting' section below
    
If running the service from a .tar file

    $ docker load facebookauth-stub.tar
    $ docker run -p 8080:8081 facebookauth-stub

If building from src

    $ docker build -t facebookauth-stub .
    $ docker run -p 8080:8081 facebookauth-stub

To export a .tar file
    
    $ docker save facebookauth-stub > facebookauth-stub.tar 
    
To docker pull try 

    $ docker pull 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub 
    
If the above fails try

    $ aws ecr get-login --no-include-email --region eu-west-1
    $ run command returned by the above line
    
To push changes, tag and push

    $ docker tag facebookauth-stub  385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub
    $ docker push 385050320367.dkr.ecr.eu-west-1.amazonaws.com/tmg-service-stubs:facebookauth-stub 



## Example
http://localhost:8080/tmgauth

POST with JSON body:
```javascript
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


## Trouble shooting
### Dependency errors 
If you get something like the following when trying to build...
    
    [warn]  ::::::::::::::::::::::::::::::::::::::::::::::
    [warn]  ::          UNRESOLVED DEPENDENCIES         ::
    [warn]  ::::::::::::::::::::::::::::::::::::::::::::::
    [warn]  :: uk.co.telegraph.qe#smartstub_2.11;0.1.0-SNAPSHOT: Forbidden (Service: Amazon S3; Status Code: 403; Error Code: 403 Forbidden; Request ID: A26B5C6611C24C83)
    [warn]  ::::::::::::::::::::::::::::::::::::::::::::::
    [warn] 
    [warn]  Note: Unresolved dependencies path:
    [warn]          uk.co.telegraph.qe:smartstub_2.11:0.1.0-SNAPSHOT (/Users/patrickc/dev/fbauth-stub/project/Dependencies.scala#L7)
    [warn]            +- com.telegraph.stub.facebookauth:facebookauthstub_2.11:0.1.0-SNAPSHOT
    sbt.ResolveException: unresolved dependency: uk.co.telegraph.qe#smartstub_2.11;0.1.0-SNAPSHOT: Forbidden (Service: Amazon S3; Status Code: 403; Error Code: 403 Forbidden; Request ID: A26B5C6611C24C83)
    [error] (*:update) sbt.ResolveException: unresolved dependency: uk.co.telegraph.qe#smartstub_2.11;0.1.0-SNAPSHOT: Forbidden (Service: Amazon S3; Status Code: 403; Error Code: 403 Forbidden; Request ID: A26B5C6611C24C83)

? currently no solution for this error
