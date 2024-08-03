# github-fetch-repos

This is a simple application that uses Github api to fetch repositories and branch information of given user.

## Technologies

* Java version: 21
* Spring boot version: 3.3.2

## Running the applicaiton

There are two ways to run the application:

* Execute the `main` method in the `src/main/java/com/github/fetch/FetchApplication.java` class from your IDE
####
* Use the Spring Boot Maven plugin to initiate clean and install lifecycles. After that use command below 
inside project root directory to containerise the app in Docker

```
docker-compose up -d
```

## Api documentation

Api documentation can be accessed via swagger ui. you can access it at http://localhost:8080/swagger-ui/index.html after running the application.