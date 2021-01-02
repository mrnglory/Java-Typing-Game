package player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import game.Info;
import player.Player;
import words.Words;

public class PlayerList {
	/* 플레이어 관리 파일 이름을 매크로 상수로 정의 */
	private final String DB_NAME="PlayerDB.txt";
	/* 플레이어 관리 파일에 플레이어에 관한 정보를 저장할 때, 구분자에 해당하며 -로 지정 */
	private final String DELEMETER = "-";
	/* 아이디, 이름, 레벨, 속도, 정확도에 대한 순서번호 매크로 상수 정의 */
	private int ID_SEQ=0;
	private int NAME_SEQ=1;
	private int LEVEL_SEQ=2;
	private int SPEED_SEQ=3;
	private int ACC_SEQ=4;
	/* 플레이어 관리 파일에 대한 경로를 저장하기 위한 변수 */
	private String PlayerDB_Path;
	/* 플레이어 목록을 저장할 List<Player> */
	private List<Player> list;
	
	/* LinkedList<Player> 객체? 인스턴스?를 할당하는 생성자 */
	public PlayerList() {
		list = new LinkedList<Player>();
	}
	/* 플레이어 관리 파일에서 플레이어 목록을 읽어와 플레이어 리스트를 초기화하는 메소드 */
	public void Init_Players() {
		int level=0;
		float speed=0;
		float acc=0;
		Player p = null;
		String read;
		/* 플레이어 관리 파일이 Words.class가 위치한 폴더에 존재하여, Words.class의 현재 경로를 반환하여 path에 저장 */
		String path = Words.class.getResource("").getPath(); //검색 한번 해보기
		/* Player_DB.txt 파일을 읽어들일 경로 저장 */
		PlayerDB_Path = path + DB_NAME;
		
		/* 뭐라 해야할지 모르겠음?? */
		File fileInSamePackage = new File(PlayerDB_Path); 
		
		FileReader textFileReader = null;	
		BufferedReader bufferFileReader = null;
		
		try {
			textFileReader = new FileReader(fileInSamePackage);	
			bufferFileReader=new BufferedReader(textFileReader);
			/* Player_DB.txt 파일에서 한줄씩 데이터를 읽어오기 */
			while(((read=bufferFileReader.readLine())!=null)) {
				/* 파일에서 한줄 읽어와 "-"를 기준으로 데이터를 분리하여 배열에 저장하고 해당 배열을 반환하여 data[]에 저장 */
				String data[] = read.split("-");
				/* data 배열에 저장된 문자열 데이터를 각 자료형 타입에 맞게 형변환 하여 저장 */
		        level=Integer.parseInt(data[LEVEL_SEQ]);
		        speed=Float.parseFloat(data[SPEED_SEQ]);
		        acc=Float.parseFloat(data[ACC_SEQ]);
		        /* 아이디와 이름은 원래 문자열이므로 변환할 필요없음
		         * 새로운 플레이어를 생성하면서 읽어온 데이터를 전달하여 생성자로 초기화 진행 */
				p = new Player(data[ID_SEQ], data[NAME_SEQ], level, speed, acc);
				/* 읽어온 데이터로 초기화된 플레이어 객체를 플레이어 LinkedList에 추가 */
				list.add(p);
			}
		}
		/* 오류 처리 부분 */
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
	/* 새로운 플레이어를 생성하여 해당 플레이어 객체를 반환하는 메소드 */
	public Player Make_Player() {
		String Id=null;
		/* 아무 정보가 들어가 있지 않은 플레이어 객체 생성 */
		Player p = new Player();
		/* 플레이어 객체의 Make_Player() 메소드 호출 */
		p.Make_Player();
		
		/* LinkedList가 비어있어 있지 않으면 == 저장된 플레이어가 하나라도 있으면 
		 * 중복아이디인지 아닌지 검사하는 과정*/
		if(!list.isEmpty()) {
			/* 배열 이름 : list, 타입 : Player, s는 list 배열의 처음 요소부터 끝 요소까지 순차적으로 반복문 실행 */
			for(Player s:list) {
				/* 기존 Player 객체의 아이디 반환하여 저장 */
				Id=s.getID();
				/* 기존 저장되어 있는 플레이어의 아이디와 새로 생성할 플레이어의 아이디를 비교후 같으면 중복 생성 방지 */
				if(Id.equals((p.getID()))) {
					System.out.println("★★★★중복ID 존재!! 생성 실패★★★★");
					/* ?? s? null? */
					return null;
				}
			}
		}
		/* 중복되어 있지 않거나, 저장된 플레이어가 하나도 없을 경우, 플레이어를 LinkedList에 추가 */
		list.add(p);
		System.out.println("★★★★플레이어 생성완료★★★★");
		/* 추가된 플레이어 linkedlist를 Player_DB.txt에 갱신 */
		Save_Player_List();
		/* 새로 생성된 플레이어를 반환 */
		return p;
	}
	/* 아이디를 인자로 전달받아, 플레이어 리스트에서 해당 플레이어를 찾아 반환해주는 메소드 */
	public Player Get_Player(String ID) {
		String Id=null;
		for(Player s:list) {
			Id=s.getID();
			if(Id.equals(ID)) {
				/* 아이디가 같은 플레이어가 있을 경우, 해당 플레이어에 대한 정보를 출력 */
				s.Print_Player_Info();
				return s;
			}
		}
		/* 없을 경우 null 반환 */
		return null;
	}
	/* 기존의 플레이어 리스트에서 플레이어를 불러와 매개변수인 load_player에 저장해주는 메소드 */
	public boolean Load_Player(Player load_player) {
		Scanner sc = new Scanner(System.in);
		String Id;
		System.out.println("★★★이전 Player 로드★★★");
		System.out.print("ID 입력 : ");
		/* 로드할 ID를 입력받음 */
		Id=sc.nextLine();
		/* 입력받은 ID에 해당하는 플레이어를 찾아 반환하여 매개변수인 load_player에 저장 */
		load_player = Get_Player(Id);
		/* 입력받은 ID에 해당하는 player가 없을 경우 load_player의 값은 null*/
		if(load_player==null) {
			System.out.println("☞해당 ID 없음");
			/* 해당 플레이어 없을 경우 false 반환 */
			return false;
		}
		/* 해당 플레이어 있을 경우 true 반환 */
		return true;
	}
	/* 현재 플레이어 리스트 정보를 파일에 저장하는 메소드 */
	public void Save_Player_List() {
		/* Player_DB.txt 함수가 위치한 경로를 path에 저장 */
		String path = Words.class.getResource("").getPath();
		/* Player_DB.txt를 읽어들이기 위한 경로를 File에 전달하여 객체 생성 */
		File fileInSamePackage = new File(path + DB_NAME);
		
		FileWriter textFileWriter = null;	
		BufferedWriter bufferFileWriter = null;
		
	    try {
	        ////////////////////////////////////////////////////////////////
	    	textFileWriter = new FileWriter(fileInSamePackage);	
	    	bufferFileWriter=new BufferedWriter(textFileWriter);
			
	    	/* 리스트에 대한 for-each문 */
	    	for(Player s:list) {
	    		/* s에 해당하는 플레이어의 정보를 읽어오는 과정 */
	    		Info temp_info= s.getInfo();
	    		Integer level = temp_info.getLevel();
	    		Float speed = temp_info.getSpeed();
	    		Float acc = temp_info.getAcc();
	    		/*  */
				String line=(s.getID()+DELEMETER+s.getName()+DELEMETER+level.toString()
				+DELEMETER+speed.toString()+DELEMETER+acc.toString());
				bufferFileWriter.write(line); 
				bufferFileWriter.newLine();
			}
	    	bufferFileWriter.close();
	        ////////////////////////////////////////////////////////////////
	      } catch (IOException e) {
	          System.err.println(e); // 에러가 있다면 메시지 출력
	          System.exit(1);
	      }

	    System.out.println("★★★★플레이어 목록 갱신 완료★★★★");
		return;
	}	
	public void Update_Players(Player p) {
		String Update_ID = p.getID();
		String temp_ID;
		int i = 0;
		for(Player player : list) {			
			temp_ID = player.getID();
			if(temp_ID.equals(Update_ID)) {
				list.set(i, p);
				break;
			}
			i++;
		}
	}
}