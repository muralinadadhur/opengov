package com.opengovproj.homework.steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.opengovproj.homework.actions.FlagAPIAction;
import com.opengovproj.homework.beans.FlagBean;
import com.opengovproj.homework.domain.CreateFlag;
import com.opengovproj.homework.domain.FlagResponse;

import org.springframework.beans.factory.annotation.Autowired;
import com.opengovproj.homework.actions.ActionsFactory;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class FlagSteps  {

    @Autowired
    FlagBean flagBean;

    @Autowired
    private ActionsFactory actions;

    String random = "enableIngest." + System.currentTimeMillis();;

    /**
     * Test to create a new flag
     */
    @When("^I create a feature flag with any name$")
    public void i_create_a_feature_flag_with_any_name() {
        FlagAPIAction flagAPIAction = actions.get(FlagAPIAction.class);
        CreateFlag createFlag = new CreateFlag(random,true);
        flagAPIAction.postFlagsInfo(createFlag);
    }

    /**
     * Test to get all flag listing and loading it in a flag bean object
     */
    @When("^I get flags listing$")
    public void i_get_flags_listing() {
        FlagAPIAction flagAPIAction = actions.get(FlagAPIAction.class);
        flagBean.setFlagResponse(flagAPIAction.getFlagsInfo());
        assertThat(flagBean.getFlagResponse()).isNotNull();
    }

    /**
     * Test steo to verify that flag that just added comes as part of the flag listing
     */
    @Then("^flags listing contains the featue flag that was just *+.*$")
    public void flags_listing_contains_the_featue_flag_that_was_just_added() {
        List<String> responseFlagTitle = new ArrayList<>();
        for (FlagResponse response : flagBean.getFlagResponse()) {
            responseFlagTitle.add(response.getName());
        }
        assertThat(responseFlagTitle.contains(random)).isTrue();
    }

    /**
     * Update flag to a new boolean value
     * @param id
     */

    @Then("^I update the feature flag status (\\d+) to a new value$")
    public void i_update_the_feature_flag_status_to_the_new_value(int id) {
        random = "enableIngestTest." + System.currentTimeMillis();
        FlagAPIAction flagAPIAction = actions.get(FlagAPIAction.class);
        CreateFlag jsonUpdateFlag = new CreateFlag(random,false);
        flagAPIAction.updateFlagsInfo(jsonUpdateFlag,id);
    }
}
