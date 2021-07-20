package es.ua.eps.cursohibernate.main;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;

import es.ua.eps.cursohibernate.model.Perfil;
import es.ua.eps.cursohibernate.model.Usuario;
import es.ua.eps.cursohibernate.util.HibernateUtil;


public class ValidationMain {
	
	public static void main(String[] args) throws Exception {
			
		
		ValidationMain.Usuario1();
		ValidationMain.Usuario2();
		ValidationMain.Usuario3();
				
		
	}



	private static void Usuario2() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");
		session.beginTransaction();
		

		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 Validator validator = factory.getValidator();
		 
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		 String dateInString = "2021-1-27";
		 Date date = sdf.parse(dateInString);
		 String pasStr = "12345";
		 byte[] pasB = pasStr.getBytes();
		 
		 
		 
		 Usuario usuario2 = new Usuario();
		 Perfil perfil= new Perfil();
		 perfil.setId_perfil(1);	 
		 usuario2.setPassword(pasB);
		 usuario2.setId_usuario(16);
		 usuario2.setEmail("aleksa@hotmail.com;");
		 usuario2.setNombre("Alexandra");
		 usuario2.setApellidos("Parichenko");
		 usuario2.setNacido(date);
		 usuario2.setPerfil(perfil);
		 
		 Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario2);
		 
		 System.out.println("The user with id "+ usuario2.getId_usuario() + " - " + usuario2.getNombre() + " " + usuario2.getApellidos() + " " + "have: " + violations.size() + " mistakes");
		
		 
		 session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
		
		
	}

	private static void Usuario1() throws Exception {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");
		session.beginTransaction();
		
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 Validator validator = factory.getValidator();
		 
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		 String dateInString = "1997-1-27";
		 Date date = sdf.parse(dateInString);
		 String pasStr = "dataBaseOneLove";
		 byte[] pasB = pasStr.getBytes();
		 
		 
		 
		 Usuario usuario1 = new Usuario();
		 Perfil perfil1= new Perfil();
		 
		 perfil1.setId_perfil(2);
		 usuario1.setPassword(pasB);
		 usuario1.setId_usuario(18);
		 usuario1.setEmail("anastacia.gorelova@mail.ru");
		 usuario1.setNombre("Anastasiia");
		 usuario1.setApellidos("Gorelova");
		 usuario1.setNacido(date);
		 usuario1.setPerfil(perfil1);
		 
		 Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario1);
		 System.out.println("The user with id "+ usuario1.getId_usuario() + " - " + usuario1.getNombre() + " " + usuario1.getApellidos() + " " + "have: " + violations.size() + " mistakes");
			
		 session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
		
	}	
	
	private static void Usuario3() throws ParseException {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");
		session.beginTransaction();
		
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 Validator validator = factory.getValidator();
		 
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		 String dateInString = "1945-1-15";
		 Date date = sdf.parse(dateInString);
		 String pasStr = "data";
		 byte[] pasB = pasStr.getBytes();
		 
		 
		 
		 Usuario usuario3 = new Usuario();
		 Perfil perfil3= new Perfil();
		 
		 perfil3.setId_perfil(-3);
		 usuario3.setPassword(pasB);
		 usuario3.setId_usuario(0);
		 usuario3.setEmail("228mail.ru");
		 usuario3.setNombre("");
		 usuario3.setApellidos("Ivanov");
		 usuario3.setNacido(date);
		 usuario3.setPerfil(perfil3);
		 
		 Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario3);
		 System.out.println("The user with id "+ usuario3.getId_usuario() + " - " + usuario3.getNombre() + " " + usuario3.getApellidos() + " " + "have: " + violations.size() + " mistakes");
			
		 session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
		
		
	}
	

	
	}
	
	


	 

	
	 

	

	
	
	
	
	
