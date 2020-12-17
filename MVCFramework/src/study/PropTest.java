/*
 * �ڹ��� �÷��� �����ӿ�ũ�� ��ü �� ������ key-value�� ������ �Ǿ��ִ� ������ �����Ͽ�
 * ó���� �� �ִ� ��ü�� properties�� �Ѵ�!!
 * �� ������ ���� ������ ������ ���� �� ���� key-value�� �� �ָ��� �����Ѵ�!!
 * */
package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	
	public PropTest() {
		//�������� �ڹ��ڵ忡�� Ư�� ���丮�� ����ִ� ���������� ���� �����ؾ���
		FileInputStream fis = null;
		Properties props= new Properties();
		try {
			fis = new FileInputStream("C:/study/ETC/academy/workspace/javaee_workspace/MVCFramework/WebContent/WEB-INF/mapping/mapping.properties");
			props.load(fis);//������Ƽ�� ��ü�� ��Ʈ�� ����
			//���ݺ��ʹ� key������ �˻��� �����ϴ�!!
			String value = props.getProperty("/movie.do");
			System.out.println(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		new PropTest();
	}
}