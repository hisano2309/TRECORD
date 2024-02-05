package com.example.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.WeightBmi;
import com.example.app.mapper.WeightMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService{
	
	private final WeightMapper weightMapper;

//	@Override
//	public List<Weight> getSelectWeight(Weight userId, LocalDate date) throws Exception {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

//	@Override
//	public void insertBmi(WeightBmi bmi) throws Exception {
//		weightMapper.insertBmi( bmi);
//		
//	}

	@Override
	public void insertWeightBmi(WeightBmi WeightBmi) throws Exception {
		weightMapper.insertWeightBmi(WeightBmi);

	}



}
