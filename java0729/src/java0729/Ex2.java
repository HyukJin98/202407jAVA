package java0729;

public class Ex2 {
	public static void main(String[] args) {
		long time1 = System.nanoTime();
		System.out.println(time1);
		
		
		for(int i=0; i < 100000; i++) {
			
		}
		long time2 = System.nanoTime();
		System.out.println(time2-time1);
	}
}
