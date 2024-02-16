package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Gacha;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GachaController {
	
	@RequestMapping("/gacha")
	public String GachaNumber(Model model, @RequestParam String gachaType) {
		Integer number = Gacha.GachaNumber();
		model.addAttribute("number", number);
		
		String unit = null;
		String comment = null;
    if ("count".equals(gachaType)) {
    	unit = "回";
    	comment = "理想の体に近づける！";
    } else if ("weight".equals(gachaType)) {
    	unit = "kg";
    	comment = "君ならできる！！";
    }
    model.addAttribute("unit", unit);
    model.addAttribute("comment", comment);
    
		
		return "gacha";
	}
}
