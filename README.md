# FootBall Teams Manager

## Technologies

1. Java 17
2. Spring Framework Boot v3.x.x
5. Spring Data Jpa
6. H2 dataBase
7. Maven

<!-- ABOUT THE PROJECT -->

## About The Project

There are 2 REST points to perform following operations related to FootBall Teams Manager  :

1. POST Endpoint /teams to create teams with players or without player.
2. GET Endpoint to retrieve teams. This end point support pagination and sortBy option

## Project repository

- [Project page](https://github.com/rahul6789sharma/manager)

### Build and run



<!-- GETTING STARTED -->
## Getting Started
Clone the repository

git clone git@github.com:rahul6789sharma/manager.git


**Setting up the workspace**

After cloning the Repository it can be placed anywhere on your local hard drive.

### Prerequisites & Installation for SpringBoot

### Requirements

For building and running the application you need:

- [JDK 1.17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)
- [H2](http://www.h2database.com/html/tutorial.html)

## Building the application locally

```
cd <path_of_backend_repo>
mvn clean install
Ex: <MainPath>/test/
```

## Deploying the application
1. Locally
```
java -jar manger-0.0.1-SNAPSHOT.jar
```
After starting applcation

you can use postman collection to test the applcation
 ```
tools/FootTeam Manager.postman_collection.json
```

## Code Formatter

https://confluence.hilti.com/pages/viewpage.action?spaceKey=pshilti&title=How+to+setup+code-formatter