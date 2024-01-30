package com.example.app.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Image {

	private Integer imgId;
	
	//@NotBlank
	//@Size(max=300)
	private String imgName;
	
	//@Size(max=300)
	private String memo;
	
	private LocalDateTime date;

}
