package es.ua.eps.cursohibernate.search;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;



import es.ua.eps.cursohibernate.model.Usuario;
import es.ua.eps.cursohibernate.util.HibernateUtil;

public class Query4 {
	public static void main(String[] args) throws InterruptedException {
		Query4.SearchUs();
	

}



	private static void SearchUs() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println("Session factory creada");			
		FullTextSession fullTextSession = Search.getFullTextSession(session);	
		session.beginTransaction();
		
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
				 .forEntity(Usuario.class)
				 .get();
		
		 Usuario usuario = new Usuario();
		 usuario.setNombre("Jaime");
		 usuario.setApellidos("Garcia");
		 
		org.apache.lucene.search.Query query = queryBuilder
				.moreLikeThis()
				 .comparingField("nombre").boostedTo(10f)
				 .andField("apellidos")
				 .toEntity(usuario)
				 .createQuery();

		
		List<Usuario> results = fullTextSession.createFullTextQuery(query, Usuario.class).list();
		for (Usuario res : results) {
			
			System.out.println("ID: "+ res.getId_usuario() + " " + "Nombre:  " + res.getNombre() + " " + " Apellido: "+ res.getApellidos());			
		}
		System.out.println("Se he encontrado " + results.size()+ " personas" + ", parecidas a Jaime Garcia");
		session.getTransaction().commit();				
		HibernateUtil.getSessionFactory().close(); 
		
		
	}
}
