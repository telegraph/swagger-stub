Build instructions
------------------
sbt reload clean assembly
docker build -t stubserver .
docker run -p 8080:8081 stubserver

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

