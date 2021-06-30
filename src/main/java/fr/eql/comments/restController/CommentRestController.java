package fr.eql.comments.restController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import fr.eql.comments.restController.dto.CommentToDisplay;
import fr.eql.comments.restController.dto.CommentsToGet;
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
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/comments")
	public ResponseEntity<?> getComments(@RequestBody CommentsToGet commentsToGet){
		try {
			// Checks
			if (commentsToGet.getEntityId() < 1l) {
				throw new InvalidNewDataPostException("Can't request comments with invalid entityId: " + commentsToGet.getEntityId());
			}
			if (commentsToGet.getEntityType().isEmpty()) {
				throw new InvalidNewDataPostException("Can't request comments with empty entityType");
			}
			
			//Get the comments by ID and entityType and transform them into CommentToDisplay
			List<CommentToDisplay> listCommentsToDisplay = 
					commentService.getAllWithEntityIdAndEntityType(commentsToGet.getEntityId(), commentsToGet.getEntityType())
					.stream().map(comment -> new CommentToDisplay(comment)).collect(Collectors.toList());
			return new ResponseEntity<List<CommentToDisplay>>(listCommentsToDisplay, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
