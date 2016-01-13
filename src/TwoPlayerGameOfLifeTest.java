import org.junit.Assert;
import org.junit.Test;

/**
 * Test of two player version of Conway's Game of Life
 * @author Lazaro Sanchez Campos
 * @version 09/21/2015
 */
public class TwoPlayerGameOfLifeTest {
	String[][] testPlayer1={{".",".",".",".",".",".",".",".",".","."},{".",".","1",".",".",".",".",".",".","."},
							{".",".",".","1",".",".",".",".",".","."},{".",".","1","1","1",".",".",".",".","."},
					  		{".",".",".",".",".",".",".",".",".","."},{".",".",".",".",".",".",".","2","2","."},
					  		{".",".",".",".",".",".","2",".",".","2"},{".",".",".",".",".",".",".","2","2","."},
					  		{".",".",".",".",".",".",".",".",".","."},{".",".",".",".",".",".",".",".",".","."}};
	
	String[][] testPlayer2={{"2","1",".","1",".","2",".",".",".","1"},{".",".","1","1","2",".",".",".",".","."},
					  		{".","2","1","1","2","1",".",".",".","1"},{".",".","1","1","2",".",".",".",".","."},
					  		{".",".",".",".",".",".",".",".",".","."},{"2",".",".",".",".",".",".","2","2","."},
					  		{".",".",".",".",".",".","2",".",".","2"},{"1",".",".",".",".",".",".","2","2","."},
					  		{".",".",".",".",".",".",".",".",".","."},{"1","2",".",".",".",".",".",".","1","2"}};
	
	String[][] testTie={{".",".",".",".",".",".",".",".",".","."},{".",".","1",".",".",".",".",".",".","."},
			  			{".",".",".",".",".",".",".",".",".","."},{".",".","1","1","1",".",".",".",".","."},
						{".",".",".",".",".",".",".",".",".","."},{".",".",".",".",".",".",".",".","2","."},
						{".",".",".",".",".",".","2",".",".","2"},{".",".",".",".",".",".",".","2","2","."},
						{".",".",".",".",".",".",".",".",".","."},{".",".",".",".",".",".",".",".",".","."}};
	
	String[][] testMoreRows={{"1","2",".","2"},{"1",".","1","."},
							 {"1",".",".","."},{".",".","1","1"},
						     {".",".","2","2"},{"2",".","1","1"}};
	
	String[][] testMoreColumns={{".","2",".","2",".","2","2","."},{"2",".","1",".",".",".","2","."},
								{"1","2",".","2","2","2",".","."},{".",".","2","2","1","2","2","."},
								{".",".","2",".",".",".","2","."},{".",".","1","1","2",".",".","."}};
	
	@Test
	public void countingLiveCellsPlayer1() {
		//given
		TwoPlayerGameOfLife.getState(testPlayer1);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getCellsPlayer1(), 5);
	}
	
	@Test
	public void countingLiveCellsPlayer2() {
		//given
		TwoPlayerGameOfLife.getState(testPlayer2);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getCellsPlayer2(), 15);
	}

	@Test
	public void player1HasToWinTheGame(){
		//given
		TwoPlayerGameOfLife.getState(testPlayer1);
		//when
		while(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()) == null){
			TwoPlayerGameOfLife.getState(TwoPlayerGameOfLife.getBoard());
		}
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()),"Player 1");
	}
	
	@Test
	public void player2HasToWinTheGame(){
		//given
		TwoPlayerGameOfLife.getState(testPlayer2);
		//when
		while(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()) == null){
			TwoPlayerGameOfLife.getState(TwoPlayerGameOfLife.getBoard());
		}
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()),"Player 2");
	}
	
	@Test
	public void gameShouldBeTied(){
		//given
		TwoPlayerGameOfLife.getState(testTie);
		//when
		while(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()) == null){
			TwoPlayerGameOfLife.getState(TwoPlayerGameOfLife.getBoard());
		}
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()),"Tie");
	}
	
	@Test
	public void liveCellWithTwoOrThreeNeighborsShouldLive(){
		//given
		Assert.assertEquals(testPlayer2[0][1], "1");
		Assert.assertEquals(testPlayer2[0][3], "1");
		Assert.assertEquals(testPlayer2[2][1], "2");
		Assert.assertEquals(testPlayer2[2][5], "1");
		Assert.assertEquals(testPlayer2[5][7], "2");
		Assert.assertEquals(testPlayer2[5][8], "2");
		Assert.assertEquals(testPlayer2[6][6], "2");
		Assert.assertEquals(testPlayer2[6][9], "2");
		Assert.assertEquals(testPlayer2[7][7], "2");
		Assert.assertEquals(testPlayer2[7][8], "2");
		//when
		TwoPlayerGameOfLife.run(testPlayer2);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[0][1], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[0][3], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][1], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][5], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[5][7], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[5][8], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[6][6], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[6][9], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[7][7], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[7][8], "2");
	}

	@Test
	public void liveCellWithFewerThanTwoNeighborsShouldDie(){
		//given
		Assert.assertEquals(testPlayer2[0][0], "2");
		Assert.assertEquals(testPlayer2[0][5], "2");
		Assert.assertEquals(testPlayer2[0][9], "1");
		Assert.assertEquals(testPlayer2[2][9], "1");
		Assert.assertEquals(testPlayer2[3][4], "2");
		Assert.assertEquals(testPlayer2[5][0], "2");
		Assert.assertEquals(testPlayer2[7][0], "1");
		Assert.assertEquals(testPlayer2[9][0], "1");
		Assert.assertEquals(testPlayer2[9][1], "2");
		Assert.assertEquals(testPlayer2[9][8], "1");
		Assert.assertEquals(testPlayer2[9][9], "2");
		//when
		TwoPlayerGameOfLife.run(testPlayer2);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[0][0], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[0][5], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[0][9], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][9], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][4], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[5][0], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[7][0], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[9][0], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[9][1], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[9][8], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[9][9], ".");
	}

	@Test
	public void liveCellWithMoreThanThreeNeighborsShouldDie(){
		//given
		Assert.assertEquals(testPlayer2[1][2], "1");
		Assert.assertEquals(testPlayer2[1][3], "1");
		Assert.assertEquals(testPlayer2[1][4], "2");
		Assert.assertEquals(testPlayer2[2][2], "1");
		Assert.assertEquals(testPlayer2[2][3], "1");
		Assert.assertEquals(testPlayer2[2][4], "2");
		Assert.assertEquals(testPlayer2[3][2], "1");
		Assert.assertEquals(testPlayer2[3][3], "1");
		Assert.assertEquals(testPlayer2[3][4], "2");
		//when
		TwoPlayerGameOfLife.run(testPlayer2);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[1][2], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[1][3], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[1][4], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][2], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][3], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[2][4], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][2], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][3], ".");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][4], ".");
	}

	@Test
	public void deadCellWithThreeNeighborsShouldLive(){
		//given
		Assert.assertEquals(testPlayer2[1][0], ".");
		Assert.assertEquals(testPlayer2[3][1], ".");
		Assert.assertEquals(testPlayer2[3][5], ".");
		Assert.assertEquals(testPlayer2[4][3], ".");
		Assert.assertEquals(testPlayer2[8][0], ".");
		Assert.assertEquals(testPlayer2[8][1], ".");
		Assert.assertEquals(testPlayer2[8][7], ".");
		Assert.assertEquals(testPlayer2[8][9], ".");
		//when
		TwoPlayerGameOfLife.run(testPlayer2);
		//then
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[1][0], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][1], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[3][5], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[4][3], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[8][0], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[8][1], "1");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[8][7], "2");
		Assert.assertEquals(TwoPlayerGameOfLife.getBoard()[8][9], "2");
	}
	
	@Test
	public void testingBoardWithMoreRowsThanColumns(){
		//given
			TwoPlayerGameOfLife.getState(testMoreRows);
		//when
			while(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()) == null){
				TwoPlayerGameOfLife.getState(TwoPlayerGameOfLife.getBoard());
			}
		//then
			Assert.assertEquals(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()),"Player 1");
	}
	
	@Test
	public void testingBoardWithMoreColumnsThanRows(){
		//given
		TwoPlayerGameOfLife.getState(testMoreColumns);
	//when
		while(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()) == null){
			TwoPlayerGameOfLife.getState(TwoPlayerGameOfLife.getBoard());
		}
	//then
		Assert.assertEquals(TwoPlayerGameOfLife.run(TwoPlayerGameOfLife.getBoard()),"Player 2");
	}
	
}