package io.experiment.distributed.backend;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import io.experiment.distributed.backend.api.Compute;

import static akka.pattern.PatternsCS.ask;
import static akka.pattern.PatternsCS.pipe;

public class ComputationSupervisor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static final int SLA_TIMEOUT_MILLIS = 100;

    private final ActorRef computationActor = getContext().actorOf(ComputationActor.props(), "comp-actor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Compute.class, compute -> {
                    log.info(compute.getInput());
                    pipe(ask(computationActor, compute, SLA_TIMEOUT_MILLIS), getContext().dispatcher()).to(sender());
                })
                .build();
    }
}
