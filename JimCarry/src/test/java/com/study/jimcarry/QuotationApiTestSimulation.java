package com.study.jimcarry;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class QuotationApiTestSimulation extends Simulation {

    // HTTP 프로토콜 설정
    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://211.188.56.168:8090")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // 시나리오 정의
    private ScenarioBuilder scn = scenario("Quotation API Test")
            .exec(http("Get Quotation List")
                    .get("/api/quotation/list")
                    .check(status().is(200)));

    // 시나리오 실행 설정
    {
        setUp(
                scn.injectOpen(
                        atOnceUsers(50),  // 한 번에 10명의 사용자가 동시에 요청
                        rampUsers(50).during(Duration.ofSeconds(30))  // 50명의 사용자가 30초 동안 점진적으로 요청
                ).protocols(httpProtocol) // HTTP 프로토콜 설정 적용
        );
    }
}