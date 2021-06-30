package fr.eql.comments.restController.dto;

import java.time.LocalDateTime;

import fr.eql.comments.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentToDisplay {
	private String text, author;
	private LocalDateTime creationDate;

	public CommentToDisplay(Comment comment) {
		this.text = comment.getText();
		this.author = comment.getAuthor();
		this.creationDate = comment.getCreationDate();
	}
	
	public CommentToDisplay() {
		
	}

}
