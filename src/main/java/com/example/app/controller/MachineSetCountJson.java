package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.MachineSetCount;
import com.example.app.service.MachineSetCountService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MachineSetCountJson {

	private final MachineSetCountService service;
	
	@GetMapping("/MachineSetCountJson")
	public List<List<MachineSetCount>> list(HttpSession session) throws Exception{
		
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		MachineSetCount machineSetCount = new MachineSetCount();
		machineSetCount.setUserId(1);
		
		
//		List<Machine> machine = new ArrayList<>();
//		int size = machine.size();
//		for(int i = 1; i <= size; i++) {
//			machineSetCount.setMachineId(i);
//			return service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
//		}
//		System.out.println("size" + size);
//		return null;
		
//		int countMachine = service.getCountMachine();
//		int i = 1;
//		while(i <= countMachine) {
//			machineSetCount.setMachineId(i);
//			return service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
//		}
//		return null;
		
//		int countMachine = service.getCountMachine();
//		for(int i = 1; i <= countMachine; i++) {
//			if(i == 1) return service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
//			if(i == 2) return service.getSelectChartMachineId2(machineSetCount.getUserId(), machineSetCount.getMachineId());
//			if(i == 3) return service.getSelectChartMachineId3(machineSetCount.getUserId(), machineSetCount.getMachineId());
//			if(i == 4) return service.getSelectChartMachineId4(machineSetCount.getUserId(), machineSetCount.getMachineId());
//			if(i == 5) return service.getSelectChartMachineId5(machineSetCount.getUserId(), machineSetCount.getMachineId());
//			if(i == 6) return service.getSelectChartMachineId6(machineSetCount.getUserId(), machineSetCount.getMachineId());
//		}
//		return null;
		
		machineSetCount.setMachineId(1);
		List<MachineSetCount> machine1 = service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(2);
		List<MachineSetCount> machine2 = service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(3);
		List<MachineSetCount> machine3 = service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
		
		List<List<MachineSetCount>> machineList = new ArrayList<>();
		machineList.add(machine1);
		machineList.add(machine2);
		machineList.add(machine3);
		System.out.println("machineList->"+machineList);
//		return service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
		return machineList;
	}
	
	
//	public List<MachineSetCount> list(HttpSession session) throws Exception{
//		
//		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
//		WeightBmi weightBmi = new WeightBmi();
//		weightBmi.setUserId(1);
//		
//		return service.getSelectChart(weightBmi.getUserId());
//	}
	
}
