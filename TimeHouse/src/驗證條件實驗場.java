
public class 驗證條件實驗場 {

	public static void main(String[] args) {
		System.out.println("101".matches("^\\d{1,5}$"));
		
//		System.out.println("3".matches("^[1-2]{1}$"));
		
//		String x="false";
//		System.out.println(x.matches("true") || x.matches("false"));
		
		String tempId ="101";
		System.out.println(tempId.matches("^\\d{1,5}$"));
	}
}
