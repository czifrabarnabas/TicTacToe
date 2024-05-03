import java.util.List;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;


	public class TicTacToe extends InputRead implements ActionListener{
		
		//attribútumok
		int n;
		int pieces;
		boolean xstarts;
		JFrame frame = new JFrame();
		JPanel title_panel = new JPanel();
		JPanel button_panel = new JPanel();
		JLabel textfield = new JLabel();
		JButton[] buttons;
		List<List<Character>> playground;
		int GroundIndex;
		boolean player1_turn;
		boolean isEnd;
		
		//GETTER és SETTER metódusok
		public List<List<Character>> getPlayground() {return playground;}
		public JButton getButton(int i) {return buttons[i];};
		public JLabel getTextfield() {return textfield;}
		public void SetWhosTurn(boolean b) {player1_turn = b;}
		public void SetPlayground(List<List<Character>> l) {playground = l;}
		public void SetisEnd(boolean b) {isEnd = b;}
		public boolean getEnd() {return isEnd;}
		public JButton[] getButtons() {return buttons;}
		
		/////////////////////////////////////////////////////////////////////////////////////
		
		TicTacToe(int number, int p, boolean whostarts){
			
		
			n = number;
			pieces = p;
			xstarts = whostarts;
			buttons = new JButton[n*n];
			playground = new ArrayList<List<Character>>(n);
			for(int j = 0; j < n; j++)  {
				
				playground.add(new ArrayList<Character>());
				for(int x = 0; x < n ; x++) {
					playground.get(j).add('c'); //az ArrayList tulajdonságai miatt inicializálni kell az egész "2D tömböt"
				}
		    }
			
			//a UI beállításai
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,800);
			frame.getContentPane().setBackground(new Color(50,50,50));
			frame.setLayout(new BorderLayout());
			frame.setVisible(true);
			
			textfield.setBackground(new Color(25,25,25));
			textfield.setForeground(new Color(25,255,0));
			textfield.setFont(new Font("Ink Free",Font.BOLD,75));
			textfield.setHorizontalAlignment(JLabel.CENTER);
			textfield.setText("Tic-Tac-Toe");
			textfield.setOpaque(true);
			
			title_panel.setLayout(new BorderLayout());
			title_panel.setBounds(0,0,800,100);
			
			button_panel.setLayout(new GridLayout(n,n));
			button_panel.setBackground(new Color(150,150,150));
			
			for(int i=0;i<(n*n);i++) {
				buttons[i] = new JButton();
				button_panel.add(buttons[i]);
				buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
				buttons[i].setFocusable(false);
				buttons[i].addActionListener(this);
			}
			
			title_panel.add(textfield);
			frame.add(title_panel,BorderLayout.NORTH);
			frame.add(button_panel);
			
			firstTurn();
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//elhelyezünk egy szimbólumot, és megnézzük, nyertünk-e
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			for(int i=0;i<(n*n);i++) {
				if(e.getSource()==buttons[i]) {
					if(player1_turn) {
						if(buttons[i].getText()=="") {
							playground.get((i%n)).set((i/n), 'X');
							buttons[i].setForeground(new Color(255,0,0));
							buttons[i].setText("X");
							player1_turn=false;
							textfield.setText("O turn");
							Check check1 = new Check();
							check1.check(i, this, playground, textfield, n, buttons, pieces);
						}
					}
					else {
						if(buttons[i].getText()=="") {
							playground.get((i%n)).set((i/n), 'O');
							buttons[i].setForeground(new Color(0,0,255));
							buttons[i].setText("O");
							player1_turn=true;
							textfield.setText("X turn");
							Check check2 = new Check();
							check2.check(i, this, playground, textfield, n, buttons, pieces);
						}
					}
				}			
			}
			
			//mentjük a játékteret
			try {
				getSave_Load().save(playground);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//mentjük hogy épp nyert e valaki, vagy játék közben hagytuk félbe
			try {
				FileOutputStream fileout = new FileOutputStream("C:\\Users\\user\\Desktop\\TicTacToe_win.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileout);
				out.writeObject(this.getEnd());
				out.close();
				fileout.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		public void firstTurn() {
			
			//X vagy O kezd?
			if(xstarts == true) {
				player1_turn=true;
				textfield.setText("X turn");
			}
			else {
				player1_turn=false;
				textfield.setText("O turn");
			}
		}
		
		
	}

