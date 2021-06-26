package fr.eql.comments.init;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import fr.eql.comments.entity.Comment;
import fr.eql.comments.service.CommentService;

@Profile("initData") //code ci-dessus activé que si le profile "initData" est choisi (parmis d'autres)
//au démarrage d'un test ou de l'application
@Component
public class InitDataSet {
	
	@Autowired
	CommentService commentService;
	
	@PostConstruct
	public void initDemoData() {
		Comment comment1Id1TypeGROUP = new Comment(1L, "This is a comment for entity 1 and Type GROUP", "Billy", "GROUP", LocalDateTime.of(2021, 4, 14, 15, 10));
		Comment comment2Id1TypeGROUP = new Comment(1L, "This is another for entity 1 and Type GROUP", "Bobby", "GROUP", LocalDateTime.of(2021, 4, 14, 16, 10));
		
		Comment comment1Id2TypeGROUP = new Comment(2L, "This is a comment for entity 2 and Type GROUP", "Mimi", "GROUP", LocalDateTime.of(2021, 4, 14, 15, 15));
		Comment comment2Id2TypeGROUP = new Comment(2L, "This is another for entity 2 and Type GROUP", "Zazy", "GROUP", LocalDateTime.of(2021, 4, 14, 16, 30));
		
		Comment comment1Id1TypeTICKET = new Comment(1L, "This is a comment for entity 1 and Type TICKET", "Corbeille", "TICKET", LocalDateTime.of(2021, 4, 15, 15, 10));
		Comment comment2Id2TypeTICKET = new Comment(2L, "This is another for entity 2 and Type TICKET", "Mordred", "TICKET", LocalDateTime.of(2021, 4, 14, 16, 10));
		
		commentService.save(comment1Id1TypeGROUP);
		commentService.save(comment2Id1TypeGROUP);
		commentService.save(comment1Id2TypeGROUP);
		commentService.save(comment2Id2TypeGROUP);
		commentService.save(comment1Id1TypeTICKET);
		commentService.save(comment2Id2TypeTICKET);		
	}

}
