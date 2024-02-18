package com.example.app.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.MachineSetCount;
import com.example.app.domain.WeightBmi;
import com.example.app.service.MachineSetCountService;
import com.example.app.service.WeightBmiService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeightBmiController {

	private final WeightBmiService weightBmiService;
	private final MachineSetCountService machineSetCountService;

//登録
	//体重の登録
	@GetMapping("/weightRegister")
	public String weightRegister(Model model) throws Exception{
		WeightBmi weightBmi = new WeightBmi();
		//日付
		LocalDate now = LocalDate.now();
		weightBmi.setDate(now);
		weightBmi.setUserWeight(50.5);
		model.addAttribute("weightBmi", weightBmi);
		return "weight";
	}

	//体重、BMIの登録
	@PostMapping("/weightRegister")
	public String bmiRegister(
			Model model,
			@Valid WeightBmi weightBmi,
			Errors errors,
			HttpSession session) throws Exception{


		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		weightBmi.setUserId(1);
//		User user = (User) session.getAttribute("user");
//		weightBmi.setUserId(user.getUserId());


		//BMIを計算
			//BMI ＝ 体重kg ÷ (身長m)2
			//適正体重 ＝ (身長m)2 ×22

		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		double heightCm = 160;
//		double heightCm = user.getUserHeight();   //単位：cm

		double userHeight = heightCm / 100;   //単位：m
		double bmi = weightBmi.getUserWeight() / (userHeight * userHeight);
		double healthyWeight = (userHeight*userHeight) * 22;

		//小数点第二位で切り捨て
		weightBmi.setBmi(Math.floor(bmi * 10) / 10);
		weightBmi.setHealthyWeight(Math.floor(healthyWeight * 10) / 10);


		//日付が同日の場合は登録しない（グラフが重複する）
		//体重・BMIの登録
		weightBmiService.insertWeightBmi(weightBmi);

		model.addAttribute("weightBmi", weightBmi);

//		System.out.println("weightBmiの中身" + weightBmi.getWeightId());
//		System.out.println("weightBmiの中身" + weightBmi.getUserId());
//		System.out.println("weightBmiの中身" + weightBmi.getUserWeight());
//		System.out.println("weightBmiの中身" + weightBmi.getBmi());
//		System.out.println("weightBmiの中身" + weightBmi.getHealthyWeight());
//		System.out.println("weightBmiの中身" + weightBmi.getDate());

		return "weightRegisterDone";
	}


//表示
	//体重・BMI表示
	@GetMapping("/weightShow")
	public String beforeWeightBmi(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	//表形式で体重・BMI表示
		WeightBmi weightBmi = new WeightBmi();

		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		weightBmi.setUserId(1);
//				User user = (User) session.getAttribute("user");
//				weightBmi.setUserId(user.getUserId());

		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!　→→→引数のdateを不要とする
//		LocalDate date = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2024-02-05", LocalDate::from);
//		weightBmi.setDate(date);
//		service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate());
//		System.out.println(service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate()));
//		model.addAttribute("weightBmi", service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate()));

		weightBmiService.getSelectBeforeWeightbmi(weightBmi.getUserId());
		System.out.println(weightBmiService.getSelectBeforeWeightbmi(weightBmi.getUserId()));

		model.addAttribute("weightBmi", weightBmiService.getSelectBeforeWeightbmi(weightBmi.getUserId()));


	//前回のトレーニング重量表示
		int machineCount = machineSetCountService.getCountMachine();
		List<MachineSetCount> beforeCount = new ArrayList<>();
		for(int i = 1; i <= machineCount; i++) {

			beforeCount.add(machineSetCountService.getSelectBefore(i));
			model.addAttribute("beforeCount", beforeCount);
			System.out.println("count.add(machineSetCountService.getSelectBefore(i))" + beforeCount);
			model.addAttribute("size", beforeCount.size());
			System.out.println("beforeCount.size()" + beforeCount.size());
		}

		return "charge/mypage_aramaki";
	}





}
