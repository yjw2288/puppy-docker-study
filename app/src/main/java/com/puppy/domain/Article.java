package com.puppy.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(columnDefinition = "text")
	private String content;
}
