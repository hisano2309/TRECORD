package com.example.app.domain;

import lombok.Data;

@Data
public class MachineMaxMin {

	private Integer machineId;
	private int maxWeight;
	private int minWeight;
}
