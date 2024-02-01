package com.example.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Machine;
import com.example.app.domain.MachineMaxMin;
import com.example.app.domain.MachineSetCount;
import com.example.app.service.MachineSetCountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MachineSetCountController {

//！！！！！後ほどRecordControllerと結合する！！！！！



	private final MachineSetCountService service;


//筋トレ記録の登録
	@GetMapping("/record_aramaki")
	public String allList(Model model) throws Exception{
		MachineSetCount machineSetCount = new MachineSetCount();

		//ユーザーID
//		HttpSession session = request.getSession();
//		User userId = session.getAttribute(user.userId);
//		machineSetCount.setUserId(userId);

		//日付
		LocalDate now = LocalDate.now();
		machineSetCount.setDate(now);
		System.out.println("今日の日付：" + machineSetCount.getDate());

		//筋トレマシン一覧表示
		List<Machine> machine = service.getSelectMachine();
		model.addAttribute("machine", machine);

		//重量
		model.addAttribute("weight", 100);

		//回数
		model.addAttribute("numCount", 30);
		machineSetCount.setCount(10);
		System.out.println(machineSetCount.getCount());

		//セット数
		model.addAttribute("numSet", 10);

		//日付、回数の初期値を設定
		model.addAttribute("machineSetCount", machineSetCount);

		return "charge/record_aramaki";
	}


	@PostMapping("/record_aramaki")
	public String recodeDone(
			@Valid MachineSetCount machineSetCount,
			Errors errors,
			Model model) throws Exception{

		if(errors.hasErrors()) {
			model.addAttribute("machine", machineSetCount);
			return "charge/record_aramaki";
		}

		service.addMachineSetCount(machineSetCount);

		return "charge/show_aramaki";
	}


//筋トレ記録の表示
	@GetMapping("/show_aramaki")
	public String showDay(
			HttpServletRequest request,
			Model model) {

		//カレンダーから特定の日の筋トレ記録表示 day
//		List<MachineSetCount> machineSetCount = service.getMachineSetCountDay(day);
//		model.addAttribute("machineSetCount", machineSetCount);
		return "charge/show_aramaki";
	}

//筋トレ記録編集
	@GetMapping("/show_aramaki/{day}")
	public String updateDay(
			@PathVariable LocalDate day,
			Model model) {
//		MachineSetCount selectDay = service.getMachineSetCountDay(day);
//		service.editMachineSetCount(selectDay);
		return "";
	}



//筋トレマシンのMax値、Min値を表示
	@GetMapping("/mypage_aramaki")
	public String showMax(
			MachineMaxMin machineMaxMin,
			Model model) throws Exception {
		//Max値を表示
		int MaxWeight = service.getMax(machineMaxMin.getMachineId());

		//Min値を表示
		int MinWeight = service.getMin(machineMaxMin.getMachineId());

		machineMaxMin.setMaxWeight(MaxWeight);
		machineMaxMin.setMinWeight(MinWeight);

		return "charge/mypage_aramaki";
	}





}
