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
	/* �÷��̾� ���� ���� �̸��� ��ũ�� ����� ���� */
	private final String DB_NAME="PlayerDB.txt";
	/* �÷��̾� ���� ���Ͽ� �÷��̾ ���� ������ ������ ��, �����ڿ� �ش��ϸ� -�� ���� */
	private final String DELEMETER = "-";
	/* ���̵�, �̸�, ����, �ӵ�, ��Ȯ���� ���� ������ȣ ��ũ�� ��� ���� */
	private int ID_SEQ=0;
	private int NAME_SEQ=1;
	private int LEVEL_SEQ=2;
	private int SPEED_SEQ=3;
	private int ACC_SEQ=4;
	/* �÷��̾� ���� ���Ͽ� ���� ��θ� �����ϱ� ���� ���� */
	private String PlayerDB_Path;
	/* �÷��̾� ����� ������ List<Player> */
	private List<Player> list;
	
	/* LinkedList<Player> ��ü? �ν��Ͻ�?�� �Ҵ��ϴ� ������ */
	public PlayerList() {
		list = new LinkedList<Player>();
	}
	/* �÷��̾� ���� ���Ͽ��� �÷��̾� ����� �о�� �÷��̾� ����Ʈ�� �ʱ�ȭ�ϴ� �޼ҵ� */
	public void Init_Players() {
		int level=0;
		float speed=0;
		float acc=0;
		Player p = null;
		String read;
		/* �÷��̾� ���� ������ Words.class�� ��ġ�� ������ �����Ͽ�, Words.class�� ���� ��θ� ��ȯ�Ͽ� path�� ���� */
		String path = Words.class.getResource("").getPath(); //�˻� �ѹ� �غ���
		/* Player_DB.txt ������ �о���� ��� ���� */
		PlayerDB_Path = path + DB_NAME;
		
		/* ���� �ؾ����� �𸣰���?? */
		File fileInSamePackage = new File(PlayerDB_Path); 
		
		FileReader textFileReader = null;	
		BufferedReader bufferFileReader = null;
		
		try {
			textFileReader = new FileReader(fileInSamePackage);	
			bufferFileReader=new BufferedReader(textFileReader);
			/* Player_DB.txt ���Ͽ��� ���پ� �����͸� �о���� */
			while(((read=bufferFileReader.readLine())!=null)) {
				/* ���Ͽ��� ���� �о�� "-"�� �������� �����͸� �и��Ͽ� �迭�� �����ϰ� �ش� �迭�� ��ȯ�Ͽ� data[]�� ���� */
				String data[] = read.split("-");
				/* data �迭�� ����� ���ڿ� �����͸� �� �ڷ��� Ÿ�Կ� �°� ����ȯ �Ͽ� ���� */
		        level=Integer.parseInt(data[LEVEL_SEQ]);
		        speed=Float.parseFloat(data[SPEED_SEQ]);
		        acc=Float.parseFloat(data[ACC_SEQ]);
		        /* ���̵�� �̸��� ���� ���ڿ��̹Ƿ� ��ȯ�� �ʿ����
		         * ���ο� �÷��̾ �����ϸ鼭 �о�� �����͸� �����Ͽ� �����ڷ� �ʱ�ȭ ���� */
				p = new Player(data[ID_SEQ], data[NAME_SEQ], level, speed, acc);
				/* �о�� �����ͷ� �ʱ�ȭ�� �÷��̾� ��ü�� �÷��̾� LinkedList�� �߰� */
				list.add(p);
			}
		}
		/* ���� ó�� �κ� */
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
	/* ���ο� �÷��̾ �����Ͽ� �ش� �÷��̾� ��ü�� ��ȯ�ϴ� �޼ҵ� */
	public Player Make_Player() {
		String Id=null;
		/* �ƹ� ������ �� ���� ���� �÷��̾� ��ü ���� */
		Player p = new Player();
		/* �÷��̾� ��ü�� Make_Player() �޼ҵ� ȣ�� */
		p.Make_Player();
		
		/* LinkedList�� ����־� ���� ������ == ����� �÷��̾ �ϳ��� ������ 
		 * �ߺ����̵����� �ƴ��� �˻��ϴ� ����*/
		if(!list.isEmpty()) {
			/* �迭 �̸� : list, Ÿ�� : Player, s�� list �迭�� ó�� ��Һ��� �� ��ұ��� ���������� �ݺ��� ���� */
			for(Player s:list) {
				/* ���� Player ��ü�� ���̵� ��ȯ�Ͽ� ���� */
				Id=s.getID();
				/* ���� ����Ǿ� �ִ� �÷��̾��� ���̵�� ���� ������ �÷��̾��� ���̵� ���� ������ �ߺ� ���� ���� */
				if(Id.equals((p.getID()))) {
					System.out.println("�ڡڡڡ��ߺ�ID ����!! ���� ���Сڡڡڡ�");
					/* ?? s? null? */
					return null;
				}
			}
		}
		/* �ߺ��Ǿ� ���� �ʰų�, ����� �÷��̾ �ϳ��� ���� ���, �÷��̾ LinkedList�� �߰� */
		list.add(p);
		System.out.println("�ڡڡڡ��÷��̾� �����Ϸ�ڡڡڡ�");
		/* �߰��� �÷��̾� linkedlist�� Player_DB.txt�� ���� */
		Save_Player_List();
		/* ���� ������ �÷��̾ ��ȯ */
		return p;
	}
	/* ���̵� ���ڷ� ���޹޾�, �÷��̾� ����Ʈ���� �ش� �÷��̾ ã�� ��ȯ���ִ� �޼ҵ� */
	public Player Get_Player(String ID) {
		String Id=null;
		for(Player s:list) {
			Id=s.getID();
			if(Id.equals(ID)) {
				/* ���̵� ���� �÷��̾ ���� ���, �ش� �÷��̾ ���� ������ ��� */
				s.Print_Player_Info();
				return s;
			}
		}
		/* ���� ��� null ��ȯ */
		return null;
	}
	/* ������ �÷��̾� ����Ʈ���� �÷��̾ �ҷ��� �Ű������� load_player�� �������ִ� �޼ҵ� */
	public boolean Load_Player(Player load_player) {
		Scanner sc = new Scanner(System.in);
		String Id;
		System.out.println("�ڡڡ����� Player �ε�ڡڡ�");
		System.out.print("ID �Է� : ");
		/* �ε��� ID�� �Է¹��� */
		Id=sc.nextLine();
		/* �Է¹��� ID�� �ش��ϴ� �÷��̾ ã�� ��ȯ�Ͽ� �Ű������� load_player�� ���� */
		load_player = Get_Player(Id);
		/* �Է¹��� ID�� �ش��ϴ� player�� ���� ��� load_player�� ���� null*/
		if(load_player==null) {
			System.out.println("���ش� ID ����");
			/* �ش� �÷��̾� ���� ��� false ��ȯ */
			return false;
		}
		/* �ش� �÷��̾� ���� ��� true ��ȯ */
		return true;
	}
	/* ���� �÷��̾� ����Ʈ ������ ���Ͽ� �����ϴ� �޼ҵ� */
	public void Save_Player_List() {
		/* Player_DB.txt �Լ��� ��ġ�� ��θ� path�� ���� */
		String path = Words.class.getResource("").getPath();
		/* Player_DB.txt�� �о���̱� ���� ��θ� File�� �����Ͽ� ��ü ���� */
		File fileInSamePackage = new File(path + DB_NAME);
		
		FileWriter textFileWriter = null;	
		BufferedWriter bufferFileWriter = null;
		
	    try {
	        ////////////////////////////////////////////////////////////////
	    	textFileWriter = new FileWriter(fileInSamePackage);	
	    	bufferFileWriter=new BufferedWriter(textFileWriter);
			
	    	/* ����Ʈ�� ���� for-each�� */
	    	for(Player s:list) {
	    		/* s�� �ش��ϴ� �÷��̾��� ������ �о���� ���� */
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
	          System.err.println(e); // ������ �ִٸ� �޽��� ���
	          System.exit(1);
	      }

	    System.out.println("�ڡڡڡ��÷��̾� ��� ���� �Ϸ�ڡڡڡ�");
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