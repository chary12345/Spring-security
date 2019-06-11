package com.springframeworks.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.springframeworks.pojos.UserPojo;

public class RegisterDaoImpl {
final static Logger logger=Logger.getLogger(RegisterDaoImpl.class);
	
	public void saveUser(UserPojo user)  {
		logger.info("entered in to save user :: RegisterDaoImpl");
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = configuration.buildSessionFactory();
		logger.debug("session factory opened");
		Session session = sf.openSession();
		logger.debug("session opened");
		session.save(user);
		logger.debug("parsed user object in to database" + user.getMailid());
		session.beginTransaction().commit();
		session.close();
		logger.info("exited in to save user :: RegisterDaoImpl");
		
		
		
	}

}
