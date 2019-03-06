package com.bts.tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.TypeWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/EndpointServlet")
public class EndpointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EndpointServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TypeWrapper typeWrapper = gson.fromJson(jsonObj, TypeWrapper.class);
		System.out.println(typeWrapper.getType());
		System.out.println(typeWrapper.getDeveloperEntity().getFullName());
		/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BugTrackingSystem");
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();
		entitymanager.persist(developerEntity1);
//		entitymanager.getTransaction().commit();
		
//		entitymanager.getTransaction().begin();
		DeveloperEntity developerEntity2 = entitymanager.find(DeveloperEntity.class, 1);
		entitymanager.getTransaction().commit();
		System.out.println(" ID = " + developerEntity2.getId());
		response.setContentType("application/json");
		entitymanager.close();
		emfactory.close();
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(developerEntity2));
		// out.flush();
*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
