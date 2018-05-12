package com.opengovproj.homework.actions;

import com.google.gson.Gson;
import com.opengovproj.homework.domain.CreateEnvironment;
import com.opengovproj.homework.domain.EnvironmentResponse;
import com.opengovproj.homework.domain.FlagResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.isOneOf;

public class EnvironmentsAPIAction extends AbstractAPIActions {

    public void postEnvInfo(CreateEnvironment createEnvironment) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_CREATED,SC_BAD_REQUEST))
                        .request()
                        .when()
                        .post("http://localhost:3000/environment/:{}",createEnvironment.getName())
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
    }

    public EnvironmentResponse[] getEnvInfo() {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_CREATED,SC_NOT_FOUND))
                        .request()
                        .when()
                        .get("http://localhost:3000/environment")
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
        Gson gson = new Gson();
        EnvironmentResponse[] environmentResponses = gson.fromJson(spec.response().getBody().asString(),EnvironmentResponse[].class);
        return environmentResponses;
    }

    public EnvironmentResponse getEnvInfobyName(String envName) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_CREATED,SC_NOT_FOUND))
                        .request()
                        .when()
                        .get("http://localhost:3000/environment/:{}",envName)
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
        Gson gson = new Gson();
        EnvironmentResponse environmentResponse = gson.fromJson(spec.response().getBody().asString(),EnvironmentResponse.class);
        return environmentResponse;

    }

}
