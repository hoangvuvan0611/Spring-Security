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
    
## Security

### Architecture 
[Docs](https://docs.spring.io/spring-security/reference/servlet/architecture.html)
[Medium docs](https://medium.com/@rasheed99/introduction-on-spring-security-architecture-eb5d7de75a4f)
[Other docs](https://magz.techover.io/2023/01/02/spring-security-tim-hieu-ve-internal-flow/)

### WorkFlow
![For All Flow](https://miro.medium.com/v2/resize:fit:828/format:webp/1*bXZoyANJiP9aqxSqtFbo_A.png)

### Diễn giải, tiếng việt
    * Giới thiệu
        - Thông thường, trong Spring MVC hay SpringBoot , tất cả các Http request gửi đến sẽ được chuyển qua một "Servlet" 
        duy nhất được gọi là "DispatcherServlet". Servlet sẽ điều hướng các request này tới các controller (endpoints).
        - Về cốt lõi Spring Security là chuỗi (chains) các bộ lọc (Filter) được thêm vào trước khi các request đến được
        "DispatcherServlet".
        -> Tất cả các request được gửi đến sẽ phải đi qua các filter. Các tiếp cận này giúp xác minh (authentication) cũng 
        như phân quyền (authorization) các request trước khi đi qua "DispatcherServlet" và cuối cùng là các Controller (endpoints).
        
#### Luồng đi của request khi có Spring Security
![Filter Chain](https://docs.spring.io/spring-security/reference/_images/servlet/architecture/filterchain.png)
![Filter chain](https://www.marcobehler.com/images/servletfilter-1a.png)

#### Thành phần kiến trúc
##### "Security Filter Chain" (Nơi đầu tiên tiếp nhận các request để xử lý)
    * Mô tả:
        - Một loạt các bộ lọc sẽ xử lý các http request bằng cách liên lạc với "Authentication Manager" để xác thực các yêu cầu.
        - Các Filter này sẽ hoạt động cùng nhau để đảm bảo ứng dụng web được an toàn và sẽ làm nhiệm vụ như xác thực, phân quyền,
        và quản lý phiên ...
        - Các bộ Filter này sẽ được áp dụng theo thứ tự nhất định vì mỗi bộ lọc sẽ chịu trách nhiệm về một khía cạnh bảo mật 
        cụ thể.

    * Các Filter mặc định
        Spring Security Authentication Filters là những filter sẽ nằm giữa client request với server. Khi nhận được
        request, các filter sẽ tách lọc những thông tin từ request thành các authentication details (username, password,
        roles,…). Default Spring Security sẽ sử dụng class UsernamePasswordAuthenticationFilter.

        - ChannelProcessingFilter: đảm bảo rằng các request là HTTP hoặc HTTPS
        - SecurityContextPersistenceFilter: đảm bảo các thông tin chi tiết xác thực của người dùng (user’s authentication details)
        vẫn tồn tại trong phiên của họ.
        - UsernamePasswordAuthenticationFilter: Kiểm tra thông tin đăng nhập được cung cấp như userName/password và tạo
        một "Authentication Object" nếu thông tin đăng nhập hợp lệ. (Authentication object sẽ là class UsernamePasswordAuthenticationToken.)
        - ConcurrentSessionFilter: Kiểm soát số lượng session mà một người dùng có thể có đồng thời xử lý tình huống 
        session vượt quá số lượng cho phép.
        - LogoutFilter: Chặn các yêu cầu đăng suất và loại bỏ, chấm dứt session của user.
        - RememberMeAuthenticationFilter: cho phép ghi nhớ user qua các session ngay cả khi đã logout.
        - AnonymousAuthenticationFilter: chịu trách nhiệm tạo người dùng ẩn danh (anonymous) không được xác thực nhưng
        có quyền truy cập hạn chế vào một số phần của ứng dụng.
        - SessionManagementFilter: quản lý session và các tác vụ khác liên quan đến session.
        - ExceptionTranslationFilter: chuyển các Exception thành các unauthenticated exception.
        - FilterSecurityInterceptor : đảm bảo user có các role và permission cần thiết để truy cập tài nguyên.

[Tham khảo thêm về "Filter Chain"](https://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html)

    * Method Filter Chain
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
    httpSecurity.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/signup/").permitAll()
            .requestMatchers("/users").authenticated()
    ).httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
}
``` 
        - Một ví dụ đơn giản cho cấu hình "Security Filter Chain", cho phép truy cập không giới hạn với url "/signup", trong 
        khi đó yêu cầu xác thực với url "users".

#### Authentication Manager
    * Mô tả:
        - Authentication Manager là một interface với authenticate() method, nó là thành phần trung tâm xác thực user.
        Nó điều phối quá trình authen và ủy quyền xác thực cho "Authentication Provider".
        - Trong một số hệ thống phức tạp thì mỗi "Authentication Manager" có thể liên kết với một nhóm các "Provider" 
        cho phép xử lý các loại xác thực khác nhau cho các thành phần khác nhau của ứng dụng.
        - Authentication Manager có 5 implement nhưng Behavior mặc định được sử dụng để authen là "Provider Manager"
