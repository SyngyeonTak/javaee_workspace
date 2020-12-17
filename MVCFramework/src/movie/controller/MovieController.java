/*
	기존의 jsp가 담당하고 있었던 컨트롤러로서의 업무를 현재 클래스로 분리 시키자!!
	그래야 jsp는 순수한 디자인 이 되기 때문에 유지보수 시 교체까지 가능하다.
	
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
		
		//알맞는 로직 객체에게 일 시킨다.
		MovieAdvisor movieAdvisor = new MovieAdvisor();
		msg = movieAdvisor.getAdvice(movie);

		//4단계: 클라이언트에게 전달할 결과를 저장해 놓는다
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		//클라이언트로 하여금 지정한 url로 재접속을 유도하자!! 명령하자
		
		
	}
	
	public String getViewPage() {
		return "/movie/movie_result.jsp";
	}
}














