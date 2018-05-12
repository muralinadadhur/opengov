package com.opengovproj.homework.actions;

import com.opengovproj.homework.beans.ResponseInfo;

public class AbstractAPIActions {
    protected ResponseInfo responseInfo;
    protected ActionsFactory actions;

    public AbstractAPIActions withResponseInformation(ResponseInfo info) {
        this.responseInfo = info;
        return this;
    }

    public AbstractAPIActions withActionsFactory(ActionsFactory actionsFactory) {
        this.actions = actionsFactory;
        return this;
    }
}
