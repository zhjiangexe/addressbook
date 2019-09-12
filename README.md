# addressbook

## Environment
- date: 10/09/2019
- java version: openjdk 1.8.0_181
- management: gradle
- tool and framework: h2, liquibase, spring boot 2, junit 5, lombok
- development: windows 10, idea, operate successfully in linux (docker openjdk:8-jre-alpine)

## When this app launch, liquibase will insert data to H2 "MEM" database, everything is ready.
- changelog path: `./src/main/resources/db/changelog/changelog-master.xml`

## Run Application
1. Execute command `gradle bootRun` or `./gradlew bootRun` in this project
2. Open up browser `localhost:8080` search bar, or directly request `localhost:8080/customers?surname=Potter`

## Import Idea
1. Select "open" or "import" *build.gradle* in this project
2. Enable Annotation processing for lombok
3. Execute main method from `com.jiang.addressbook.AddressbookApplication`

## Test Coverage
1. Execute command `./gradlew build jacocoTestReport`
2. You can find the report in `./build/reports/jacoco/test/html/index.html`

## Docker(testing in linux environment)
1. Generate jar file: `./gradlew bootJar`
2. Generate docker image: `docker build . -t addressbook`
3. Up container `docker run -p 8080:8080 addressbook`

## Explanation

please follow below classes, go through top to down, there are only method named fuzzySearchBySurname

- com.jiang.addressbook.web.controller.CustomerController
- com.jiang.addressbook.service.CustomerService
- com.jiang.addressbook.dao.CustomerDao

you also can see their unit test in folder `test/java`


## API TEST CASE
- server/customers?surname=
- server/customers?surname=qoo
- server/customers?surname=Potter
- server/customers?surname=POTTER
- server/customers?surname=potter
- server/customers?surname=po
- server/customers?surname=er
- server/customers?surname=ot