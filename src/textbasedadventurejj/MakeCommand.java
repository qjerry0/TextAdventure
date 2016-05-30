package textbasedadventurejj;

public class MakeCommand implements Command {

	@Override
	public void execute(String[] words) {
		if (words.length != 5 && words.length != 6) {
			return;
		}
		if (words[1].equals("in") && words[3].equals("as")) {// "make object in
																// location as
																// name"
			String object = words[0];
			Location location = LocationManager.getInstance().getSubLocation(words[2]);
			String objectName = words[4];
			location.getChildren().put(objectName, GameObjectManager.getInstance().newObject(objectName, object));
		}
		if (words[1].equals("linked") && words[2].equals("in") && words[4].equals("as")){
			String object = words[0];
			Location location = LocationManager.getInstance().getSubLocation(words[3]);
			String objectName = words[5];
			location.getChildren().put(objectName, LocationManager.getInstance().getObject(object));
		}
	}
}