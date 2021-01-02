package words;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileList {
	/* String Ÿ���� list �÷��� Ŭ���� ��ü ���� */
	private List<String> file_list;
	
	public FileList() {
		/* ���� Ŭ���� : LinkedList<String> */
		file_list=new LinkedList<String>();
	}
	/* �ܾ��� ���� ����� �ʱ�ȭ�ϴ� �޼ҵ� */
	public void Init_Word_File_List() {
		/*FileList.class ������ �ִ� ���� ��� ��ȯ�Ͽ� path�� ����*/
		String path = FileList.class.getResource("").getPath(); 
		/* path�� �ܾ��� ���� ����� ����Ǿ� �ִ� ������ ��θ� �߰� */
		File dir = new File(path+"Word_Lists/");
		/* �ܾ��� ���� ��� �迭�� �����Ͽ� ��ȯ�ϰ� fileList�� ���� */
		File[] fileList = dir.listFiles(); 
		
		/* �ܾ��� ���� ��� �迭�� for-each ���� �̿��Ͽ� �����˻��� �����ϸ鼭 LinkedList�� ���� �̸� �߰� */
		for(int i = 0 ; i < fileList.length ; i++){
			File file = fileList[i]; 
			/* �ܾ��� ���� ��� �迭�� ���������� Ž���ϸ鼭 �ش� file�� �������� ���������� Ȯ���ϰ� ������ ��, if���� ���� */
			if(file.isFile()){
				/* �����̸��� String ������ ��ȯ�Ͽ� file_list�� �߰� */
				file_list.add(file.getName());;
			}
		}
	}
	/* �ܾ��� ���� ����� ������ִ� �Լ� */
	public void Show_File_List(){
		int i=0;
		System.out.println("�ڡڡڡڴܾ��� ��ϡڡڡڡ�\n");
		/* for-each���� �̿��Ͽ� ����� �ܾ��� ����� ���������� Ž���ϸ鼭 ȭ�鿡 ��� */
		for(String s:file_list) {
			System.out.println((++i) +" �ܾ��� : " + s);
		}
	}
	/* �ܾ��� ��Ͽ��� �ܾ����� �����Ͽ� ������ �ܾ����� ��ȯ�ϴ� �޼ҵ� */
	public String Select_File() {	
		int choice=0;
		Scanner sc = new Scanner(System.in);
		System.out.print("�Ѽ��� : ");
		choice = sc.nextInt();
		
		/*���� ������ �� - 1�� �����̸� ��ȯ*/
		return file_list.get((choice-1));
	}
	
}
