import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavePlayground implements Serializable{
	
	//mentjük fájlba a paraméterként kapott playground-ot
	public void save(List<List<Character>> playground) throws IOException{
		try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\user\\Desktop\\TicTacToe_playground.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(playground);
			out.close();
			fileout.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//visszatöltés fájlból, majd visszatérünk egy List-tel
	public List<List<Character>> load() throws IOException, ClassNotFoundException{
		
		List<List<Character>> playground = new ArrayList();
		try {
			
			FileInputStream filein = new FileInputStream("C:\\Users\\user\\Desktop\\TicTacToe_playground.txt");
			ObjectInputStream in = new ObjectInputStream(filein);
			playground = (List<List<Character>>) in.readObject();
			in.close();
			filein.close();
			
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return playground;
	}
}
