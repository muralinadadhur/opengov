package com.opengovproj.homework.actions;

import com.opengovproj.homework.domain.CreateEnvironment;
import com.opengovproj.homework.domain.CreateFlag;
import com.opengovproj.homework.domain.EnvFlags;
import com.opengovproj.homework.domain.FlagResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.isOneOf;

import com.google.gson.Gson;
import java.util.List;

public class FlagAPIAction extends AbstractAPIActions {

    public void postFlagsInfo(CreateFlag jsonCreateFlag) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_CREATED))
                        .request()
                        .when()
                        .body(jsonCreateFlag)
                        .post("http://localhost:3000/Flags")
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
    }
    public FlagResponse[] getFlagsInfo() {
        ExtractableResponse<Response> spec =
                given()
                        .header("Accept","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_OK))
                        .request()
                        .when()
                        .log().method()
                        .log().uri()
                        .get("http://localhost:3000/Flags")
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
        Gson gson = new Gson();
        FlagResponse[] flagResponse = gson.fromJson(spec.response().getBody().asString(),FlagResponse[].class);
        return flagResponse;
    }
    public void updateFlagsInfo(CreateFlag jsonUpdateFlag,Integer updateId) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_OK))
                        .request()
                        .when()
                        .body(jsonUpdateFlag)
                        .post("http://localhost:3000/Flags/" + updateId)
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
    }

    public EnvFlags getFlagsEnv(String flagName, String envName) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_OK,SC_NOT_FOUND))
                        .request()
                        .when()
                        .queryParam("environment",envName)
                        .queryParam("flag", flagName)
                        .get("http://localhost:3000/Flags/")
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
        Gson gson = new Gson();
        EnvFlags envFlags = gson.fromJson(spec.response().getBody().asString(),EnvFlags.class);
        return envFlags;
    }

    public FlagResponse[] getAllFlagsEnv(String envName) {
        ExtractableResponse<Response> spec =
                given()
                        .header("Content-Type","application/json")
                        .expect()
                        .statusCode(isOneOf(SC_OK,SC_NOT_FOUND))
                        .request()
                        .when()
                        .queryParam("environment",envName)
                        .get("http://localhost:3000/Flags/")
                        .then().log().all()
                        .extract();
        responseInfo.setStatusCode(spec.statusCode());
        Gson gson = new Gson();
        FlagResponse[] flagResponse = gson.fromJson(spec.response().getBody().asString(),FlagResponse[].class);
        return flagResponse;
    }

}
