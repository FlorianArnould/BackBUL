# BackBUL

This project is based on Java 8 and Java Spark and need a Postgresql database.

## Prerequisites

Deploy a Postgresql database on the machine with a user `bul` (using the password `savon`) and a database `bulbase`.
You also need to initialize this database with the script `script.sql` which create the tables and fill them with some examples.

## Build

Run `./gradlew fullJar` to build the project. The build artifacts will be stored in the `build/libs/` directory.

## Launch

Run `java -jar build/libs/BulBackEnd-all-1.0.jar` to launch the BackEnd server.