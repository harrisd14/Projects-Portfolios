package p4.Trees; // do not delete

import p4.ChromaticTree_HarrisD;

import java.io.IOException;

public class TestChromaticTree_HarrisD {

	public static String squeeze(String s) {
		if( s == null )
			return "";

		StringBuilder str = new StringBuilder();
		for(int i = 0; i < s.length(); i++)
			if( Character.isJavaIdentifierPart( s.charAt(i) ) )
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
			score += 10;

			System.out.println();

			if (B.countTriChromaticGroups() == 2)
				score += 10;
			else {
				System.out.println("countTriChromaticGroups() failed");
				System.out.println("Your output: " + B.countTriChromaticGroups() + "\n");
			}

			if (squeeze(B.getReverseElevatorOrder()).equals("JacksonvilleChicagoMiamiNYCBostonOmahaTampaAtlantaOrlando"))
				score += 10;
			else {
				System.out.println("getReverseElevatorOrder() failed");
				System.out.println("Your output: " + B.getReverseElevatorOrder() + "\n");
			}

			if (B.computeHeight() == 3)
				score += 10;
			else {
				System.out.println("computeHeight() failed");
				System.out.println("Your output: " + B.computeHeight() + "\n");
			}


			if (squeeze(B.findFirstCommonCity("Boston", "Tampa")).equals("Chicago"))
				score += 10;
			else {
				System.out.println("findFirstCommonCity(\"Boston\", \"Tampa\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("Boston", "Tampa") + "\n");
			}

			if (squeeze(B.findFirstCommonCity("Tampa", "NYC")).equals("NYC"))
				score += 10;
			else {
				System.out.println("findFirstCommonCity(\"Tampa\", \"NYC\") failed");
				System.out.println("Your output: " + B.findFirstCommonCity("Tampa", "NYC") + "\n");
			}

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
			score += 10;


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

		}
		///=================================================================//
		System.out.println("Score: " + score);
	}
}
