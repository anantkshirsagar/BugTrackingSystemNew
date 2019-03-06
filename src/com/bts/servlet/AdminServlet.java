package com.bts.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bts.entities.DeveloperEntity;
import com.bts.entities.Employee;
import com.bts.entities.ProjectEntity;
import com.bts.entities.TesterEntity;
import com.bts.entities.TypeWrapper;
import com.bts.services.ConnectionService;
import com.bts.services.DatabaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.fabric.xmlrpc.base.Data;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsonObj = request.getReader().lines().collect(Collectors.joining());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ConnectionService connectionService = new ConnectionService();
		TypeWrapper typeWrapper = gson.fromJson(jsonObj, TypeWrapper.class);
		PrintWriter out = response.getWriter();

		switch (typeWrapper.getType()) {
		case DEVELOPER_LIST:
			List<DeveloperEntity> developerList = connectionService.getEntityManager()
					.createQuery("SELECT d FROM DeveloperEntity d WHERE d.isApproved=1").getResultList();
			out.write(gson.toJson(developerList));
			break;
		case TESTER_LIST:
			List<TesterEntity> testerList = connectionService.getEntityManager()
					.createQuery("SELECT t FROM TesterEntity t WHERE t.isApproved=1").getResultList();
			out.write(gson.toJson(testerList));
			break;
		case PROJECT_LIST:
			List<ProjectEntity> projectList = connectionService.getEntityManager()
					.createQuery("SELECT p FROM ProjectEntity p").getResultList();
			out.write(gson.toJson(projectList));
			break;
		case APPROVAL_LIST:
			List<Employee> approvalList = new ArrayList<>();
			List<Employee> developers = connectionService.getEntityManager()
					.createQuery("SELECT d FROM DeveloperEntity d WHERE d.isApproved=0").getResultList();
			List<Employee> testers = connectionService.getEntityManager()
					.createQuery("SELECT t FROM TesterEntity t WHERE t.isApproved=0").getResultList();
			approvalList.addAll(developers);
			approvalList.addAll(testers);
			out.write(gson.toJson(approvalList));
			break;
		case ADMIN_HOME_FETCH_LIVE_PROJECTS:
			List<ProjectEntity> liveProjectList = new DatabaseService().fetchProjectList();
			out.write(gson.toJson(liveProjectList));
			break;
		case ADMIN_HOME_FETCH_COMPLETED_PROJECTS:
			List<ProjectEntity> completedProjects = new DatabaseService().fetchCompletedProjectList();
			out.write(gson.toJson(completedProjects));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
