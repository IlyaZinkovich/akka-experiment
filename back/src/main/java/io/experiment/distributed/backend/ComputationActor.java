package io.experiment.distributed.backend;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import io.experiment.distributed.backend.api.Compute;

public class ComputationActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    static Props props() {
        return Props.create(ComputationActor.class, ComputationActor::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Compute.class, request -> sender().tell(new Computation(request.getInput()).result(), self()))
                .matchAny(o -> log.info("Received unknown message"))
                .build();
    }
}
