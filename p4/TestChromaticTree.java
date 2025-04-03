package p4; // do not delete

import java.io.*;

public class TestChromaticTree {

	public static String squeeze(String s) {
		if( s == null )
			return "";

		StringBuilder str = new StringBuilder();
		for(int i = 0; i < s.length(); i++)
			if( Character.isJavaIdentifierPart( s.charAt(i) ) )
				str.append( s.charAt(i) );

		return str.toString();
	}

	public static String removeSpace(String s) {
		if( s == null )
			return "";

		StringBuilder str = new StringBuilder();
		for(int i = 0; i < s.length(); i++)
			if(  s.charAt(i) != ' ')
				str.append( s.charAt(i) );

		return str.toString();
	}

	public static void main(String[] args) throws IOException {
		int score = 0;
    ///======================= Testing p4-Tree1.txt ============================//
		{
			System.out.println("Testing p4-Tree1.txt\n");
			try {
				ChromaticTree_HarrisD B = new ChromaticTree_HarrisD("src/p4/Trees/p4-Tree1.txt");
				B.computeHeight();// a dummy statement to eliminate warnings
			} catch (Exception e) {
				System.out.println("Your constructor is not working correctly! Please fix it.\nWithout this, your code cannot be tested. ");
				System.out.println("if the other methods are implemented correctly.\n\n");
			}

			ChromaticTree_HarrisD B = new ChromaticTree_HarrisD("src/p4/Trees/p4-Tree1.txt");
			score += 5;

			System.out.println();

			if (B.countTriChromaticGroups() == 2)
				score += 10;
			else {
				System.out.println("countTriChromaticGroups() failed");
				System.out.println("Your output: " + B.countTriChromaticGroups() + "\n");
			}

			if (squeeze(B.getReverseElevatorOrder()).equals("JacksonvilleChicagoMiamiNYCBostonOmahaTampaAtlantaOrlando"))
				score += 5;
			else {
				System.out.println("getReverseElevatorOrder() failed");
				System.out.println("Your output: " + B.getReverseElevatorOrder() + "\n");
			}

			if (B.computeHeight() == 3)
				score += 5;
			else {
				System.out.println("computeHeight() failed");
				System.out.println("Your output: " + B.computeHeight() + "\n");
			}

			/*if (squeeze(B.printPathsBackToTheRoot()).equals(squeeze("Jacksonville Miami Jacksonville Chicago Jacksonville Omaha Miami Jacksonville Boston Chicago Jacksonville NYC Chicago Jacksonville Orlando Omaha Miami Jacksonville Atlanta NYC Chicago Jacksonville Tampa NYC Chicago Jacksonville")))
				score += 10;
			else {
				System.out.println("printPathsBackToTheRoot() failed");
				System.out.println("Your output: " + B.printPathsBackToTheRoot() + "\n");
			}
			*/
			if (squeeze(B.findFirstCommonCity("Boston", "Tampa")).equals("Chicago"))
				score += 5;
			else {
				System.out.println("findFirstCommonCity(\"Boston\", \"Tampa\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("Boston", "Tampa") + "\n");
			}

			if (squeeze(B.findFirstCommonCity("Tampa", "NYC")).equals("NYC"))
				score += 5;
			else {
				System.out.println("findFirstCommonCity(\"Tampa\", \"NYC\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("Tampa", "NYC") + "\n");
			}

			/*StringBuilder str = new StringBuilder();
			for(String s : B)  // should work when the nested iterator class has been completed
				str.append(s);

			if(removeSpace(str.toString()).equals("<Omaha,Purple><Orlando,Amber><Miami,Yellow><Jacksonville,Magenta><Boston,Yellow><Chicago,DarkGreen><Atlanta,Pink><NYC,Magenta><Tampa,Magenta>"))
				score += 5;
			else {
				System.out.println("for-each loop did not work as expected!");
				System.out.println("str contains: " + str);
			}*/
		}

		///======================= Testing p4-Tree2.txt ============================//
		{
			System.out.println("Testing p4-Tree2.txt\n");
			try {
				ChromaticTree_HarrisD B = new ChromaticTree_HarrisD("src/p4/Trees/p4-Tree2.txt");
				B.computeHeight();// a dummy statement to eliminate warnings
			} catch (Exception e) {
				System.out.println("Your constructor is not working correctly! Please fix it.\nWithout this, your code cannot be tested even ");
				System.out.println("if the other methods are implemented correctly.\n\n");
			}

			ChromaticTree_HarrisD B = new ChromaticTree_HarrisD("src/p4/Trees/p4-Tree2.txt");
			score += 5;


			if (B.countTriChromaticGroups() == 3)
				score += 10;
			else {
				System.out.println("countTriChromaticGroups() failed");
				System.out.println("Your output: " + B.countTriChromaticGroups() + "\n");
			}

			if (squeeze(B.getReverseElevatorOrder()).equals("C1C3C2C5C4C9C8C7C6C11C10C12"))
				score += 5;
			else {
				System.out.println("getReverseElevatorOrder() failed");
				System.out.println("Your output: " + B.getReverseElevatorOrder() + "\n");
			}

			if (B.computeHeight() == 5)
				score += 5;
			else {
				System.out.println("computeHeight() failed");
				System.out.println("Your output: " + B.computeHeight() + "\n");
			}
/*
			if (squeeze(B.printPathsBackToTheRoot()).equals(squeeze("C1 C2C1 C3C1 C4C3C1 C5C3C1 C6C4C3C1 C7C4C3C1 C8C5C3C1 C9C5C3C1 C10C6C4C3C1 C11C8C5C3C1 C12C10C6C4C3C1")))
				score += 10;
			else {
				System.out.println("printPathsBackToTheRoot() failed");
				System.out.println("Your output: " + B.printPathsBackToTheRoot() + "\n");
			}
*/
			if (squeeze(B.findFirstCommonCity("C12", "C9")).equals("C3"))
				score += 5;
			else {
				System.out.println("findFirstCommonCity(\"C12\", \"C9\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("C12", "C9") + "\n");
			}

			if (squeeze(B.findFirstCommonCity("C2", "C11")).equals("C1"))
				score += 5;
			else {
				System.out.println("findFirstCommonCity(\"C2\", \"C11\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("C2", "C11") + "\n");
			}

			StringBuilder str = new StringBuilder();
			for(String s : B)  // should work when the nested iterator class has been completed
				str.append(s);

			if(removeSpace(str.toString()).equals("<C2,Green><C1,Red><C12,Red><C10,Green><C6,Green><C4,Red><C7,Blue><C3,Yellow><C11,Purple><C8,Orange><C5,Yellow><C9,Black>"))
				score += 5;
			else {
				System.out.println("for-each loop did not work as expected!");
				System.out.println("str contains: " + str);
			}
		}
		///=================================================================//
		System.out.println("Score: " + score);
	}
}
