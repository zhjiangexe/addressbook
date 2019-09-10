# addressbook

## Environment

- date: 10/09/2019
- java version: jdk 1.8.0_181
- management: gradle
- framework and tool: h2, liquibase, spring boot 2, junit 5, lombok
- development: windows 10, idea, operate successfully in docker(openjdk:8-jre-alpine)


## Run Application

- execute command `gradle bootRun` or `./gradlew bootRun` in this project
- open up browser `localhost:8080`, or directly request `localhost:8080/customers?surname=Potter`


## Import Idea
- Select "open" or "import" *build.gradle* in this project
- Enable Annotation processing for lombok

## Test Coverage
1. execute command `./gradlew build jacocoTestReport`
2. you can find the report in `./build/reports/jacoco`

