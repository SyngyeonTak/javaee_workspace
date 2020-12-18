/*
 	�� ���� ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ�
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
	JSONObject controllerMap;//��Ʈ�ѷ��� �������� ����ִ� ��
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
			
			//�Ľ�!!
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
		//if���� ����� ����ȭ�� �����͸� ��������!!(xml, json, properties)
		className = (String)controllerMap.get(uri);
		System.out.println(className);
		
		try {
			Class controllerClass =  Class.forName(className);//���ڿ��� ������ Ŭ������ ���� ���� Ŭ������ ��ȯ�Ѵ�..
			controller = (Controller)controllerClass.newInstance();//������ Ŭ������ �ν��Ͻ��� ��ȯ�Ѵ�.
			controller.execute(request, response);
			resultPath = (String)viewMap.get(controller.getResultView());
			//���� �� sendRedirect �� ó���ؾ� �� ��찡 �ְ�, �� �ۼ� �� ����Ʈ, ���� �ٸ� �������� �������� ������ ��
			if(controller.isForward()){
				//���δ� forwarding ó���ؾ� �� ��찡 �ִ�..
				//�����͸� �����ϰ� ���� ��...������ ��� ����ϸ� ������ �ִ�.
				RequestDispatcher dis =request.getRequestDispatcher(resultPath);
				dis.forward(request, response);//���� ����, �������� �� �ٸ� �ڿ����� ��û�� ����!!
			}else {
				response.sendRedirect(resultPath);//���� �ϰ� ��Ұ� ����
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











