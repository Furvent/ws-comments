package fr.eql.comments.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import fr.eql.comments.WebServiceCommentsApplication;
import fr.eql.comments.entity.Comment;
import fr.eql.comments.repository.CommentRepository;
import fr.eql.comments.restController.dto.CommentToDisplay;
import fr.eql.comments.restController.dto.CommentsToGet;
import fr.eql.comments.restController.dto.NewComment;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WebServiceCommentsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class WSCommentIntegration {
	
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private CommentRepository commentRepository;
    
	@AfterEach
	void cleanDbAfterTest() {
		commentRepository.deleteAll();
	}
	
	@Test
	void checkIfCreate() {
		String url = "/public/create/";
		NewComment newComment = new NewComment();
		newComment.setAuthor("FirstCommenter");
		newComment.setEntityId(1L);
		newComment.setEntityType("TEST");
		newComment.setText("cmt1Group");
		HttpEntity<NewComment> request = new HttpEntity<>(newComment);
		restTemplate.postForObject(url, request, ResponseEntity.class);
		
		assertTrue(commentRepository.findById(1L).get().getAuthor().equals("FirstCommenter"));
	}
	
	//@Disabled
	@Test
	void checkIfNotCreate() {
		String url = "/public/create/";
		NewComment newComment = new NewComment();
		newComment.setAuthor("FirstCommenter");
		newComment.setEntityId(1L);
		newComment.setEntityType("");
		newComment.setText("cmt1Group");
		HttpEntity<NewComment> request = new HttpEntity<>(newComment);
		restTemplate.postForObject(url, request, ResponseEntity.class);
		Comment comment = commentRepository.findById(1L).orElse(null);
		assertTrue(comment == null);
	}
	

	@Test
	void checkIfGetAllComments() {
		commentRepository.save(new Comment(1L, "A text", "Author1", "TEST", LocalDateTime.now()));
		commentRepository.save(new Comment(1L, "Another text", "Author2", "TEST", LocalDateTime.now()));
		commentRepository.save(new Comment(1L, "Lipsum", "AuthorDrei", "TEST", LocalDateTime.now()));
		
		String urlGet = "/public/comments/";
		CommentsToGet commentsToGet = new CommentsToGet(1L, "TEST");
		HttpEntity<CommentsToGet> request = new HttpEntity<>(commentsToGet);
		CommentToDisplay[] preResult = restTemplate.postForObject(urlGet, request, CommentToDisplay[].class);
		assertTrue(preResult.length == 3);
	}
	
	@Test
	void checkIfGetNoComments() {
		commentRepository.save(new Comment(1L, "A text", "Author1", "TEST", LocalDateTime.now()));
		commentRepository.save(new Comment(1L, "Another text", "Author2", "TEST", LocalDateTime.now()));
		commentRepository.save(new Comment(1L, "Lipsum", "AuthorDrei", "TEST", LocalDateTime.now()));
		
		String urlGet = "/public/comments/";
		CommentsToGet commentsToGet = new CommentsToGet(1L, "NOTEST");
		HttpEntity<CommentsToGet> request = new HttpEntity<>(commentsToGet);
		CommentToDisplay[] preResult = restTemplate.postForObject(urlGet, request, CommentToDisplay[].class);
		assertTrue(preResult.length == 0);
	}

}
