package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
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
	public String register(@Valid User user,
			Errors errors

			) {		
		
		
		if(errors.hasErrors()) {
			//エラーがあった時の処理
			System.out.println("エラーです");
			return "register";
		}else {
		System.out.println(user);

		mapper.insert(user);
		return "redirect:/user/login";
		}
	}
	
	
	//ログイン認証
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user" , new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid User user, Errors errors, HttpSession session,Model model) {
		if(errors.hasErrors()) {
			System.out.println("不備あり");
			return "login";
		
		}
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
			@Valid User user, Errors errors,
			@PathVariable Integer id, 
			HttpSession session, 
			Model model
			) {
		if(errors.hasErrors()) {
			//エラーがあった時の処理
			System.out.println("エラーです");
			return "register";
		}else {
			 // フォームから受け取ったユーザー情報を使用して更新処理を行う
			user.setUserId(id);
		System.out.println(user);
		mapper.update(user);
		return "redirect:/user/setting/"+id;
		}
	}
}
	
