package com.example.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.Image;
import com.example.app.domain.User;
import com.example.app.mapper.ImageMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ImageController {

	private final ImageMapper mapper;

	// 1ページあたりの表示件数
	private static final int NUM_PER_PAGE = 6;

	// ユーザーの画像一覧取得
	@GetMapping("/mypage")
	public String list(@RequestParam(name = "page", defaultValue = "1") Integer page, Model model,
			HttpSession session) {
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

//	// 画像の個別取得
	@GetMapping("/show/{date}")
	public String show(@PathVariable("date") String date,
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		// Mapを作成してパラメータをセットする
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", user.getUserId());
//		param.put("date", date);
//		// Mapを引数にしてメソッドを呼び出す
//		Image image = mapper.getImageById(param);
//      個別でデータをとる記述
		List<Image> image = mapper.getImageById(user.getUserId(), date);
		System.out.println(image);
		model.addAttribute("imageList", image);
		return "show";
	}

//	// 削除
	@GetMapping("/delete/{date}")
	public String delete(@PathVariable Integer id, 
			@PathVariable("date") String date,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", id);
//		param.put("userId", user.getUserId());
//		param.put("date", date);
//		mapper.delete(param);
		mapper.delete(user.getUserId(), date);
		return "redirect:/record";
	}

//
//	// 記録ページ
	@GetMapping("/record")
	public String add(Model model, HttpSession session) {
		model.addAttribute("image", new Image());
		User user = (User) session.getAttribute("user");
		System.out.println("record->" + user);
		return "record";
	}

//
//	// 新規登録
	@PostMapping("/record")
	public String add(@RequestParam MultipartFile upload, @RequestParam String memo, Model model, HttpSession session)
			throws IllegalStateException, IOException {
		if (upload.isEmpty()) {
			model.addAttribute("msg", "画像が選択されていません");
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
			// 格納場所取得(各々のフォルダ名に変更して下さい)
			File dest = new File("C:/Users/uploads/" + imgName);
			// File dest = new File("C:/uploads/" + imgName);
			Image image = new Image();
			User user = (User) session.getAttribute("user");
			image.setUserId(user.getUserId());
			image.setImgName(imgName);
			image.setMemo(memo);
			upload.transferTo(dest);// フォルダに保存
			mapper.add(image);// DBに保存

			// TODO ここにレベルアップ機能を実装
			// TODO 済み画像表示機能実装

		}
		return "redirect:/mypage";
	}

//
//	// トレーニング内容個別編集
	@GetMapping("/edit/{date}")
	public String editGet(@PathVariable("date") String date,
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		// Mapを作成してパラメータをセットする
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", user.getUserId());
//		param.put("date", date);
//		Image image = mapper.getImageById(param);
		List<Image> image = mapper.getImageById(user.getUserId(), date);
		model.addAttribute("image", image);
		return "record";
	}

//
	@PostMapping("/edit/{date}")
	public String edit(@RequestParam MultipartFile upload, 
			@Valid Image image, Errors errors,
			HttpSession session,
			@PathVariable Date date, Model model) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			System.out.println("errors");
			model.addAttribute("image", image);
			return "record";
		}
		String imgName = upload.getOriginalFilename();
		User user = (User) session.getAttribute("user");
		image.setUserId(user.getUserId());
		image.setImgId(image.getImgId());
		image.setImgName(imgName);
		File dest = new File("C:/Users/uploads/" + imgName);
		upload.transferTo(dest);
//		Map<String, Object> param = new HashMap<>();
//		param.put("imgId", image.getImgId());
//		param.put("imgName", image.getImgName());
//		param.put("memo", image.getMemo());
//		mapper.edit(param);
		mapper.edit(image);
		return "redirect:/mypage";

	}

}
