package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sessionId;
	private String authkey;
	private String email;
	private LocalDateTime sessionTime;

}
