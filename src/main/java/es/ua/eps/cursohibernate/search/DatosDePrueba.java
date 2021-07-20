package es.ua.eps.cursohibernate.search;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import es.ua.eps.cursohibernate.model.Conexion;
import es.ua.eps.cursohibernate.model.Perfil;
import es.ua.eps.cursohibernate.model.Usuario;
import es.ua.eps.cursohibernate.util.HibernateUtil;

public class DatosDePrueba {

	public static void main(String[] args) {
		Random rnd = new Random();
		
		//Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");
		List<Integer> createdUserIds = new LinkedList<Integer>();
		
		session.beginTransaction();
		Usuario user=new Usuario();
		user.setNombre("Jaime");
		user.setApellidos("García");
		user.setEmail("jaime.garcia@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		int perfil_id = rnd.nextInt(3);
		Perfil perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);

		user=new Usuario();
		user.setNombre("Jaime");
		user.setApellidos("Peris");
		user.setEmail("jaime.peris@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
		
		user=new Usuario();
		user.setNombre("Clara");
		user.setApellidos("García");
		user.setEmail("clara.garcia@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
		
		user=new Usuario();
		user.setNombre("Francisco");
		user.setApellidos("Gracia");
		user.setEmail("clara.garcia@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
		
		user=new Usuario();
		user.setNombre("Helena");
		user.setApellidos("González");
		user.setEmail("clara.garcia@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
		
		user=new Usuario();
		user.setNombre("Jaime");
		user.setApellidos("Carreras");
		user.setEmail("jaime.carreras@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
	
		user=new Usuario();
		user.setNombre("Severino");
		user.setApellidos("García");
		user.setEmail("severino.garcia@dlsi.ua.es");
		user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
		user.setApodo(null);
		perfil_id = rnd.nextInt(3);
		perfil = session.get(Perfil.class, perfil_id);
		user.setPerfil(perfil);
		user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
		session.persist(user);
						
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		
		
		//Generando la indexacion inicial de Lucene SolR
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		try {
			fullTextSession.createIndexer().threadsToLoadObjects(1).startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
}