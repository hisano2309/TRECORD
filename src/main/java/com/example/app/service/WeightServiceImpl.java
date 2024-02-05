package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Weight;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService{
	
	@Override
	public List<Weight> getSelectWeight(Weight userId, LocalDate date) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insertWeight(Weight userWeight) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void insertBmi(Weight bmi) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	
}
