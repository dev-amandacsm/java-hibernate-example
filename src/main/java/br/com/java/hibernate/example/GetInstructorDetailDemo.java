package br.com.java.hibernate.example;

import br.com.java.hibernate.example.model.entities.Instructor;
import br.com.java.hibernate.example.model.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GetInstructorDetailDemo {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GetInstructorDetailDemo.class);

	public static void main(String[] args) {
		SpringApplication.run(GetInstructorDetailDemo.class, args);

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		try (factory) {
			Session session = factory.getCurrentSession();
			//start a transaction
			session.beginTransaction();

			//get the instructor detail object
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, 2L);

			//print the instructor detail
			logger.info("The instructor detail: " + instructorDetail);

			//print the associated instructor
			logger.info("The associated instructor: " + instructorDetail.getInstructor());

			//commit transaction
			session.getTransaction().commit();

		}
	}

}
