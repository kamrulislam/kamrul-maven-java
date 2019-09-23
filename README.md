# Coding Challenge

This a maven java project. I have used [the plain project](https://github.com/kamrulislam/kamrul-plain-java) to convert it into a **maven** project. This project also have unit tests.


## key considerations

- Added unit tests. 
  

## Improvements

- Add more unit tests covering input reader.


## How to run

- Needs maven to be installed. My java and maven detail are as follows -- 

```
Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-28T01:06:16+10:00)
Maven home: /usr/local/Cellar/maven/3.6.2/libexec
Java version: 1.8.0_171, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/jre
Default locale: en_AU, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
```
- run the following commands
```
mvn clean package
java -cp target/kamrul-maven-app-1.0-SNAPSHOT.jar kamrul.App
```
- to run test using following command
```
mvn test
```

