package com.example.app.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.User;
import com.example.app.mapper.UserMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
private final UserMapper mapper;
	
	//@Autowired
    //private HttpSession session; // HttpSessionを注入
	
	//ユーザー登録
	@GetMapping("/register")
	public String register(Model model) {
		//ビューの方でドメインクラスが使えるように
		//ドメインクラス名 頭を小文字にした名前を指定すると
		//のちのち楽に記述ができる
		model.addAttribute("user" , new User());
		return "register";
	}
	
	@PostMapping("/register")
	// @ModelAttribute("user") User user
	public String register(@RequestParam MultipartFile upload,
			@Valid User user,
			Errors errors,Model model
			) throws IllegalStateException,IOException{
		
		if(errors.hasErrors()) {
			//エラーがあった時の処理
			System.out.println("エラーです");
			// エラーメッセージをモデルに追加
			model.addAttribute("msg", "入力内容にエラーがあります");
			return "register";
		}else {

		if(upload.isEmpty() && (user.getFileName() == null || user.getFileName().isEmpty())) {
			//アップロードが空の場合の処理
			// デフォルトの画像パスを取得する
			//String defaultImagePath = " ";
			user.setFileName(null);

			// ユーザーをデータベースに保存
			mapper.insert(user);
			return "redirect:/user/login";
		} else {
			//ファイル情報の取得と保存
			// ファイルサイズ
			System.out.println(upload.getSize());
			// ファイル種類
			System.out.println(upload.getContentType());
			// ファイル名
			System.out.println(upload.getOriginalFilename());
			
			// ファイル名取得
			String fileName = upload.getOriginalFilename();
			//TODO ★★★ ファイルパスを編集する ★★★
			// 格納場所取得(各々のフォルダ名に変更して下さい)
			//File dest = new File("C:/Users/zd3M02/uploads/" + fileName);
			 File dest = new File("/home/trainee/uploads/" + fileName); //公開サーバー
			
		user.setFileName(fileName);
		upload.transferTo(dest); //フォルダに保存
		mapper.insert(user); //DBに保存
		return "redirect:/user/login";
		}
		}
	}
	
	
	//ログイン認証
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user" , new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid User user, 
			Errors errors, 
			HttpSession session,
			Model model) {
		if(errors.hasErrors()) {
			System.out.println("不備あり");
			return "login";
		
		}
		System.out.println("loginUser->"+user);
		// ログインIDからDBへ問い合わせ
		User foundUser = mapper.findByLoginId(user.getLoginId());
		System.out.println("foundUser->"+foundUser);
		if(foundUser != null) {
			//データが戻ってきたらログインIDが登録されているので次はパスワードチェック
			if(foundUser.getLoginPass().equals(user.getLoginPass())) {
				// ログイン成功時にセッションにユーザー情報を保存
				session.setAttribute("user",  foundUser);
				System.out.println("user->"+session.getAttribute("user"));
				
				return "redirect:/mypage";
				
			}else {
				// パスワードが一致しない場合
	            model.addAttribute("loginError", "Invalid password");
				return "login";
				
			}
		} else {
			// ログインIDが見つからない場合
	        model.addAttribute("loginError", "Invalid login ID");
			return "login";
		}
			
	}

	//ユーザー個別情報取得
	@GetMapping("/setting/{id}")
    public String getUserById(@PathVariable Integer id, HttpSession session, Model model) {
       
            User user = mapper.selectById(id);
            model.addAttribute("user",user);
            return "setting";
        }
	
	
	
	//ログイン確認機能(余力があったら)
//	@GetMapping("/mypage")
//	public String getUser(Model model,  HttpSession session) {
//		if(session.getAttribute("user")){
//		   model.addAttribute("user", session.getAttribute("user"));
//     }else{}
//		return "mypage";
//	}
	
	//ログアウト
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}

	//削除
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		System.out.println("id->"+id);
        mapper.delete(id);
        return "redirect:/user/register"; // 削除後のリダイレクト先を指定
	}
        
	//更新
	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, HttpSession session,Model model) {
	  User user = mapper.selectById(id);
	  model.addAttribute("user",user);
		return "/register";	
	}
	
	@PostMapping("/edit/{id}")
	public String editPost(
			@RequestParam(required = false) MultipartFile upload,
			@Valid User user, 
			Errors errors,
			@PathVariable Integer id, 
			HttpSession session, 
			Model model
			) throws IllegalStateException, IOException {
		if(errors.hasErrors()) {
			//エラーがあった時の処理
			System.out.println("エラーです");
			model.addAttribute("user", user);
			return "register";
		}
		user.setUserId(id);
		// 画像がアップロードされたかどうかを確認
	    if(upload != null && !upload.isEmpty()) {
	        // 画像がアップロードされた場合の処理
	    	String fileName = upload.getOriginalFilename();
			user.setFileName(fileName);
			//TODO ★★★ ファイルパスを編集する ★★★
			//(各々のフォルダ名に変更して下さい)
			//File dest = new File("C:/Users/zd3M02/uploads/" + fileName);
			 File dest = new File("/home/trainee/uploads/" + fileName); //公開サーバー
			upload.transferTo(dest);
	    } else {
	        // 画像がアップロードされなかった場合の処理
	        // 既存のFileNameをそのまま保持する
	        User existingUser = mapper.selectById(id);
	        user.setFileName(existingUser.getFileName());
	    }
		System.out.println("User object: " + user);
		mapper.update(user);
		session.setAttribute("user", user);
		return "redirect:/user/setting/"+id;
		}
	}
	
