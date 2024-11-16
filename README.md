## 1. 프로젝트 개요
JimCarry는 현재 서비스 중인 '짐싸'를 오마주 한 프로젝트로 사용자의 견적 요청을 통한 기사님들의 견적비용을 비교 하는 역 경매 이사 서비스 입니다. 

![image](https://github.com/user-attachments/assets/c29140a6-553d-4b0b-8a0d-37ca3e8b8066)

-짐싸 사이트 주소
https://www.zimssa.com/

## 2. 프로젝트 목표 및 학습 방향
이번 프로젝트를 통해 다음과 같은 학습을 하는 것을 목표로 하고 있습니다.
1. 자바의 기초 공부: JDK, JVM 구조와 메모리 관리
2. 컨텍스트 스위칭, 블로킹 I/O와 논 블로킹 I/O 개념 이해 및 코드 작성
3. 자바 GC, Heap 구조 분석
4. 대용량 데이터 처리 시의 성능 튜닝
5. Java의 멀티 스레딩과 비동기 처리
6. Java의 동등성(equality)와 동일성(identity)에 대한 이해와 학습
7. 인터페이스와 추상 클래스의 차이점과 사용 용도 정리
8. 헬퍼 패턴과 스트래티지 패턴에 대한 깊이 있는 학습
9. TDD와 관련된 용어와 기법에 대해 학습
10. DTO와 VO에 대한 차이점 학습
11. API설계와 구현 시 고려할 점 학습 및 API Spec에 대한 정리
12. 테이블 모델링과 정규화/비정규화 개념 학습
13. 동시성 제어에 대한 학습
14. 서블릿 컨테이너의 동작 원리와 쓰레드풀 관리 방식 대한 조사와 학습
15. Tracsactoin 관리의 필요성과 이해
16. 언체크드 익셉션과 체크드 익셉션의 차이점 조사하기
17. 데이터 베이스의 클러스터드 인덱스와 논클러스터드 인덱스의 차이를 5분동안 설명 할 정도로 학습
18. JsessionID의 생성 및 사용 방법에 대해 조사와 이해하기
19. HTTP 프로토콜에서 쿠키와 헤더의 관계에 대해 조사 히기
20. CI/CD 파이프라인 설정 및 네이버 클라우드 플랫폼을 활용한 배포 방법 학습하기

* 자바 동시성 관련 학습 
https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C

## 3. 사용 기술
1. SpringBoot 3.3.4
2. Java 17
3. Mybatis
4. STS4
5. Maven
6. MariaDB 8.0

![image](https://github.com/user-attachments/assets/2cd71aba-0f4d-471e-8e79-d2d375df579d)

## 4. ERD
![JimCarry drawio](https://github.com/user-attachments/assets/b15e39b0-3bf3-4626-ade4-45b3a69455cb)

## 5. 기능 정의서
# 사용자 웹

1. 회원가입
2. 로그인
3. ID/PW 찾기(이메일 임시코드 방식)
4. 견적요청서 작성

![image](https://github.com/user-attachments/assets/5dcd999c-e6e3-4c06-a904-b0cd0c6fb1e4)
![image](https://github.com/user-attachments/assets/d1cb4b98-c136-469e-bb87-61baf312fa1e)
![image](https://github.com/user-attachments/assets/fc680c10-f86a-412b-b60f-0808bb9331af)

5. 견적 조회 및 기사님 정보 상세보기, 채택

![image](https://github.com/user-attachments/assets/4eb5d107-1a8d-4ec6-b4e2-04ee159d7035)

* 이미지는 이해를 돕기 위해 추가 하였습니다.

6. 결제 모듈을 넣을지 고민중...
7. 나의 이사내역 조회 (견적요청 내역 및 상태/이사 완료 내역 조회)
8. 이사 확정 후 리뷰 작성 및 조회

***

# 관리자 웹

1. 사용자 리뷰 조회 및 삭제
2. 이사 내역 조회 
3. 회원관리


***

# 기사님 웹

1. 회원가입
2. 로그인
3. ID/PW 찾기
4. 견적 요청 리스트 조회, 상세 조회
5. 견적 확정 및 철회
6. 나의 리뷰 조회 
7. 나의 이사 내역 조회(견적 확정 리스트 조회/이사 완료 내역 조회)

## 6. Data Flow Diagram
- 사용자 견적 요청 후, 해당 정보를 Kafka 토픽에 이벤트로 발행 후 견적 요청 정보를 DB에 저장

### 2.기사님측에서 견적 요청 이벤트 구독 (Consumer)

- 사용자 견적 요청에서 발행한 이벤트를 수신 하고, 해당 견적 요청 정보를 DB에서 조회

### 3.기사님측에서 견적 확정 이벤트 발행 (Producer)

- 기사님의 원하는 사용자의 견적요청 정보를 확정을 하고 Kafka 토픽에 이벤트로 발행 후 확정 정보를 DB에 저장

### 4.사용자 측에서 견적 확정 이벤트 구독 (Consumer)

- 사용자 측에서 기사님의 견적 확정 이벤트를 수신 하고, DB에서 견적 확정 정보 리스트를 조회

### 5.사용자 측에서 견적 채택

- 사용자 측에서 견적 확정 정보 리스트 중 원하는 견적 건을 채택 후 이사 정보 DB에 저장

![JimCarry DFD drawio](https://github.com/user-attachments/assets/7afaf4bf-b569-486c-828c-94a863f15ca3)

1. 엔티티(사각형) - 데이터가 발생 하는 시작과 종료를 표현

![image](https://github.com/user-attachments/assets/40e2ab14-cc66-4230-814b-98e5f4693856)

2. 프로세스(타원형) - 데이터를 처리하는 프로세스를 표현

![image](https://github.com/user-attachments/assets/9926a90e-4834-4205-9af3-991e160119ee)

3. 데이터 흐름(화살표) - 데이터의 흐름을 표현

![image](https://github.com/user-attachments/assets/c592022f-7b64-4b57-bb7d-6ea0fbad838f)
