/**
 * Derrick Harris
 */

package p2;

import java.io.*;
import java.util.Scanner;

public class SparseMatrix2D extends AbstractSparseMatrix2D {

    // The constructor; we set up the pink and blue nodes first in this constructor
    // Then we read the file and create the gray nodes one by one.
    protected SparseMatrix2D(String inputFileName) throws FileNotFoundException {


        FileInputStream fileStream = new FileInputStream(inputFileName);
        Scanner fileScnr = new Scanner(fileStream);

        //reads in the first line of file (size of matrix), parsed to an int
        sizeOfMatrix = Integer.parseInt(fileScnr.nextLine());

        //creates header nodes(pink)
        rows = new MatrixNode[sizeOfMatrix];
        cols = new MatrixNode[sizeOfMatrix];
        for (int i = 0; i < sizeOfMatrix; i++) {
            rows[i] = new MatrixNode(i, 0);
            cols[i] = new MatrixNode(0, i);
        }

        String[] fileRowArray = new String[3];
        while (fileScnr.hasNextLine()) {
            fileRowArray = fileScnr.nextLine().split(" ");
            setEntry(Integer.parseInt(fileRowArray[0]), Integer.parseInt(fileRowArray[1]), Integer.parseInt(fileRowArray[2]));

        }
        // Do not forget to update the sizeOfMatrix variable once you read it from the file
        // Using the setEntryMethod, you need to populate the matrix; insert the entry immediately when you read it
        // Do not hold the entire file content in any array or data structure
        // protected MatrixNode[] rows, cols; inside the AbstractSparseMatrix2D holds the sparse matrix
    }

    // Please do not touch this method
    public void writeTheFourTraversalsToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);

        fileWriter.write(printRowMajorLtoR());
        fileWriter.write(printRowMajorRtoL());
        fileWriter.write(printColMajorTtoB());
        fileWriter.write(printColMajorBtoT());

        fileWriter.close();
    }

    // Please do not touch this method; This method adds to matrices and sends the result to a file
    // Runs in O(n^3) time, where n is the size of the matrix
    public static void addMatricesCubic(SparseMatrix2D A, SparseMatrix2D B, String outputFile) throws IOException {
        if (A.sizeOfMatrix != B.sizeOfMatrix)
            throw new IllegalArgumentException("The input matrices must have a same size!");

        FileWriter output = new FileWriter(outputFile);
        output.write(A.sizeOfMatrix + "\n");

        // Runs in O(n^3) time
        for (int i = 0; i < A.sizeOfMatrix; i++)
            for (int j = 0; j < A.sizeOfMatrix; j++) {
                Integer Aij = A.getEntry(i, j); // takes O(n) time
                Integer Bij = B.getEntry(i, j); // takes O(n) time
                if (Aij == null) Aij = 0;
                if (Bij == null) Bij = 0;
                if (Aij + Bij != 0) output.write(i + " " + j + " " + (Aij + Bij) + "\n");
            }
        output.close();
    }

    // Complete this method; This method should run in O(n^2) time
    // Do not declare new arrays/ArrayLists or any other data structure inside this method!
    public static void addMatricesQuadratic(SparseMatrix2D A, SparseMatrix2D B, String outputFile) throws IOException {
        // Do not declare any kind of array or any other data structures inside this method.
        if (A.sizeOfMatrix != B.sizeOfMatrix)
            throw new IllegalArgumentException("The input matrices must have a same size!");

        FileWriter output = new FileWriter(outputFile);

        output.write(A.sizeOfMatrix + "\n");
//        int i = 0;
//        int j = 0;
//
//        while( i < A.sizeOfMatrix){
//            Integer Aij = A.getEntry(i, j); // takes O(n) time
//            Integer Bij = B.getEntry(i, j); // takes O(n) time
//            if (Aij == null) Aij = 0;
//            if (Bij == null) Bij = 0;
//            if (Aij + Bij != 0) output.write(i + " " + j + " " + (Aij + Bij) + "\n");
//
//
//          j++;
//          if(j>=A.sizeOfMatrix)
//              j=0;
//              i++;
//         }
        for (int i = 0; i < A.sizeOfMatrix; i++)
            for (int j = 0; j < A.sizeOfMatrix; j++) {
                Integer Aij = A.getEntry(i, j); // takes O(n) time
                Integer Bij = B.getEntry(i, j); // takes O(n) time
                if (Aij == null) Aij = 0;
                if (Bij == null) Bij = 0;
                if (Aij + Bij != 0) output.write(i + " " + j + " " + (Aij + Bij) + "\n");
            }

        output.close();
    }


    // Complete this method; do not declare new arrays/ArrayLists or any other
    // data structure inside this method!
    private void setEntry(Integer i, Integer j, Integer entry) {
        if (i > sizeOfMatrix - 1 || j > sizeOfMatrix - 1 || i < 0 || j < 0)
            throw new IllegalArgumentException("At least one index is out of bound!");

        MatrixNode newNode = new MatrixNode(i, j, entry); // insert this node properly into the matrix

        MatrixNode currNode = rows[i];


        while (currNode.right != null && currNode.right.col < j) {
            currNode = currNode.right;
        }

        newNode.right = currNode.right;
        if (currNode.right != null) {
            currNode.right.left = newNode;
        }
        currNode.right = newNode;
        newNode.left = currNode;


        currNode = cols[j];

        while (currNode.down != null && currNode.down.row < i) {
            currNode = currNode.down;
        }

        newNode.down = currNode.down;
        if (currNode.down != null) {
            currNode.down.up = newNode;
        }
        currNode.down = newNode;
        newNode.up = currNode;
    }

}
