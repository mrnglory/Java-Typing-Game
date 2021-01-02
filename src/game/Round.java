package game;

import java.util.List;
import java.util.LinkedList;
import java.util.List;
import game.Info;

public class Round {
	/* 플레이어가 게임을 진행하는 라운드를 카운트할 변수 */
	private int Round_Count;
	/* 플레이어가 라운드를 진행하면서
	 * 읽어온 각 단어에 대해 입력한 정보(속도, 정확도, 레벨)에 대해
	 * LinkedList에 순차적으로 저장
	 *  */
	private List<Info> info;
	
	/* ONE_LEVEL : 특정 레벨씩 증가시키기 위해 매크로 상수 정의 */
	final int ONE_LEVEL=1;
	
	/* 디폴트 생성자 */
	public Round() {
		Round_Count=0;
		info=new LinkedList<Info>();
	}
	/* 리스트에 단어에 대한 info를 추가하는 메소드 */
	public void Insert_Info(Info word_info) {
		info.add(word_info);
	}
	/* 리스트에 단어에 대한 info를 추가하는 메소드 */
	public void Insert_Info(float acc, float speed) {
		Info temp = new Info(Round_Count, acc, speed);
		info.add(temp);
	}
	/* 인덱스에 해당하는 단어의 정보를 출력하는 메소드 */
	public void Show_Result(int num) {
		Info temp=info.get(num);
		temp.Show_Info();
	}
	/* 한 라운드에 있었던 모든 단어들에 대한 정확도, 속도를 총합하여 전체 단어 개수로 나눈 후, 라운드에 대한 평균 정보를 구해 반환해주는 메소드 */
	public Info Show_Total_Result() {
		float total_acc=0;
		float total_speed=0;
		
		/* 해당 메소드에서 계산한 라운드 정확도, 속도에 대한 평균과 레벨을 임시적으로 저장하여 반환해줄 변수 */
		Info temp = new Info();
		
		/* for-each문을 이용해 정확도, 속도의 총합을 구하는 과정 */
		for(Info i : info) {
			total_acc+=i.getAcc();
			total_speed+=i.getSpeed();
		}
		/* 총합을 단어 개수로 나누어 평균을 구한 후 저장하는 과정 */
		total_acc/=info.size();
		total_speed/=info.size();
		/* 계산한 정보를 temp에 저장  */
		temp.Insert(ONE_LEVEL, total_acc, total_speed);
		System.out.println("★★★★★라운드 결과 표시★★★★★");
		/* 계산한 정보를 출력 */
		temp.Show_Info();
		/* 계산된 정보를 반환 -> 플레이어 객체에서 전달받아 정보를 업데이트함 */
		return temp;
	}
	/* 라운드를 1만큼 증가시키는 메소드 */
	public void Add_Round() {
		Round_Count++;
	}
}
