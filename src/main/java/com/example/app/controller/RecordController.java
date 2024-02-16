package com.example.app.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.example.app.mapper.ImageMapper;
import com.example.app.service.MachineSetCountService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecordController {
	// 筋トレ記録
	private final MachineSetCountService service;
	// 画像
	private final ImageMapper mapper;
	// 1ページあたりの表示件数
	private static final int NUM_PER_PAGE = 6;



@GetMapping("/mypage")
	public String list(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model,
		HttpSession session) throws Exception {
	// ユーザーの画像一覧取得
		User user = (User) session.getAttribute("user");
		List<Image> imgList = mapper.getImageByUserId(user.getUserId());
		System.out.println(imgList);
		// ページング追加のため以下の記述をコメントアウト
		// model.addAttribute("imgList", imgList);
		int offset = NUM_PER_PAGE * (page - 1);
		model.addAttribute("imgList", mapper.selectLimited(offset, NUM_PER_PAGE));

		// 現在のページを更新
		model.addAttribute("page", page);

		// 全体のデータ数取得
		// 小数点で受け取りたいため、キャスト
		double totalNum = (double) mapper.count();
		// 全体のページ数の計算：numPerPageは1ページあたりの表示件数とする
		// Math.ceil 小数点以下を切り上げしてくれる
		int totalPage = (int) Math.ceil((double) totalNum / NUM_PER_PAGE);
		model.addAttribute("totalPage", totalPage);

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
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
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


// 【トレーニング記録詳細ページ】
	// "/show/{date}"→アルバムからshow、"/show"→カレンダーからshow
	@GetMapping({"/show/{date}", "/show"})
	public String showDay(
			@PathVariable(required = false) String date, // アルバムをクリックdate
			@RequestParam(name = "date2", required = false) String date2, // カレンダーのパスから引っ張ってくるdate
			HttpSession session,
			Model model) throws Exception {

	// 画像の個別取得
		User user = (User) session.getAttribute("user");

      // 個別でデータをとる記述
		List<Image> image  = null;
		if(date != null) {
		// アルバムからshow("/show/{date}")
			image = mapper.getImageByDate(user.getUserId(), date);
		}else {
		// カレンダーからshow("/show")
			// 画像
			image = mapper.getImageByDate
					(user.getUserId(), date2);

			//カレンダーから特定の日の筋トレ記録を取得
			// トレーニング記録
			MachineSetCount machineSetCount = new MachineSetCount();
			List<MachineSetCount> getDayData = service.getMachineSetCountDay(date2, user.getUserId());
			machineSetCount.setDate(date2);

			System.out.println("getDayData：" + getDayData);
			model.addAttribute("machineSetCount", getDayData);
		}

		System.out.println(image);
		model.addAttribute("imageList", image);

		return "show";
	}



}

