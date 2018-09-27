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
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SudokuGUI extends SudokuFunction implements ActionListener{

	public SudokuGUI(Cell[][] sudokuGrid) {
		
		super(sudokuGrid);
	}
	public JFrame frame;	
	private int gridSize = 9;
	private int boxSize = 3;
	private JTextField fieldGrid [][] = new JTextField [gridSize][gridSize];
	private JPanel miniSqPnl [][] = new JPanel [boxSize][boxSize];
	private Dimension fieldDimension = new Dimension (45, 45);
	private JPanel btnPnl = new JPanel();
	private JPanel gridPnl = new JPanel();
	private JButton solveBtn =  new JButton("Solve");
	private JButton resetBtn =  new JButton("Reset Grid");
	
	public void initialize() {
		
		setFrame();
		
		setTextFields();
		
		setPanels();
		
		setButtonPanel();
		
		
	}
	
	public void setFrame(){
		
		frame = new JFrame("Sudoku Solver");
		frame.setBounds(100, 100, 415, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(gridPnl, BorderLayout.NORTH);
		frame.getContentPane().add(btnPnl, BorderLayout.SOUTH);
	}
	public void setButtonPanel(){
	
		solveBtn.setPreferredSize(new Dimension(100, 50));
		resetBtn.setPreferredSize(new Dimension(100, 50));
		
		btnPnl.setLayout(new BorderLayout());
		btnPnl.add(solveBtn, BorderLayout.WEST);
		btnPnl.add(resetBtn, BorderLayout.EAST);
		
		solveBtn.addActionListener(this);
		resetBtn.addActionListener(this);
	}
	public void putPuzzle(){
		
		
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				String value = fieldGrid[y][x].getText();
				
				if(value.equals(null)){
					
					sudokuGrid[y][x] = new Cell(value, true);
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e){
		
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				if(e.getSource() == fieldGrid[y][x]){
					
					String value = fieldGrid[y][x].getText();
					
					sudokuGrid[y][x] = new Cell(value, false);
				}
			}
		}
		if(e.getSource() == solveBtn){
			
			getGrid();
			
			solvePuzzle();
			
			setFieldValues();
		}
		if(e.getSource() == resetBtn){
			
			for(int y = 0; y < 9; y++){
				
				for(int x = 0; x < 9; x++){
				
					fieldGrid[y][x].setText(null);
				}
			}
		}
		
		
	}
	public void setFieldValues(){
		
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				fieldGrid[y][x].setText(sudokuGrid[y][x].getValue());
				
			}
		}
	}
	public void getGrid(){
		
		for(int y = 0; y < 9; y++){
			
			for(int x = 0; x < 9; x++){
				
				if(!fieldGrid[y][x].getText().trim().isEmpty()){
					
					sudokuGrid[y][x] = new Cell(fieldGrid[y][x].getText(), false);
	
				}
				else{
					
					sudokuGrid[y][x] = new Cell("0", true);
				}
				
			}
		}
	}
	public void setTextFields(){
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		Font fieldFont = new Font("Calibri", Font.CENTER_BASELINE, 30);
		
		for(int y = 0; y < gridSize; y++){
			
			for(int x = 0; x < gridSize; x++){
				
				JTextField field = new JTextField();
				
				field.setFont(fieldFont);
				
				field.setBorder(border);
				
				field.setPreferredSize(fieldDimension);
				
				field.setHorizontalAlignment(JTextField.CENTER);
				
				field.addActionListener(this);
								
				fieldGrid[y][x] =  field;
			}
		}
	}
	public void setPanels(){
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		gridPnl.setBorder(border);
		
		gridPnl.setLayout(new GridLayout(boxSize, boxSize));
		
		for(int y = 0; y < boxSize; y++){
			
			for(int x = 0; x < boxSize; x++){
				
				JPanel panel = new JPanel();
				
				panel.setLayout(new GridLayout(boxSize, boxSize));
				
                miniSqPnl[y][x] = panel;
                
                panel.setBorder(border);
                
				gridPnl.add(panel);
			}
		}
		for(int y = 0; y < gridSize; y++){
			
			for(int x = 0; x < gridSize; x++){
				
				int boxX = x / boxSize;
				
				int boxY = y / boxSize;
				
				miniSqPnl[boxY][boxX].add(fieldGrid[y][x]);
			}
		}
	}

}
