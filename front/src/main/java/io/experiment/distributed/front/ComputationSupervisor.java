package io.experiment.distributed.front;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import io.experiment.distributed.backend.api.Compute;

import static akka.pattern.PatternsCS.ask;
import static akka.pattern.PatternsCS.pipe;

public class ComputationSupervisor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private static final int SLA_TIMEOUT_MILLIS = 100;

    static Props props() {
        return Props.create(ComputationSupervisor.class, ComputationSupervisor::new);
    }

    private final ActorSelection computationActor =
            getContext().actorSelection("akka.tcp://computation-system@127.0.0.1:2552/user/computation-actor");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Compute.class, compute -> {
                    log.info("Computation supervisor received: {}", compute.getInput());
                    pipe(ask(computationActor, compute, SLA_TIMEOUT_MILLIS), getContext().dispatcher()).to(sender());
                })
                .build();
    }
}
