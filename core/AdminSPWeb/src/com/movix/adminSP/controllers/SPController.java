package com.movix.adminSP.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movix.adminSP.model.dto.BillServicePriceDTO;
import com.movix.adminSP.model.dto.EnvServicePriceDTO;
import com.movix.adminSP.model.dto.RecServicePriceDTO;
import com.movix.adminSP.model.dto.ServicePriceDTO;
import com.movix.shared.Operador;
import com.movixla.ldap.LDAPResponse;

/**
 * Servlet implementation class SPController
 */

// TODO : probar cache
@WebServlet("/SPController")
public class SPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //SPCache cache;   
    private static String EDIT ="/editSP.jsp";
    private static String LIST_BILL_SP ="/listBillSP.jsp";
    private static String LIST_ENV_SP ="/listEnvSP.jsp";
    private static String LIST_REC_SP ="/listRecSP.jsp";
    private static String ERROR_PAGE = "/error.html";
    
    public SPController() {
        super();
        //cache = new SPCache();
        //cache.init();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String type = request.getParameter("type");
		String forward = "";
		
		/**
		LDAPResponse ldapResponse = (LDAPResponse) request.getSession().getAttribute("ldapResponse");
		if(ldapResponse!=null&&ldapResponse.isTieneExito()){
			if(action.equalsIgnoreCase("refresh")){
				//cache.refreshCacheSP();				
				//TODO: Refrescar lista página
			}
			else if(action.equalsIgnoreCase("edit")){
				
				//TODO: Redirect a edit
				int idSP = Integer.parseInt(request.getParameter("idSP"));
				request.setAttribute("idSP", idSP);
				forward = EDIT;
			}
			else if(action.equalsIgnoreCase("delete")){
				//TODO: accion eliminar
			
			}
		}
		else{
			forward=ERROR_PAGE;
		}
		
		**/
		
		if(type.equalsIgnoreCase("Bill")){
			forward=LIST_BILL_SP;
			List<BillServicePriceDTO> sps = new ArrayList<BillServicePriceDTO>();
			//sps = cache.getAllBill();
			sps.add(new BillServicePriceDTO(1,Operador.ENTEL, "sus3_ClaroSV_7733_cobro",0.09,ServicePriceDTO.Estrategia.FULLPRICE,"channelClaroSV_Bill","493|1|7733|Suscripciones_7733",true));
			sps.add(new BillServicePriceDTO(2,Operador.ENTEL, "sus3_ClaroSV_7733_cobro",0.08,ServicePriceDTO.Estrategia.FULLPRICE,"channelClaroSV_Bill","493|1|7733|Suscripciones_7733",true));
			sps.add(new BillServicePriceDTO(3,Operador.ENTEL, "sus3_ClaroSV_7733_cobro",0.07,ServicePriceDTO.Estrategia.FULLPRICE,"channelClaroSV_Bill","493|1|7733|Suscripciones_7733",true));
			sps.add(new BillServicePriceDTO(4,Operador.ENTEL, "sus3_ClaroSV_7733_cobro",0.06,ServicePriceDTO.Estrategia.FULLPRICE,"channelClaroSV_Bill","493|1|7733|Suscripciones_7733",true));
			request.setAttribute("sps", sps);
		}
		else if(type.equalsIgnoreCase("Env")){
			forward=LIST_ENV_SP;
			List<EnvServicePriceDTO> sps = new ArrayList<EnvServicePriceDTO>();
			//sps = cache.getAllEnv();
			request.setAttribute("sps", sps);
		}
		else{
			forward=LIST_REC_SP;
			List<RecServicePriceDTO> sps =  new ArrayList<RecServicePriceDTO>();
			//sps = cache.getAllRec();
			request.setAttribute("sps", sps);
		}
			
		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO: Ingresar SP o Actualizarlo
	}

}
