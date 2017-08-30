Build instructions
------------------
sbt reload clean assembly
docker build -t stubserver .
docker run -p 8080:8081 stubserver

