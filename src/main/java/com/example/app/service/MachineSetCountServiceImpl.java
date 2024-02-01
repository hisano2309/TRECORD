package com.example.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;
import com.example.app.mapper.MachineMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class MachineSetCountServiceImpl implements MachineSetCountService{

	private final MachineMapper machineMapper;

	@Override
	public List<Machine> getSelectMachine() throws Exception {
		return machineMapper.selectMachine();
	}

	@Override
	public List<MachineSetCount> getSelectAll() throws Exception {
		return machineMapper.selectAll();
	}

	@Override
	public List<MachineSetCount> getMachineSetCountDay(LocalDate day) throws Exception {
		return machineMapper.selectDay(day);
	}

	@Override
	public int getMax(Integer machineId) throws Exception {
		return machineMapper.selectMax(machineId);
	}

	@Override
	public int getMin(Integer machineId) throws Exception {
		return machineMapper.selectMin(machineId);
	}

	@Override
	public void addMachineSetCount(MachineSetCount machineRecord) throws Exception {
		machineMapper.insert(machineRecord);

	}

	@Override
	public void editMachineSetCount(MachineSetCount machineRecord) throws Exception {
		machineMapper.update(machineRecord);

	}

	@Override
	public void deleteMachineSetCount(LocalDateTime date) throws Exception {
		machineMapper.delete(date);

	}





}
