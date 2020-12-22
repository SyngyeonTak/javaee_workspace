package food;

public class Cook {
	//상위 자료형으로 has a 관계를 명시했을 때 얻는 장점
	//하위 자료형을 삭제되거나, 변화가 생기더라도 현재 클래스와의 has a관계에 있는 클래스간
	//의존성을 약화시켰기 때문에 유지보수성을 높여줄 수 있다.
	private Pan pan;//정확한 자료형으로 has a관계를 표시하지 없기
	
	//외부로부터 필요한 객체를 주입받기 위한 setter메서드
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	
	public void makeFood() {
		pan.boil();
	}
}
