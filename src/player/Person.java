package player;

import java.util.Scanner;

/* Person 클래스, Player 클래스의 부모 클래스 */
public class Person {
	/* 상속받는 자식 클래스에서 부모클래스의 ID, name 변수에 대해 접근이 가능하도록
	 * protected로 해당 멤버변수들을 선언함. */
	protected String ID;
	protected String name;
	
	/* 디폴트 생성자 */
	public Person() {
		ID=null;
		name=null;
	}
	/* 아이디, 이름 전달받는 생성자 */
	public Person(String ID, String name) {
		this.ID=ID;
		this.name=name;
	}
	/* 플레이어 생성 함수 */
	public void Make_Player() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ID 입력 : "); ID=sc.nextLine();
		System.out.print("이름 입력 : "); name=sc.nextLine();
	}
	/* 플레이어 정보 출력 함수 */
	public void Print_Player_Info() {
		System.out.println("ID : "+ID);
		System.out.println("이름 : "+name);
	}
	/* Person 클래스의 멤버변수 ID에 대한 get 메소드 */
	public String getID() {
		return ID;
	}
	/* Person 클래스의 멤버변수 name에 대한 get 메소드 */
	public String getName() {
		return name;
	}
}
