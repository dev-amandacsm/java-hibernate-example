package br.com.java.hibernate.example;

import br.com.java.hibernate.example.model.entities.Instructor;
import br.com.java.hibernate.example.model.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class DeleteInstructorDemo {

	private static final Logger logger = LoggerFactory.getLogger(DeleteInstructorDemo.class);

	public static void main(String[] args) {
		SpringApplication.run(DeleteInstructorDemo.class, args);

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try (factory) {
			//start a transaction
			session.beginTransaction();

			//get the instructor by primary key
			Instructor instructor = session.get(Instructor.class, 1L);

			if(Objects.nonNull(instructor)){
				//delete the Instructor
				logger.info("Deleting: " + instructor);
				session.delete(instructor);
			}
			else{
				throw new RuntimeException("Instructor with the id provided was not found!");
			}

			//commit transaction
			session.getTransaction().commit();

		}
	}
}
