# Create java jar project from command line (outside of the IDE) #####

## JAR is created in the "target" sub-directory

### 1. From bash command line prompt - create jar spring project 
```
vladu@DESKTOP-SHJ200J MINGW64 /d/Udemy - Spring & Hibernate/Eclipse - Spring & Hibernate/Spring-Project/04-spring-boot-command-line-demo (main)
$./mvnw package
```
### 2. If you already have Maven installed just use: mvn package
```
$mvn package
$clear
```

### 3. Use java -jar
```
$cd target
$ls *.jar
$java -jar name_of_the_app

```
### Run app using Spring Boot Maven plugin
```
vladu@DESKTOP-SHJ200J MINGW64 /d/Udemy - Spring & Hibernate/Eclipse - Spring & Hibernate/Spring-Project/04-spring-boot-command-line-demo (main)
$ ./mvnw spring-boot:run
```

---
