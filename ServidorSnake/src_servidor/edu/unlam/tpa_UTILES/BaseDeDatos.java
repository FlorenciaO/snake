package edu.unlam.tpa_UTILES;

import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import org.hibernate.Transaction;

public class BaseDeDatos {

	
	private Configuration cfg;
	private SessionFactory factory;
	private Session session;
	
	public void iniciarBd() {
		Configuration c = new Configuration();
		this.cfg = c.configure("hibernate.cfg.xml");
		this.factory = cfg.buildSessionFactory();
	}
	
	public boolean existeUsuarioEnLaBD(String nombreUsuario){	
		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
		Query q = session.createQuery("Select j.username from ABM j");
		List<String> listaDeNombresUsuarios = q.getResultList();
		session.close();
		return listaDeNombresUsuarios.contains(nombreUsuario) ? true : false;
	}

	public boolean registrarUsuarioEnBD(String nombreUsuario, String pass) {
	
		if(!nombreUsuario.isEmpty() && !existeUsuarioEnLaBD(nombreUsuario)) {	
			ABM a = new ABM();
			a.setUsername(nombreUsuario);
			a.setPassword(pass);
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(a);
			tx.commit();
			session.close();
			return true;
			
		}
		else {
			return false;
		}
		
	}
	
	public boolean habilitarConexion(ABM a) {
		
		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
		Query q = session.createQuery("Select p from ABM p");
		List<ABM> listaDePersonas = q.getResultList();
		session.close();
		for (int j = 0; j < listaDePersonas.size(); j++) {
			if(listaDePersonas.get(j).getUsername().equals(a.getUsername()) && listaDePersonas.get(j).getPassword().equals(a.getPassword()))
				return true;
		}
		return false;
	}
	
	public void cerrarFactory() {
		factory.close();
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	
}
