package es.ua.eps.cursohibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.persistence.Id;
//import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
//import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name="conexion")
public class Conexion implements Serializable {
		

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/*@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario", unique = true, nullable = false)
    private int Id_usuario; */
	
	





	@Id
	@Column(name="momento_entrada", nullable = false)
	@NotNull
	@PastOrPresent
    private java.util.Date momento_entrada;
	
	
	@ManyToOne
	@JoinColumn(name="id_usuario", nullable = false)
	@NotNull
	private Usuario usuario;
	

	public Usuario getUsuario() {
		return usuario;
	}
	public void setIdusuario(Usuario usuario) {
		this.usuario= usuario;
	}
	
	 /* //M:N
	@ManyToMany(mappedBy = "conexiones")
	private Set<Perfil> perfiles;
	
	public Set<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles (Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	} 
	
	//M:N */
	
	 /* public int getIdusuario() {
    	return idUsuario;
    }
	 public void setIdusuario(int idUsuario) {
    	this.idUsuario=idUsuario;
    } */
	
	
    
    public java.util.Date getMomento_entrada() {
    	return momento_entrada;
    }
    public void setMomento_entrada(java.util.Date momento_entrada) {
    	this.momento_entrada=momento_entrada;
    }

}
