/*
	������ jsp�� ����ϰ� �־��� ��Ʈ�ѷ��μ��� ������ ���� Ŭ������ �и� ��Ű��!!
	�׷��� jsp�� ������ ������ �� �Ǳ� ������ �������� �� ��ü���� �����ϴ�.
	
*/

package movie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdvisor;
import movie.model.MovieAdvisor;

public class MovieController implements Controller{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String movie = request.getParameter("movie");
		String msg = null;
		
		//�˸´� ���� ��ü���� �� ��Ų��.
		MovieAdvisor movieAdvisor = new MovieAdvisor();
		msg = movieAdvisor.getAdvice(movie);

		//4�ܰ�: Ŭ���̾�Ʈ���� ������ ����� ������ ���´�
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������!! �������
		
		
	}
	
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}














