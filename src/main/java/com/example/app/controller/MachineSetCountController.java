package com.example.app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		
		//日付
		LocalDate now = LocalDate.now();
		machineSetCount.setDate(now);
		System.out.println("今日の日付：" + machineSetCount.getDate());
		
		//筋トレマシン一覧表示
//		List<Machine> machine = service.getSelectMachine();
//		model.addAttribute("machine", machine);
		
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
		
		
		//カレンダーから特定の日の筋トレ記録表示
//		HttpSession session = request.getSession();
//		session.getAttribute(user.date);
		return "";
	}
	
	
	
	
	
	
	
}
