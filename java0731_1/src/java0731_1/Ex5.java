package java0731_1;

public class Ex5 {
	public static void main(String[] args) {
		String grade = null;
		switch(grade) {
		case "A", "a" ->{
			System.out.println("우수회원스");
		}
		case "B", "b" ->{
			System.out.println("일반회원스");
		}
		case null -> System.out.println("null");
		default ->{
			System.out.println("손님");
		}
		
		}
	}
}
