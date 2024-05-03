import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.MenuElement;


public class InputRead extends JFrame{
	
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JButton button;
	private JComboBox<Integer> box;
	private JComboBox<Integer> box2;
	private JButton ContinueButton;
	JMenuBar menu;
	JMenu starter;
	JMenuItem item1;
	JMenuItem item2;
	char chr = 'X';
	SavePlayground save_load = new SavePlayground();
	
	public SavePlayground getSave_Load() {return save_load;}
	
	
	//X listener, chr X értéket kap
	////////////////////////////////////
	public class item1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 chr = 'X';
		}
		
	}
	
	////////////////////////////////////
	
	
	//O listener, chr O értéket kap
	////////////////////////////////////
	public class item2Listener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			chr = 'O';
		}
		
		}
	
	////////////////////////////////////
	
	
	//*******************************************
	//			OKBUTTONLISTENER--INNER CLASS---
	//*******************************************
	public class OKButtonActionListener implements ActionListener{
		
		private TicTacToe tictactoe;
		public TicTacToe getTicTacToe() {return tictactoe;}
		
		
		//OK button megnyomására létrejön a TicTacToe objektum, elindul a játék
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(chr);
			int c = (int)box.getSelectedItem();
			
			int p = (int)box2.getSelectedItem();
			
			
			boolean starts;
			if(chr == 'X') {starts = true;}else {starts = false;}
			
			tictactoe = new TicTacToe(c, p, starts);
			
			//mentsük el a pálya méretét, ha esetleg késõbb akarnánk folytatni a játékot
			ArrayList array = new ArrayList();
			array.add(c);
			array.add(p);
			array.add(starts);

			try {
				FileOutputStream fileout = new FileOutputStream("C:\\Users\\user\\Desktop\\TicTacToe_n.txt");
				ObjectOutputStream out = new ObjectOutputStream(fileout);
				out.writeObject(array);
				out.close();
				fileout.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
			
		}
	}
	//**********************************************
	//		END OF OKBUTTONLISTENER--INNER CLASS--
	//**********************************************
	
	
	//**************************************************
	//			CONTINUEBUTTONLISTENER--INNER CLASS---
	//**************************************************
	public class ContinueButttonActionListener implements ActionListener{
		
		
		//Continue gomb megynomására folytathatjuk a játékot, 
		//visszatöltõdik az elõzõ játék állása
		@Override
		public void actionPerformed(ActionEvent ae) {
			
			ArrayList array = new ArrayList();
			Boolean end = new Boolean(false);
			
			try {
				
				FileInputStream filein = new FileInputStream("C:\\Users\\user\\Desktop\\TicTacToe_n.txt");
				ObjectInputStream in = new ObjectInputStream(filein);
				array = (ArrayList) in.readObject();
				in.close();
				filein.close();
				
			}catch(ClassNotFoundException c){
				c.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			
			try {
				//visszaolvassuk a fájlból az adatokat
				FileInputStream filein = new FileInputStream("C:\\Users\\user\\Desktop\\TicTacToe_win.txt");
				ObjectInputStream in = new ObjectInputStream(filein);
				end = (Boolean) in.readObject();
				in.close();
				filein.close();
				
			}catch(ClassNotFoundException c){
				c.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			
			if(end == false) {
				TicTacToe t = new TicTacToe((int)(array.get(0)), (int)(array.get(1)), (boolean)(array.get(2)));
				List<List<Character>> playground = new ArrayList();
				
				try {
					try {
						playground = save_load.load();
						t.SetPlayground(playground);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//visszaállítjuk a táblát
				int X = 0;
				int O = 0;
				for(int o = 0; o < playground.size(); o++) {
					for(int s = 0; s < playground.size(); s++) {
						if(playground.get(o).get(s).equals('X')) {
							X++;
							t.getButton(s*playground.size() + o).setForeground(new Color(255,0,0));
							t.getButton(s*playground.size() + o).setText("X");
						}else if(playground.get(o).get(s).equals('O')) {
							O++;
							t.getButton(s*playground.size() + o).setForeground(new Color(0,0,255));
							t.getButton(s*playground.size() + o).setText("O");
						}
					}
				}
				//...és folytassuk ott, ahol abbahagytuk
				if(X > O) {
					t.SetWhosTurn(false);
					t.getTextfield().setText("O turn");
				}else {
					t.SetWhosTurn(true);
					t.getTextfield().setText("X turn");
				}
			}
			
		}
	}
	//****************************************************
	//		END OF CONTINUEBUTTONLISTENER--INNER CLASS--
	//****************************************************
	
	public InputRead() {
		frame = new JFrame();
		panel1 = new JPanel();
		panel2 = new JPanel();
		JLabel j2 = new JLabel("Válassza ki a tábla méretét!");
		JLabel j3 = new JLabel("Összegyûjtendõ szimbólumok: ");
		menu = new JMenuBar();
		
		//JMenuBar létrhozása: x vagy o kezdjen?
		starter = new JMenu("Who starts?");
		menu.add(starter);
		item1 = new JMenuItem("X starts");
		item2 = new JMenuItem("O starts");
		starter.add(item1);
		starter.add(item2);
		
		item1.addActionListener(new item1Listener());
		item2.addActionListener(new item2Listener());
		
		button = new JButton("Ok!");
		button.addActionListener(new OKButtonActionListener());
		
		ContinueButton = new JButton("Continue?");
		ContinueButton.addActionListener(new ContinueButttonActionListener());
		
		Object[] tomb = new Object[100];
		int j = 0;
		for(int i = 1; i <= 100; i++) {
			tomb[j++] = i;
		}
		box = new JComboBox(tomb);
		
		
		Object[] tomb2 = new Object[100];
		int x = 0;
		for(int i = 1; i <= 100; i++) {
			tomb2[x++] = i;
		}
		box2 = new JComboBox(tomb2);
		

		
		//*****elsõ panel**********
		panel1.setBorder(BorderFactory.createEmptyBorder());
		panel1.setLayout(new FlowLayout());
		panel1.add(j2);
		panel1.add(box);
		panel1.add(j3);
		panel1.add(box2);
		panel1.add(button);
		
		
		//*****második panel***********
		panel2.setBorder(BorderFactory.createEmptyBorder());
		panel2.setLayout(new FlowLayout());
		panel2.add(ContinueButton);
		
		//frame beállítása
		frame.setJMenuBar(menu);
		frame.add(panel1, BorderLayout.PAGE_START);
		frame.add(panel2, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("A JÁTÉKTÉR MÉRETE");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
}
