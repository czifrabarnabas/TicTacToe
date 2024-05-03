import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SavePlaygroundTest {
	
	TicTacToe t;
	SavePlayground s;
	
	@Before
	public void setup() {
		t = new TicTacToe(5, 3, true);
		s = new SavePlayground();
	}
	
	
	@Test
	public void testSave_load() throws ClassNotFoundException, IOException {
		List<List<Character>> playground = new ArrayList();
		
		s.save(t.getPlayground());
		playground = s.load();
	}

}
