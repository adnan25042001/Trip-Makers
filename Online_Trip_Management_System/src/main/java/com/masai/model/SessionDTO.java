package com.masai.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SessionDTO {
	
	private Integer authkey;
	private LocalDateTime sessionTime;
	
}
