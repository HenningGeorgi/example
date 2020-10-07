package org.example.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateGreetingUpdateResponse extends CreateGreetingResponse {

    @NotNull
    private Integer version;

    public CreateGreetingUpdateResponse(UUID id, String name, Boolean vegan, Integer age, Integer version) {
        super(id, name, vegan, age);
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}

