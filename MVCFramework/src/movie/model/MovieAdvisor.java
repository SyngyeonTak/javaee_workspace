package movie.model;

public class MovieAdvisor {
	public String getAdvice(String movie) {
		System.out.println("movie: "+movie);
		String msg = null;
		if(movie.equals("���ũŻ��")){
			msg = "����������� ���Ⱑ ����\n����:10��";
		}else if(movie.equals("���")){
			msg = "���� ������� ���Ⱑ ����\n����:10��";				
		}else if(movie.equals("�ظ�����")){
			msg = "������ ���� ����\n����:7��";					
		}else if(movie.equals("��Ȧ������")){
			msg = "������ ���� ����. �ɺ�~~\n����:8��";											
		}		
		
		return msg;
	}
}
