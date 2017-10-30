package io.experiment.distributed.backend;

import io.experiment.distributed.backend.api.ComputationResult;

import java.util.Arrays;
import java.util.Random;

import static java.util.stream.Collectors.joining;

public class Computation {

    private final String input;
    private final Random random;

    public Computation(String input) {
        this.input = input;
        this.random = new Random();
    }

    public ComputationResult result() {
        final String resultString = Arrays.stream(input.split(""))
                .sorted((first, second) -> random.nextInt() % 2 - 1)
                .collect(joining());
        return new ComputationResult(resultString);
    }
}
