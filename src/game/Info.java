package game;

public class Info {
	/* 레벨, 정확도, 속도 */
	private int level;
	private float acc;
	private float speed;
	
	/* 디폴트 생성자 */
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
	/* 매개변수로 전달받은 정보를 각 멤버변수에 대입하는 함수 
	 * Round 클래스의 매크로 상수 ONE_LEVEL(1)를 해당함수의 level 값으로 전달
	 * */
	public void Insert(int level, float speed, float acc) {
		this.level += level;
		this.acc=acc;
		this.speed=speed;
	}
	/* 매개변수로 전달받은 정보를 각 멤버변수에 대입하는 함수 */
	public void Insert(Info in) {
		this.level=in.level;
		this.acc=in.acc;
		this.speed=in.speed;
	}
	/* 각 멤버변수의 정보를 출력해주는 메소드 */
	public void Show_Info() {
		System.out.println("레벨 : "+level);
		System.out.println("분당타자수 : "+speed);
		System.out.println("정확도 : "+acc+"\n\n");
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