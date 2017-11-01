package io.experiment.distributed.backend;

import akka.actor.ActorSystem;

public class Application {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("computation-system");
        system.actorOf(ComputationActor.props(), "computation-actor");
    }
}
