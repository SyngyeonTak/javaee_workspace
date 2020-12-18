package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.notice.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class DetailController implements Controller{
	NoticeDAO dao = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		Notice notice = new Notice();
		notice = dao.select(notice_id);
		
		request.setAttribute("notice", notice);
		
	}

	@Override
	public String getResultView() {
		return "/result/notice/detail";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
