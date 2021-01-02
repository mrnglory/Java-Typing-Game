package player;

import player.Person;
import game.Info;

/* Person(ID, name를 포함) 클래스 상속 */
public class Player extends Person {
	/* Info 클래스의 객체 info(레벨, 속도, 정확도를 포함하고 있음)를 포함 */
	private Info info;
	/* 디폴트 생성자 */
	public Player()	{
		super();
		info = new Info();
	}
	/* 아이디와 이름을 전달받는 생성자 */
	public Player(String ID, String name){
		/* 부모 클래스의 생성자 호출 => Person 클래스의 Person(String ID, String name) 생성자 호출  */
		super(ID,name);
		info = new Info();
	}
	/* 5가지 정보를 전달받는 생성자 */
	public Player(String ID, String name, int level, float speed,  float acc){
		/* 부모 클래스의 생성자 호출 => Person 클래스의 Person(String ID, String name) 생성자 호출  */
		super(ID,name);
		info = new Info(level, speed, acc);
	}
	/* 플레이어를 전달받는 생성자 */
	public Player(Player p){
		/* 부모 클래스의 생성자 호출 => Person 클래스의 Person(String ID, String name) 생성자 호출  */
		super(p.ID, p.name);
		info = new Info(p.info);
	}
	/* 플레이어를 생성하는 메소드 -> 부모클래스 메소드 오버라이딩 */
	public void Make_Player() {
		System.out.println("★★★★플레이어 생성★★★★\n\n");
		/* 부모 클래스의 메소드 호출 */
		super.Make_Player();
	}
	/* 플레이어 정보를 출력하는 메소드 -> 부모클래스 메소드 오버라이딩 */
	public void Print_Player_Info() {
		System.out.println("★★★★플레이어 정보★★★★");
		/* 부모 클래스의 메소드 호출 */
		super.Print_Player_Info();
		/* info 객체의 정보 출력 메소드 호출 */
		info.Show_Info();
		System.out.println("★★★★★★★★★★★★★★★★");
	}
	/* 플레이어 정보를 업데이트하는 메소드
	 * 방식은 여러가지가 있을 수 있으나, 새로운 정보가 기존 정보를 대체하는 형식으로 정의함.
	 *  */
	public void Update_Info(Info info) {
		this.info=info;
	}
	/* 멤버변수에 대한 get 메소드 */
	public String getID() {
		return super.getID();
	}
	/* 멤버변수에 대한 get 메소드 */
	public String getName() {
		return super.getName();
	}
	/* 멤버변수에 대한 get 메소드 */
	public Info getInfo() {
		return info;
	}
}