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
import com.example.app.mapper.RecordMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class RecordController {

	private final RecordMapper mapper;

	// ユーザーの画像一覧取得
	@GetMapping("/mypage")
	public String list(Model model) {
		List<Image> imgList = mapper.getImageByUserId(1);
		model.addAttribute("imgList", imgList);
		return "mypage";
	}

	// 画像の個別取得
	@GetMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		Image image = mapper.getImageById(id);
		model.addAttribute("image", image);
		return "show";
	}

	// 削除
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		mapper.delete(id);
		return "redirect:/record";
	}

	// 記録ページ
	@GetMapping("/record")
	public String add(Model model) {
		model.addAttribute("image", new Image());
		return "record";
	}

	// 新規登録
	@PostMapping("/record")
	public String add(@RequestParam MultipartFile upload, @RequestParam String memo, Model model)
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
			File dest = new File("C:/Users/zd3M06/uploads/" + imgName);
			// File dest = new File("C:/uploads/" + imgName);
			Image image = new Image();
			image.setImgName(imgName);
			image.setMemo(memo);
			upload.transferTo(dest);// フォルダに保存
			mapper.add(image);// DBに保存

			// TODO ここにレベルアップ機能を実装

		}
		return "redirect:/mypage";
	}

	// トレーニング内容個別編集
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) {
		Image image = mapper.getImageById(id);
		model.addAttribute("image", image);
		return "record";
	}

	@PostMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, @Valid Image image, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("image", image);
			return "record";
		}
		image.setImgId(id);
		mapper.edit(image);
		return "redirect:/mypage";

	}

}
