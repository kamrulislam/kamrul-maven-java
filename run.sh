#!/bin/bash

mvn clean package
java -cp target/kamrul-maven-app-1.0-SNAPSHOT.jar kamrul.App
