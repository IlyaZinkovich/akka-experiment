package io.experiment.distributed.backend.api;

import java.io.Serializable;

public final class Compute implements Serializable {

	private String input;

	public Compute(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}
}
