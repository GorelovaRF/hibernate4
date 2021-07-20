package es.ua.eps.cursohibernate.multitenancy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.hibernate.Session;

import es.ua.eps.cursohibernate.model.Perfil;
import es.ua.eps.cursohibernate.model.Usuario;
import es.ua.eps.cursohibernate.util.HibernateUtil;

public class MainPruebaMultiTenancy {

	public static void main(String[] args) throws ParseException {
		
		MainPruebaMultiTenancy.MainPrueba();
		
		MainPruebaMultiTenancy.createAnastasiia();
		
		MainPruebaMultiTenancy.searchAnastasiia();
	}



	private static void MainPrueba() {
		NameGenerator generator = new NameGenerator();
		List<Name> names = generator.generateNames( 10 );
		Random rnd = new Random();
		List<Integer> createdUserIds = new LinkedList<Integer>();

		/**************** Activamos el tenant P05user1 *******************/
		TenantIdentifierResolver.setTenantID("P501user1");
		
		//Añadimos 10 nuevos usuarios
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		for(Name n: names) {
			Usuario user=new Usuario();
			user.setNombre(n.getFirstName());
			user.setApellidos(n.getLastName());
			user.setEmail(n.getFirstName().toLowerCase()+"."+n.getLastName().toLowerCase()+"@dlsi.ua.es");
			user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
			user.setApodo(null);
			int perfil_id = rnd.nextInt(3);
			Perfil perfil = session.get(Perfil.class, perfil_id);
			user.setPerfil(perfil);
			user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
			session.persist(user);
			createdUserIds.add(user.getId_usuario());
		}
		//Forcing to write to the DB and cleaning 1st level cache 
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		
		//Volvemos a abrir la sesión y leemos los ID de los usuarios que hemos creado
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Consultamos los 10 usuarios que hemos creado
		for(int idUser: createdUserIds) {
			Usuario curUser = session.get(Usuario.class,idUser);
			System.out.println("El usuario con ID: " + curUser.getId_usuario() + "fue añadido a base de datos P501user1");
		}						
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		
		
		
		/**************** Activamos el tenant P05user2 *******************/
			TenantIdentifierResolver.setTenantID("P501user2");
		createdUserIds.clear();

		//Añadimos otros 10 usuarios nuevos 
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		for(Name n: names) {
			Usuario user=new Usuario();
			user.setNombre(n.getFirstName());
			user.setApellidos(n.getLastName());
			user.setEmail(n.getFirstName().toLowerCase()+"."+n.getLastName().toLowerCase()+"@dlsi.ua.es");
			user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
			user.setApodo(null);
			int perfil_id = rnd.nextInt(3);
			Perfil perfil = session.get(Perfil.class, perfil_id);
			user.setPerfil(perfil);
			user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
			session.persist(user);
			createdUserIds.add(user.getId_usuario());
		}
		
		//Forcing to write to the DB and cleaning 1st level cache 
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		
		//Volvemos a abrir la sesión y leemos los ID de los usuarios que hemos creado
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		//Consultamos los 10 usuarios que hemos creado
		for(int idUser: createdUserIds) {
			Usuario curUser = session.get(Usuario.class,idUser);
			System.out.println("El usuario con ID: " + curUser.getId_usuario() + " fue añadido a base de datos P501user2");
		}						
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();	
		
	}

	private static void createAnastasiia() throws ParseException {
		
		TenantIdentifierResolver.setTenantID("P501user1");
		Random rnd = new Random();
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
		
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		 String dateInString = "1997-1-27";
		 Date date = sdf.parse(dateInString);
		 String pasStr = "dataBaseOneLove";
		 byte[] pasB = pasStr.getBytes();
		 
		 
		 
		 Usuario usuario1 = new Usuario();
		
		 
		
		 usuario1.setPassword(pasB);
		 usuario1.setId_usuario(50);
		 usuario1.setEmail("anastacia.gorelova@mail.ru");
		 usuario1.setNombre("Anastasiia");
		 usuario1.setApellidos("Gorelova");
		 usuario1.setNacido(date);
		 int perfil_id = rnd.nextInt(3);
		 Perfil perfil = session.get(Perfil.class, perfil_id);
		 usuario1.setPerfil(perfil);
		 
		 session.save(usuario1);
		 
		 
		   String hql1 = "select u from Usuario u";
	         List<Usuario> usuariosEnBD = session.createQuery(hql1, Usuario.class).list();
	         for(Usuario usu : usuariosEnBD) {
	           
	             System.out.println("ID: " + usu.getId_usuario() + " Nombre:" + usu.getNombre());
	     };
		 
		 
		

		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	
		
	} 
	
	private static void searchAnastasiia() {
		
		TenantIdentifierResolver.setTenantID("P501user2");
		
		
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql1 = "select u from Usuario u where u.nombre = 'Anastasiia'";
		 List<Usuario> usuarios = session.createQuery(hql1, Usuario.class).list();

         for(Usuario usuario : usuarios) {
                 System.out.println("ID: " + usuario.getId_usuario()+ "Nombre:" + usuario.getNombre());
         };
         
         String hql2 = "select u from Usuario u";
         List<Usuario> usuariosEnBD = session.createQuery(hql2, Usuario.class).list();
         for(Usuario usu : usuariosEnBD) {
           
             System.out.println("ID: " + usu.getId_usuario() + " Nombre:" + usu.getNombre());
     };
         
		
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	
		
	}
	
	
	
}