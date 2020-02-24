package com.myproject.gatling
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class WebServiceSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://petstore.swagger.io")
		//.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "application/json",
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-US,en;q=0.9",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "cross-site",
		"Content-Type" -> "application/json",
		"Proxy-Connection" -> "Keep-Alive")

		val token_feeder = csv("F:\\Saju\\Program\\GatlingProject\\GatlingDemoProject\\src\\test\\resources\\data\\datafile.csv").random
		val name_feeder = csv("F:\\Saju\\Program\\GatlingProject\\GatlingDemoProject\\src\\test\\resources\\data\\namefile.csv").random
		
	val scn = scenario("WebServiceSimulation")
		// Launch
		.feed(token_feeder)
		.exec(http("WebServiceGET")
			.get("/v2/pet/${Name}")
			//.queryParam("petId", "1")
			.headers(headers_0)
			.check(substring("name")))
		.pause(1)
		.feed(name_feeder)
		.exec(http("WebServicePOST")
			.post("/v2/pet")
			//.queryParam("petId", "1")
			.headers(headers_0)
			.body(ElFileBody("F:\\Saju\\Program\\GatlingProject\\GatlingDemoProject\\src\\test\\resources\\data\\sample.json"))
			.check(status.in(200)))
		.pause(1)
	

	setUp(scn.inject(atOnceUsers(1)))
	//setUp(scn.inject(rampUsersPerSec(2) to (20) during (120 seconds)))
	//setUp(scn.inject(rampUsers(20) during (60 seconds))).maxDuration (3 minutes)
	.protocols(httpProtocol)
}