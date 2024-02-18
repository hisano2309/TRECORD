package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import com.example.app.domain.WeightBmi;

public interface WeightBmiService {

//登録
	//体重・BMIの登録
	void insertWeightBmi(WeightBmi userWeight) throws Exception;

//表示
	//体重・BMIの表示
	WeightBmi getSelectBeforeWeightbmi(int userId) throws Exception;

	//グラフ表示
	List<WeightBmi> getSelectChart(int userId) throws Exception;
}
