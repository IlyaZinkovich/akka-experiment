package io.experiment.distributed.backend;

import akka.actor.ActorRef;
import io.experiment.distributed.backend.api.Compute;

public class ComputationRequest {

    private Compute compute;
    private ActorRef issuer;

    public ComputationRequest(Compute compute, ActorRef issuer) {
        this.compute = compute;
        this.issuer = issuer;
    }

    public Compute getCompute() {
        return compute;
    }

    public ActorRef getIssuer() {
        return issuer;
    }
}
