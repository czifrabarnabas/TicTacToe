import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToe_Test {

	
	TicTacToe t;
	
	@Before
	public void setup() {
		t = new TicTacToe(5, 3, true);
	}
	
	@Test
	public void testConstructor() {
		
		Assert.assertEquals(t.playground.size(), 5);
		
		for(int i = 0; i < 25; i++) {
			Assert.assertEquals(t.getButton(i).getText(), "");
		}
	}
	
	@Test
	public void testclick() {
		t.SetWhosTurn(true);
		t.getButton(0).doClick();
		Assert.assertEquals((char)t.getPlayground().get(0).get(0), 'X');
	}

}
