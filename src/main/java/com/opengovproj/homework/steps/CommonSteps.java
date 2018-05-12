package com.opengovproj.homework.steps;

import com.opengovproj.homework.beans.ResponseInfo;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps extends AbstractSteps {

    @Autowired
    ResponseInfo responseInfo;

    /**
     * Validate status returned from the API.
     * @param arg1
     */

    @Then("^status code is (\\d+)$")
    public void status_code_is(int arg1) {
        assertThat(responseInfo.getStatusCode()).isEqualTo(arg1);
    }
}
