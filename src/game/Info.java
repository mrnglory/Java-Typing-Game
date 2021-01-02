package game;

public class Info {
	/* ����, ��Ȯ��, �ӵ� */
	private int level;
	private float acc;
	private float speed;
	
	/* ����Ʈ ������ */
	public Info() {
		level=0;
		acc=0;
		speed=0;
	}
	
	public Info(int level, float speed, float acc) {
		this.level = level;
		this.acc=acc;
		this.speed=speed;
	}
	public Info(Info info) {
		this.level = info.level;
		this.acc = info.acc;
		this.speed = info.speed;
	}
	/* �Ű������� ���޹��� ������ �� ��������� �����ϴ� �Լ� 
	 * Round Ŭ������ ��ũ�� ��� ONE_LEVEL(1)�� �ش��Լ��� level ������ ����
	 * */
	public void Insert(int level, float speed, float acc) {
		this.level += level;
		this.acc=acc;
		this.speed=speed;
	}
	/* �Ű������� ���޹��� ������ �� ��������� �����ϴ� �Լ� */
	public void Insert(Info in) {
		this.level=in.level;
		this.acc=in.acc;
		this.speed=in.speed;
	}
	/* �� ��������� ������ ������ִ� �޼ҵ� */
	public void Show_Info() {
		System.out.println("���� : "+level);
		System.out.println("�д�Ÿ�ڼ� : "+speed);
		System.out.println("��Ȯ�� : "+acc+"\n\n");
	}
	public float getAcc() {
		return acc;
	}
	public float getSpeed() {
		return speed;
	}
	public int getLevel() {
		return level;
	}
}