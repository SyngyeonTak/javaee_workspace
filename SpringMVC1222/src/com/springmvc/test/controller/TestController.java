package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*�� Ŭ������ ��û�� ���������� ó���ϴ� ��Ʈ�ѷ����� ���
 * �߱��ϴ� ��ǥ: POJO���(����� Ŭ���� ���)���� ������ ������ ����...
 * */

//@�ּ��� �ν��Ͽ� ������ import�ȴ�.
//controller �Ӽ��� ���� ��Ʈ�ѷ��� ����

public class TestController implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3�ܰ�: �˸´� ���� ��ü�� �Ͻ�Ų��..
		String msg = "�ȳ�";
		
		//4�ܰ�: ������ ���� �ִٸ� request��ü�� ����!! ModelAndView�ȿ� request��ü�� �� ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		
		
		//���� ��Ʈ�ѷ��� � �������� ������������� ���� ������ ������ mav�� ����
		mav.setViewName("test/result");
		return mav;
	}
	
	//��� URL ��û�� ó�������� �޼��� �������� �ۼ��ϴ� ����
	
}
