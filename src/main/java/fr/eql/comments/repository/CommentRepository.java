package fr.eql.comments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.comments.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	public List<Comment> findAllByEntityIdAndEntityType(Long entityId, String entityType);
}
