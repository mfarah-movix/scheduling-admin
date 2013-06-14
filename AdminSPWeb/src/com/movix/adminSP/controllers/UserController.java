package com.movix.adminSP.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movixla.ldap.LDAPAuthentication;
import com.movixla.ldap.LDAPResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("logout")){
			request.removeAttribute("ldapResponse");
			//TODO: Redirect al login
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("pass");
		
		//TODO: Revisar la conexión
		LDAPAuthentication ldapAuth = new LDAPAuthentication("",8080);
		
		LDAPResponse ldapResponse = ldapAuth.autenticarLDAP(user, password, "SP");
		
		request.getSession().setAttribute("ldapResponse", ldapResponse);
		
		if(ldapResponse.isTieneExito()){
			//TODO : Redirect a la pagina principal
		}
		else{
			//TODO : Redirect página error
		}
		
		
		
	}

}
