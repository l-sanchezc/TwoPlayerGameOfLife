import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Two player version of Conway's Game of Life
 * @author Lazaro Sanchez Campos
 * @version 09/21/2015
 */
public class TwoPlayerGameOfLife {
	private static int cellsPlayer1;
	private static int cellsPlayer2;
	private static int generation;
	private static String[][] board;

	public TwoPlayerGameOfLife (int cellsPlayer1, int cellsPlayer2, int generation, String[][] board){
		TwoPlayerGameOfLife.cellsPlayer1 = cellsPlayer1;
		TwoPlayerGameOfLife.cellsPlayer2 = cellsPlayer2;
		TwoPlayerGameOfLife.generation = generation;
		TwoPlayerGameOfLife.board = board;
	}
	
	public static void setCellsPlayer1(int cellsPlayer1){
		TwoPlayerGameOfLife.cellsPlayer1=cellsPlayer1;
	}
	
	public static void setCellsPlayer2(int cellsPlayer2){
		TwoPlayerGameOfLife.cellsPlayer2=cellsPlayer2;
	}
	
	public static void setGeneration(int generation){
		TwoPlayerGameOfLife.generation=generation;
	}
	
	public static void setBoard(String[][] board){
		TwoPlayerGameOfLife.board=board;
	}
	
	public static int getCellsPlayer1(){
		return cellsPlayer1;
	}
	
	public static int getCellsPlayer2(){
		return cellsPlayer2;
	}
	
	public static int getGeneration(){
		return generation;
	}
	
	public static String[][] getBoard(){
		return board;
	}
	
	public static void main (String args []){
		try{
			readInitial(args);
			print(getState(board));
			while(run(board) == null){
				print(getState(board));
			}if (run(board) == "Tie"){
				System.out.println("There is a tie");
			}else{
				int cellsAlive = cellsPlayer1 + cellsPlayer2;
				System.out.println(run(board)+" wins with "+cellsAlive+" cells alive.");
			}
		}
		catch(Exception GenerationFile){
			System.out.println("Generation file is invalid.");
		}
	}
	
	/**
	 * This method gets the number of live cells by players of the current game
	 * @param board The current board of the game
	 * @return The number of live cells of each player, the number of generation and the current board
	 */
	public static TwoPlayerGameOfLife getState(String[][] board){
		int cellsPlayer1 = 0;
		int cellsPlayer2 = 0;
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j].contains("1")) cellsPlayer1++;
				if (board[i][j].contains("2")) cellsPlayer2++;
			}
		}
		setCellsPlayer1(cellsPlayer1);
		setCellsPlayer2(cellsPlayer2);
		return new TwoPlayerGameOfLife(cellsPlayer1, cellsPlayer2, generation, board);
	}
	
	/**
	 * This method reads the generation file
	 * @param args Argument used in the class
	 * @throws IOException 
	 */
	public static void readInitial(String args []) throws IOException{
		String path = "";
		for(String s: args){
			path = path + s;
		}
		BufferedReader br = null;
		try {
		br = new BufferedReader(new FileReader(path));
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		String line;
		ArrayList<String> initialFile = new ArrayList<String>();
		while ((line = br.readLine()) != null)	initialFile.add(line);
		//Getting the number of rows and columns from the top of the generation file.
		int rows = Integer.parseInt(initialFile.get(0).substring(0, initialFile.get(0).indexOf(' ')));
		int columns = Integer.parseInt(initialFile.get(0).substring(initialFile.get(0).indexOf(' ')+1,initialFile.get(0).length()));
		//Checking that the generation file is valid (number of rows and columns match with the indicated at the top of the file)
		for(int i = 1; i < initialFile.size(); i++){
			if(columns != initialFile.get(i).length()) return;
		}
		if(rows != initialFile.size()-1) return;
		String[][] initial = new String[rows][columns];
		for(int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				char a =initialFile.get(i+1).charAt(j);
				//Checking if there are any character that is not point, 1 or 2  
				if (a == '.' || a == '1' || a == '2'){
					initial [i][j]= ""+a;
				}else{
					return;
				}	
			}	
		}
		setBoard(initial);
	}
	
	/**
	 * This method runs the game and creates the new board of the generation
	 * @param board Board of the current generation of the game
	 * @return Return the winner player or if there is a tie
	 */
	public static String run(String[][] board){
		String winner = null;
		String[][] next = new String[board.length][board[1].length];
		for (int i = 0; i < next.length; i++){
			for(int j = 0; j< board[i].length; j++){
				next[i][j]=board[i][j];
			}
		}
		for (int row = 0; row < board.length; row++){
			for(int column = 0; column < board[row].length; column++){
				int neighbors[] = getNeighbors(board, row, column);
				int totalneighbors=neighbors[0]+neighbors[1];
				//Live cells
				if(board[row][column].contains("1") || board[row][column].contains("2")){ 
					//Any live cell with fewer than 2 or more than 3 live neighbors of either player dies.
					if(totalneighbors < 2 || totalneighbors > 3){ 
						next[row][column]=".";
					//Any live cell with 2 or 3 live neighbors of either player lives.
					}else{ 
						continue;
					} 
				}
				//Any dead cell with 3 live neighbors becomes a live cell owned by the player who has the 
				//majority of live neighboring cells.
				if(board[row][column].contains(".") && totalneighbors == 3){
					if(neighbors[0] > neighbors[1]){
						next[row][column]="1";
						}else {
						next[row][column]="2";}
					}
				}
			} 
		setBoard(next); 
		generation++;
		if (cellsPlayer1 == 0){
			winner = (cellsPlayer2 == 0) ? "Tie"  : "Player 2";
		}if (cellsPlayer2 == 0 ){
			winner = (cellsPlayer1 == 0) ? "Tie"  : "Player 1";
		}
		return winner;
	}
	
	/**
	 * This method gets the neighbors of a specific point (use the method "countNeighbors" to count them)
	 * @param board The board of the current game
	 * @param row Row's number of the point
	 * @param column Column's number of the point
	 * @return Number of neighbors (1 and 2) of the point
	 */
	public static int[] getNeighbors(String[][] board, int row, int column){
		String neighbors="";
		if(column == 0){
			if (row == 0) {
				neighbors = board[row][column+1]+board[row+1][column]+board[row+1][column+1];
			}else if (row == board.length-1){
				neighbors = board[row-1][column]+board[row-1][column+1]+board[row][column+1];
			}else{
				neighbors = board[row-1][column]+board[row-1][column+1]+board[row][column+1]+board[row+1][column]+board[row+1][column+1];
			}
		} else if (column == board[row].length-1){
			if (row == 0) {
				neighbors = board[row][column-1]+board[row+1][column-1]+board[row+1][column];
			}else if (row == board.length-1){
				neighbors = board[row-1][column-1]+board[row-1][column]+board[row][column-1];
			}else{
				neighbors = board[row-1][column-1]+board[row-1][column]+board[row][column-1]+board[row+1][column-1]+board[row+1][column];
			}
		} else {
			if  (row == 0) {
				neighbors = board[row][column-1]+board[row][column+1]+board[row+1][column-1]+board[row+1][column]+board[row+1][column+1];
			}else if (row == board.length-1) {
				neighbors = board[row-1][column-1]+board[row-1][column]+board[row-1][column+1]+board[row][column-1]+board[row][column+1];
			}else{
				neighbors = board[row-1][column-1]+board[row-1][column]+board[row-1][column+1]+board[row][column-1]+board[row][column+1]+board[row+1][column-1]+board[row+1][column]+board[row+1][column+1];
			}
		}
		return countNeighbors(neighbors);	
	}
	
	/**
	 * This method counts the number of ones and twos that are in one string (obtained in "getNeighbors")
	 * @param neighbors String with the neighbors of the point
	 * @return Number of neighbors (1 and 2) of the point
	 */
	public static int[] countNeighbors(String neighbors){
		int neighbors1 = 0;
		int neighbors2 = 0;
		for(char c: neighbors.toCharArray()){
			if(c == '1') neighbors1++;
			if(c == '2') neighbors2++;
		}
		return new int[]{neighbors1,neighbors2};
	}
	
	/**
	 * This method prints the current status of the game
	 * @param parameters Parameters of the current status
	 */
	public static void print(TwoPlayerGameOfLife parameters) {
		System.out.println("Generation #"+ generation);
		System.out.println("Player 1 Cells: "+ cellsPlayer1);
		System.out.println("Player 2 Cells: "+ cellsPlayer2);
		for(int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
}