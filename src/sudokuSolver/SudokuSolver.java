package sudokuSolver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class SudokuSolver {

	Cell [][] Grid = new Cell [9][9];
	
	public void setGrid(){
		
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				Grid[y][x] = new Cell(0, true);
			}
		}
	}
	public void printPuzzle(){
		
		String line = " -------------------------";
		
		System.out.println(line);
		
		for(int row = 0; row < 9; row++){
			
			for(int col = 0; col < 9; col++){
				
				int value = Grid[row][col].getValue();
				
				if(col % 3 == 0){
					
					System.out.print(" | " + value);
				}
				else{
					
					System.out.print(" " + value);
				}
				
			}
			System.out.print(" |");

			System.out.println();
			
			if((row + 1) % 3 == 0){
			
				System.out.println(line);
			}
		}
	}
	public void solvePuzzle(){
		
		for(int row = 0; row < 9; row++){
			
			for(int col = 0; col < 9; col++){
				
				boolean test = Grid[row][col].getBoolean();
				
				if(test == true){
					
					for(int value = 1; value <= 9; value++){
						
						if(isLegal(value, row, col)){
							
							Grid[row][col] = new Cell(value, true);
							
							break;
						}
						else if(value == 9){
							
							while(Grid[row][col].getBoolean() == false || value == 9){
								
								if(Grid[row][col].getBoolean() == true){
									
									Grid[row][col] = new Cell(0, true);
								}
								if(col == 0){
									
									col = 8;
									
									row--;
								}
								else{
									
									col--;
								}
								value = Grid[row][col].getValue();
							}
						}
					}
				}
			}
		}
	}
	public boolean isLegal(int value, int row, int col){
		
		for(int inderow = 0; inderow < 9; inderow++){
			
			if(Grid[row][inderow].getValue() == value){
				
				return false;
			}
			if(Grid[inderow][col].getValue() == value){
				
				return false;
			}
		}
		int rowLowBound = (row / 3) * 3; //bound of the borow
		
		int colLowBound = (col / 3) * 3;
		
		int rowUpBound = rowLowBound + 3;
		
		int colUpBound = colLowBound + 3;
		
		for(row = rowLowBound; row < rowUpBound; row++){
			
			for(col = colLowBound; col < colUpBound; col++){
				
				if(Grid[row][col].getValue() == value){
					
					return false;
				}
			}
		}
		return true;
	}
	class Cell{
		
		int value;
		
		boolean isChangeable;
		
		Cell(int value, boolean isChangeable){
			
			this.value = value;
			
			this.isChangeable = isChangeable;	
		}
		public int getValue(){
			
			return value;
		}
		public boolean getBoolean(){
			
			return isChangeable;
		}
		public void setValue(int newValue){
			
			value = newValue;
		}
		public void setBoolean(boolean newBoolean){
			
			isChangeable = newBoolean;
		}
		
	}
}
