package fr.eql.comments.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.eql.comments.entity.Comment;
import fr.eql.comments.repository.CommentRepository;

public class CommentServiceTest {
	
	@Mock
	private CommentRepository commentRepository;
	private AutoCloseable autoCloseable;
	private CommentService underTest;
	
	@BeforeEach
	void setUp() {
		autoCloseable = MockitoAnnotations.openMocks(this);
		underTest = new CommentServiceImpl(commentRepository);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
	
	@Test
	void itShouldSave() {
		Comment comment = new Comment(1L, "a new comment", "Lordy", "TEST", LocalDateTime.now());
		underTest.save(comment);
		
		ArgumentCaptor<Comment> commentArgCaptor = ArgumentCaptor.forClass(Comment.class);
		
		verify(commentRepository).save(commentArgCaptor.capture());
		
		Comment capturedComment = commentArgCaptor.getValue();
		assertThat(capturedComment).isEqualTo(comment);
	}
	
	@Test
	void itShouldgiveBackAListOfComments() {
		
		underTest.getAllWithEntityIdAndEntityType(1L, "TEST");
		
		verify(commentRepository).findAllByEntityIdAndEntityType(1L, "TEST");
	}

}
