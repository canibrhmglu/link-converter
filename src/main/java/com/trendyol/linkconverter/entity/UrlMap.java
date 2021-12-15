package com.trendyol.linkconverter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UrlMap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UrlMap {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "request", length = 400, nullable = false)
	private String request;
	
	@Column(name = "response", length = 400, nullable = false)
	private String response;
	
	@Column(name = "createdDate")
	private LocalDateTime createdDate;
}
