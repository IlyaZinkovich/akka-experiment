package io.experiment.distributed.front;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import io.experiment.distributed.backend.api.ComputationResult;
import io.experiment.distributed.backend.api.Compute;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ActorSystem actorSystem = ActorSystem.create("app-system");
        ActorSelection computationSupervisor =
                actorSystem.actorSelection("akka.tcp://computation-system@127.0.0.1:2552/user/supervisor");
        while (true) {
            final CompletableFuture<Object> computationResult =
                    ask(computationSupervisor, new Compute("input"), 10).toCompletableFuture();
            computationResult.thenApply((result) -> (ComputationResult) result)
                    .whenComplete((result, throwable) -> System.out.println(result.getResult()));
            Thread.sleep(10);
        }
    }
}
