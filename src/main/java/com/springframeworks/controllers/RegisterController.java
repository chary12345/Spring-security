package com.springframeworks.controllers;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframeworks.pojos.UserPojo;

import mobile.mail.validations.MobileValidate;
/*Frame work is set of predefine customized
 * methods*/


@Controller
public class RegisterController {//com.springframeworks.controllers.RegisterController
	final static Logger logger = Logger.getLogger(RegisterController.class);

	// RegisterDaoImpl daoimpl=new RegisterDaoImpl(); //hibernate obj



	@SuppressWarnings("deprecation")
	@RequestMapping(value="/saveUser")
	public String saveUser(UserPojo user, Model model) {

		logger.info("Entered in to save user");

		logger.debug(user.getFname() +user.getLname() + " " + user.getMobileno() + " " + user.getMailid() + " " + user.getLocation() + " "
				+ user.getPassword());
		logger.debug("Register Button Triggered!!");

		try {
			// validations begins here
			boolean isvalidmobile = MobileValidate.isValidMobile(user.getMobileno());
			if (isvalidmobile == true) {
				logger.info("enterd valid mobile number!! please continue with registration process");

			} else {

				logger.info("wrong mobile no" + user.getMobileno());
				model.addAttribute("mobile", "invalid mobile number");
				return "register";
			}
	


			// database Validation need to be implemented here!!
			//hibernate Logic here
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session session = sf.openSession();
			
			logger.debug("session opened");
			// Need to save user pojo to database
			// hbernate logic here

			

		session.save(user);
		session.beginTransaction().commit();
		session.close();
		logger.debug("user details saved of user"+user.getFname()+user.getLname());

		} catch (Exception e) {
			logger.error("exception occured", e);

			model.addAttribute("message", "something went wrong with registration");
			return "register";
		}
		model.addAttribute("message","register successfully do login with credential,for fetching details");
		return "login";
	}

}
