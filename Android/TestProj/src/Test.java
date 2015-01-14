

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t = new Test();
		
		t.display("Hello");
		t.display(null);
		

	}
	
	
	public void display(String str){
		

		try {
			
			System.out.println("String passed1 --- "+str);
			System.out.println("String passed2 --- "+str);
			System.out.println("String passed3 --- "+str);
			
			
			if(str.equals(""))
				System.out.println("String is empty!!!");
			
			System.out.println("String passed4 --- "+str);
			System.out.println("String passed5 --- "+str);
			System.out.println("String passed6 --- "+str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
