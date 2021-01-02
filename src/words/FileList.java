package words;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileList {
	/* String 타입의 list 컬렉션 클래스 객체 선언 */
	private List<String> file_list;
	
	public FileList() {
		/* 구현 클래스 : LinkedList<String> */
		file_list=new LinkedList<String>();
	}
	/* 단어장 파일 목록을 초기화하는 메소드 */
	public void Init_Word_File_List() {
		/*FileList.class 파일이 있는 현재 경로 반환하여 path에 저장*/
		String path = FileList.class.getResource("").getPath(); 
		/* path에 단어장 파일 목록이 저장되어 있는 폴더의 경로를 추가 */
		File dir = new File(path+"Word_Lists/");
		/* 단어장 파일 목록 배열을 생성하여 반환하고 fileList에 저장 */
		File[] fileList = dir.listFiles(); 
		
		/* 단어장 파일 목록 배열을 for-each 문을 이용하여 순차검색을 진행하면서 LinkedList에 파일 이름 추가 */
		for(int i = 0 ; i < fileList.length ; i++){
			File file = fileList[i]; 
			/* 단어장 파일 목록 배열을 순차적으로 탐색하면서 해당 file이 파일인지 폴더인지를 확인하고 파일일 때, if문을 실행 */
			if(file.isFile()){
				/* 파일이름을 String 형으로 반환하여 file_list에 추가 */
				file_list.add(file.getName());;
			}
		}
	}
	/* 단어장 파일 목록을 출력해주는 함수 */
	public void Show_File_List(){
		int i=0;
		System.out.println("★★★★단어장 목록★★★★\n");
		/* for-each문을 이용하여 저장된 단어장 목록을 순차적으로 탐색하면서 화면에 출력 */
		for(String s:file_list) {
			System.out.println((++i) +" 단어장 : " + s);
		}
	}
	/* 단어장 목록에서 단어장을 선택하여 선택한 단어장을 반환하는 메소드 */
	public String Select_File() {	
		int choice=0;
		Scanner sc = new Scanner(System.in);
		System.out.print("☞선택 : ");
		choice = sc.nextInt();
		
		/*실제 선택한 값 - 1의 파일이름 반환*/
		return file_list.get((choice-1));
	}
	
}
