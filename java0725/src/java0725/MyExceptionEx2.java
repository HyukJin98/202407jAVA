package java0725;

class MyClass2{
	void method() throws MyException2 {
		throw new MyException2("마이 예외!"); //예외 발생
	}
}

public class MyExceptionEx2 {
	public static void main(String[] args) {
		MyClass2 mc = new MyClass2();
		
		mc.method();
		
	}
}
