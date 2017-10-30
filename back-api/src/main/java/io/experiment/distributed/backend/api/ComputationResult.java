package io.experiment.distributed.backend.api;

import java.io.Serializable;

public final class ComputationResult implements Serializable {

    private String result;

    public ComputationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
