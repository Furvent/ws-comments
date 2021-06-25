package fr.eql.comments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.comments.entity.Comment;
import fr.eql.comments.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository repository;

	@Override
	public Comment save(Comment comment) {
		return repository.save(comment);
	}

	@Override
	public List<Comment> getAllWithEntityIdAndEntityType(Long id, String entityType) {
		return repository.findAllByEntityIdAndEntityType(id, entityType);
	}

}
