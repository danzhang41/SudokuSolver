package sudokuSolver;

public class SudokuFunction {

	Cell [][] sudokuGrid = new Cell [9][9];
	
	public SudokuFunction(Cell[][] sudokuGrid){
		
		this.sudokuGrid = sudokuGrid;
	}
	public void printPuzzle(){
		
		String line = " -------------------------";
		
		System.out.println(line);
		
		for(int row = 0; row < 9; row++){
			
			for(int col = 0; col < 9; col++){
				
				String value = sudokuGrid[row][col].getValue();
				
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
				
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				if(sudokuGrid[y][x].getBoolean() == true){
					
					for(String value = "1"; Integer.valueOf(value) <= 9; value = Integer.toString(Integer.valueOf(value) + 1)){
						
						if(isLegal(value, x, y)){
							
							sudokuGrid[y][x] = new Cell(value, true);
														
							break;
						}
						else if(value.equals("9")){
							
							while(sudokuGrid[y][x].getBoolean() == false || value.equals("9")){
								
								if(sudokuGrid[y][x].getBoolean() == true){
									
									sudokuGrid[y][x] = new Cell("0", true);
								}
								if(x == 0){
									
									x = 8;
									
									y--;
								}
								else{
									
									x--;
								}
								value = sudokuGrid[y][x].getValue();
								
							}
						}
					}
				}
			}
		}
	}
	public boolean isLegal(String value, int x, int y){
		
		for(int index = 0; index < 9; index++){
			
			if(sudokuGrid[y][index].getValue().equals(value)){
				
				return false;
			}
			if(sudokuGrid[index][x].getValue().equals(value)){
				
				return false;
			}
		}
		int rowLowBound = (y / 3) * 3; 
		
		int colLowBound = (x / 3) * 3;
		
		int rowUpBound = rowLowBound + 3;
		
		int colUpBound = colLowBound + 3;
		
		for(y = rowLowBound; y < rowUpBound; y++){
			
			for(x = colLowBound; x < colUpBound; x++){
				
				if(sudokuGrid[y][x].getValue().equals(value)){
					
					return false;
				}
			}
		}
		return true;
	}
	class Cell{
		
		String value;
		
		boolean isChangeable;
		
		Cell(String value, boolean isChangeable){
			
			this.value = value;
			
			this.isChangeable = isChangeable;	
		}
		public String getValue(){
			
			return value;
		}
		public boolean getBoolean(){
			
			return isChangeable;
		}
		public void setValue(String newValue){
			
			value = newValue;
		}
		public void setBoolean(boolean newBoolean){
			
			isChangeable = newBoolean;
		}
		
	}
}
