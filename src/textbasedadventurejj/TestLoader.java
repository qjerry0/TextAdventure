package textbasedadventurejj;

public class TestLoader {

	public static void main(String[] argv){
		
		Interpreter interp = Interpreter.getInstance();
		interp.addCommand("make", new MakeCommand());
		interp.addCommand("set", new SetCommand());
		interp.addCommand("if", new IfCommand());
		interp.addCommand("say", new SayCommand());
		
		GameObjectManager gmanager = GameObjectManager.getInstance();
		LocationManager lmanager = LocationManager.getInstance();

		gmanager.loadObjects();
		lmanager.load();
		
		Location root = lmanager.getRoot();
		System.out.println(root);
		
		GameObject controlDoor = lmanager.getObject("World.Ship.Closet.control_room_door");
		System.out.println(controlDoor);
	}
}
