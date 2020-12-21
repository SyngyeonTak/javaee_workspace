package com.model2.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class ListController implements Controller{
	NoticeDAO dao = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notice> list =  dao.selectAll();
		
		
		request.setAttribute("noticeList", list);
		
	}

	@Override
	public String getResultView() {
		return "/result/notice/list";
	}

	public boolean isForward() {
		return true;
	}

}






