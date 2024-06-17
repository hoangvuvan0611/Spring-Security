# Spring-Security

## Prerequisite
    - JDK Corretto-21
    - Maven 3.5+

## Tech Stack
    - Java 21
    - Maven 3.5+
    - SpringBoot 3.3.0
    - Spring Data Jpa   
    - H2 Database
    - Lombok
    - Spring Security
    - Jwt
    - SpringBoot Validation

## Config
    - Database: 
```yaml 
Spring:
    jpa:
        database-platform: org.hibernate.dialect.H2Dialect (config in jpa level, Specified type db for hibernate)```
        properties:
            hibernate:
                dialect: org.hibernate.dialect.H2Dialect (Config in hibernate level, overriding database-platform, direct indication for hibernate)
```
        => This parameter is typically used to automatically determine the appropriate Dialect (grammar) based on the connected database.
        [Reference](https://medium.com/@humbleCoder007/configuring-h2-database-in-a-spring-boot-application-3c5b1ec49189#id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6ImMzYWJlNDEzYjIyNjhhZTk3NjQ1OGM4MmMxNTE3OTU0N2U5NzUyN2UiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTAzMzE0NjExMTA0NzEzNTQ4MTYiLCJlbWFpbCI6ImhvYW5ndnV2YW42NzdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5iZiI6MTcxODYxMzQ0MSwibmFtZSI6Ikhvw6BuZyBWxakgdsSDbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NMRWxQREh1ck5heEtPU08tNUxnUzU4RjJ5R0lUVkF0WU9odWQwSnROWG53SGJtcHZKaj1zOTYtYyIsImdpdmVuX25hbWUiOiJIb8OgbmciLCJmYW1pbHlfbmFtZSI6IlbFqSB2xINuIiwiaWF0IjoxNzE4NjEzNzQxLCJleHAiOjE3MTg2MTczNDEsImp0aSI6ImQ5NzQ1ZGE5NGQyODJmMGI2ZmE5OWMzYjlhNDBhOWZhZTU3M2Y1ZGQifQ.DDujE-f5ElJk_sVDc7uOS14zoH3w-tQ58lr2O65OuQMHjYtJePJsDexrx_SqQODGvM9fkOULMeSqZEqS0J2yeNM6iK1niacKSzp7vAX8uJIwAHVgL2CwjLeha2Yd0zvTJfgfBTlOKhf33GReib671WYHWLIGA_8owtdFgtyV3YAdef1AgD84iF3HTaDGJ40H1HI51iCeQgnazfVtrVrDDz12-di0b3hSdGMreJ9jEsqBNdJhFVFgVtS4-hklmEU0I_LwG7uhuj6JdhoeNRzYc1APLkf9BjP6Rs5EwXCHsbzUIHaMw7rrJ9_7aEeLNVIY0hCkNCDlfyYzfxGrRMdVOQ)

```yaml
  h2:
    console:
      enabled: true
      path: /h2
```
        => show h2 database with path http://localhost:8080/h2
    
    - Security

## Architecture 
    - [Docs](https://docs.spring.io/spring-security/reference/servlet/architecture.html)
    - [Other docs](https://medium.com/@rasheed99/introduction-on-spring-security-architecture-eb5d7de75a4f)

### WorkFlow
    ![For All Flow](https://miro.medium.com/v2/resize:fit:828/format:webp/1*bXZoyANJiP9aqxSqtFbo_A.png)