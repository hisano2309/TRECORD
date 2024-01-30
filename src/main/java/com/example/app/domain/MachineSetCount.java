package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MachineSetCount {

	private int userId;
	private LocalDateTime date;

	private Integer machineId;
	private Integer machineCount;

	//private Integer bodyWeightId;
	//private Integer bodyWeightCount;

	private int sets;
}
