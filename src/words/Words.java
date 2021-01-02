package words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Words {
	/* String Ÿ���� list �÷��� Ŭ���� ��ü ���� */
	private List<String> list;
	
	public Words(){
		/* ���� Ŭ���� : LinkedList<String> */
		list = new LinkedList<String>();
	}
	public void Input_Words(String filename) {
		String read= new String();
		
		/* Words.class ������ ���� ��θ� ��ȯ�Ͽ� path�� ���ڿ� ���·� ���� */
		String path = Words.class.getResource("").getPath();
		/* �ܾ����� Words.class�� ����ִ� ������ ���� ���� : Word_Lists�� �����Ƿ� path�� ��θ� �߰�
		 * �ܾ��� ������ �߰��� ��ο� �����̸��� �߰� */
		path +="Word_Lists/" + filename;
		
		/* �ش� ���� ��η� ������ ���� */
		File fileInSamePackage = new File(path); 
		
		/* FileReader ��ü ���� �� �ʱ�ȭ */
		FileReader textFileReader = null;
		/* BufferReader ��ü ���� �� �ʱ�ȭ */
		BufferedReader bufferFileReader = null;
		
		try {
			textFileReader = new FileReader(fileInSamePackage);	
			bufferFileReader=new BufferedReader(textFileReader);
			/* ���Ͽ��� ���� ������ ������ �о���� �ݺ��� -> ���ϳ� �����Ҷ����� �ݺ�*/
			while(((read=bufferFileReader.readLine())!=null)) {
				list.add(read);
			}
		}
		/* ���Ͽ��� �����͸� �ҷ����� ������ ���� ���� ó�� �κ�*/
		catch (FileNotFoundException e) {
		    System.out.println("�ش� ������ �������� �ʽ��ϴ�.");
		    e.printStackTrace();
		}
		catch (IOException e) {
        e.printStackTrace();
		}finally {
			if(bufferFileReader!=null)try {bufferFileReader.close();}catch (IOException e) {}
		}
	}
	/* ���޹��� �ε����� �ش��ϴ� �ܾ ��ȯ���ִ� �޼ҵ� */
	public String Select_Word(int index) {
		return list.get(index);
	}
	/* �ܾ� ����Ʈ�� ��ȯ���ִ� �޼ҵ� */
	public List<String> getList() {
		return list;
	}
	/* �о�� �ܾ��� ������ ��ȯ�� �ִ� �޼ҵ� */
	public int get_Words_Num() {
		return list.size();
	}
	/*  */
	public boolean Is_Word_Same(int index, String type) {
		String temp = list.get(index);
		if(temp.equals(type)) {
			return true;
		}
		return false;
	}
}
