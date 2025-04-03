package p2;

import java.io.*;

public class TestSparseMatrix2D {

	public static void main(String[] args) throws IOException {
		/*================= Test 1 =================*/
		SparseMatrix2D A = new SparseMatrix2D("src/p2/Matrices/p2-m1.txt");
		SparseMatrix2D B = new SparseMatrix2D("src/p2/Matrices/p2-m2.txt");
		SparseMatrix2D.addMatricesQuadratic(A, B, "src/p2/Outputs/p2-m3-AO.txt");

		if( Utilities.doContentsMatch("src/p2/Outputs/p2-m3-EO.txt","src/p2/Outputs/p2-m3-AO.txt") )
			System.out.println("Good job! File representation of the resulting matrix looks good!");
		else
			System.out.println("ALERT: File representation of the resulting matrix does not look good!");

		// C = A + B
		SparseMatrix2D C = new SparseMatrix2D("src/p2/Outputs/p2-m3-AO.txt");
		C.writeTheFourTraversalsToFile("src/p2/Outputs/p2-m3-Traversals-AO.txt");

		if(Utilities.doContentsMatch("src/p2/Outputs/p2-m3-Traversals-AO.txt", "src/p2/Outputs/p2-m3-Traversals-EO.txt"))
			System.out.println("Good job! Matrix C traversals look good!");
		else
			System.out.println("ALERT: Matrix C traversals do not look good!");
        /*========================================*/

		System.out.println();

		/*================= Test 2 =================*/
		SparseMatrix2D D = new SparseMatrix2D("src/p2/Matrices/p2-m4.txt");
		SparseMatrix2D E = new SparseMatrix2D("src/p2/Matrices/p2-m5.txt");
		SparseMatrix2D.addMatricesQuadratic(D, E, "src/p2/Outputs/p2-m6-AO.txt");

		if( Utilities.doContentsMatch("src/p2/Outputs/p2-m6-EO.txt","src/p2/Outputs/p2-m6-AO.txt") )
			System.out.println("Good job! File representation of the resulting matrix looks good!");
		else
			System.out.println("ALERT: File representation of the resulting matrix does not look good!");

		// F = D + E
		SparseMatrix2D F = new SparseMatrix2D("src/p2/Outputs/p2-m6-AO.txt");
		F.writeTheFourTraversalsToFile("src/p2/Outputs/p2-m6-Traversals-AO.txt");
		if(Utilities.doContentsMatch("src/p2/Outputs/p2-m6-Traversals-AO.txt", "src/p2/Outputs/p2-m6-Traversals-EO.txt"))
			System.out.println("Good job! Matrix F traversals look good!!");
		else
			System.out.println("ALERT: Matrix F traversals do not look good!");
		/*==========================================*/

		System.out.println("\n\nGenerating speedups...");

		// Sample output from my Macbook Pro 2019 (plugged in)
		//		n : 1000, Avg. speedup: 14X
		//		n : 2000, Avg. speedup: 12X
		//		n : 3000, Avg. speedup: 23X
		//		n : 4000, Avg. speedup: 36X
		//		n : 5000, Avg. speedup: 59X
		//		n : 6000, Avg. speedup: 90X
		//		n : 7000, Avg. speedup: 113X
		//		n : 8000, Avg. speedup: 152X
		//		n : 9000, Avg. speedup: 170X
		//		n : 10000, Avg. speedup: 225X

		// Comparing the two implementations of matrix addition
		// Comment out the following line if you have not completed the addMatricesQuadratic method
		Utilities.generateLargeMatricesandCompare();
		System.out.println("\n\n.----> Copy-paste the above speed-ups at the top of the file SparseMatrix2D.java before submission");

		System.out.println("Testing terminated.");
	}
}