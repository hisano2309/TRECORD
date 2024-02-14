package com.example.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Count;
import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;
import com.example.app.mapper.MachineMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class MachineSetCountServiceImpl implements MachineSetCountService{

	private final MachineMapper machineMapper;

//	@Override
//	public List<MachineSetCount> checkDate(LocalDate date) throws Exception {
//		return machineMapper.selectCheckDate(date);
//	}

	@Override
	public void addMachineSetCount(MachineSetCount machineRecord) throws Exception {
		machineMapper.insert(machineRecord);	
	}

		@Override
	public List<Machine> getSelectMachine() throws Exception {
		return machineMapper.selectMachine();
	}

	@Override
	public List<MachineSetCount> getMachineSetCountDay(LocalDate day, int userId) throws Exception {
		return machineMapper.selectDay(day, userId);
	}

	@Override
	public int getCountMachine() throws Exception {
		return machineMapper.countMachine();
	}

	
	
	@Override
	public List<MachineSetCount> getSelectLineGraph(int userId, int machineId) throws Exception {
		return machineMapper.selectLineGraph(userId, machineId);
	}

	@Override
	public List<Count> getSelectPieGraph(int UserId) throws Exception {
		return machineMapper.selectPieGraph(UserId);
	}



//	@Override
//	public List<MachineSetCount> getSelectChart(int userId) throws Exception {
//		return machineMapper.selectChart(userId);
//	}



//	@Override
//	public void editMachineSetCount(MachineSetCount machineRecord) throws Exception {
//		machineMapper.update(machineRecord);
//
//	}

//	@Override
//	public void deleteMachineSetCount(LocalDateTime date) throws Exception {
//		machineMapper.delete(date);
//
//	}





}
