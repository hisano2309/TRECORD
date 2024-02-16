package com.example.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;
import com.example.app.service.MachineSetCountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordController {
	// 筋トレ記録
	private final MachineSetCountService service;



// 【ユーザーの画像一覧取得】
	@GetMapping("/mypage")
	public String list(Model model,
			HttpSession session) {

		return "mypage";
	}


// 【記録ページ】
	@GetMapping("/record")
	public String add(Model model, HttpSession session) throws Exception {

	// トレーニング記録
		MachineSetCount machineSetCount = new MachineSetCount();

		//ユーザーID
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		machineSetCount.setUserId(1);

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
		machineSetCount.setMachineCount(10);
		System.out.println(machineSetCount.getMachineCount());

		//セット数
		model.addAttribute("numSet", 10);

		//日付、回数の初期値を設定
		model.addAttribute("machineSetCount", machineSetCount);

		return "record";
	}


	@PostMapping("/record")
	public String add(
			@Valid MachineSetCount machineSetCount,
			Errors errors, Model model, HttpSession session)
			throws Exception {
	// トレーニング記録
			System.out.println(machineSetCount);
			service.addMachineSetCount(machineSetCount);

		return "recordDone";
	}



}

