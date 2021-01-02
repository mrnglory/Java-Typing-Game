package player;

import java.util.Scanner;

/* Person Ŭ����, Player Ŭ������ �θ� Ŭ���� */
public class Person {
	/* ��ӹ޴� �ڽ� Ŭ�������� �θ�Ŭ������ ID, name ������ ���� ������ �����ϵ���
	 * protected�� �ش� ����������� ������. */
	protected String ID;
	protected String name;
	
	/* ����Ʈ ������ */
	public Person() {
		ID=null;
		name=null;
	}
	/* ���̵�, �̸� ���޹޴� ������ */
	public Person(String ID, String name) {
		this.ID=ID;
		this.name=name;
	}
	/* �÷��̾� ���� �Լ� */
	public void Make_Player() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ID �Է� : "); ID=sc.nextLine();
		System.out.print("�̸� �Է� : "); name=sc.nextLine();
	}
	/* �÷��̾� ���� ��� �Լ� */
	public void Print_Player_Info() {
		System.out.println("ID : "+ID);
		System.out.println("�̸� : "+name);
	}
	/* Person Ŭ������ ������� ID�� ���� get �޼ҵ� */
	public String getID() {
		return ID;
	}
	/* Person Ŭ������ ������� name�� ���� get �޼ҵ� */
	public String getName() {
		return name;
	}
}
