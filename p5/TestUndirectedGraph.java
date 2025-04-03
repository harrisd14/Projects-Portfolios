package p5;

import java.io.IOException;

public class TestUndirectedGraph {
	
	public static String squeeze(String s) {
		if( s == null )
			return "";
		
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if( Character.isDigit( c ) || c == '<' || c == '>' || c == ':' || c == ',')
				str.append(s.charAt(i));
		}
		return str.toString();
	}

	public static void main(String[] args) throws IOException {
		int score = 0;
		//==========================================================================
		System.out.println("*******************\nTesting G1...\n*******************\n");

		UndirectedGraph G1 = new UndirectedGraph("src/p5/Graphs/p5-G1.txt");

		score += 10;

		if( squeeze(G1.toString()).equals(squeeze("0:<1,5><2,4><5,6><6,2>1:<0,5><6,10>2:<0,4><4,3><5,5>3:<4,10><7,1>4:<2,3><3,10><5,10><7,5><8,1>5:<0,6><2,5><4,10><6,9><8,1>6:<0,2><1,10><5,9><8,1>7:<3,1><4,5>8:<4,1><5,1><6,1>") ))
			score += 5;
		else System.out.println("Incorrect toString(); returned "+G1);

		if(G1.countTriplets() == 7)
			score += 5;
		else System.out.println("Incorrect countTriplets(); returned "+G1.countTriplets());

		if (G1.findShortestPathLengthBetween(0, 8) == 3)
			score += 10;
		else System.out.println("Incorrect findShortestPathLengthBetween(); returned "+G1.findShortestPathLengthBetween(0,8));

		if(G1.ifConnectedThenBreadthFirstTraversal()!=null && squeeze(G1.ifConnectedThenBreadthFirstTraversal()).equals(squeeze("0 1 2 5 6 4 8 3 7")))
			score += 10;
		else System.out.println("Incorrect ifConnectedThenBreadthFirstTraversal(); returned "+G1.ifConnectedThenBreadthFirstTraversal());

		if(!G1.isTree())
			score += 5;
		else System.out.println("Incorrect isTree(); returned ");
		//==========================================================================
		System.out.println("*******************\nTesting G2...\n*******************\n");

		UndirectedGraph G2 = new UndirectedGraph("src/p5/Graphs/p5-G2.txt");

		score += 10;

		if( squeeze(G2.toString()).equals(squeeze("0: <1,1> <6,2> <7,1> 1: <0,1> <2,1> <3,2> <11,3> 2: <1,1> <3,1> 3: <1,2> <2,1> <4,1> <5,2> 4: <3,1> <5,1> 5: <3,2> <4,1> <6,1> <7,2> 6: <0,2> <5,1> <7,1> 7: <0,1> <5,2> <6,1> 8: <9,4> <10,5> <11,4> 9: <8,4> <10,4> 10: <8,5> <9,4> <11,4> 11: <1,3> <8,4> <10,4>") ))
			score += 5;
		else System.out.println("Incorrect toString(); returned "+G2);

		if(G2.countTriplets() == 6)
			score += 5;
		else System.out.println("Incorrect countTriplets(); returned "+G2.countTriplets());
		
		if (G2.findShortestPathLengthBetween(9, 3) == 13)
			score += 10;
		else System.out.println("Incorrect findShortestPathLengthBetween(); returned "+G2.findShortestPathLengthBetween(9,3));

		if(G2.ifConnectedThenBreadthFirstTraversal()!=null && squeeze(G2.ifConnectedThenBreadthFirstTraversal()).equals(squeeze("0 1 6 7 2 3 11 5 4 8 10 9")))
			score += 10;
		else System.out.println("Incorrect ifConnectedThenBreadthFirstTraversal(); returned "+G2.ifConnectedThenBreadthFirstTraversal());

		if(!G2.isTree())
			score += 5;
		else System.out.println("Incorrect isTree(); returned ");
		//==========================================================================
		System.out.println("*******************\nTesting G3 and G4...\n*******************\n");

		UndirectedGraph G3 = new UndirectedGraph("src/p5/Graphs/p5-G3.txt");
		UndirectedGraph G4 = new UndirectedGraph("src/p5/Graphs/p5-G4.txt");

		score += 5;

		if(G3.isTree() && !G4.isTree())
			score += 5;
		else System.out.println("Incorrect isTree(); returned ");
		//==========================================================================
		System.out.println("Your score: " + score + "/100");
	}
}