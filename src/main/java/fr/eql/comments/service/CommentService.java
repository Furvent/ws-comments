package fr.eql.comments.service;

import java.util.List;

import fr.eql.comments.entity.Comment;

public interface CommentService {
	Comment save(Comment comment);
	List<Comment> getAllWithEntityIdAndEntityType(Long id, String entityType);
}
