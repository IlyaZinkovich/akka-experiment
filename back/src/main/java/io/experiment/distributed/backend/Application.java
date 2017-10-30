package io.experiment.distributed.backend;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class Application {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("computation-system");
        system.actorOf(Props.create(ComputationSupervisor.class), "supervisor");
    }
}
