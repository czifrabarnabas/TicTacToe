import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckTest {
	
	int i; 
	int pieces;
	TicTacToe tic;
	
	@Before
	public void setup() {
		i = 2;
		tic = new TicTacToe(3, 3, true);
		pieces = 3;
		//állítsunk be egy döntetlent
	}
	
	@Test
	public void testup_Xwins() {
		
		Check c = new Check();
		int[] arr = new int[3];
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		c.xWins(arr, tic, tic.getButtons(), tic.getTextfield(), pieces, i);
		Assert.assertEquals(tic.getEnd(), true);
		Assert.assertEquals(tic.getTextfield().getText(), "X wins");
	}
	
	@Test
	public void testcheck() {
		//állítsunk be egy döntetlent
		tic.getPlayground().get(0).set(0, 'X');
		tic.getPlayground().get(0).set(2, 'X');
		tic.getPlayground().get(1).set(1, 'X');
		tic.getPlayground().get(2).set(1, 'X');
		tic.getPlayground().get(0).set(1, 'O');
		tic.getPlayground().get(1).set(0, 'O');
		tic.getPlayground().get(1).set(2, 'O');
		tic.getPlayground().get(2).set(0, 'O');
		tic.getPlayground().get(2).set(2, 'O');
		Check c = new Check();
		c.check(i, tic, tic.getPlayground(), tic.getTextfield(), tic.getPlayground().size(), tic.getButtons(), pieces);
		Assert.assertEquals(tic.getTextfield().getText(), "It's a draw!");
	}
	
	@Test
	public void check_left_up_cross() {
		
		tic.getPlayground().get(0).set(0, 'X');
		tic.getPlayground().get(2).set(2, 'X');
		tic.getPlayground().get(1).set(1, 'X');
		tic.getButton(0).setText("X");
		tic.getButton(4).setText("X");
		tic.getButton(8).setText("X");
		Check c = new Check();
		c.left_up_cross(1, 1, 3, tic, tic.getButtons(), tic.getTextfield(), tic.getPlayground(), pieces);
		Assert.assertEquals(tic.getEnd(), true);
	}
	
	
	@Test
	public void testup_down() {
		
		tic.getPlayground().get(1).set(0, 'X');
		tic.getPlayground().get(1).set(1, 'X');
		tic.getPlayground().get(1).set(2, 'X');
		tic.getButton(1).setText("X");
		tic.getButton(4).setText("X");
		tic.getButton(7).setText("X");
		Check c = new Check();
		c.up_down(1, 1, 3, tic, tic.getButtons(), tic.getTextfield(), tic.getPlayground(), pieces);
		Assert.assertEquals(tic.getEnd(), true);
	}
	
	
	@Test
	public void testleft_right() {
		
		tic.getPlayground().get(0).set(1, 'X');
		tic.getPlayground().get(1).set(1, 'X');
		tic.getPlayground().get(2).set(1, 'X');
		tic.getButton(3).setText("X");
		tic.getButton(4).setText("X");
		tic.getButton(5).setText("X");
		Check c = new Check();
		c.left_right(1, 1, 3, tic, tic.getButtons(), tic.getTextfield(), tic.getPlayground(), pieces);
		Assert.assertEquals(tic.getEnd(), true);
	}
	
	
	@Test
	public void testright_up_cross() {
		
		tic.getPlayground().get(0).set(2, 'X');
		tic.getPlayground().get(1).set(1, 'X');
		tic.getPlayground().get(2).set(0, 'X');
		tic.getButton(1).setText("X");
		tic.getButton(4).setText("X");
		tic.getButton(7).setText("X");
		Check c = new Check();
		c.right_up_cross(1, 1, 3, tic, tic.getButtons(), tic.getTextfield(), tic.getPlayground(), pieces);
		Assert.assertEquals(tic.getEnd(), true);
	}
	
	@Test
	public void testup_Owins() {
		
		Check c = new Check();
		int[] arr = new int[3];
		arr[0] = 0;
		arr[1] = 1;
		arr[2] = 2;
		c.oWins(arr, tic, tic.getButtons(), tic.getTextfield(), pieces, i);
		Assert.assertEquals(tic.getEnd(), true);
		Assert.assertEquals(tic.getTextfield().getText(), "O wins");
		
	}
	
		
	
}
