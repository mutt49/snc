package org.demo.spinncast.connections;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConnectionPool {
	private static ConnectionPool instance = null;
	
	SessionFactory fact;
	Session session;
	Configuration cfg;
	
	private ConnectionPool () {
		cfg = new Configuration();
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
