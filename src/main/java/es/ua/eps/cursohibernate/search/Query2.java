package es.ua.eps.cursohibernate.search;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;



import es.ua.eps.cursohibernate.model.Usuario;
import es.ua.eps.cursohibernate.util.HibernateUtil;

public class Query2 {
	
	public static void main(String[] args) throws InterruptedException {
		 Query2.SearchJaimeAp();
	 }

	private static void SearchJaimeAp() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");			
		FullTextSession fullTextSession = Search.getFullTextSession(session);	
		session.beginTransaction();
		
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
				 .forEntity(Usuario.class)
				 .get();
		
		org.apache.lucene.search.Query query = queryBuilder
				.bool()
		        .must(queryBuilder.keyword()
		        .onField("nombre").matching("Jaime")
		        .createQuery())
		        .must(queryBuilder.keyword().wildcard()
		        .onField("apellidos")
		        .matching("*a*")
		        .createQuery()).createQuery();
		
		List<Usuario> results = fullTextSession.createFullTextQuery(query, Usuario.class).list();
		for (Usuario res : results) {
			
			System.out.println("ID: "+ res.getId_usuario() + " " + "Nombre:  " + res.getNombre() + " " + " Apellido: "+ res.getApellidos());			
		}
		
		session.getTransaction().commit();				
		HibernateUtil.getSessionFactory().close(); 
		
	}

}
