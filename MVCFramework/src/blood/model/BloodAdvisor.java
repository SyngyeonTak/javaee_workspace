package blood.model;

public class BloodAdvisor {
	public String getAdvice(String blood) {
		String msg = null;
		if(blood.equals("A")){
			msg = "�Ĳ��ϰ� �����ϴ�. ���ϰ� �����ϴ�. �׷��� ���� �ҽ��ϴ�.";
		}else if(blood.equals("O")){
			msg = "�米���� ����. �յ�յ��ϴ�. �׷��� �������� �������̴�.";				
		}else if(blood.equals("B")){
			msg = "���ڴ� ������ ���. ���ڴ� �����ϴ�. ���ϰ� Ȱ���ϴ�.";		
		}else if(blood.equals("AB")){
			msg = "������ְ� �ִ�. 4�����̴�.";						
		}		
		
		return msg;
	}
}