package com.example.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	@Size(min=6, max=20)
	private String loginPass;
	private Float userHeight;
	
	private String fileName;
	

}
