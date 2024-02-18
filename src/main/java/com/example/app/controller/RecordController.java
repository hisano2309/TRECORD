package com.example.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.domain.Image;
import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;
import com.example.app.domain.User;
import com.example.app.domain.WeightBmi;
import com.example.app.mapper.ImageMapper;
import com.example.app.service.MachineSetCountService;
import com.example.app.service.WeightBmiService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordController {
	// 筋トレ記録
	private final MachineSetCountService machineSetCountService;
	private final WeightBmiService weightBmiService;
	// 画像
	private final ImageMapper mapper;
	// 1ページあたりの表示件数
	private static final int NUM_PER_PAGE = 6;



@GetMapping("/mypage")
	public String list(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model,
		HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");

	//表形式で体重・BMI表示
		WeightBmi weightBmi = new WeightBmi();
//		weightBmi.setUserId(user.getUserId());
	//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		weightBmi.setUserId(1);

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
		return "mypage";
	}


// 【記録ページ】
	@GetMapping("/record")
	public String add(Model model, HttpSession session) throws Exception {

	// 画像
		model.addAttribute("image", new Image());
		User user = (User) session.getAttribute("user");
		System.out.println("record->" + user);

	// トレーニング記録
		MachineSetCount machineSetCount = new MachineSetCount();

		//ユーザーID
		machineSetCount.setUserId(user.getUserId());
		System.out.println("user.getUserId()" + (user.getUserId()));
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
		// weightBmi.setUserId(1);

		//日付
		LocalDate now = LocalDate.now();
		machineSetCount.setDate(now);
		System.out.println("今日の日付：" + machineSetCount.getDate());

		//筋トレマシン一覧表示
		List<Machine> machine = machineSetCountService.getSelectMachine();
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
			machineSetCountService.addMachineSetCount(machineSetCount);

		return "recordDone";
	}


//// 【トレーニング記録詳細ページ】
//	// "/show/{date}"→アルバムからshow、"/show"→カレンダーからshow
//	@GetMapping({"/show/{date}", "/show"})
//	public String showDay(
//			@PathVariable(required = false) String date, // アルバムをクリックdate
//			@RequestParam(name = "date2", required = false) String date2, // カレンダーのパスから引っ張ってくるdate
//			HttpSession session,
//			Model model) throws Exception {
//
//	// 画像の個別取得
//		User user = (User) session.getAttribute("user");
//
//      // 個別でデータをとる記述
//		List<Image> image  = null;
//		if(date != null) {
//		// アルバムからshow("/show/{date}")
//			image = mapper.getImageByDate(user.getUserId(), date);
//		}else {
//		// カレンダーからshow("/show")
//			// 画像
//			image = mapper.getImageByDate
//					(user.getUserId(), date2);
//
//			//カレンダーから特定の日の筋トレ記録を取得
//			// トレーニング記録
//			MachineSetCount machineSetCount = new MachineSetCount();
//			LocalDate convertToLocalDate = machineSetCountService.convertToLocalDate(date2, "yyyy-MM-dd");
//			List<MachineSetCount> getDayData = machineSetCountService.getMachineSetCountDay(convertToLocalDate, user.getUserId());
//			machineSetCount.setDate(convertToLocalDate);
//
//			System.out.println("getDayData：" + getDayData);
//			model.addAttribute("machineSetCount", getDayData);
//		}
//
//		System.out.println(image);
//		model.addAttribute("imageList", image);
//
//		return "show";
//	}



}

