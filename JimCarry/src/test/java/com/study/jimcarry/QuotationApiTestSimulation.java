package com.study.jimcarry;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class QuotationApiTestSimulation extends Simulation {

    // HTTP 프로토콜 설정: 요청을 보낼 서버와 요청/응답의 콘텐츠 타입을 정의
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://211.188.56.168:8090/")  // 테스트할 API 서버의 기본 URL
            .acceptHeader("application/json")  // 서버에서 반환할 응답의 형식 설정
            .contentTypeHeader("application/json");  // 요청 본문의 형식 설정

    // 시나리오 정의: 테스트할 시나리오를 설정
    private ScenarioBuilder scn = scenario("Quotation API Test")  // 시나리오 이름 설정
            .exec(http("Get Quotation List")  // HTTP 요청의 이름 설정
                    .get("api/quotation/list")  // 호출할 API 엔드포인트
                    .check(status().is(200)));  // 응답 상태 코드가 200인지 확인

    // 시나리오 실행 설정: 부하를 주는 방식과 실행 설정을 정의
    {
        setUp(
                scn.injectOpen(
                        atOnceUsers(500),  // 한 번에 50명의 사용자가 동시에 요청을 보냄
                        rampUsers(500).during(Duration.ofSeconds(30))  // 50명의 사용자가 30초 동안 점진적으로 요청을 보냄
                ).protocols(httpProtocol)  // HTTP 프로토콜 설정을 시나리오에 적용
        );
    }
}
