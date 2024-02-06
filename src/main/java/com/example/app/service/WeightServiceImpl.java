package com.example.app.service;

import java.time.LocalDate;

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

	@Override
	public void insertWeightBmi(WeightBmi WeightBmi) throws Exception {
		weightMapper.insertWeightBmi(WeightBmi);

	}

	@Override
	public WeightBmi getSelectBeforeWeightbmi(int userId, LocalDate date) throws Exception {
		return weightMapper.selectBeforeWeightBmi(userId, date);
	}



}
