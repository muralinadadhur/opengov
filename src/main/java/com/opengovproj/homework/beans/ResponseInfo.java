package com.opengovproj.homework.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ResponseInfo {
    @Getter @Setter
    private Integer statusCode;
}
