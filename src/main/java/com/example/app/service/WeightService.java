package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.domain.Weight;

public interface WeightService {

//表示
	//体重表示
	List<Weight> getSelectWeight(Weight userId, LocalDate date) throws Exception;

//登録
	//体重の登録
	void insertWeight(Weight userWeight) throws Exception;

	//BMIの登録
	void insertBmi(Weight bmi) throws Exception;

}
