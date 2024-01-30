package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SetCount {

	private int userId;
	private LocalDateTime date;
	
	private Integer machineId;
	private Integer bodyWeightId;
	
	private Integer machineCount;
	private Integer bodyWeightCount;
	private int sets;
}
