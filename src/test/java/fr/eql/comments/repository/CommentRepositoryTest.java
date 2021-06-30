package fr.eql.comments.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.eql.comments.entity.Comment;

@DataJpaTest
public class CommentRepositoryTest {
	
	@Autowired
	CommentRepository underTest;
	
	@AfterEach
	void cleanDbAfterTest() {
		underTest.deleteAll();
	}
	
	@Test
	public void itShouldFindThreeComments() {
		Long testEntityId = 1L;
		String testEntityType = "GROUP";
		Comment comment1 = new Comment(testEntityId, "cmt1Group", "FirstCommenter", testEntityType, LocalDateTime.now().minusMinutes(6));
		Comment comment2 = new Comment(testEntityId, "cmt2Group", "SecondCommenter", testEntityType, LocalDateTime.now().minusMinutes(4));
		Comment comment3 = new Comment(testEntityId, "cmt2Group", "SecondCommenter", testEntityType, LocalDateTime.now().minusMinutes(2));
		underTest.save(comment1);
		underTest.save(comment2);
		underTest.save(comment3);
		List<Comment> testedCommentList = underTest.findAllByEntityIdAndEntityType(testEntityId, testEntityType);
		
		assertThat(testedCommentList.size() == 3).isTrue();

	}
	
	@Test
	public void itShouldNotFindThreeComments() {
		Long testEntityId = 1L;
		String testEntityType = "TICKET";
		Comment comment1T = new Comment(testEntityId, "cmt1Ticket", "FirstCommenter", testEntityType, LocalDateTime.now().minusMinutes(6));
		Comment comment2T = new Comment(testEntityId, "cmt2Ticket", "SecondCommenter", testEntityType, LocalDateTime.now().minusMinutes(4));
		underTest.save(comment1T);
		underTest.save(comment2T);
		List<Comment> testedCommentList = underTest.findAllByEntityIdAndEntityType(testEntityId, testEntityType);
		
		assertThat(testedCommentList.size() == 3).isFalse();
	}
	
	@Test
	public void itShouldFindZeroComments() {
		Long testEntityId = 1L;
		String testEntityType = "TEST";
		List<Comment> testedCommentList = underTest.findAllByEntityIdAndEntityType(testEntityId, testEntityType);
		assertThat(testedCommentList.size() == 0).isTrue();
	}
	
	
}
