# FundsTransferAPI
The core purpose is to create a simple funds transfer REST API ​including​ ​data​ ​model​ ​and​ ​the​ ​backing​ ​implementation that can be used to transfer funds from one account to another.

## Technologies
- Java
- JAX-RS API
- Junit
- HTTP Server
- Jersey HTTP Client


## Features
  - No use of heavy 3rd party libraries/framework.
  - No use of containers. (Jersey's client and server classes are used to setup a server).
  - In memory datastore

## How to use
Kindly execute attached RUN.bat file or below command to start application. 
```sh
mvn clean compile exec:java
```
By default, the API is accessible on port 8080.
Kindly use the below json sample transaction for processing.

### Sample JSON to post a transaction 

```sh
{  
   "transactionId":"123",
   "fromAccount":"123456",
   "toAccount":"789456",
   "amount": "50"
}
```

## Available Services

| HTTP METHOD | PATH | USAGE |
| ----------- | ------ | ------ |
| GET | /     | Server Up Status |  
| POST | /api/fundstransfer/{transaction} | Post transaction | 

## Http Status
- 200 OK: The request has succeeded
- 400 Bad Request: The request could not be understood by the server 
- 404 Not Found: The requested resource cannot be found
- 500 Internal Server Error: The server encountered an unexpected condition 

