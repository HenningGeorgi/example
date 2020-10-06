package org.example.model;

import javax.validation.constraints.NotNull;

public class CreateGreetingUpdateRequest extends CreateGreetingRequest{

    @NotNull
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

