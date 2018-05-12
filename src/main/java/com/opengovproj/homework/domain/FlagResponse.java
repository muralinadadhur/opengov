package com.opengovproj.homework.domain;

import lombok.Getter;
import lombok.Setter;
import  java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName;


@Getter
@Setter
@JsonRootName(value = "flags")
public class FlagResponse {
    private String name;
    private Boolean cross;
    private Integer id;
}
