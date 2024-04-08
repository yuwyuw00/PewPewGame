
public class Exe {

	public static void main(String[] args) {
		
		Thread pewGameThread = new Thread(new Intro());
		pewGameThread.run();

	}

}
