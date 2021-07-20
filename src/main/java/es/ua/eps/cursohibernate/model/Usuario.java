package es.ua.eps.cursohibernate.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;

import javax.validation.constraints.Size;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;




//import java.util.HashSet;
//import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;




//import java.util.Set;






@Entity
@Indexed
@Table(name="usuario")
public class Usuario  {
   



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario", unique = true, nullable=false)
	@NotNull(message = "Id cannot be null")
	
    private int id_usuario;
    
   
    
    @Column(name="password", nullable = true)
    @Size(min = 10, max = 100, message = "Password must be between 8 and 100 characters")
    private byte[] password;
    
    @Column(name="apodo", nullable = true)
    @Null
    private String apodo;
    
    @Column(name="email")
     @Email
     @NotNull
    private String email;
    
    @Column(name="nombre", nullable = false)
     @NotNull
     @Size(min = 1, max = 100)
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO, termVector = TermVector.YES)
    
    private String nombre;
    
    @Column(name="apellidos", nullable = false)
     @NotNull
     @Size(min = 1, max = 120)
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO, termVector = TermVector.YES)
    
    private String apellidos;
    
    @Column(name="nacido", nullable = false)
    @NotNull
    @PastOrPresent
    private java.util.Date nacido;
    
  /*  @Column(name="id_perfil", nullable = true)
    private short id_perfil;*/
    
    
    //with conexion
    @OneToMany (mappedBy="usuario")
    private Set<Conexion> conexionset;
    
    public Set<Conexion> getConexionset(){
    	return conexionset;
    }
    public void setConexionset(Set<Conexion> conexionset) {
    	this.conexionset = conexionset;
    }
    //
    
    //with perfil
    @ManyToOne 
    @JoinColumn (name="id_perfil", nullable= false)
    private Perfil perfil;
    
    public Perfil getPerfil() {
    	return perfil;
    }
    public void setPerfil(Perfil perfil) {
    	this.perfil = perfil;
    }
    
    //
    
    
    
    //1:1 with infPub
    
    @OneToOne(mappedBy = "usuario")
    private InformacionPublica informacionPublica;
    
    public InformacionPublica getInformacionPublica () {
    	return informacionPublica;
    }
    public void setInformacionPublica(InformacionPublica informacionPublica) {
    	this.informacionPublica=informacionPublica;
    } 
    
    
    //1:1
    
    /* // N:M with Sigue
    
    private Set<Usuario> followers;
    private Set<Usuario> following;
    
    public Usuario () {}
    public Usuario (String nombre) {
    	this.nombre = nombre;
    	this.followers = new HashSet<Usuario>();
    	this.following = new HashSet<Usuario>();
    }
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable (name="sigue",
    joinColumns = @JoinColumn (name="id_seguido"),
    inverseJoinColumns = @JoinColumn(name = "id_seguidor"))
    public Set<Usuario> getFollowers() {
    	return followers;
    }
    public void setFollowers (Set<Usuario> followers) {
    	this.followers = followers;
    }
    public void addFollower(Usuario follower) {
    	followers.add(follower);
    	follower.following.add(this);
    	}
    @ManyToMany(mappedBy = "followers")
    public Set<Usuario> getFollowing(){
    	return following;
    }
    public void setFollowing(Set<Usuario> following) {
    	this.following = following;
    }
    public void addFollowing(Usuario followed) {
    	followed.addFollower(this);
    	
    }
    // */
    
    // N:M with Sigue

    
    @ManyToMany (mappedBy = "seguidores")
    private Set<Usuario> seguidos;
    
   

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable (name = "sigue", joinColumns = @JoinColumn (name="id_seguido"),
    inverseJoinColumns = @JoinColumn(name = "id_seguidor"))
    private Set<Usuario> seguidores;
    
    
    //
    
    public int getId_usuario() {
    	return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
    	this.id_usuario=id_usuario;
    }
    
    public byte[] getPassword() {
    	return password;
    }
    public void setPassword(byte[] password) {
    	this.password=password;
    }
    
    public String getApodo() {
    	return apodo;
    }
    public void setApodo(String apodo) {
    	this.apodo=apodo;
    }
    
    public String getEmail() {
    	return apodo;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
    
    public String getNombre() {
    	return nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre=nombre;
    }
    
    public String getApellidos() {
    	return apellidos;
    }
    public void setApellidos(String apellidos) {
    	this.apellidos=apellidos;
    }
    
    
    public java.util.Date getNacido() {
    	return nacido;
    }
    public void setNacido(java.util.Date nacido) {
    	this.nacido=nacido;
    }
    
    
   
    
    

    
	public void setSiguea(Set<Usuario> followed) {
		// TODO Auto-generated method stub
		this.seguidos = followed;
		for (Usuario user : followed) {
			user.getSeguidores().add(this);
		}
		
		
	}
	private Set<Usuario> getSeguidores() {
		// TODO Auto-generated method stub
		return seguidores;
	}
    
    
   
    
}

