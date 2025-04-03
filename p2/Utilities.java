package p2;

import java.io.*;
import java.util.*;

// Do not touch this class!
public class Utilities {
    public record MatrixEntry (int i, int j, int val) {}
    public static boolean doContentsMatch(String fileA, String fileB) throws IOException {
        Scanner fileInputA = new Scanner(new File(fileA)), fileInputB = new Scanner(new File(fileB));
        StringBuilder contentsOfFileA = new StringBuilder(), contentsOfFileB = new StringBuilder();

        while( fileInputA.hasNextLine() )
            contentsOfFileA.append(fileInputA.nextLine());
        fileInputA.close();

        while( fileInputB.hasNextLine() )
            contentsOfFileB.append(fileInputB.nextLine());
        fileInputB.close();

        return contentsOfFileA.toString().replaceAll(" ","").equals(contentsOfFileB.toString().replaceAll(" ",""));
    }

    public static void fillMatrix(int[][] M, double fillPercentage) {
        int count = 0;
        while( count < M.length * fillPercentage) {
            Random generator = new Random();
            int X = generator.nextInt(M.length), Y = generator.nextInt(M.length);
            if (M[X][Y] == 0) {
                M[X][Y] = generator.nextInt(10000);
                count++;
            }
        }
    }

    public static void writeToAFile(int[][] M, String fileName) throws IOException {
        FileWriter output = new FileWriter("src/p2/Matrices/"+fileName);
        output.write(M.length + "\n");
        for(int i = 0; i < M.length; i++)
            for(int j = 0; j < M.length; j++)
                if( M[i][j] != 0) output.write(i + " " + j + " " + M[i][j] + "\n");
        output.close();
    }

    public static void generateLargeMatricesandCompare( ) throws IOException {
        double fillPercentage = 0.1;

        for(int n = 1000; n <= 10000; n += 1000) {
            int numberOfSamples = 5;
            double speedUp = 0.0;
            for(int i = 0; i < numberOfSamples; i++) {
                int[][] M_A = new int[n][n], M_B = new int[n][n];

                Utilities.fillMatrix(M_A, fillPercentage);
                Utilities.fillMatrix(M_B, fillPercentage);

                Utilities.writeToAFile(M_A, "A.txt");
                Utilities.writeToAFile(M_B, "B.txt");

                SparseMatrix2D A = new SparseMatrix2D("src/p2/Matrices/A.txt");
                SparseMatrix2D B = new SparseMatrix2D("src/p2/Matrices/B.txt");

                long start = System.currentTimeMillis();
                SparseMatrix2D.addMatricesQuadratic(A, B, "src/p2/Outputs/junk1.txt");
                double timeForQuadratic = System.currentTimeMillis() - start;
                //System.out.printf("addMatricesQuadratic took %7d ms\n", (System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                SparseMatrix2D.addMatricesCubic(A, B, "src/p2/Outputs/junk2.txt");
                //System.out.printf("addMatricesCubic took %7d ms\n", (System.currentTimeMillis() - start));
                double timeForCubic = System.currentTimeMillis() - start;

                if (!Utilities.doContentsMatch("src/p2/Outputs/junk1.txt", "src/p2/Outputs/junk2.txt"))
                    throw new IllegalStateException("Sorry, addMatricesQuadratic is not working as expected!");

                if(timeForQuadratic == 0)
                    timeForQuadratic = 1;

                speedUp += (timeForCubic / timeForQuadratic);

               // System.out.println(speedUp);
            }
            System.out.println("n : " + n + ", Avg. speedup: " + (int) (speedUp / numberOfSamples) + "X");

        }
    }
}
