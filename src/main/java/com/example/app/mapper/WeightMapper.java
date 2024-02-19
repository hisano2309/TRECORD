package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.WeightBmi;

@Mapper
public interface WeightMapper {

//確認//	
	// IDでweightDB、machineSetCountに記録があるか確認(データがある場合のみ表形式グラフの表示)
	List<WeightBmi> selectCheckId(int userId)throws Exception;
		
//登録
	//体重の登録
	void insertWeightBmi(WeightBmi WeightBmi) throws Exception;

//表示
	//前回の体重・BMI表示
	WeightBmi selectBeforeWeightBmi(int userId) throws Exception;

	//グラフ表示
	List<WeightBmi> selectChart(int userId) throws Exception;
}
