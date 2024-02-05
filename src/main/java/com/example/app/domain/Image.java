package com.example.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Image {

	private Integer imgId;
	
	private Integer userId;
	
	//@NotBlank
	//@Size(max=300)
	private String imgName;
	
	//@Size(max=300)
	private String memo;
	
	private Date date;

}
