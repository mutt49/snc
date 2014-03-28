package org.demo.spinncast.connections;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConnectionPool {
	private static ConnectionPool instance = null;
	
	private SessionFactory fact;
	private Session session;
	private Configuration cfg;
	
	public SessionFactory getFact() {
		return fact;
	}

	public void setFact(SessionFactory fact) {
		this.fact = fact;
	}

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	private ConnectionPool () {
		cfg = new Configuration().setProperty("hibernate.show_sql", "true");
		cfg.configure("hibernate.cfg.xml");
		fact = cfg.buildSessionFactory();
	}
	
	public static ConnectionPool getInstance () {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	public Transaction getTransaction () {
		return session.beginTransaction();
	}

	public Session getSession() {
		session = fact.openSession();
		return session;
	}
}
