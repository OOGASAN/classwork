public class Scratch {
	
	public static void generateSums(int n) {
		int total = 0;
		int i = 1;
		while (i <= n) {
			total = total + i;
			i++;
			System.out.println(total);		
		}
	}
	
	public static void main(String[] args) {
		generateSums(4);
		generateSums(6);	
		
	}
}