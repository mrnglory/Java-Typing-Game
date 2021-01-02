package player;

import player.Person;
import game.Info;

/* Person(ID, name�� ����) Ŭ���� ��� */
public class Player extends Person {
	/* Info Ŭ������ ��ü info(����, �ӵ�, ��Ȯ���� �����ϰ� ����)�� ���� */
	private Info info;
	/* ����Ʈ ������ */
	public Player()	{
		super();
		info = new Info();
	}
	/* ���̵�� �̸��� ���޹޴� ������ */
	public Player(String ID, String name){
		/* �θ� Ŭ������ ������ ȣ�� => Person Ŭ������ Person(String ID, String name) ������ ȣ��  */
		super(ID,name);
		info = new Info();
	}
	/* 5���� ������ ���޹޴� ������ */
	public Player(String ID, String name, int level, float speed,  float acc){
		/* �θ� Ŭ������ ������ ȣ�� => Person Ŭ������ Person(String ID, String name) ������ ȣ��  */
		super(ID,name);
		info = new Info(level, speed, acc);
	}
	/* �÷��̾ ���޹޴� ������ */
	public Player(Player p){
		/* �θ� Ŭ������ ������ ȣ�� => Person Ŭ������ Person(String ID, String name) ������ ȣ��  */
		super(p.ID, p.name);
		info = new Info(p.info);
	}
	/* �÷��̾ �����ϴ� �޼ҵ� -> �θ�Ŭ���� �޼ҵ� �������̵� */
	public void Make_Player() {
		System.out.println("�ڡڡڡ��÷��̾� �����ڡڡڡ�\n\n");
		/* �θ� Ŭ������ �޼ҵ� ȣ�� */
		super.Make_Player();
	}
	/* �÷��̾� ������ ����ϴ� �޼ҵ� -> �θ�Ŭ���� �޼ҵ� �������̵� */
	public void Print_Player_Info() {
		System.out.println("�ڡڡڡ��÷��̾� �����ڡڡڡ�");
		/* �θ� Ŭ������ �޼ҵ� ȣ�� */
		super.Print_Player_Info();
		/* info ��ü�� ���� ��� �޼ҵ� ȣ�� */
		info.Show_Info();
		System.out.println("�ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�");
	}
	/* �÷��̾� ������ ������Ʈ�ϴ� �޼ҵ�
	 * ����� ���������� ���� �� ������, ���ο� ������ ���� ������ ��ü�ϴ� �������� ������.
	 *  */
	public void Update_Info(Info info) {
		this.info=info;
	}
	/* ��������� ���� get �޼ҵ� */
	public String getID() {
		return super.getID();
	}
	/* ��������� ���� get �޼ҵ� */
	public String getName() {
		return super.getName();
	}
	/* ��������� ���� get �޼ҵ� */
	public Info getInfo() {
		return info;
	}
}