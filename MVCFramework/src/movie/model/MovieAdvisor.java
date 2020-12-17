package movie.model;

public class MovieAdvisor {
	public String getAdvice(String movie) {
		System.out.println("movie: "+movie);
		String msg = null;
		if(movie.equals("쇼생크탈출")){
			msg = "모건프리먼의 연기가 좋다\n평점:10점";
		}else if(movie.equals("대부")){
			msg = "말론 브란도의 연기가 좋다\n평점:10점";				
		}else if(movie.equals("해리포터")){
			msg = "연말에 보기 좋다\n평점:7점";					
		}else if(movie.equals("나홀로집에")){
			msg = "연말에 보기 좋다. 케빈~~\n평점:8점";											
		}		
		
		return msg;
	}
}
