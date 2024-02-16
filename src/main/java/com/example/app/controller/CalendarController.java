package com.example.app.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.mapper.ImageMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {

	private final ImageMapper mapper;

	@GetMapping("calendar")
	public String calend(Model model) {
//		List<Integer> list = service.CreateCalendar(1);
//		System.out.println(list);

		return "mypage";
	}

	@PostMapping("calendar")
	public String calend(@RequestParam LocalDate date) {
//		public String calend(@RequestParam LocalDate date) {
//		System.out.println("date->"+date);
		System.out.println("calendar->" + date);
//		System.out.println("calendar.getDate->" + calendar.getDate());
//		List<Image> image = mapper.selectByDate(calendar.getDate());
//		System.out.println(image);
		return "mypage";

	}

}
