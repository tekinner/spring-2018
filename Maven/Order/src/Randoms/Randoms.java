package Randoms;

import java.util.Random;

public class Randoms {

	
	public String randomOnetoHundred() {
	
		Random random = new Random();
		int result = random.nextInt(100);
		
		String quantity = ""+result;
		
	return quantity;
	
	}
	
	public String randomMiddleName() {
		Random random = new Random();
		int result = random.nextInt(90);
	
		while(result<65) {
			result = random.nextInt(90);
		}
		char c = (char) result;
		String s = c+".";
		
		return s;
		
//		Random random = new Random();
//		String[] names = {"Alex","Alice","Maria","John","Tom","Mark"}; 
//		int result = random.nextInt(6);
//		System.out.println(names[result]);
	
	}
	
	public String randomZip() {
		
		Random random = new Random();
		int result = random.nextInt(100);

		while(result<10000) {
			result = random.nextInt(99999);
		}
		String s = ""+result;
		
		return s;
		
		
	}
	
	public int cardSelect() {
		
		Random random = new Random();
		int result = random.nextInt(3);
		while(result==0)
			result = random.nextInt(3);
		return result;
		}
	
	public String cardNumber(int first, int limit) {
		Random rd = new Random();
		int number = rd.nextInt(10);
		StringBuilder s = new StringBuilder();
		s.append(first);		
		for (int i = 0; i <= limit; i++) {
			s.append(rd.nextInt(10));
		}
		
		System.out.println(s.toString());

		return s.toString();
	}
	
}
