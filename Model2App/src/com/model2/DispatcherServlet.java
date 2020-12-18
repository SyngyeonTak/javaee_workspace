/*
 	웹 상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러
 */

package com.model2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class DispatcherServlet extends HttpServlet{
	FileReader fis;
	JSONObject controllerMap;//컨트롤러의 정보들이 들어있는 맵
	JSONObject viewMap;
	Controller controller;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	public void init(ServletConfig config) throws ServletException {
		JSONParser jsonParser = new JSONParser();
		ServletContext context = config.getServletContext();
		String initParameter = config.getInitParameter("contextConfigLocation");
		String savePath = context.getRealPath(initParameter);
		
		System.out.println(savePath);
		
		try {
			fis = new FileReader(savePath);
			
			//파싱!!
			JSONObject json = (JSONObject)jsonParser.parse(fis);
			controllerMap =  (JSONObject)json.get("controller");
			viewMap = (JSONObject)json.get("view");

			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if(fis != null) fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		
		String className = null;
		String resultPath = null;
		//if문을 대신할 구조화된 데이터를 선택하자!!(xml, json, properties)
		className = (String)controllerMap.get(uri);
		System.out.println(className);
		
		try {
			Class controllerClass =  Class.forName(className);//문자열로 지정한 클래스에 대한 실제 클래스를 반환한다..
			controller = (Controller)controllerClass.newInstance();//지정한 클래스의 인스턴스를 반환한다.
			controller.execute(request, response);
			resultPath = (String)viewMap.get(controller.getResultView());
			//응답 시 sendRedirect 로 처리해야 할 경우가 있고, 글 작성 후 리스트, 전혀 다른 페이지로 재접속을 유도할 때
			if(controller.isForward()){
				//때로는 forwarding 처리해야 할 경우가 있다..
				//데이터를 유지하고 싶을 때...하지만 계속 사용하면 문제가 있다.
				RequestDispatcher dis =request.getRequestDispatcher(resultPath);
				dis.forward(request, response);//응답 없이, 서버상의 또 다른 자원으로 요청을 전달!!
			}else {
				response.sendRedirect(resultPath);//세션 믿고 까불고 있음
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
}











