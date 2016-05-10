package textbasedadventurejj;

public class TestLoader {

	public static void main(String[] argv) {

		Interpreter interp = Interpreter.getInstance();
		interp.addCommand("make", new MakeCommand());
		interp.addCommand("set", new SetCommand());
		interp.addCommand("if", new IfCommand());
		interp.addCommand("say", new SayCommand());
		interp.addCommand("do", new DoCommand());

		GameObjectManager gmanager = GameObjectManager.getInstance();
		LocationManager lmanager = LocationManager.getInstance();

		gmanager.loadObjects();
		lmanager.load();
//		System.out.println(LocationManager.getInstance().getRoot());

		//Location root = lmanager.getRoot();
		//System.out.println(root);
		lmanager.setContext(LocationManager.getInstance().getSubLocation("World.Ship.Closet"));
		RunGame.runGame();
	}
}
