package sec01.exam01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
	public static void main(String[] args) throws Exception {
		OutputStream os = new FileOutputStream("c://Temp/test1.db");
		
		byte[] array = {10,20,-1,30,50};
		
		
		os.write(array,1,3);
//		byte a = 10;
//		byte b = 20;
//		byte c = 30;
//		
//		os.write(a);
//		os.write(b);
//		os.write(c);
		
		os.flush();
		os.close();
	}
}
