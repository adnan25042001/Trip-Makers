package com.masai.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessionDto {
	
	private String authkey;
	private LocalDateTime sessionTime;
	
}
