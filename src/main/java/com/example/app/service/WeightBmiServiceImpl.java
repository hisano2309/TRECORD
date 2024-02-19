package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.WeightBmi;
import com.example.app.mapper.WeightMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class WeightBmiServiceImpl implements WeightBmiService{

	private final WeightMapper weightMapper;

	@Override
	public List<WeightBmi> CheckId(int userId) throws Exception {
		return weightMapper.selectCheckId(userId);
	}

	@Override
	public void insertWeightBmi(WeightBmi WeightBmi) throws Exception {
		weightMapper.insertWeightBmi(WeightBmi);

	}

	@Override
	public WeightBmi getSelectBeforeWeightbmi(int userId) throws Exception {
		return weightMapper.selectBeforeWeightBmi(userId);
	}

	@Override
	public List<WeightBmi> getSelectChart(int userId) throws Exception {
		return weightMapper.selectChart(userId);
	}




}
