package p2;

public abstract class AbstractSparseMatrix2D {
	protected int sizeOfMatrix; // Stores the number of rows (same as number of columns)

	//-----------------The node class------------------------------//
	protected static class MatrixNode {
		Integer entry;
		int row, col;
		MatrixNode up, down, left, right;

		// This constructor is used for creating the gray nodes only
		public MatrixNode(int row, int col, Integer entry) {
			this.entry = entry;
			this.row = row; this.col = col;
			this.up = this.down = this.left = this.right = null;
		}

		// This constructor is used for creating the headers (the pink and blue nodes) only
		public MatrixNode(int row, int col) {
			this.entry = 0;
			this.row = row; this.col = col;
			this.up = this.down = this.left = this.right = null;
		}
	}
	//-----------------------------------------------------------//

	protected MatrixNode[] rows, cols;

	// returns the entry at the ith row and jth column
	// Please do not touch this method
	public Integer getEntry(int i, int j) {

		if (rows[i].right == null || cols[j].down == null)
			return null;

		MatrixNode current = rows[i].right;

		while (current != null && current.col <= j) {
			if (current.col == j)
				return current.entry;
			current = current.right;
		}
		return null;
	}

	// Please do not touch this method
	protected String printRowMajorLtoR() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < sizeOfMatrix; i++) {
			MatrixNode m = rows[i].right;

			if (i == 0) str.append("Row ").append(i).append(":");
			else str.append("\nRow ").append(i).append(":");

			while (m != null) {
				str.append(" <--> (").append(m.row).append(",").append(m.col).append(",").append(m.entry).append(") ");
				m = m.right;
			}
		}
		str.append("\n");
		return str.toString();
	}

	// Please do not touch this method
	protected String printRowMajorRtoL() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < sizeOfMatrix; i++) {
			MatrixNode m = rows[i].right;
			str.append("\nRow ").append(i).append(":");

			if (m == null) continue;

			while (m.right != null)
				m = m.right;

			while (m != rows[i]) {
				str.append(" <--> (").append(m.row).append(",").append(m.col).append(",").append(m.entry).append(") ");
				m = m.left;
			}
		}
		str.append("\n");
		return str.toString();
	}

	// Please do not touch this method
	protected String printColMajorTtoB() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < sizeOfMatrix; i++) {
			MatrixNode m = cols[i].down;
			str.append("\nCol ").append(i).append(":");

			while (m != null) {
				str.append(" <--> (").append(m.row).append(",").append(m.col).append(",").append(m.entry).append(") ");
				m = m.down;
			}
		}
		str.append("\n");
		return str.toString();
	}

	// Please do not touch this method
	protected String printColMajorBtoT() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < sizeOfMatrix; i++) {
			MatrixNode m = cols[i].down;
			str.append("\nCol ").append(i).append(":");

			if (m == null) continue;

			while (m.down != null)
				m = m.down;

			while (m != cols[i]) {
				str.append(" <--> (").append(m.row).append(",").append(m.col).append(",").append(m.entry).append(") ");
				m = m.up;
			}
		}
		return str.toString();
	}
}