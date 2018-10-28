package game.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.scoutant.blokish.model.Board;
import org.scoutant.blokish.model.Game;
import org.scoutant.blokish.model.Move;

public class WhiteBoxTesting {
	
	/*
	 * Lists of methods to test :
	 * 	over()
	 * 	winner()
	 * 
	 */
	

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		testOverMethod();
		testReplayMethod();
		testToStringMethod();
	}
	
	/* Testing a simple boolean input verifier with condition coverage
	 * Each of the four booleans needs to be true at least once and false at least once
	 */
	
	@Test
	public void testOverMethod() {
		
		Game game = new Game();
		
		// all overs are true
		for (Board board : game.boards) {
			board.setOver(true);
		}
		boolean isOver = game.over();
		assertTrue(isOver);
		
		// one over is false
		game.boards.get(1).setOver(false);
		isOver = game.over();
		assertFalse(isOver);
		
		// all overs are false
		for (Board board : game.boards) {
			board.setOver(false);
		}
		isOver = game.over();
		assertFalse(isOver);
		
	}

	// Testing a method where the boolean output depends on the nature of the input list elements
	
	@Test
	public void testReplayMethod() {
		
		Game game = new Game();
		
		// pass an empty list of moves, for loop is skipped and code jumps to return true
		List<Move> emptyList = new ArrayList<Move>();
		boolean returnedReplay = game.replay(emptyList);
		assertTrue(returnedReplay);
		
		// pass a single invalid move, for loop and if condition both entered, code jumps to return false
		Move genericMove = new Move(0);
		List<Move> oneElementList = new ArrayList<Move>();
		oneElementList.add(genericMove);
		returnedReplay = game.replay(oneElementList);
		assertFalse(returnedReplay);
		
		/* 
		 * Pass a single valid move, for loop is entered but not the if, code jumps to return true
		 * Reusing the generic Move and List from above
		 * Given the depth of the stack call when calling replay, the Move is 'forced' as valid by forcing the
		 * result of the deeply-nested Board.fits(int, Piece, int, int) method.
		 */
		
		Board.returnedByFits = true;
		returnedReplay = game.replay(oneElementList);
		assertTrue(returnedReplay);
	}
	 
	@Test
	public void testToStringMethod() {
		
		Game game = new Game();
		
		// has no moves
		String toStrMsg = game.toString(), expectedMsg = "# moves : 0";
		assertEquals(toStrMsg, expectedMsg);
		
		// has one move
		game.moves.add(new Move(0));
		toStrMsg = game.toString();
		expectedMsg = "# moves : 1\nsuccess";
		assertEquals(toStrMsg, expectedMsg);
	}
	
	@Test
	public void testPlayMethod() {
		
	}
	

}
