package game;

import java.util.List;
import java.util.LinkedList;
import java.util.List;
import game.Info;

public class Round {
	/* �÷��̾ ������ �����ϴ� ���带 ī��Ʈ�� ���� */
	private int Round_Count;
	/* �÷��̾ ���带 �����ϸ鼭
	 * �о�� �� �ܾ ���� �Է��� ����(�ӵ�, ��Ȯ��, ����)�� ����
	 * LinkedList�� ���������� ����
	 *  */
	private List<Info> info;
	
	/* ONE_LEVEL : Ư�� ������ ������Ű�� ���� ��ũ�� ��� ���� */
	final int ONE_LEVEL=1;
	
	/* ����Ʈ ������ */
	public Round() {
		Round_Count=0;
		info=new LinkedList<Info>();
	}
	/* ����Ʈ�� �ܾ ���� info�� �߰��ϴ� �޼ҵ� */
	public void Insert_Info(Info word_info) {
		info.add(word_info);
	}
	/* ����Ʈ�� �ܾ ���� info�� �߰��ϴ� �޼ҵ� */
	public void Insert_Info(float acc, float speed) {
		Info temp = new Info(Round_Count, acc, speed);
		info.add(temp);
	}
	/* �ε����� �ش��ϴ� �ܾ��� ������ ����ϴ� �޼ҵ� */
	public void Show_Result(int num) {
		Info temp=info.get(num);
		temp.Show_Info();
	}
	/* �� ���忡 �־��� ��� �ܾ�鿡 ���� ��Ȯ��, �ӵ��� �����Ͽ� ��ü �ܾ� ������ ���� ��, ���忡 ���� ��� ������ ���� ��ȯ���ִ� �޼ҵ� */
	public Info Show_Total_Result() {
		float total_acc=0;
		float total_speed=0;
		
		/* �ش� �޼ҵ忡�� ����� ���� ��Ȯ��, �ӵ��� ���� ��հ� ������ �ӽ������� �����Ͽ� ��ȯ���� ���� */
		Info temp = new Info();
		
		/* for-each���� �̿��� ��Ȯ��, �ӵ��� ������ ���ϴ� ���� */
		for(Info i : info) {
			total_acc+=i.getAcc();
			total_speed+=i.getSpeed();
		}
		/* ������ �ܾ� ������ ������ ����� ���� �� �����ϴ� ���� */
		total_acc/=info.size();
		total_speed/=info.size();
		/* ����� ������ temp�� ����  */
		temp.Insert(ONE_LEVEL, total_acc, total_speed);
		System.out.println("�ڡڡڡڡڶ��� ��� ǥ�áڡڡڡڡ�");
		/* ����� ������ ��� */
		temp.Show_Info();
		/* ���� ������ ��ȯ -> �÷��̾� ��ü���� ���޹޾� ������ ������Ʈ�� */
		return temp;
	}
	/* ���带 1��ŭ ������Ű�� �޼ҵ� */
	public void Add_Round() {
		Round_Count++;
	}
}
