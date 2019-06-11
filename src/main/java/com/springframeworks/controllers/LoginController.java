package com.springframeworks.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springframeworks.pojos.UserPojo;

@Controller
public class LoginController {
	final static Logger logger = Logger.getLogger(LoginController.class);

	@SuppressWarnings("deprecation")
	@RequestMapping(value="/loginUser")
	public String loginUser(@RequestParam("mailid") String mailid,@RequestParam("password") String password , Model m){
		logger.info("login user method triggered");
		logger.debug(mailid + "::" + password );
		
		//hibernate logic here
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		
		//select query to get mailid and password into login page from database
		Query query = session.createQuery("from UserPojo where mailid= :mailid and password= :password");
		query.setParameter("mailid", mailid);
		query.setParameter("password", password);
		List list = query.list();
		
		
		if(list.isEmpty()){
			logger.debug("invalid login credentials mailid or password");
			logger.debug(mailid  +  password+"user entered");
			m.addAttribute("messege","Incorrect mailid or password.");
			return "login";
		}else{
			logger.debug("login credentials valid "+mailid + "  and" +password);
			//m.addAttribute("list",list);
			
		/*	Query queryallusers = session.createQuery("from UserPojo");
			
			List<UserPojo> userList = queryallusers.list();
			
			for (UserPojo userPojo : userList) {
				System.out.println("name is"+userPojo.getFname()+userPojo.getLname());
				System.out.println("user mailid"+userPojo.getMailid());
				System.out.println("location is"+userPojo.getLocation());
			}
			m.addAttribute("userList",userList);//userlist is refference of userpojo in for each
			session.close();*/
			return "success";
		
		}
	
			
		}
	
	
	@RequestMapping(value="/fetchAllRecords")
	public String getAllRecords(Model m) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Query queryallusers = (Query) session.createQuery("from UserPojo");
		
		List<UserPojo> allusers = queryallusers.list();
		for (UserPojo userPojo : allusers) {
			System.out.println(userPojo.getFname()+" "+userPojo.getLname()+"||"+userPojo.getMailid());
			System.out.println("|| || || ||");
			System.out.println(userPojo.getMobileno());
		}
		/**/
		m.addAttribute("userList",allusers);
		session.beginTransaction().commit();
		session.close();
		return "profile";
		
	}
}
