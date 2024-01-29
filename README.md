# CV Tool 4.0 #

CV Tool 4.0 is the latest iteration of our CV management software, designed to streamline and simplify the candidate selection process. This version was completely rebuilt from scratch with the newest technologies, offering enhanced features and improved usability to help you efficiently manage and evaluate candidates' CVs.

Key Features:

* **Efficient CV Management**: Easily organize and track candidate CVs in one central location.

* **Advanced Search**: Quickly find the right candidates using powerful search and filtering options.
<!-- - Applicant Matching: Leverage AI-driven matching to identify the most qualified candidates.-->

* **AI-driven**: Leverage AI to improve candidates summary to better fit the required position.

* **Streamlined Hiring**: Reduce administrative tasks and make data-driven hiring decisions.

## Technical features ##

This project was built using the following technologies:

* Java 17

* Spring Boot 3

## Setup local environment ##

To setup your local environment:

* Clone this repository to your local environment

* Build the project with maven to download dependencies: `mvn clean install`

* Import project to your IDE

* Edit `application-local.yml` to change values to your own

* Setup the following environment variables:

	* `ENV_DB_PASS` - The password to connect to the database

	* `OPENAPI_TOKEN` - The token to request chatGPT

	* `SPRING_SECU_PASS` - The password to secure the API

* Launch the application

## Project features ##

### Maven structure  ###

This project is built in a multi-module structure, where the main code is  on `cv-tool-api-impl` and the deliverables are generated on `cv-tool-api-dist`. 
The deliverable is packaged on a zip file (..\cv-tool-api-dist\target\cv-tool-api-distribution-<VERSION>.zip) that was generated using `maven-assembly-plugin`. Inside the zip you'll find a `conf` folder which contains the configuration files, and a `lib` folder which contains the application jar.

### Logs ###

The logs have been configured with logback for spring using the file `logback-spring.xml`. The location of the logs is set on the `application-local.yml` using the property `logging.file.path`.

### Database ###

The database used (for now) is MariaDB. The model can be viewed [here](https://lucid.app/lucidchart/9aee1649-f513-4fc5-abea-e4ae94d8c510/edit?viewport_loc=-41%2C1808%2C2649%2C1327%2C~d5PLuYy2ElF&invitationId=inv_0faabf6d-d3e4-4699-b602-db6401ac2f3f)
The DDL are generated automatically using JPA and the DML can be found on `..\cv-tool-api-impl\src\main\resources\data.sql`

### Security ###

This project is secured using Spring Security. All access to the API are protected with basic user/pass authentication (for now). Access to swagger and health check are open so that they can be accessed without authentication.

### API Documentation ###

We are using Swagger to document automatically the API. The Swagger can be accessed in the following URL: http://<host>/cvtoolapi/swagger-ui.html

