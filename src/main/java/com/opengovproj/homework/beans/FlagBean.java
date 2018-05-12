package com.opengovproj.homework.beans;

import com.opengovproj.homework.domain.FlagResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class FlagBean {
    @Getter @Setter
    private FlagResponse[] flagResponse;
}
