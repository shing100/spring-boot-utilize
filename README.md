# 스프링 부트 개념과 활용 : 스프링 부트 원리

## 소개
Spring Boot를 사용하면 실행할 수 있는 독립 실행 형 프로덕션 급 Spring 기반 응용 프로그램을 쉽게 만들 수 있습니다. 우리는 Spring 플랫폼 및 써드 파티 라이브러리에 대한 의견을 수렴하여 최소한의 소동으로 시작할 수 있습니다. 대부분의 Spring Boot 응용 프로그램은 Spring 구성이 거의 필요하지 않습니다.

## Spring Boot Reference Guide – 시작하기
> https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/htmlsingle/#getting-started-maven-installation

SpringBootApplication Main 코드
```java
    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }
```

## 스프링 부트 프로젝트 구조

- 메이븐 기본 프로젝트 구조와 동일
    - 소스 코드
    - 소스 리소스
    - 테스트 코드
    - 테스트 리소스

> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-structuring-your-code


## Spring Boot Reference Guide – 의존성 관리
> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-dependency-management


- 의존성 관리 기능 활용
    - 버전 관리 해주는 의존성 추가
    - 버전 관리 안해주는 의존성 추가
    - 기존 의존성 버전 변경하기
    - https://mvnrepository.com/


## 자동 설정 이해  
- @EnableAutoConfiguration (@SpringBootApplication 안에 숨어 있음) 
- 빈은 사실 두 단계로 나눠서 읽힘 
    - 1단계: @ComponentScan 
    - 2단계: @EnableAutoConfiguration 
    - @ComponentScan 
    - @Component 
    - @Configuration 
-Repository @Service @Controller @RestController 
    - @EnableAutoConfiguration 
    - spring.factories 
- org.springframework.boot.autoconfigure.EnableAutoConfigu ration 
    - @Configuration 
    - @ConditionalOnXxxYyyZzz


> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-auto-configuration

-	Xxx-Spring-Boot-Autoconfigure 모듈: 자동 설정
-	Xxx-Spring-Boot-Starter 모듈: 필요한 의존성 정의
-	그냥 하나로 만들고 싶을 때는?
-	Xxx-Spring-Boot-Starter


## 구현 방법
1.	의존성 추가
```xml
<dependencies>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-autoconfigure</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-autoconfigure-processor</artifactId>
      <optional>true</optional>
  </dependency>
</dependencies>

<dependencyManagement>
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-dependencies</artifactId>
          <version>2.0.3.RELEASE</version>
          <type>pom</type>
          <scope>import</scope>
      </dependency>
  </dependencies>
</dependencyManagement>
```

2. @Configuration 파일 작성 

3. src/main/resource/META-INF에 spring.factories 파일 만들기

4. spring.factories 안에 자동 설정 파일 추가

    - org.springframework.boot.autoconfigure.EnableAutoConfiguration=\ FQCN,\

5. mvn install

이후 디펜던시 추가하기

```xml
<dependency>
    <groupId>com.kingname.springbootautoconfig</groupId>
    <artifactId>kingname-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 덮어쓰기 방지하기
    @ConditionalOnMissingBean

### 빈 재정의 수고 덜기
    @ConfigurationProperties(“holoman”)
    @EnableConfigurationProperties(HolomanProperties)
    프로퍼티 키값 자동 완성
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-configuration-processor</artifactId>
   <optional>true</optional>
</dependency>
```

## 스프링 부트는 서버가 아니다.
- 톰캣 객체 생성
- 포트 설정
- 톰캣에 컨텍스트 추가
- 서블릿 만들기
- 톰캣에 서블릿 추가
- 컨텍스트에 서블릿 맵핑
- 톰캣 실행 및 대기
	
    이 모든 과정을 보다 상세히 또 유연하고 설정하고 실행해주는게 바로 스프링 부트의 자동 설정.
    - ServletWebServerFactoryAutoConfiguration (서블릿 웹 서버 생성)
    - TomcatServletWebServerFactoryCustomizer (서버 커스터마이징)
    - DispatcherServletAutoConfiguration
    - 서블릿 만들고 등록

> https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html

- 다른 서블릿 컨테이너로 변경
- 웹 서버 사용 하지 않기
    - spring.main.web-application-type=none
- 포트
    - server.port
    - 랜덤 포트 port를 0 으로
    - ApplicationListner<ServletWebServerInitializedEvent>


- HTTPS 설정하기
    - 키스토어 만들기
    ```
    keytool -genkey 
        -alias tomcat 
        -storetype PKCS12 
        -keyalg RSA 
        -keysize 2048 
        -keystore keystore.p12 
        -validity 4000
    ```
        
## HTTP 커넥터는 코딩으로 설정하기
> https://github.com/spring-projects/spring-boot/tree/v2.0.3.RELEASE/spring-boot-samples/spring-boot-sample-tomcat-multi-connectors

## HTTP2 설정
- server.http2.enable
- 사용하는 서블릿 컨테이너 마다 다름.

```
server.ssl.key-store=keystore.p12
server.ssl.key-store-password=123456
server.ssl.keyStoreType=PKCS12
server.ssl.keyAlias=spring
server.port=0
server.http2.enabled=true
```

> https://docs.spring.io/spring-boot/docs/current/reference/html/executable-jar.html

mvn package를 하면 실행 가능한 JAR 파일 “하나가" 생성 됨.

- spring-maven-plugin이 해주는 일 (패키징)
- 과거 “uber” jar 를 사용
- 모든 클래스 (의존성 및 애플리케이션)를 하나로 압축하는 방법
- 뭐가 어디에서 온 건지 알 수가 없음
- 무슨 라이브러리를 쓰는 건지..


### 스프링 부트의 전략
- 내장 JAR : 기본적으로 자바에는 내장 JAR를 로딩하는 표준적인 방법이 없음.
- 애플리케이션 클래스와 라이브러리 위치 구분
- org.springframework.boot.loader.jar.JarFile을 사용해서 내장 JAR를 읽는다.
- org.springframework.boot.loader.Launcher를 사용해서 실행한다.

> https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-spring-application.html#boot-features-spring-application

- FailureAnalyzer
    - 에러를 이쁘게 출력시켜주는 스프링부트의 기능

## 배너
- banner.txt | gif | jpg | png
- classpath 또는 spring.banner.location
- ${spring-boot.version} 등의 변수를 사용할 수 있음.
- Banner 클래스 구현하고 SpringApplication.setBanner()로 설정 가능.
- 배너 끄는 방법
- SpringApplicationBuilder로 빌더 패턴 사용 가능


> https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-spring-application.html#boot-features-application-events-and-listeners

## ApplicationEvent 등록

ApplicationContext를 만들기 전에 사용하는 리스너는 @Bean으로 등록할 수 없다.
- SpringApplication.addListners()

- WebApplicationType 설정
- 애플리케이션 아규먼트 사용하기
- ApplicationArguments를 빈으로 등록해 주니까 가져다 쓰면 됨.
- 애플리케이션 실행한 뒤 뭔가 실행하고 싶을 때
- ApplicationRunner (추천) 또는 CommandLineRunner
- 순서 지정 가능 @Order


### 사용할 수 있는 외부 설정
> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config 

- properties
- YAML
- 환경 변수
- 커맨드 라인 아규먼트

### 프로퍼티 우선 순위
1.	유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties
2.	테스트에 있는 @TestPropertySource
3.	@SpringBootTest 애노테이션의 properties 애트리뷰트
4.	커맨드 라인 아규먼트
5.	SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티
6.	ServletConfig 파라미터
7.	ServletContext 파라미터
8.	java:comp/env JNDI 애트리뷰트
9.	System.getProperties() 자바 시스템 프로퍼티
10.	OS 환경 변수
11.	RandomValuePropertySource
12.	JAR 밖에 있는 특정 프로파일용 application properties
13.	JAR 안에 있는 특정 프로파일용 application properties
14.	JAR 밖에 있는 application properties
15.	JAR 안에 있는 application properties
16.	@PropertySource
17.	기본 프로퍼티 (SpringApplication.setDefaultProperties)

### application.properties 우선 순위 (높은게 낮은걸 덮어 씁니다.)
1.	file:./config/
2.	file:./
3.	classpath:/config/
4.	classpath:/

### 랜덤값 설정하기
- ${random.*}

### 플레이스 홀더
- name = geun
- fullName = ${name} lim

## 외부설정

https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor

타입-세이프 프로퍼티 @ConfigurationProperties
- 여러 프로퍼티를 묶어서 읽어올 수 있음
- 빈으로 등록해서 다른 빈에 주입할 수 있음
- @EnableConfigurationProperties
- @Component
- @Bean
   
	
- 융통성 있는 바인딩
    - context-path (케밥)
    - context_path (언드스코어)
    - contextPath (캐멀)
    - CONTEXTPATH

- 프로퍼티 타입 컨버전
    - @DurationUnit

- 프로퍼티 값 검증
    - @Validated
    - JSR-303 (@NotNull, ...)
    - 메타 정보 생성

- @Value
    - SpEL 을 사용할 수 있지만...
    - 위에 있는 기능들은 전부 사용못함
\
## 프로파일
@Profile 애노테이션은 어디에?

- @Configuration
- @Component
	
### 어떤 프로파일을 활성화 할 것인가?
- spring.profiles.active

### 어떤 프로파일을 추가할 것인가?
- spring.profiles.include

### 프로파일용 프로퍼티
- application-{profile}.properties

## 로깅

### 로깅 퍼사드 VS 로거
    Commons Logging, SLF4j
    JUL, Log4J2, Logback

### 스프링 5에 로거 관련 변경 사항
	https://docs.spring.io/spring/docs/5.0.0.RC3/spring-framework-reference/overview.html#overview-logging
	Spring-JCL
	Commons Logging -> SLF4j or Log4j2
	pom.xml에 exclusion 안해도 됨.

###  스프링 부트 로깅
	기본 포맷
	--debug (일부 핵심 라이브러리만 디버깅 모드로)
	--trace (전부 다 디버깅 모드로)
	컬러 출력: spring.output.ansi.enabled
	파일 출력: logging.file 또는 logging.path
	로그 레벨 조정: logging.level.패지키 = 로그 레벨

## 로깅
> https://docs.spring.io/spring-boot/docs/current/reference/html/

howto-logging.html 커스텀 로그 설정 파일 사용하기
-	Logback: logback-spring.xml
-	Log4J2: log4j2-spring.xml
-	JUL (비추): logging.properties
-	Logback extension
    -	프로파일 <springProfile name=”프로파일”>
    -	Environment 프로퍼티 <springProperty>
### 로거를 Log4j2로 변경하기
>	https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html#howto-configure-log4j-for-logging

## 테스트

시작은 일단 spring-boot-starter-test를 추가하는 것 부터
- test 스콥으로 추가.

### @SpringBootTest
- @RunWith(SpringRunner.class)랑 같이 써야 함.
- 빈 설정 파일은 설정을 안해주나? 알아서 찾습니다. (@SpringBootApplication)
- webEnvironment
    - MOCK: mock servlet environment. 내장 톰캣 구동 안 함.
    - RANDON_PORT, DEFINED_PORT: 내장 톰캣 사용 함.
    - NONE: 서블릿 환경 제공 안 함.
 
### @MockBean
- ApplicationContext에 들어있는 빈을 Mock으로 만든 객체로 교체 함.
- 모든 @Test 마다 자동으로 리셋.


### 슬라이스 테스트
- 레이어 별로 잘라서 테스트하고 싶을 때
- @JsonTest
- @WebMvcTest
- @WebFluxTest
- @DataJpaTest

## MVC

> https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/web.html#spring-web
 
 HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용 {“username”:”keesun”, “password”:”123”} <-> User
- @ReuqestBody
- @ResponseBody

## 스프링 부트
- 뷰 리졸버 설정 제공
- HttpMessageConvertersAutoConfiguration

### XML 메시지 컨버터 추가하기
```xml
<dependency>
   <groupId>com.fasterxml.jackson.dataformat</groupId>
   <artifactId>jackson-dataformat-xml</artifactId>
   <version>2.9.6</version>
</dependency>
```

## 정적 리소스 맵핑 “ /**”

### 기본 리소스 위치
- classpath:/static
- classpath:/public
- classpath:/resources/
- classpath:/META-INF/resources
   - 예) “/hello.html” => /static/hello.html

- spring.mvc.static-path-pattern: 맵핑 설정 변경 가능
- spring.mvc.static-locations: 리소스 찾을 위치 변경 가능
- Last-Modified 헤더를 보고 304 응답을 보냄.
    - ResourceHttpRequestHandler가 처리함.

- WebMvcConfigurer의 addRersourceHandlers로 커스터마이징 할 수 있음
```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
  registry.addResourceHandler("/m/**")
    .addResourceLocations("classpath:/m/")
    .setCachePeriod(20);
```

### 웰컴 페이지
- index.html 찾아 보고 있으면 제공.
- index.템플릿 찾아 보고 있으면 제공.
- 둘 다 없으면 에러 페이지.

### 파비콘
- favicon.ico
- 파이콘 만들기 https://favicon.io/
- 파비콘이 안 바뀔 때?
    - https://stackoverflow.com/questions/2208933/how-do-i-force-a-favicon-refresh


## 스프링 부트가 자동 설정을 지원하는 템플릿 엔진
- FreeMarker
- Groovy
- Thymeleaf
- Mustache

### JSP를 권장하지 않는 이유
- JAR 패키징 할 때는 동작하지 않고, WAR 패키징 해야 함.
- Undertow는 JSP를 지원하지 않음.
> https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-jsp-limitations

## Thymeleaf 사용하기
- https://www.thymeleaf.org/
- https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html

- 의존성 추가: spring-boot-starter-thymeleaf
- 템플릿 파일 위치: /src/main/resources/template/
- 예제: https://github.com/thymeleaf/thymeleafexamples-stsm/blob/3.0-master/src/main/webapp/WEB-INF/templates/seedstartermng.html

## HTML 템플릿 뷰 테스트
> http://htmlunit.sourceforge.net/
>http://htmlunit.sourceforge.net/gettingStarted.html

## 의존성 추가
```xml
<dependency>
   <groupId>org.seleniumhq.selenium</groupId>
   <artifactId>htmlunit-driver</artifactId>
   <scope>test</scope>
</dependency>
<dependency>
   <groupId>net.sourceforge.htmlunit</groupId>
   <artifactId>htmlunit</artifactId>
   <scope>test</scope>
</dependency>
```
@Autowire WebClient

## Hypermedia As The Engine Of Application State
- 서버: 현재 리소스와 연관된 링크 정보를 클라이언트에게 제공한다.
- 클라이언트: 연관된 링크 정보를 바탕으로 리소스에 접근한다.

### 연관된 링크 정보
- Relation Hypertext Reference)
- spring-boot-starter-hateoas 의존성 추가
> https://spring.io/understanding/HATEOAS
> https://spring.io/guides/gs/rest-hateoas/
> https://docs.spring.io/spring-hateoas/docs/current/reference/html/

### ObjectMapper 제공
- spring.jackson.*
- Jackson2ObjectMapperBuilder
- LinkDiscovers 제공
    - 클라이언트 쪽에서 링크 정보를 Rel 이름으로 찾을때 사용할 수 있는 XPath 확장 클래스


## SOP과 CORS
Single-Origin Policy

### Cross-Origin Resource Sharing
Origin?
- URI 스키마 (http, https)
- hostname (whiteship.me, localhost)
- 포트 (8080, 18080)

### 스프링 MVC @CrossOrigin
> https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/web.html#mvc-cors

- @Controller나 @RequestMapping에 추가하거나
- WebMvcConfigurer 사용해서 글로벌 설정




## 지원하는 인-메모리 데이터베이스  
- H2 (추천, 콘솔 때문에...)
- HSQL
- Derby
  - Spring-JDBC가 클래스패스에 있으면 자동 설정이 필요한 빈을 설정 해줍니다.

- DataSource
- JdbcTemplate

### 인-메모리 데이터베이스 기본 연결 정보 확인하는 방법
- URL: “testdb”
- username: “sa”
- password: “”

### H2 콘솔 사용하는 방법
- spring-boot-devtools를 추가하거나...
- spring.h2.console.enabled=true 만 추가.
- /h2-console로 접속 (이 path도 바꿀 수 있음)

### 실습 코드
```sql
CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))
INSERT INTO USER VALUES (1, ‘keesun’)
```

## 지원하는 DBCP
### HikariCP (기본)
> https://github.com/brettwooldridge/HikariCP#frequently-used
- Tomcat CP
- Commons DBCP2
- DBCP 설정
    - spring.datasource.hikari.*
    - spring.datasource.tomcat.*
    - spring.datasource.dbcp2.*

### MySQL 커넥터 의존성 추가
```xml
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### MySQL 추가 (도커 사용)
```
docker run -p 3306:3306 --name mysql_boot -e MYSQL_ROOT_PASSWORD=1 -e MYSQL_DATABASE=springboot -e MYSQL_USER=keesun -e MYSQL_PASSWORD=pass -d mysql

docker exec -i -t mysql_boot bash
mysql -u root -p
```

### MySQL용 Datasource 설정
- spring.datasource.url=jdbc:mysql://localhost:3306/springboot?useSSL=false
- spring.datasource.username=keesun
- spring.datasource.password=pass

### MySQL 접속시 에러
- MySQL 5.* 최신 버전 사용할 때
- 문제	
```
Sat Jul 21 11:17:59 PDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
```
### jdbc:mysql:/localhost:3306/springboot?useSSL=falseMySQL 8.* 최신 버전 사용할 때
```
문제	com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Public Key Retrieval is not allowed
해결	jdbc:mysql:/localhost:3306/springboot?useSSL=false&allowPublicKeyRetrieval=trueMySQL 라이센스 (GPL) 주의
```
- MySQL 대신 MariaDB 사용 검토
- 소스 코드 공개 의무 여부 확인

의존성 추가
```xml
<dependency>
   <groupId>org.postgresql</groupId>
   <artifactId>postgresql</artifactId>
</dependency>
```

### PostgreSQL 설치 및 서버 실행 (docker)
```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=keesun -e POSTGRES_DB=springboot --name postgres_boot -d postgres

docker exec -i -t postgres_boot bash

su - postgres

psql springboot
```
- 데이터베이스 조회
    
    \list

- 테이블 조회

    \dt

### 쿼리
SELECT * FROM account;


## JPA

### 스프링 데이터 JPA 의존성 추가
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

- 스프링 데이터 JPA 사용하기
- @Entity 클래스 만들기
- Repository 만들기
- 스프링 데이터 리파지토리 테스트 만들기
- H2 DB를 테스트 의존성에 추가하기
- @DataJpaTest (슬라이스 테스트) 작성


### JPA를 사용한 데이터베이스 초기화
- spring.jpa.hibernate.ddl-auto
- spring.jpa.generate-dll=true로 설정 해줘야 동작함.

### SQL 스크립트를 사용한 데이터베이스 초기화
- schema.sql 또는 schema-${platform}.sql
- data.sql 또는 data-${platform}.sql
- ${platform} 값은 spring.datasource.platform 으로 설정 가능.

## 데이터베이스 마이그레이션

- Flyway와 Liquibase가 대표적인데, 지금은 Flyway를 사용하겠습니다. 
> https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup

### 의존성 추가
- org.flywaydb:flyway-core

### 마이그레이션 디렉토리
- db/migration 또는 db/migration/{vendor}
- spring.flyway.locations로 변경 가능

### 마이그레이션 파일 이름
- V숫자__이름.sql
- V는 꼭 대문자로.
- 숫자는 순차적으로 (타임스탬프 권장)
- 숫자와 이름 사이에 언더바 두 개.
- 이름은 가능한 서술적으로.