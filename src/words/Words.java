package words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Words {
	/* String 타입의 list 컬렉션 클래스 객체 선언 */
	private List<String> list;
	
	public Words(){
		/* 구현 클래스 : LinkedList<String> */
		list = new LinkedList<String>();
	}
	public void Input_Words(String filename) {
		String read= new String();
		
		/* Words.class 파일의 현재 경로를 반환하여 path에 문자열 형태로 저장 */
		String path = Words.class.getResource("").getPath();
		/* 단어장은 Words.class가 들어있는 폴더의 하위 폴더 : Word_Lists에 있으므로 path에 경로를 추가
		 * 단어장 폴더가 추가된 경로에 파일이름을 추가 */
		path +="Word_Lists/" + filename;
		
		/* 해당 파일 경로로 파일을 오픈 */
		File fileInSamePackage = new File(path); 
		
		/* FileReader 객체 선언 및 초기화 */
		FileReader textFileReader = null;
		/* BufferReader 객체 선언 및 초기화 */
		BufferedReader bufferFileReader = null;
		
		try {
			textFileReader = new FileReader(fileInSamePackage);	
			bufferFileReader=new BufferedReader(textFileReader);
			/* 파일에서 라인 단위로 데이터 읽어오는 반복문 -> 파일끝 도착할때까지 반복*/
			while(((read=bufferFileReader.readLine())!=null)) {
				list.add(read);
			}
		}
		/* 파일에서 데이터를 불러오는 과정에 대한 오류 처리 부분*/
		catch (FileNotFoundException e) {
		    System.out.println("해당 파일이 존재하지 않습니다.");
		    e.printStackTrace();
		}
		catch (IOException e) {
        e.printStackTrace();
		}finally {
			if(bufferFileReader!=null)try {bufferFileReader.close();}catch (IOException e) {}
		}
	}
	/* 전달받은 인덱스에 해당하는 단어를 반환해주는 메소드 */
	public String Select_Word(int index) {
		return list.get(index);
	}
	/* 단어 리스트를 반환해주는 메소드 */
	public List<String> getList() {
		return list;
	}
	/* 읽어온 단어의 개수를 반환해 주는 메소드 */
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
