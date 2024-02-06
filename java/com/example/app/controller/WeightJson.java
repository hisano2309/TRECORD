package com.example.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.WeightBmi;
import com.example.app.service.WeightService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeightJson {

	private final WeightService service;
	
	@GetMapping("/json")
	public List<WeightBmi> list(HttpSession session) throws Exception{
		WeightBmi weightBmi = new WeightBmi();
//		User user = (User) session.getAttribute("user");
//		weightBmi.setUserId(user.getUserId());
		weightBmi.setUserId(1);
		
//		System.out.println("WeightJson　セッションのUserId" + user.getUserId());
		return service.getSelectChart(weightBmi.getUserId());

	}
}
