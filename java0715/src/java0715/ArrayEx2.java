package java0715;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayEx2 {
	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<>();
		
/*		ArrayList<String> list2 = new ArrayList<String>();
		ArrayList<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
*/		
		list.add(123);
		list.add("홍길동");
		list.add("자바");
		list.add(3.15);
		
//      이터레이터 (자바 디자인 패턴)
		Iterator<Object> it = list.iterator();
		while(it.hasNext()) {
			String str = (String) it.next();
			int num = Integer.parseInt(str) + 100;
			System.out.println(str);
		}
		
		
/*		for(String str : list) {
			System.out.println(str);
		}
*/
	}

}
