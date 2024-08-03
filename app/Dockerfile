FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} githubRepos.jar
ENTRYPOINT ["java","-jar","/githubRepos.jar"]