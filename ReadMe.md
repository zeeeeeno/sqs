#  SQS + Spring Boot

#### SQS(Simple Queue Service)

- 마이크로 서비스, 분산 시스템 및 서버리스 애플리케이션을 분리하고 확장할 수 있는 완전 관리형 메시지 대기열 서비스
- 지속성이 우수하고 사용 가능한 보안 호스팅 대기열을 제공하며 이를 통해 분산 소프트웨어 시스템과 구성 요소를 통합 및 분리할 수 있음
- 일반 웹 서비스 API를 제공하므로 AWS SDK가 지원하는 모든 프로그래밍 언어로 액세스 가능

---

#### 사전준비

- AWS 회원가입
- AWS SQS(Simple Queue Service) 생성
- IAM 키 발급

---

##### 환경설정

- Project: Gradle
- Language: Java
- Spring Boot: 2.4.2

- Spring Boot Dependency
  - AWS Simple Queue Service
  - Lombok

---

#### 개발기간

- 2020-01-22 ~ 2020-01-25

---

#### 발생했던 문제점

- Failed to connect to service endpoint
  - 환경설정 미흡으로 인한 문제: IAM Key
- The specified queue does not exist for this wsdl version.
  - Queue Name을 정확히 기입해야 함

---

#### 참고자료

https://lannstark.tistory.com/84?category=840827







