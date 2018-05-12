package com.opengovproj.homework.steps;

import com.opengovproj.homework.actions.ActionsFactory;
import com.opengovproj.homework.actions.FlagAPIAction;
import com.opengovproj.homework.beans.EnvFlagBean;
import com.opengovproj.homework.beans.FlagBean;
import com.opengovproj.homework.domain.FlagResponse;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import org.testng.util.Strings;

import static org.assertj.core.api.Assertions.assertThat;

public class EnvFlagSteps {
    @Autowired
    private ActionsFactory actions;

    @Autowired
    private EnvFlagBean envFlagBean;

    @Autowired
    private FlagBean flagBean;


    /**
     * Read flag details based on a environment
     * @param arg1
     * @param arg2
     * @throws Throwable
     */
    @When("^get a flag with name \"([^\"]*)\" from \"([^\"]*)\" environment$")
    public void get_a_flag_with_name_from_environment(String arg1, String arg2) throws Throwable {
        FlagAPIAction flagAPIAction = actions.get(FlagAPIAction.class);
        envFlagBean.setEnvFlags(flagAPIAction.getFlagsEnv(arg1,arg2));
        assertThat(envFlagBean.getEnvFlags()).isNotNull();
    }

    /**
     * Test step to verify all flag details are returned for that environment
     * @param arg1
     * @throws Throwable
     */
    @When("^I should see all details of flag \"([^\"]*)\" from that environment$")
    public void i_should_see_all_details_of_flag_from_that_environment(String arg1) throws Throwable {
        assertThat(envFlagBean.getEnvFlags().getEnvFlags().contains(arg1)).isTrue();
    }

    /**
     * Test step to get all flags for that environment
     * @param arg1
     * @throws Throwable
     */
    @When("^I get all flags for environment \"([^\"]*)\"$")
    public void i_get_all_flags_for_environment(String arg1) throws Throwable {
        FlagAPIAction flagAPIAction = actions.get(FlagAPIAction.class);
        flagBean.setFlagResponse(flagAPIAction.getAllFlagsEnv(arg1));

        for(FlagResponse flagResponse : flagBean.getFlagResponse()) {
            assertThat(flagResponse.getName()).isNotNull();
            assertThat(flagResponse.getId()).isNotNull();
            assertThat(flagResponse.getCross()).isNotNull();
        }
    }

    /**
     * Validate flag values match the expected values
     * @param arg1
     * @throws Throwable
     */
    @When("^Flags should match with the expected \"([^\"]*)\"$")
    public void flags_should_match_with_the_expected(String arg1) throws Throwable {
        List<String> out = new ArrayList<>();
        String[] split = arg1.split(",", -1);
        for (String s : split) {
            if (Strings .isNotNullAndNotEmpty(s)) {
                out.add(s.trim());
            }
        }
        List<String> flagsList = new ArrayList<>();
        for(FlagResponse flagResponse : flagBean.getFlagResponse()) {
            flagsList.add(flagResponse.getName());
        }
        assertThat(flagsList).hasSize(out.size()).containsAll(out);
    }

}
