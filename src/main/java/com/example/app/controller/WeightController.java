package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.service.WeightService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WeightController {

	private final WeightService Service;

//登録
	//体重の登録
	@GetMapping("/weightRegister")
	public String weightRegister(Model model) throws Exception{

		return "weight";
	}

	//BMIの登録
	@PostMapping("/weightRegister")
	public String bmiRegister(Model model) throws Exception{

		return "weightRegisterDone";
	}


//表示
	//体重表示
//	@GetMapping(/weighShow)





}
