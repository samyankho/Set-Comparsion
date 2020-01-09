import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

public class SetTester {


	public static void main(String[] args) {
		A4Set<Integer> tree = new A4Set<Integer>();
		HashSet<Integer> hset = new HashSet<Integer>();
		TreeSet<Integer> tset = new TreeSet<Integer>();

		
		
		for(int n =1000; n <= 1000000; n *= 10) {
			System.out.print("n = ");
			System.out.print(n);
			System.out.println(":");
			
			Random r = new Random();
			
			long start = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				tree.add(r.nextInt(10 * n));
			}
			long end = System.currentTimeMillis();
			System.out.println("A4Set          add             "  + (end - start) + " ms");
			
			
			long start1 = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				tset.add(r.nextInt(10 * n));
			}
			long end1 = System.currentTimeMillis();
			System.out.println("TreeSet        add             "  + (end1 - start1) + " ms");
			
			
			long start2 = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				hset.add(r.nextInt(10 * n));
			}
			long end2 = System.currentTimeMillis();
			System.out.println("HashSet        add             "  + (end2 - start2) + " ms");
			
			
			long start3 = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				tree.contains(r.nextInt(10 * n));
			}
			long end3 = System.currentTimeMillis();
			System.out.println("A4Set          contain         "  + (end3 - start3) + " ms");
			
			
			long start4 = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				tset.contains(r.nextInt(10 * n));
			}
			long end4 = System.currentTimeMillis();
			System.out.println("TreeSet        contain         "  + (end4 - start4) + " ms");
			
			
			
			long start5 = System.currentTimeMillis();
			for(int i = 0;i < n;i++ ) {
				hset.contains(r.nextInt(10 * n));
			}
			long end5 = System.currentTimeMillis();
			System.out.println("HashSet        contain         "  + (end5 - start5) + " ms");
			System.out.println(" ");
		}
	}
		
		
	

}
