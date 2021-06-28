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
		
		//Init of comments for GROUP 1
		Comment Group1Commentaire1 = new Comment(1L, "Welcome to our group guys", "mimi92", "GROUP", LocalDateTime.of(2021, 05, 11, 11, 15));
		Comment Group1Commentaire2 = new Comment(1L, "Thanks for inviting me !!!!", "matteo le dev", "GROUP", LocalDateTime.of(2021, 05, 11, 13, 15));
		
		Comment Group1Commentaire3 = new Comment(1L, "Do you wanna develop an app ?", "mimi92", "GROUP", LocalDateTime.of(2021, 05, 14, 15, 15));
		Comment Group1Commentaire4 = new Comment(1L, "No thanks mimi92", "matteo le dev", "GROUP", LocalDateTime.of(2021, 05, 15, 16, 30));
		Comment Group1Commentaire5 = new Comment(1L, "What's a 'ticketing app' ?", "devMy75", "GROUP", LocalDateTime.of(2021, 05, 15, 16, 30));
		commentService.save(Group1Commentaire1);
		commentService.save(Group1Commentaire2);
		commentService.save(Group1Commentaire3);
		commentService.save(Group1Commentaire4);
		commentService.save(Group1Commentaire5);
	
		//Init comments for Tickets related to group1 (1 to 9) 
//		LocalDateTime.of(2021, 4, 14, 15, 10)
//		LocalDateTime.of(2021, 4, 14, 15, 15)
//		LocalDateTime.of(2021, 04, 14, 15, 20)
		
		Comment Ticket1Commentaire1 = new Comment(1L, "I need to find some infos", "mimi92", "TICKET", LocalDateTime.of(2021, 05, 15, 15, 10));
		Comment Ticket1Commentaire2 = new Comment(1L, "Check Stackoverflow!", "mimi92", "TICKET", LocalDateTime.of(2021, 05, 15, 15, 30));
		
		Comment Ticket2Commentaire1 = new Comment(2L, "Hey, about the database I think we should use StarUML, what do you think ?", "matteo le dev", "TICKET", LocalDateTime.of(2021, 5, 15, 17, 10));
		Comment Ticket2Commentaire2 = new Comment(2L, "I have more experience with PowerAMC, can we use it instead ?", "devMy75", "TICKET", LocalDateTime.of(2021, 5, 15, 18, 10));
		Comment Ticket2Commentaire3 = new Comment(2L, "Sure, no problems.", "matteo le dev", "TICKET", LocalDateTime.of(2021, 5, 16, 17, 10));
		Comment Ticket2Commentaire4 = new Comment(2L, "Check the discord, I posted a preliminary model", "devMy75", "TICKET", LocalDateTime.of(2021, 5, 17, 18, 10));
		
		commentService.save(Ticket1Commentaire1);		
		commentService.save(Ticket1Commentaire2);
		commentService.save(Ticket2Commentaire1);		
		commentService.save(Ticket2Commentaire2);
		commentService.save(Ticket2Commentaire3);		
		commentService.save(Ticket2Commentaire4);
		
		
	}

}
