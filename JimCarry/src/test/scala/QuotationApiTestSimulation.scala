package com.study.jimcarry

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class QuotationApiTestSimulation extends Simulation {

  // HTTP 프로토콜 설정
  val httpProtocol = http.baseUrl("http://localhost:8090") // 기본 URL 설정
    .acceptHeader("application/json")   // 응답 헤더 설정 (선택 사항)
    .contentTypeHeader("application/json")  // 요청 헤더 설정 (선택 사항)

  // 시나리오 정의
  val scn = scenario("Quotation API Test")  // 시나리오 이름
    .exec(http("Get Quotation List")  // 요청 이름
      .get("/api/quotation/list")  // 요청 URL
      .check(status.is(200)))  // 응답 상태 코드가 200인지 확인

  // 시나리오 실행 설정
  setUp(
    scn.inject(
      atOnceUsers(10),  // 한 번에 10명의 사용자가 동시에 요청
      rampUsers(50) during (30.seconds)  // 50명의 사용자가 30초 동안 점진적으로 요청
    ).protocols(httpProtocol)  // HTTP 프로토콜 설정 적용
  )
}