/*
 MVC 패턴으로 개발하다보면, 늘어나는 컨트롤러에 대해 일일이 매핑과정을 진행해야 한다..
 이때 너무 많은 매핑은 관리하기가 까다롭다 따라서 유지보수성이 떨어진다.
 현실과 유사하게 , 어플리케이션에서도 대형업부처리 시 클라이언트에 요청을 곧바로 해당 컨트롤러에게
 처리하게 하지 않고 중간에 메인 컨트롤러를 두고, 모든 요청을 이 메인 컨트롤러에서 처리하여
 적절한 하위 컨트롤러에게 전담시키는 방식을 이용한다...
 
 이 컨트롤러는 웹 어플리케이션의 모든 요청을 1차적으로 처리해야한다.
 */
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	FileInputStream fis;
	Properties props;
	
	//init은 서블릿의 생명주기에서 최초 접속자에 의해 톰켓이 인스턴스를 생성하며 이와 동시에 초기화 메서드로
	//호출된다..
	public void init(ServletConfig config) throws ServletException {
		//getRealPath는 JSP의 내장객체 중 application에 대한 정보를 갖는 application 내장객체에서 지원함
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");//파일명 유지보수는 자바에서하는 것이 아니라 web.xml에서 한다.
		String savePath = context.getRealPath(contextConfigLocation); 
		System.out.println(savePath);
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서블릿이 죽을 때  inputstream을 죽여야 한다. 서블릿의 생명주기 메서드 중 서블릿이 소멸할 때 호출되는 메서드인 destroy()에 스트림 등의 자원을
	//닫는 처리를 하자!!
	public void destroy() {
		try {
			if(fis != null) fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dorequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dorequest(request, response);
	}
	
	//get or post 상관없이 모든 요청을 이 메서드에서 처리하자!!
	//요청을 하나의 진입점으로 모은다. *.do
	public void dorequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		//1단계: 요청을 받는다!!
		//클라이언트가 영화를 원하면? --> 영화 담당 컨트롤러에게 전가		
		//클라이언트가 혈액형을 원하면? --> 혈액형 담당 컨트롤러에게 전가
		
		//2단계: 요청을 분석하여, 알맞는 컨트롤러에게 요청을 전달
		//글쓰기?, 삭제?, 형랙형?
		//클라이언트가 원하는게 무엇인지를 알아야 한다..
		//해답은 클라이언트 요청 자체에 있다.. 즉 요청 시 사용된 URI가 곧 요청 구분값이다!!
		String uri = request.getRequestURI();
		String className = null;
		Controller controller = null;
		
		//if문 대신, 프로퍼티스 객체를 이용하여 key와 메모리 올려질 컨트롤러의 이름을 value로 반환 받자
		className = props.getProperty(uri);
		
		try {
			Class controllerClass = Class.forName(className);//클래스 로드
			//인스턴스 생성
			 controller = (Controller)controllerClass.newInstance();
			
			//다형적으로 실행된다.(movie로 실행됐다가, blood로 실행했다가....)
			controller.execute(request, response);//2단계: 하위컨트롤러에 전달
			
			//5단계: 클라이언트에게 알맞는 결과를 보여준다.
			response.sendRedirect(controller.getViewPage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}











