package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	
	@NotBlank(message="必須項目です")
	private String loginId;
	
	@NotBlank(message="必須項目です")
	@Size(min=6, max=20, message="{min}～{max}文字で入力してください")
	private String loginPass;
	private Float userHeight;
	
	private String fileName;
	

}
