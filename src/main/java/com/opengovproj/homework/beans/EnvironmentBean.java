package com.opengovproj.homework.beans;

import com.opengovproj.homework.domain.EnvironmentResponse;
import com.opengovproj.homework.domain.FlagResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentBean {
    @Getter
    @Setter
    private EnvironmentResponse[] environmentResponses;

}
