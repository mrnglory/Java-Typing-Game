package game;

import words.*;

import java.util.Random;
import java.util.Scanner;
import game.Round;
import player.*;

public class Game {
	/* 라운드 진행할 단어 리스트 저장 */
	private Words word;
	/* 단어장 리스트 저장  */
	private FileList file;
	/* 게임 진행 플레이어 저장 */
	private Player p;
	/* 게임에 등록된 플레이어 리스트 저장 */
	private PlayerList pl;
	/* 게임 단어 랜덤 출력을 위한 정수형 배열 */
	private int[] rand_num;
	/* 현재까지 입력한 단어 개수 계산 */
	private int count;
	/* 한 라운드에 대한 정보 저장 */
	private Round r;
	
	/* 매크로 상수 지정 */
	final int GAME_START = 1;
	final int GAME_EXIT = 2;
	final int SELECT_PRIOR_PLAYER = 2;
	final int CONTINUE = 1;
	
	/* 매크로 상수 지정 */
	private final int SELECT_PLAYER=2;
	private final int MAKE_PLAYER=1;	
	
	/* 실제 프로그램 시작 */
	public static void main(String[] args) {
		/* 게임 생성 및 생성자 초기화 */
		Game game = new Game();
		/* 게임 진행 */
		game.Game_Process();
	}
	/* 게임 생성자 */
	public Game() {
		/* 게임 시작 전 입력 단어 0으로 초기화 */
		count = 0;
		/* 파일에서 단어 리스트를 읽어와 저장할 공간 할당 및 생성자 초기화 */
		word = new Words();
		/* 단어장 파일 리스트를 읽어와 제목들을 저장할 공간 할당 및 생성자 초기화 */
		file = new FileList();
		/* 게임을 시작하는 플레이어를 저장할 공간 할당 및 생성자 초기화 */
		p = new Player();
		/* 게임에 등록된 플레이어 리스트 불러와 저장할 공간 할당 및 생성자 초기화 */
		pl = new PlayerList();
		/* 한 라운드에 대한 정보를 저장할 공간 할당 및 생성자 초기화 */
		r = new Round();
	}
	/* 게임을 진행하는 메소드 */
	public void Game_Process() {
		/* 게임 시작 전 단어장 리스트와 등록된 플레이어들에 대한 초기화 진행 메소드 */
		Init_Game();
		/* 1.게임 시작, 2. 프로그램 종료 메뉴 중 선택 및 선택에 따른 처리 진행 메소드 */
		if(Select_Main_Menu()== GAME_EXIT) {
			/* 2. 프로그램 종료 선택시 게임 종료 */
			Game_Exit();
		}
		/* 1. 게임 시작 선택 후 이어서 진행 */
		/* 1.Player 생성, 2. 이전 Player 로드 메뉴 중 선택 및 선택에 따른 처리 진행 메소드 */
		Select_Player_Menu();
		while(true) {
			/* 단어장을 선택해 단어 리스트를 불러와 저장하고, 해당 단어 개수만큼의 난수 배열을 생성하여 실제 게임을 시작하는 메소드 */
			Game_Start();
			Continue_Game();
		}
	}
	/* 게임 시작 전 단어장 리스트와 등록된 플레이어들에 대한 초기화 진행 메소드 */
	public void Init_Game() {
		/* 단어장 리스트를 읽어와 저장하는 메소드 */
		file.Init_Word_File_List();
		/* 등록된 플레이어 리스트를 읽어와 저장하는 메소드 */
		pl.Init_Players();
	}
	/* 1.게임 시작, 2. 프로그램 종료 메뉴 중 선택 및 선택에 따른 처리 진행 메소드 */
	public int Select_Main_Menu() {
		/* 메뉴 선택을 저장할 정수형 변수 */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("멀티미디어 타자왕 게임\n\n");
		System.out.println("--메뉴--");
		System.out.println("1. 게임 시작");
		System.out.println("2. 프로그램 종료");
		System.out.print("☞선택 : ");
		choice=sc.nextInt();
		
		/* 선택값 반환 */
		return choice; 
	}
	public void Select_Player_Menu() {
		/* 메뉴 선택을 저장할 정수형 변수 */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("★★★게임 시작★★★");
		System.out.println();
		System.out.println("--메뉴--");
		System.out.println("1. Player 생성");
		System.out.println("2. 이전 Player 로드");
		System.out.print("☞선택 : ");
		
		choice=sc.nextInt();
		
		/* 2. 이전 플레이어 로드 선택시 if문 실행 */
		if(choice == SELECT_PLAYER) {
			/* 이전 플레이어 로드를 진행하는 메소드 -> 성공적으로 불러올 경우 return true, 실패할 경우 return false */
			if((pl).Load_Player(p)) {
				/* 성공적으로 불러올 경우 플레이어 로드 함수 return */
				return;
			}
		}
		/* 플레이어 로드 실패시 플레이어 생성 메소드 실행 및 생성한 플레이어를 반환하여 p에 저장 */
		p = pl.Make_Player();
	}
	public void Game_Start() {
		Scanner sc = new Scanner(System.in);
		/* 단어 입력 받기 직전의 시간 저장 */
		long start = 0;
		/* 단어 입력 받은 직후의 시간 저장 */
		long end = 0;
		/* 한 단어에 대한 정확도 저장 변수 */
		float acc=0;
		/* 한 단어에 대한 속도 저장 변수 */
		float speed=0;
		String Word_to_Type;
		/* 매 라운드 시작시 입력 단어 수 0으로 초기화  */
		count = 0;
		
		/* 단어장 목록을 보여주고, 단어장을 선택하여, 해당 단어장에서 단어 리스트를 불러와 word 객체에 저장하는 메소드 */
		Select_Words();
		/* 읽어온 단어 리스트의 단어 개수만큼 중복없는 난수 배열을 생성하는 메소드 */
		Make_Random_Sequence();
		/* 플레이어가 입력할 단어를 저장할 String 변수 */
		String type_word;
		System.out.println("★★★★게임 시작★★★★");
		/* 라운드를 1라운드 만큼 증가시키는 메소드 */
		r.Add_Round();
		/* 읽어온 단어 리스트에 대한 플레이어의 입력을 생성된 난수 순서로 진행하는 반복문 */
		for(int i=0;i<rand_num.length;i++) {
			while(true){
				/* 입력해야할 단어를 읽어와 Word_to_Type 변수에 저장 */
				Word_to_Type = word.Select_Word(rand_num[i]);
				/* 단어 입력 받기 직전의 시간 저장 */
				start = System.currentTimeMillis();
				System.out.println(Word_to_Type);
				type_word=sc.nextLine();
				/* 단어 입력 받은 직후의 시간 저장 */
				end = System.currentTimeMillis();
				/* 단어 입력전 시간, 단어 입력후 시간, 입력한 단어의 길이를 전달받아 
				 * 단어 입력 속도를 계산하여 반환해 주는 메소드 및 speed 변수에 저장 */
				speed = Check_Speed(start,end,type_word.length());
				/* 플레이어가 입력한 단어와 입력해야할 단어를 전달받아 비교하여 정확도를 반환해 주는 메소드 및 정확도를 acc 변수에 저장 */
				acc = Check_Accuracy(type_word, Word_to_Type);
				/* 정확도가 60%를 넘을 경우 다음 단어로 진행, 넘지 않을 경우 재입력 */
				if(acc>60.) {
					break;
				}
			}
			/* 한 단어에 대한 정확도와 속도를 라운드에 저장 */
			r.Insert_Info(acc,speed);
			/* 입력한 단어 1만큼 증가 */
			count++;
		}
		/* 한 라운드 진행 후 라운드에 대한 최종 정보를 플레이어 정보에 업데이트하는 메소드 */
		p.Update_Info(r.Show_Total_Result());
		/* 업데이트된 플레이어 정보를 플레이어 리스트에 반영하는 메소드  */
		pl.Update_Players(p);
		/* 변경된 플레이어 리스트에 대한 정보를 파일에 업데이트하는 메소드 */
		pl.Save_Player_List();
	}
	/* 단어장 목록을 보여주고, 단어장을 선택하여, 해당 단어장에서 단어 리스트를 불러와 word 객체에 저장하는 메소드 */
	public void Select_Words() {
		/* 단어장 목록을 보여주는 메소드 */
		file.Show_File_List();
		/* file.Select_File() : 단어장 리스트에서 단어장을 선택하여 선택한 단어장의 제목을 String 형으로 반환해주는 메소드
		 * word.Input_Words(String filename) : 파일 제목을 String 형으로 전달받아 해당 파일에서 
		 * 단어 리스트를 읽어와 word 객체에 순차적으로 저장하는 메소드
		 * */
		word.Input_Words(file.Select_File());
	}
	/* 플레이어가 입력한 단어를 전달받아 입력해야할 단어와 비교하여 정확도를 반환해 주는 메소드 */
	public float Check_Accuracy(String input_words, String word_to_type) {
		float acc=0;
		/* 입력해야할 단어를 test_word에 저장 */
		String test_word = word_to_type;
		/* 플레이어가 입력한 단어의 길이만큼 입력해야할 단어와 정확도 비교 */
		for(int i=0;i<input_words.length();i++) {
			/* 플레이어가 입력한 단어가 입력해야할 단어의 길이를 초과할 경우 비교 중단 */
			if(i>=test_word.length()) {
				break;
			}
			/* 단어 각자리 마다 비교를 하면서 일치할 경우, 정확도를 1만큼 증가 */
			if(test_word.charAt(i)==input_words.charAt(i)) {
				acc+=1;
			}
		}
		/* 정확도를 입력해야할 단어의 길이로 나눈 후 몫을 저장 */
		acc/=test_word.length();
		/* 곱하기 100을 하여 %로 구하여 저장 */
		acc*=100;
		/* 해당 단어에 대해 계산한 정확도 acc를 반환 */
		return acc;
	}
	/* 단어 입력전 시간, 단어 입력후 시간, 입력한 단어의 길이를 전달받아 
	 * 단어 입력 속도를 계산하여 반환해 주는 메소드 및 speed 변수에 저장 */
	public float Check_Speed(long start, long end,int length) {
		/* end-start : 단어 입력에 걸린 시간
		 * (float)length / (end-start))*10000 : 분당 타자수
		 * speed = 한 단어 입력 속도
		 * */
		float speed = ((float)length / (end-start))*100000+80;
		/* 한 단어에 대해 계산한 단어 입력 속도를 반환 */
		return speed;
	}
	/* 읽어온 단어 리스트의 단어 개수만큼 중복없는 난수 배열을 생성하는 메소드 */
	public void Make_Random_Sequence() {
		/* 읽어온 단어의 개수만큼 정수형 배열 공간 할당 */
		rand_num=new int[word.get_Words_Num()];
		/* 난수 생성을 위한 클래스 객체 생성 */
		Random r= new Random();
		
		/* 읽어온 단어의 개수만큼 난수 할당 과정을 반복 */
		for(int i=0;i<rand_num.length;i++) {
			/* r.nextInt(rand_num.length) : 0 ~ (rand_num.length - 1) 범위에서 랜덤으로 난수 생성하여 반환
			 * rand_num[i] = 반환값 : 반환값을 rand_num[i]에 저장 */
			rand_num[i] = r.nextInt(rand_num.length);
			/* 중복 여부  확인에 대해 초기화 */
			/* random_num 배열의 0번째 인덱스부터 현재까지 생성되어 random_num 배열에 저장된 인덱스까지 
			 * 지금 생성되어 random 변수에 저장되어 있는 난수에 대해 중복 여부를 확인 */
			for(int j=0;j<i;j++) {
				/* 중복이 존재할 경우 */
				if(rand_num[j]==rand_num[i]) {
					/* 중복값 1개 제거 */
					i--;
					/* 반복 확인 중단 */
					break;
				}
			}
		}
	}
	/* 게임 종료 함수 */
	public void Game_Exit() {
		System.exit(1);
	}
	/* 게임을 계속할건지를 묻는 메소드 */
	public void Continue_Game() {
		/* 선택값 저장 변수 */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		System.out.println("다시 하시겠습니까?");
		System.out.println("1. yes : 다시");
		System.out.println("2. no : 게임종료");
		System.out.print("☞선택 : ");
		choice = sc.nextInt();
		/* 종료 선택시 게임 종료 함수 호출 */
		if(choice == GAME_EXIT) {
			Game_Exit();
		}
	}
}
