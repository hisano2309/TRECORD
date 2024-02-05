package com.example.app.service;

import com.example.app.domain.WeightBmi;

public interface WeightService {

//表示
	//体重表示
//	List<Weight> getSelectWeight(Weight userId, LocalDate date) throws Exception;

//登録
	//BMIの計算
//	void insertBmi(WeightBmi bmi) throws Exception;

	//体重・BMIの登録
	void insertWeightBmi(WeightBmi userWeight) throws Exception;


}
