package com.grocerycrud.qa.controllers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiController {

	private RequestSpecification request;
	private Response response;

	public ApiController setUri(String uri) {
		RestAssured.baseURI = uri;
		request = RestAssured.given();
		return this;
	}

	public ApiController get() {
		response = request.request(io.restassured.http.Method.GET);
		return this;
	}

	public ApiController printResponseOnConsole(String prefix) {
		System.out.println(prefix+response.getBody().asString());
		return this;
	}

	public ApiController addHeader(String param, String value) {
		request.header("Content-Type","application/json");
		return this;
	}

	public ApiController setBody(String body) {
		request.body(body);
		return this;
	}

	public ApiController post() {
		response = request.request(io.restassured.http.Method.POST);
		return this;
	}
}
