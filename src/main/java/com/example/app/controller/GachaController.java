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
		
		String content = null;
    if ("count".equals(gachaType)) {
        content = "kg";
    } else if ("weight".equals(gachaType)) {
        content = "回";
    }
    model.addAttribute("content", content);
    
		
		return "gacha";
	}
}
