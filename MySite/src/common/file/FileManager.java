/*
 * 파일과 관련된 유용한 기능을 모아놓는 클래스
 * */

package common.file;

public class FileManager {
	//확장자만 추출하기

	public static String getExtend(String path) {
		int index = path.lastIndexOf(".");
		String ext = path.substring(index+1,path.length());
		return ext;
	}
	
}












