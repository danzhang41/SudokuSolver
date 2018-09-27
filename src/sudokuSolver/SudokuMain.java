package sudokuSolver;

import java.awt.EventQueue;

import sudokuSolver.SudokuFunction.Cell;

public class SudokuMain {

	static Cell [][] test = new Cell[9][9];

	private SudokuGUI window = new SudokuGUI(test);
	
	public static void main(String[] args) {
		
		SudokuMain main = new SudokuMain();
				
		main.GUI();
		
	}
	public void GUI(){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
