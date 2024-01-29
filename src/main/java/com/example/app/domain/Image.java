package com.example.app.domain;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Image {

	@NotBlank
	private Integer imgId;
	
	@NotBlank
	@Size(max=300)
	private String imgName;
	
	@Size(max=300)
	private String memo;
	
	@NotNull
	private LocalDateTime date;

}
