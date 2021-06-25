package fr.eql.comments.restController;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eql.comments.entity.Comment;
import fr.eql.comments.exception.InvalidNewDataPostException;
import fr.eql.comments.restController.dto.NewComment;
import fr.eql.comments.service.CommentService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/public", headers = "Accept=application/json")
public class CommentRestController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createComment(@RequestBody NewComment newComment) {
		try {
			// Checks
			if (newComment.getEntityId() < 1l) {
				throw new InvalidNewDataPostException("Can't create comment with invalid entityId: " + newComment.getEntityId());
			}
			if (newComment.getEntityType().isEmpty()) {
				throw new InvalidNewDataPostException("Can't create comment with empty entityType");
			}
			if (newComment.getAuthor().isEmpty()) {
				throw new InvalidNewDataPostException("Can't create comment with empty author's name");
			}
			if (newComment.getText().isEmpty()) {
				throw new InvalidNewDataPostException("Can't create comment with empty text");
			}
			// We can create a new comment
			Comment newCommentEntity = new Comment();
			BeanUtils.copyProperties(newComment, newCommentEntity);
			newCommentEntity.setCreationDate(LocalDateTime.now());
			commentService.save(newCommentEntity);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
