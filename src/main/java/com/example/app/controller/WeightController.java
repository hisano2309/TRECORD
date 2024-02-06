package com.example.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.WeightBmi;
import com.example.app.service.WeightService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeightController {

	private final WeightService service;

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

		//体重・BMIの登録
		service.insertWeightBmi(weightBmi);

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
	@GetMapping("/weighShow")
	public String beforeWeightBmi(Model model) throws Exception {
		WeightBmi weightBmi = new WeightBmi();
		
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		weightBmi.setUserId(1);
//				User user = (User) session.getAttribute("user");
//				weightBmi.setUserId(user.getUserId());
				
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		LocalDate date = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse("2024-02-05", LocalDate::from);
		weightBmi.setDate(date);
		
		service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate());
		System.out.println(service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate()));
				
		model.addAttribute("weightBmi", service.getSelectBeforeWeightbmi(weightBmi.getUserId(), weightBmi.getDate()));
		
		return "charge/mypage_aramaki";
	}





}
