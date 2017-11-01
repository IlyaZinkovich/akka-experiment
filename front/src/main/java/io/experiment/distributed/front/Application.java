package io.experiment.distributed.front;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import io.experiment.distributed.backend.api.ComputationResult;
import io.experiment.distributed.backend.api.Compute;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ActorSystem actorSystem = ActorSystem.create("app-system");
        final ActorRef computationSupervisor = actorSystem.actorOf(ComputationSupervisor.props(), "computation-supervisor");
        while (true) {
            final CompletableFuture<Object> computationResult =
                    ask(computationSupervisor, new Compute("input"), 10).toCompletableFuture();
            computationResult.thenApply((result) -> (ComputationResult) result)
                    .whenComplete((result, throwable) -> System.out.println(result.getResult()));
            Thread.sleep(10);
        }
    }
}
