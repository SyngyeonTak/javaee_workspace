package blood.model;

public class BloodAdvisor {
	public String getAdvice(String blood) {
		String msg = null;
		if(blood.equals("A")){
			msg = "꼼꼼하고 세심하다. 착하고 솔직하다. 그러나 때론 소심하다.";
		}else if(blood.equals("O")){
			msg = "사교성이 좋다. 둥들둥들하다. 그러나 쓸데없이 오지랖이다.";				
		}else if(blood.equals("B")){
			msg = "남자는 고집이 쎄다. 여자는 엉뚱하다. 쿨하고 활발하다.";		
		}else if(blood.equals("AB")){
			msg = "결정장애가 있다. 4차원이다.";						
		}		
		
		return msg;
	}
}
