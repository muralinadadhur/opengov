package com.opengovproj.homework.steps;

import com.opengovproj.homework.actions.EnvironmentsAPIAction;
import com.opengovproj.homework.beans.EnvironmentBean;
import com.opengovproj.homework.beans.FlagBean;
import com.opengovproj.homework.domain.CreateEnvironment;
import com.opengovproj.homework.domain.EnvironmentResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.opengovproj.homework.actions.ActionsFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EnvSteps {
    @Autowired
    private ActionsFactory actions;

    @Autowired
    private EnvironmentBean environmentBean;

    @Autowired
    FlagBean flagBean;

    /**
     * Step to create a new environment
     * @param arg1
     * @throws Throwable
     */

    @When("^I create an environment with name as \"([^\"]*)\"$")
    public void i_create_an_enviroment_with_name_as(String arg1) throws Throwable {
        EnvironmentsAPIAction environmentsAPIAction = actions.get(EnvironmentsAPIAction.class);
        CreateEnvironment createEnvironment = new CreateEnvironment(arg1);
        environmentsAPIAction.postEnvInfo(createEnvironment);
    }

    /**
     * Read all environments from environment table.
     * @throws Throwable
     */

    @When("^I get all environments$")
    public void i_get_an_environment() throws Throwable {
        EnvironmentsAPIAction environmentsAPIAction = actions.get(EnvironmentsAPIAction.class);
        environmentBean.setEnvironmentResponses(environmentsAPIAction.getEnvInfo());
        assertThat(environmentBean.getEnvironmentResponses()).isNotNull();
    }

    /**
     * Environment contains the value that was just added
     * @param arg1
     * @throws Throwable
     */
    @When("^Environment name should match with \"([^\"]*)\"$")
    public void environment_name_should_match_with(String arg1) throws Throwable {
        List<String> envList = new ArrayList<String>();
        for (EnvironmentResponse environmentResponse : environmentBean.getEnvironmentResponses()) {
            envList.add(environmentResponse.getName());
        }
        assertThat(envList.contains(arg1)).isTrue();
    }

    /**
     * Read an environment by name
     * @param arg1
     * @throws Throwable
     */
    @When("^I get an environment with name as \"([^\"]*)\"$")
    public void i_get_an_environment_with_name_as(String arg1) throws Throwable {
        EnvironmentsAPIAction environmentsAPIAction = actions.get(EnvironmentsAPIAction.class);
        assertThat(environmentsAPIAction.getEnvInfobyName(arg1)).isNotNull();
    }

}

