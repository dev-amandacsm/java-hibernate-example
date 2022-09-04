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
public class CreateInstructorDemo {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateInstructorDemo.class);

	public static void main(String[] args) {
		SpringApplication.run(CreateInstructorDemo.class, args);

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try (factory) {
			//create the objects
			Instructor instructor = new Instructor("Amanda", "Muniz", "dev.amandacsm@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("https://youtube.com/devamandacsm", "Luv 2 sing!");

			//associate the objects
			instructor.setInstructorDetail(instructorDetail);

			//start a transaction
			session.beginTransaction();

			//save the Instructor
			/* This will also save the InstructorDetail
			object because of CascadeType.ALL */
			logger.info("Saving: " + instructor);
			session.save(instructor);

			//commit transaction
			session.getTransaction().commit();

		}
	}

}
