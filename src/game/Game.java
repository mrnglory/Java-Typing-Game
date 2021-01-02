package game;

import words.*;

import java.util.Random;
import java.util.Scanner;
import game.Round;
import player.*;

public class Game {
	/* ���� ������ �ܾ� ����Ʈ ���� */
	private Words word;
	/* �ܾ��� ����Ʈ ����  */
	private FileList file;
	/* ���� ���� �÷��̾� ���� */
	private Player p;
	/* ���ӿ� ��ϵ� �÷��̾� ����Ʈ ���� */
	private PlayerList pl;
	/* ���� �ܾ� ���� ����� ���� ������ �迭 */
	private int[] rand_num;
	/* ������� �Է��� �ܾ� ���� ��� */
	private int count;
	/* �� ���忡 ���� ���� ���� */
	private Round r;
	
	/* ��ũ�� ��� ���� */
	final int GAME_START = 1;
	final int GAME_EXIT = 2;
	final int SELECT_PRIOR_PLAYER = 2;
	final int CONTINUE = 1;
	
	/* ��ũ�� ��� ���� */
	private final int SELECT_PLAYER=2;
	private final int MAKE_PLAYER=1;	
	
	/* ���� ���α׷� ���� */
	public static void main(String[] args) {
		/* ���� ���� �� ������ �ʱ�ȭ */
		Game game = new Game();
		/* ���� ���� */
		game.Game_Process();
	}
	/* ���� ������ */
	public Game() {
		/* ���� ���� �� �Է� �ܾ� 0���� �ʱ�ȭ */
		count = 0;
		/* ���Ͽ��� �ܾ� ����Ʈ�� �о�� ������ ���� �Ҵ� �� ������ �ʱ�ȭ */
		word = new Words();
		/* �ܾ��� ���� ����Ʈ�� �о�� ������� ������ ���� �Ҵ� �� ������ �ʱ�ȭ */
		file = new FileList();
		/* ������ �����ϴ� �÷��̾ ������ ���� �Ҵ� �� ������ �ʱ�ȭ */
		p = new Player();
		/* ���ӿ� ��ϵ� �÷��̾� ����Ʈ �ҷ��� ������ ���� �Ҵ� �� ������ �ʱ�ȭ */
		pl = new PlayerList();
		/* �� ���忡 ���� ������ ������ ���� �Ҵ� �� ������ �ʱ�ȭ */
		r = new Round();
	}
	/* ������ �����ϴ� �޼ҵ� */
	public void Game_Process() {
		/* ���� ���� �� �ܾ��� ����Ʈ�� ��ϵ� �÷��̾�鿡 ���� �ʱ�ȭ ���� �޼ҵ� */
		Init_Game();
		/* 1.���� ����, 2. ���α׷� ���� �޴� �� ���� �� ���ÿ� ���� ó�� ���� �޼ҵ� */
		if(Select_Main_Menu()== GAME_EXIT) {
			/* 2. ���α׷� ���� ���ý� ���� ���� */
			Game_Exit();
		}
		/* 1. ���� ���� ���� �� �̾ ���� */
		/* 1.Player ����, 2. ���� Player �ε� �޴� �� ���� �� ���ÿ� ���� ó�� ���� �޼ҵ� */
		Select_Player_Menu();
		while(true) {
			/* �ܾ����� ������ �ܾ� ����Ʈ�� �ҷ��� �����ϰ�, �ش� �ܾ� ������ŭ�� ���� �迭�� �����Ͽ� ���� ������ �����ϴ� �޼ҵ� */
			Game_Start();
			Continue_Game();
		}
	}
	/* ���� ���� �� �ܾ��� ����Ʈ�� ��ϵ� �÷��̾�鿡 ���� �ʱ�ȭ ���� �޼ҵ� */
	public void Init_Game() {
		/* �ܾ��� ����Ʈ�� �о�� �����ϴ� �޼ҵ� */
		file.Init_Word_File_List();
		/* ��ϵ� �÷��̾� ����Ʈ�� �о�� �����ϴ� �޼ҵ� */
		pl.Init_Players();
	}
	/* 1.���� ����, 2. ���α׷� ���� �޴� �� ���� �� ���ÿ� ���� ó�� ���� �޼ҵ� */
	public int Select_Main_Menu() {
		/* �޴� ������ ������ ������ ���� */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("��Ƽ�̵�� Ÿ�ڿ� ����\n\n");
		System.out.println("--�޴�--");
		System.out.println("1. ���� ����");
		System.out.println("2. ���α׷� ����");
		System.out.print("�Ѽ��� : ");
		choice=sc.nextInt();
		
		/* ���ð� ��ȯ */
		return choice; 
	}
	public void Select_Player_Menu() {
		/* �޴� ������ ������ ������ ���� */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�ڡڡڰ��� ���ۡڡڡ�");
		System.out.println();
		System.out.println("--�޴�--");
		System.out.println("1. Player ����");
		System.out.println("2. ���� Player �ε�");
		System.out.print("�Ѽ��� : ");
		
		choice=sc.nextInt();
		
		/* 2. ���� �÷��̾� �ε� ���ý� if�� ���� */
		if(choice == SELECT_PLAYER) {
			/* ���� �÷��̾� �ε带 �����ϴ� �޼ҵ� -> ���������� �ҷ��� ��� return true, ������ ��� return false */
			if((pl).Load_Player(p)) {
				/* ���������� �ҷ��� ��� �÷��̾� �ε� �Լ� return */
				return;
			}
		}
		/* �÷��̾� �ε� ���н� �÷��̾� ���� �޼ҵ� ���� �� ������ �÷��̾ ��ȯ�Ͽ� p�� ���� */
		p = pl.Make_Player();
	}
	public void Game_Start() {
		Scanner sc = new Scanner(System.in);
		/* �ܾ� �Է� �ޱ� ������ �ð� ���� */
		long start = 0;
		/* �ܾ� �Է� ���� ������ �ð� ���� */
		long end = 0;
		/* �� �ܾ ���� ��Ȯ�� ���� ���� */
		float acc=0;
		/* �� �ܾ ���� �ӵ� ���� ���� */
		float speed=0;
		String Word_to_Type;
		/* �� ���� ���۽� �Է� �ܾ� �� 0���� �ʱ�ȭ  */
		count = 0;
		
		/* �ܾ��� ����� �����ְ�, �ܾ����� �����Ͽ�, �ش� �ܾ��忡�� �ܾ� ����Ʈ�� �ҷ��� word ��ü�� �����ϴ� �޼ҵ� */
		Select_Words();
		/* �о�� �ܾ� ����Ʈ�� �ܾ� ������ŭ �ߺ����� ���� �迭�� �����ϴ� �޼ҵ� */
		Make_Random_Sequence();
		/* �÷��̾ �Է��� �ܾ ������ String ���� */
		String type_word;
		System.out.println("�ڡڡڡڰ��� ���ۡڡڡڡ�");
		/* ���带 1���� ��ŭ ������Ű�� �޼ҵ� */
		r.Add_Round();
		/* �о�� �ܾ� ����Ʈ�� ���� �÷��̾��� �Է��� ������ ���� ������ �����ϴ� �ݺ��� */
		for(int i=0;i<rand_num.length;i++) {
			while(true){
				/* �Է��ؾ��� �ܾ �о�� Word_to_Type ������ ���� */
				Word_to_Type = word.Select_Word(rand_num[i]);
				/* �ܾ� �Է� �ޱ� ������ �ð� ���� */
				start = System.currentTimeMillis();
				System.out.println(Word_to_Type);
				type_word=sc.nextLine();
				/* �ܾ� �Է� ���� ������ �ð� ���� */
				end = System.currentTimeMillis();
				/* �ܾ� �Է��� �ð�, �ܾ� �Է��� �ð�, �Է��� �ܾ��� ���̸� ���޹޾� 
				 * �ܾ� �Է� �ӵ��� ����Ͽ� ��ȯ�� �ִ� �޼ҵ� �� speed ������ ���� */
				speed = Check_Speed(start,end,type_word.length());
				/* �÷��̾ �Է��� �ܾ�� �Է��ؾ��� �ܾ ���޹޾� ���Ͽ� ��Ȯ���� ��ȯ�� �ִ� �޼ҵ� �� ��Ȯ���� acc ������ ���� */
				acc = Check_Accuracy(type_word, Word_to_Type);
				/* ��Ȯ���� 60%�� ���� ��� ���� �ܾ�� ����, ���� ���� ��� ���Է� */
				if(acc>60.) {
					break;
				}
			}
			/* �� �ܾ ���� ��Ȯ���� �ӵ��� ���忡 ���� */
			r.Insert_Info(acc,speed);
			/* �Է��� �ܾ� 1��ŭ ���� */
			count++;
		}
		/* �� ���� ���� �� ���忡 ���� ���� ������ �÷��̾� ������ ������Ʈ�ϴ� �޼ҵ� */
		p.Update_Info(r.Show_Total_Result());
		/* ������Ʈ�� �÷��̾� ������ �÷��̾� ����Ʈ�� �ݿ��ϴ� �޼ҵ�  */
		pl.Update_Players(p);
		/* ����� �÷��̾� ����Ʈ�� ���� ������ ���Ͽ� ������Ʈ�ϴ� �޼ҵ� */
		pl.Save_Player_List();
	}
	/* �ܾ��� ����� �����ְ�, �ܾ����� �����Ͽ�, �ش� �ܾ��忡�� �ܾ� ����Ʈ�� �ҷ��� word ��ü�� �����ϴ� �޼ҵ� */
	public void Select_Words() {
		/* �ܾ��� ����� �����ִ� �޼ҵ� */
		file.Show_File_List();
		/* file.Select_File() : �ܾ��� ����Ʈ���� �ܾ����� �����Ͽ� ������ �ܾ����� ������ String ������ ��ȯ���ִ� �޼ҵ�
		 * word.Input_Words(String filename) : ���� ������ String ������ ���޹޾� �ش� ���Ͽ��� 
		 * �ܾ� ����Ʈ�� �о�� word ��ü�� ���������� �����ϴ� �޼ҵ�
		 * */
		word.Input_Words(file.Select_File());
	}
	/* �÷��̾ �Է��� �ܾ ���޹޾� �Է��ؾ��� �ܾ�� ���Ͽ� ��Ȯ���� ��ȯ�� �ִ� �޼ҵ� */
	public float Check_Accuracy(String input_words, String word_to_type) {
		float acc=0;
		/* �Է��ؾ��� �ܾ test_word�� ���� */
		String test_word = word_to_type;
		/* �÷��̾ �Է��� �ܾ��� ���̸�ŭ �Է��ؾ��� �ܾ�� ��Ȯ�� �� */
		for(int i=0;i<input_words.length();i++) {
			/* �÷��̾ �Է��� �ܾ �Է��ؾ��� �ܾ��� ���̸� �ʰ��� ��� �� �ߴ� */
			if(i>=test_word.length()) {
				break;
			}
			/* �ܾ� ���ڸ� ���� �񱳸� �ϸ鼭 ��ġ�� ���, ��Ȯ���� 1��ŭ ���� */
			if(test_word.charAt(i)==input_words.charAt(i)) {
				acc+=1;
			}
		}
		/* ��Ȯ���� �Է��ؾ��� �ܾ��� ���̷� ���� �� ���� ���� */
		acc/=test_word.length();
		/* ���ϱ� 100�� �Ͽ� %�� ���Ͽ� ���� */
		acc*=100;
		/* �ش� �ܾ ���� ����� ��Ȯ�� acc�� ��ȯ */
		return acc;
	}
	/* �ܾ� �Է��� �ð�, �ܾ� �Է��� �ð�, �Է��� �ܾ��� ���̸� ���޹޾� 
	 * �ܾ� �Է� �ӵ��� ����Ͽ� ��ȯ�� �ִ� �޼ҵ� �� speed ������ ���� */
	public float Check_Speed(long start, long end,int length) {
		/* end-start : �ܾ� �Է¿� �ɸ� �ð�
		 * (float)length / (end-start))*10000 : �д� Ÿ�ڼ�
		 * speed = �� �ܾ� �Է� �ӵ�
		 * */
		float speed = ((float)length / (end-start))*100000+80;
		/* �� �ܾ ���� ����� �ܾ� �Է� �ӵ��� ��ȯ */
		return speed;
	}
	/* �о�� �ܾ� ����Ʈ�� �ܾ� ������ŭ �ߺ����� ���� �迭�� �����ϴ� �޼ҵ� */
	public void Make_Random_Sequence() {
		/* �о�� �ܾ��� ������ŭ ������ �迭 ���� �Ҵ� */
		rand_num=new int[word.get_Words_Num()];
		/* ���� ������ ���� Ŭ���� ��ü ���� */
		Random r= new Random();
		
		/* �о�� �ܾ��� ������ŭ ���� �Ҵ� ������ �ݺ� */
		for(int i=0;i<rand_num.length;i++) {
			/* r.nextInt(rand_num.length) : 0 ~ (rand_num.length - 1) �������� �������� ���� �����Ͽ� ��ȯ
			 * rand_num[i] = ��ȯ�� : ��ȯ���� rand_num[i]�� ���� */
			rand_num[i] = r.nextInt(rand_num.length);
			/* �ߺ� ����  Ȯ�ο� ���� �ʱ�ȭ */
			/* random_num �迭�� 0��° �ε������� ������� �����Ǿ� random_num �迭�� ����� �ε������� 
			 * ���� �����Ǿ� random ������ ����Ǿ� �ִ� ������ ���� �ߺ� ���θ� Ȯ�� */
			for(int j=0;j<i;j++) {
				/* �ߺ��� ������ ��� */
				if(rand_num[j]==rand_num[i]) {
					/* �ߺ��� 1�� ���� */
					i--;
					/* �ݺ� Ȯ�� �ߴ� */
					break;
				}
			}
		}
	}
	/* ���� ���� �Լ� */
	public void Game_Exit() {
		System.exit(1);
	}
	/* ������ ����Ұ����� ���� �޼ҵ� */
	public void Continue_Game() {
		/* ���ð� ���� ���� */
		int choice =0;
		Scanner sc = new Scanner(System.in);
		System.out.println("�ٽ� �Ͻðڽ��ϱ�?");
		System.out.println("1. yes : �ٽ�");
		System.out.println("2. no : ��������");
		System.out.print("�Ѽ��� : ");
		choice = sc.nextInt();
		/* ���� ���ý� ���� ���� �Լ� ȣ�� */
		if(choice == GAME_EXIT) {
			Game_Exit();
		}
	}
}
