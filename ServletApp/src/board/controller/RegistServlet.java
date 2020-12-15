/*
 * jsp������ �����ߴ� ��Ŀ��� regist.jsp�� ����ϴ� ������
 * �������� �����غ���!!
 * */
package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.model.Board;
import board.model.BoardDAO;
import common.file.FileManager;

public class RegistServlet extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO(); //������ ������ �� �� ���� ����� ���ؼ� ��������� ���ش�.
	
	//Ŭ���̾�Ʈ�� get�� ��û : doGet(), post ��û: doPost()
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��� ��Ʈ�� �̾� ����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//Ŭ���̾�Ʈ�� ��û�� multipart/form-data����� ���Ե� ��� �ؽ�Ʈ�� �ƴ� ���̳ʸ� ���Ϸ� ���۵Ǵ�
		//���̴�. ���� ���ε� ������Ʈ�� ����ؾ� �Ѵ�.
		int maxSize = 2 * 1024 * 1024;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext context = request.getServletContext();//��û ��ü�� ServletContext ������ ������ �� �ִ�.
																						//ServletContext�� jsp���� application ���尴ü�� �����ؾ� �Ѵ�.
		String saveDir = context.getRealPath("/data");
		factory.setSizeThreshold(maxSize);
		factory.setRepository(new File(saveDir));
		factory.setDefaultCharset("utf-8");
		boolean flag = false;//���� ���ε� ���� ����
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items =  upload.parseRequest(request);//jsp�� �ƴ϶� try-catch�� �ؾ��Ѵ�.
			Board board = new Board();
			for(FileItem item : items) {
				if(item.isFormField()) {
					if(item.getFieldName().equals("title")) {
						board.setTitle(item.getString());
					}else if(item.getFieldName().equals("writer")) {
						board.setWriter(item.getString());
						
					}else if(item.getFieldName().equals("content")) {
						board.setContent(item.getString());
						
					}
				}else {
					long time = System.currentTimeMillis();
					String newName = time+"."+FileManager.getExtend(item.getName());
					//���θ������ �̸��� vo�� ��´�.
					board.setFilename(newName);
					File file = new File(saveDir+"/"+newName);
					item.write(file);
					flag = true;
				}
			}
			//db�� insert
			if(flag) {
				int result = boardDAO.insert(board);
				
				if(result==0) {
					out.print("<script>");
					out.print("alert('��Ͻ���');");
					out.print("hitory.back();");
					out.print("</script>");
				}else {
					out.print("<script>");
					out.print("alert('��ϼ���');");
					out.print("location.href = '/board/list.jsp';");
					out.print("</script>");
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}











