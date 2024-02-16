package com.example.app.controller;

import java.io.File;
import java.io.IOException;
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

	// 画像の個別取得
	// "/show/{date}"→アルバムからshow、"/show"→カレンダーからshow
	@GetMapping({"/show/{date}", "/show"})
	public String show(@PathVariable(required = false) String date, // アルバムをクリックdate
			@RequestParam(name = "date2", required = false) String date2, // カレンダーのパスから引っ張ってくるdate
			HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		// Mapを作成してパラメータをセットする
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", user.getUserId());
//		param.put("date", date);
//		// Mapを引数にしてメソッドを呼び出す
//		Image image = mapper.getImageById(param);
//      個別でデータをとる記述
		List<Image> image  = null;
		if(date != null) {
			// "/show/{date}"→アルバムからshow
			image = mapper.getImageByDate(user.getUserId(), date);
		}else {
			// "/show"→カレンダーからshow
			image = mapper.getImageByDate
					(user.getUserId(), date2);
			
		}
		
		System.out.println(image);
		model.addAttribute("imageList", image);
		return "show";
	}

	// 削除
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		mapper.delete(id);
		return "redirect:/mypage";
	}


	// 記録ページ
	@GetMapping("/record")
	public String add(Model model, HttpSession session) {
		model.addAttribute("image", new Image());
		User user = (User) session.getAttribute("user");
		System.out.println("record->" + user);
		return "record";
	}


	// 新規登録
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
//		return "recordDone";
		return "redirect:/mypage";
	}

//	トレーニング内容個別編集
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Image image = mapper.getImageById(id);
		model.addAttribute("image", image);
		return "record";
	}


	@PostMapping("/edit/{id}")
	public String edit(@RequestParam MultipartFile upload, 
			@Valid Image image, Errors errors,
			HttpSession session,
			@PathVariable Integer id, Model model) throws IllegalStateException, IOException {
		if (errors.hasErrors()) {
			System.out.println("errors");
			model.addAttribute("image", image);
			return "record";
		}
		String imgName = upload.getOriginalFilename();
		User user = (User) session.getAttribute("user");
		image.setUserId(user.getUserId());
		image.setImgId(id);
		image.setImgName(imgName);
		File dest = new File("C:/Users/uploads/" + imgName);
		upload.transferTo(dest);
		mapper.edit(image);
		return "redirect:/mypage";

	}

}
