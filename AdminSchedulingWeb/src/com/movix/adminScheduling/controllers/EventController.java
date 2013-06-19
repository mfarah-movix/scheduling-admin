package com.movix.adminScheduling.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movix.AdminScheduling.model.dto.EventDTO;
import com.movix.shared.Operador;

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<EventDTO> events = new ArrayList<EventDTO>();
		events.add(new EventDTO(1, Operador.CLARO_PERU, "MP", 1, "claro_PE/1.0", "claro_PE/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(2, Operador.CLARO_PERU, "MP", 1, "claro_PE/1.0", "claro_PE/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(3, Operador.CLARO_CHILE, "MP", 1, "claro_CL/1.0", "claro_CL/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(4, Operador.CLARO_CHILE, "MP", 1, "claro_CL/1.0", "claro_CL/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(5, Operador.CLARO_CHILE, "MP", 1, "claro_CL/1.0", "claro_CL/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(6, Operador.ENTEL, "MP", 1, "entel/1.0", "entel/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(7, Operador.ENTEL, "MP", 1, "entel/1.0", "entel/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(8, Operador.ENTEL, "MP", 1, "entel/1.0", "entel/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		events.add(new EventDTO(9, Operador.ENTEL, "MP", 1, "entel/1.0", "entel/1.0_MP_Operador", "LU a VI", "03:00-04:00, 08:00-10:00"));
		request.setAttribute("events", events);
		RequestDispatcher view = request.getRequestDispatcher("/events.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
