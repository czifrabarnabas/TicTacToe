import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Check{
	
	//megn�zi,hogy melyik ir�nyban ind�thatjuk el az egyez� szimb�lumok vizsg�lat�t, 
	//illetve detekt�lja a d�ntetlen v�geredm�nyt
	public void check(int i, TicTacToe tic, List<List<Character>> playground, JLabel textfield, int n, JButton[] buttons, int pieces) {
		int oszlop;
		int sor;
		sor = i/n;
		oszlop = i % n;
		
		//balra-fel �s jobbra-le ir�ny
		if((sor != 0 && oszlop != 0 && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop-1).get(sor-1)) ||
				((oszlop+1) < n && (sor+1) < n && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop+1).get(sor+1))) {
			left_up_cross(oszlop, sor, n, tic, buttons, textfield, playground, pieces);
		}
		//fel-le ir�ny
		if(sor != 0 && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop).get(sor-1) || 
				(sor+1) < n && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop).get(sor+1)) {
			up_down(oszlop, sor, n, tic, buttons, textfield, playground, pieces);
		}
		//jobbra-fel �s balra-le ir�ny
		if( sor != 0 && (oszlop+1) < n && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop+1).get(sor-1) ||
				(sor+1) < n && oszlop != 0 && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop-1).get(sor+1)) {
			right_up_cross(oszlop, sor, n, tic, buttons, textfield, playground, pieces);
		}
		//balra-jobbra ir�ny
		if( oszlop != 0 && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop-1).get(sor) ||
				(oszlop+1) < n && (int)playground.get(oszlop).get(sor) == (int)playground.get(oszlop+1).get(sor)) {
			left_right(oszlop, sor, n,  tic, buttons, textfield, playground, pieces);
		}
		
		
		//lehet d�ntetlen?
		int counter = 0;
		for(int j = 0; j < n; j++) {
			for(int k = 0; k < n; k++) {
				if(playground.get(j).get(k).equals('X') || playground.get(j).get(k).equals('O')) {
					counter++;
				}else {break;}
			}
		}
		if(counter == (n*n) && !(textfield.getText().equals("O wins") || textfield.getText().equals("X wins"))) {
			textfield.setText("It's a draw!");
			tic.SetisEnd(true);
		}
		
	}
	
	//check fv-hez tartozik, balra-fel ir�ny vizsg�lata
		void left_up_cross(int o, int s, int n, TicTacToe tic, JButton[] buttons, JLabel textfield, List<List<Character>> playground, int pieces) {
			int jx = 1;
			int jo = 1;
			int count = 1;
			int o1 = o;
			int s1 = s;
			int[] xwin = new int[n];
			int[] owin = new int[n];
			
			if(buttons[s*n + o].getText()== "X") {xwin[0] = s*n + o;}else {owin[0] = s*n + o;}
			
			
			while( (s1 != 0 && o1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1-1).get(s1-1)) || 
					((o+1) < n && (s+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o+1).get(s+1))) {
				
				
				if( s1 != 0 && o1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1-1).get(s1-1)) {  
					o1 -= 1;
					s1 -= 1;
					count++;
					if(buttons[s1*n + o1].getText()== "X") {
						xwin[jx++] = s1*n + o1;
					}else {
						owin[jo++] = s1*n + o1;
					}
					if(count == pieces) {
						
						if(buttons[s1*n + o1].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic, buttons, textfield, pieces, n);
						}
						break;
					}
				}
				      //vizsg�ljuk meg a m�sik ir�nyba is
				if((o+1) < n && (s+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o+1).get(s+1)) {  
					o += 1;
					s += 1;
					count++;
					if(buttons[s*n + o].getText()== "X") {
						xwin[jx++] = s*n + o;
					}else {
						owin[jo++] = s*n + o;
					}
					if(count == pieces) {
						if(buttons[s*n + o].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic, buttons, textfield, pieces, n);
						}
						break;
					}
				}
				
			}
		}
			
		//check fv-hez tartozik, fel-le ir�ny vizsg�lata
		void up_down(int o, int s, int n, TicTacToe tic, JButton[] buttons, JLabel textfield, List<List<Character>> playground, int pieces) {
			int jx = 1;
			int jo = 1;
			int count = 1;
			int o1 = o;
			int s1 = s;
			int[] xwin = new int[n];
			int[] owin = new int[n];

			if(buttons[s*n + o].getText()== "X") {xwin[0] = s*n + o;}else {owin[0] = s*n + o;}
			
			
			while( s1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1).get(s1-1) || 
					(s+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o).get(s+1)) {
				
				
				if( s1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1).get(s1-1)) {  
					s1 -= 1;
					count++;
					
					if(buttons[s1*n + o1].getText()== "X") {
						xwin[jx++] = s1*n + o1;
					}else {
						owin[jo++] = s1*n + o1;
					}
					if(count == pieces) {
						
						if(buttons[s1*n + o1].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic, buttons, textfield, pieces, n);
						}
						
						break;
					}
				}
				      //vizsg�ljuk meg a m�sik ir�nyba is
				if((s+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o).get(s+1)) {  
					s += 1;
					count++;
					
					if(buttons[s*n + o].getText()== "X") {
						xwin[jx++] = s*n + o;
					}else {
						owin[jo++] = s*n + o;
					}
					if(count == pieces) {
						if(buttons[s*n + o].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic, buttons, textfield, pieces, n);
						}
						break;
					}
				}
					
			}
		}
			
		//check fv-hez tartozik, jobbra-fel �s balra-le ir�ny vizsg�lata
		void right_up_cross(int o, int s, int n, TicTacToe tic, JButton[] buttons, JLabel textfield, List<List<Character>> playground, int pieces) {
			int jx = 1;
			int jo = 1;
			int count = 1;
			int o1 = o;
			int s1 = s;
			int[] xwin = new int[n];
			int[] owin = new int[n];

			if(buttons[s*n + o].getText()== "X") {xwin[0] = s*n + o;}else {owin[0] = s*n + o;}
			
			
			while( s1 != 0 && (o1+1) < n && (int)playground.get(o1).get(s1) == (int)playground.get(o1+1).get(s1-1) || 
					(s+1) < n && o != 0 && (int)playground.get(o).get(s) == (int)playground.get(o-1).get(s+1)) {
				
				
				if( s1 != 0 && (o1+1) < n && (int)playground.get(o1).get(s1) == (int)playground.get(o1+1).get(s1-1)) {  
					o1 += 1;
					s1 -= 1;
					count++;
					
					if(buttons[s1*n + o1].getText()== "X") {
						xwin[jx++] = s1*n + o1;
					}else {
						owin[jo++] = s1*n + o1;
					}
					if(count == pieces) {
						
						if(buttons[s1*n + o1].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic, buttons, textfield, pieces, n);
						}
						break;
					}
				}
				      //vizsg�ljuk meg a m�sik ir�nyba is
				if((s+1) < n && o != 0 && (int)playground.get(o).get(s) == (int)playground.get(o-1).get(s+1)) {  
					o -= 1;
					s += 1;
					count++;
					
					if(buttons[s*n + o].getText()== "X") {
						xwin[jx++] = s*n + o;
					}else {
						owin[jo++] = s*n + o;
					}
					if(count == pieces) {
						if(buttons[s*n + o].getText()== "X") {
							xWins(xwin, tic, buttons, textfield, pieces, n);
						}else {
							oWins(owin, tic,buttons, textfield, pieces, n);
						}
						break;
					}
				}
					
			}
		}
			
		//check fv-hez tartozik, balra-jobbra ir�ny vizsg�lata
		void left_right(int o, int s, int n, TicTacToe tic, JButton[] buttons, JLabel textfield, List<List<Character>> playground, int pieces) {
			int jx = 1;
			int jo = 1;
			int count = 1;
			int o1 = o;
			int s1 = s;
			int[] xwin = new int[n];
			int[] owin = new int[n];

			if(buttons[s*n + o].getText()== "X") {xwin[0] = s*n + o;}else {owin[0] = s*n + o;}
			
			
			while( o1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1-1).get(s1) || 
					(o+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o+1).get(s)) {
				
				
					if(  o1 != 0 && (int)playground.get(o1).get(s1) == (int)playground.get(o1-1).get(s1) ) {  
						o1 -= 1;
						count++;
						
						if(buttons[s1*n + o1].getText()== "X") {
							xwin[jx++] = s1*n + o1;
						}else {
							owin[jo++] = s1*n + o1;
						}
						if(count == pieces) {
							
							if(buttons[s1*n + o1].getText()== "X") {
								xWins(xwin, tic, buttons, textfield, pieces, n);
							}else {
								oWins(owin, tic, buttons, textfield, pieces, n);
							}
							
							break;
						}
					}
					      //vizsg�ljuk meg a m�sik ir�nyba is
					if((o+1) < n && (int)playground.get(o).get(s) == (int)playground.get(o+1).get(s)) {  
						o += 1;
						count++;
						
						if(buttons[s*n + o].getText()== "X") {
							xwin[jx++] = s*n + o;
						}else {
							owin[jo++] = s*n + o;
						}
						if(count == pieces) {
							if(buttons[s*n + o].getText()== "X") {
								xWins(xwin, tic, buttons, textfield, pieces, n);
							}else {
								oWins(owin, tic, buttons, textfield, pieces, n);
							}
							break;
						}
					}	
			}
		}
			
		
		//esetleges gy�zelmet tesztel� met�dusok
		public void xWins(int[] arr, TicTacToe tic, JButton[] buttons, JLabel textfield, int pieces, int n) {
			for(int i = 0; i < pieces; i++) {
				buttons[arr[i]].setBackground(Color.GREEN);
			}
			for(int i=0;i<(n*n);i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("X wins");
			tic.SetisEnd(true);
			
			//mentj�k hogy ki nyert
			try {
				FileOutputStream fileout = new FileOutputStream("C:\\Users\\user\\Desktop\\TicTacToe_win.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileout);
				out.writeObject(tic.getEnd());
				out.close();
				fileout.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		
		public void oWins(int[] arr, TicTacToe tic,JButton[] buttons, JLabel textfield, int pieces, int n) {
			for(int i = 0; i < pieces; i++) {
				buttons[arr[i]].setBackground(Color.GREEN);
			}
			for(int i=0;i<(n*n);i++) {
				buttons[i].setEnabled(false);
			}
			textfield.setText("O wins");
			tic.SetisEnd(true);
			//mentj�k hogy ki nyert
			try {
				FileOutputStream fileout = new FileOutputStream("C:\\Users\\user\\Desktop\\TicTacToe_win.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileout);
				out.writeObject(tic.getEnd());
				out.close();
				fileout.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
}
