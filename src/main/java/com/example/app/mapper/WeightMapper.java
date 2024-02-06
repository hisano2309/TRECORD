package com.example.app.mapper;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.WeightBmi;

@Mapper
public interface WeightMapper {


//登録
	//体重の登録
	void insertWeightBmi(WeightBmi WeightBmi) throws Exception;

//表示
	//前回の体重・BMI表示
	WeightBmi selectBeforeWeightBmi(int userId, LocalDate date) throws Exception;

}
