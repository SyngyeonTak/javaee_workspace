/*
 * ���ϰ� ���õ� ������ ����� ��Ƴ��� Ŭ����
 * */

package common.file;

import java.io.File;

public class FileManager {
	//Ȯ���ڸ� �����ϱ�

	public static String getExtend(String path) {
		int index = path.lastIndexOf(".");
		String ext = path.substring(index+1,path.length());
		return ext;
	}
	
	public static boolean deleteFile(String path) {
		File file = new File(path);
		
		return file.delete();
	}
}












