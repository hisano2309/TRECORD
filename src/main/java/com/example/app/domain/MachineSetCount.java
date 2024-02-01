package com.example.app.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MachineSetCount {

	private int userId;

	//再表示用にHTMLのtype="date"の型に合わせる
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date;

	private Integer machineId;
	private int machineWeight;
	private Integer count;

	//private Integer bodyWeightId;
	//private Integer bodyWeightCount;

	private int sets;


}
