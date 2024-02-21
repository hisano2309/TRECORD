package com.example.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.domain.User;
import com.example.app.domain.WeightBmi;
import com.example.app.service.WeightBmiService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeightBmiJson {

	private final WeightBmiService service;

	@GetMapping("/weightBmiJson")
	public List<WeightBmi> list(HttpSession session) throws Exception{

		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		WeightBmi weightBmi = new WeightBmi();
		User user = (User) session.getAttribute("user");
		weightBmi.setUserId(user.getUserId());
//		weightBmi.setUserId(1);

		return service.getSelectChart(weightBmi.getUserId());

	}
}
