package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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


// 【マイページ表示】
@GetMapping("/mypage")
	public String mypage(
			@RequestParam(name = "page", defaultValue = "1") Integer page, Model model,
			HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		System.out.println("user.getUserId()" + user.getUserId());
		//!!!!!!!!!!!ダミーデータ!!!!!!!!!!!!!!!!!!!!!!!!
//		WeightBmi weightBmi = new WeightBmi();
//		weightBmi.setUserId(1);

	// 画像一覧
		List<Image> imgList = mapper.getImageByUserId(user.getUserId());
		System.out.println(imgList);
		// ページング追加のため以下の記述をコメントアウト
		// model.addAttribute("imgList", imgList);
		int offset = NUM_PER_PAGE * (page - 1);
		model.addAttribute("imgList", mapper.selectLimited(offset, NUM_PER_PAGE, user.getUserId()));

		// 現在のページを更新
		model.addAttribute("page", page);

		// 全体のデータ数取得
		// 小数点で受け取りたいため、キャスト
		double totalNum = (double) mapper.count();
		// 全体のページ数の計算：numPerPageは1ページあたりの表示件数とする
		// Math.ceil 小数点以下を切り上げしてくれる
		int totalPage = (int) Math.ceil((double) totalNum / NUM_PER_PAGE);
		model.addAttribute("totalPage", totalPage);

		// 一番古い日付でuploadした画像を取得
		Image oldestImage = mapper.getOldestImage(user.getUserId());
		model.addAttribute("oldestImage", oldestImage);

		// 一番新しい日付でuploadした画像を取得
		Image newestImage = mapper.getNewestImage(user.getUserId());
		model.addAttribute("newestImage", newestImage);
		
		
	
	// IDでweightDB、machineSetCountに1つ以上記録があるか確認
		if(machineSetCountService.checkId(user.getUserId()).size() != 0) {
			model.addAttribute("machineSetCountCheckIdOver1", "aaa");
			System.out.println("aiueo");
		}
		if(weightBmiService.CheckId(user.getUserId()).size() != 0) {
			model.addAttribute("weightCheckIdOver1", "aaa");
			System.out.println("machineSetCountService.checkId(weightBmi.getUserId())aaa" + machineSetCountService.checkId(user.getUserId()));
		}
		
	// IDでweightDB、machineSetCountに2つ以上記録があるか確認
		if(machineSetCountService.checkId(user.getUserId()).size() >= 2) {
			model.addAttribute("machineSetCountCheckIdOver2", "aaa");
			System.out.println("aiueo");
		}
		if(weightBmiService.CheckId(user.getUserId()).size() >= 2) {
			model.addAttribute("weightCheckIdOver2", "aaa");
			System.out.println("machineSetCountService.checkId(weightBmi.getUserId())aaa" + machineSetCountService.checkId(user.getUserId()));
		}

		
	//表形式で体重・BMI表示
//		weightBmi.setUserId(user.getUserId());

		weightBmiService.getSelectBeforeWeightbmi(user.getUserId());
		System.out.println(weightBmiService.getSelectBeforeWeightbmi(user.getUserId()));

		model.addAttribute("weightBmi", weightBmiService.getSelectBeforeWeightbmi(user.getUserId()));


	//前回のトレーニング重量表示
		int machineCount = machineSetCountService.getCountMachine();
		List<MachineSetCount> beforeCount = new ArrayList<>();
		for(int i = 1; i <= machineCount; i++) {

			beforeCount.add(machineSetCountService.getSelectBefore(user.getUserId(), i));
			model.addAttribute("beforeCount", beforeCount);
			System.out.println("count.add(machineSetCountService.getSelectBefore(i))" + beforeCount);
			model.addAttribute("size", beforeCount.size());
			System.out.println("beforeCount.size()" + beforeCount.size());
		}
		return "mypage";
	}


// 【トレーニング記録】
	@GetMapping("/record")
	public String registerRecord(Model model, HttpSession session) throws Exception {

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
	public String registerRecord(
			@Valid MachineSetCount machineSetCount,
			Errors errors, Model model, HttpSession session)
			throws Exception {
	// トレーニング記録
			System.out.println(machineSetCount);
			machineSetCountService.addMachineSetCount(machineSetCount);

		return "recordDone";
	}


//// 【トレーニング記録詳細ページ】
	// "/show/{date}"→アルバムからshow、"/show"→カレンダーからshow
	@GetMapping({"/show/{pictureDate}", "/show"})
	public String showDay(
			@PathVariable(required = false) String pictureDate, // アルバムをクリックdate
			@RequestParam(name = "calendarDate", required = false) String calendarDate, // カレンダーのパスから引っ張ってくるdate
			HttpSession session,
			Model model) throws Exception {

		User user = (User) session.getAttribute("user");

	// 画像・記録情報の個別取得
      // 個別でデータをとる記述
		List<Image> image  = null;
		
		if(pictureDate != null) {
	// アルバムからshow("/show/{pictureDate}")
			
			image = mapper.getImageByDate(user.getUserId(), pictureDate);
		}else {
	// カレンダーからshow("/show")
			System.out.println("1");
		// 画像
			image = mapper.getImageByDate(user.getUserId(), calendarDate);
			System.out.println("2"+image);
		//カレンダーから特定の日の筋トレ記録を取得
			// トレーニング記録
//			MachineSetCount machineSetCount = new MachineSetCount();
//			System.out.println("3" + calendarDate);
//			Date convertToDate = machineSetCountService.convertToLocalDate(calendarDate);
//			System.out.println("4convertToLocalDate" + convertToDate);
			
			// DB検索時はString型とDate型どちらでも良い。返り値はDate型にする必要がある（DBの型とdomainの型はDate）。
			List<MachineSetCount> getDayData = machineSetCountService.getMachineSetCountDay(calendarDate, user.getUserId());
//			machineSetCount.setDate(convertToDate);

			System.out.println("getDayData：" + getDayData);
			model.addAttribute("machineSetCount", getDayData);
		}

		System.out.println(image);
		model.addAttribute("imageList", image);

		return "show";
	}
	
//	トレーニング内容個別編集
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Image image = mapper.getImageById(id);
		model.addAttribute("image", image);
		return "recordEdit";
	}

	@PostMapping("/edit/{id}")
	public String edit(@RequestParam MultipartFile upload, @Valid Image image, Errors errors, HttpSession session,
			@PathVariable Integer id, Model model) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			System.out.println("errors");
			model.addAttribute("image", image);
			return "recordEdit";
		}
		String imgName = upload.getOriginalFilename();
		User user = (User) session.getAttribute("user");
		image.setUserId(user.getUserId());
		image.setImgId(id);
		image.setImgName(imgName);
	/////// ★★★格納場所取得(各々のフォルダ名に変更して下さい)★★★ ///////
		File dest = new File("C:/Users/uploads/" + imgName);
		upload.transferTo(dest);
		mapper.edit(image);
		return "redirect:/mypage";

	}
	
	// 削除
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		mapper.delete(id);
		return "redirect:/mypage";
	}


// 【ボディコンディションを登録】
	@GetMapping("/bodyCondition")
	public String registerBodyCondition(
			Model model, HttpSession session) throws Exception{
		
	// 画像
		model.addAttribute("image", new Image());
		User user = (User) session.getAttribute("user");
		System.out.println("record->" + user);
		

	// 体重を登録
		WeightBmi weightBmi = new WeightBmi();
		//日付(初期値)
		LocalDate now = LocalDate.now();
		weightBmi.setDate(now);
		weightBmi.setUserWeight(50.5);
		model.addAttribute("weightBmi", weightBmi);

		return "bodyCondition";
	}

	@PostMapping("/bodyCondition")
	public String registerBodyCondition(
			@RequestParam MultipartFile upload, 
			@RequestParam String memo,
			Model model,
			@Valid WeightBmi weightBmi,
			Errors errors,
			HttpSession session) throws IllegalStateException, IOException, Exception{
		
		User user = (User) session.getAttribute("user");
		System.out.println("weightBmi.getDate()->"+weightBmi.getDate());
		
	//画像
		if (upload.isEmpty()) {
			model.addAttribute("msg", "画像が選択してください");
			return "record";

		} else {
			// ファイルサイズ
			System.out.println(upload.getSize());
			// ファイル種類
			System.out.println(upload.getContentType());
			// ファイル名
			System.out.println(upload.getOriginalFilename());
			// ファイル名取得
			String imgName = upload.getOriginalFilename();
	/////// ★★★格納場所取得(各々のフォルダ名に変更して下さい)★★★ ///////
			File dest = new File("C:/Users/uploads/" + imgName);
			// File dest = new File("C:/uploads/" + imgName);
			Image image = new Image();
			image.setUserId(user.getUserId());
			image.setImgName(imgName);
			image.setMemo(memo);
			
			image.setDate(weightBmi.getDate());
			upload.transferTo(dest);// フォルダに保存
			mapper.add(image);// DBに保存

			// TODO ここにレベルアップ機能を実装
			// TODO 済み画像表示機能実装
		}
		
	// 体重
		weightBmi.setUserId(user.getUserId());

	//BMIを計算
		//BMI ＝ 体重kg ÷ (身長m)2
		//適正体重 ＝ (身長m)2 ×22

		double heightCm = 160;  //単位：cm

		double userHeight = heightCm / 100;   //単位：m
		double bmi = weightBmi.getUserWeight() / (userHeight * userHeight);
		//適正体重
		double healthyWeight = (userHeight*userHeight) * 22;
		//美容体重
		double beautyWeight = (userHeight*userHeight) * 20;
		//標準BMI（22）
		double healthyBmi = 22;

		//小数点第二位で切り捨て
		weightBmi.setBmi(Math.floor(bmi * 10) / 10);
		weightBmi.setHealthyWeight(Math.floor(healthyWeight * 10) / 10);
		weightBmi.setBeautyWeight(Math.floor(beautyWeight * 10) / 10);
		weightBmi.setHealthyBmi(healthyBmi);

		//日付が同日の場合は登録しない（グラフが重複する）
	//体重・BMIの登録
		weightBmiService.insertWeightBmi(weightBmi);

		model.addAttribute("weightBmi", weightBmi);

		return "redirect:/mypage";
	}



}

