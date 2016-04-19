
package textbasedadventurejj;

import java.util.ArrayList;
import java.util.Arrays;

public class IfCommand implements Command {

    @Override
    public boolean execute(String[] words) {
        if (words.length < 1) {
            return false;
        }
        GameObject object = Interpreter.getInstance().parseObject(words[0]);
        if (words[1].equals("contains")) {
            GameObject subObject = Interpreter.getInstance().parseObject(words[2]);
            if (subObject.getParent().equals(object)) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else if (words[1].equals("is")) {
            if (object.getState().equals(words[1])) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else if (words[1].equals("exists")) {
            GameObject target = Interpreter.getInstance().parseObject(words[2]);
            Location currentLocation = Interpreter.getInstance().getContext();
            ArrayList<GameObject> allObjects = new ArrayList<GameObject>();
            for (GameObject obj : currentLocation.getGameObjects()) {
                allObjects.add(obj);
            }
            boolean exist = false;
            for (GameObject obj : allObjects) {
                if(obj.equals(target))
                    exist = true;
            }
            if (exist) {
                return true;
            } else {
                Interpreter.getInstance().skipUntilNewline();
                return false;
            }
        } else {
            return false;//nothing is executed
        }
    }

}
