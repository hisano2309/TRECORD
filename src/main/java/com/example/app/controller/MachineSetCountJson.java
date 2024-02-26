package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.Count;
import com.example.app.domain.MachineSetCount;
import com.example.app.domain.User;
import com.example.app.service.MachineSetCountService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MachineSetCountJson {

	private final MachineSetCountService service;

	@GetMapping("/MachineSetCountLineGraph")
	public List<List<MachineSetCount>> line(HttpSession session) throws Exception{

		User user = (User) session.getAttribute("user");
		MachineSetCount machineSetCount = new MachineSetCount();
		machineSetCount.setUserId(user.getUserId());
		System.out.println("MachineSetCountLineGraph_user.getUserId()" + user.getUserId());


		machineSetCount.setMachineId(1);
		List<MachineSetCount> machine1 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(2);
		List<MachineSetCount> machine2 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(3);
		List<MachineSetCount> machine3 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(4);
		List<MachineSetCount> machine4 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(5);
		List<MachineSetCount> machine5 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());
		machineSetCount.setMachineId(6);
		List<MachineSetCount> machine6 = service.getSelectLineGraph(machineSetCount.getUserId(), machineSetCount.getMachineId());

		List<List<MachineSetCount>> machineList = new ArrayList<>();
		machineList.add(machine1);
		machineList.add(machine2);
		machineList.add(machine3);
		machineList.add(machine4);
		machineList.add(machine5);
		machineList.add(machine6);
		System.out.println("machineList->"+machineList);
//		return service.getSelectChartMachineId1(machineSetCount.getUserId(), machineSetCount.getMachineId());
		return machineList;
	}


	@GetMapping("/MachineSetCountPieGraph")
	public List<Count> pie(HttpSession session) throws Exception{

		User user = (User) session.getAttribute("user");
		MachineSetCount machineSetCount = new MachineSetCount();
		machineSetCount.setUserId(user.getUserId());
		System.out.println("MachineSetCountPieGraph_user.getUserId()" + user.getUserId());

		List<Count> count = service.getSelectPieGraph(machineSetCount.getUserId());
		System.out.println("MachineSetCountPieGraph_machineSetCount.getUserId()" + machineSetCount.getUserId());

		return count;
	}


}
