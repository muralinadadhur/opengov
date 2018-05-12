package com.opengovproj.homework.actions;

import com.opengovproj.homework.beans.ResponseInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionsFactory {

    @Autowired
    private ResponseInfo responseInfo;

    @SneakyThrows
    public <T extends AbstractAPIActions> T get(Class<T> clazz) {
        return (T) clazz.newInstance()
                .withResponseInformation(responseInfo)
                .withActionsFactory(this);
    }
}
