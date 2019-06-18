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
