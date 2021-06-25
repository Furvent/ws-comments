package fr.eql.comments.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long entityId;

	@Column(nullable = false)
	private String text, author;

	@Column(nullable = false)
	private String entityType;

	@Column(nullable = false)
	private LocalDateTime creationDate;

	public Comment(Long entityId, String text, String author, String entityType, LocalDateTime creationDate) {
		this.entityId = entityId;
		this.text = text;
		this.author = author;
		this.entityType = entityType;
		this.creationDate = creationDate;
	}

}
