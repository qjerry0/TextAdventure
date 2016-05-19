
package textbasedadventurejj;

import java.util.Map;

public class DoCommand implements Command {

	@Override
	public void execute(String[] words) {
		if(words.length == 2 && words[0].equals("debug")){
			System.out.println(LocationManager.getInstance().getContext());
		}
		Map<String, String> aliases = LocationManager.getInstance().getContext().getAliases();
		for(int i = 0; i < words.length; ++i){
			if(aliases.containsKey(words[i]))
				words[i] = aliases.get(words[i]);
		}
		Interpreter.getInstance().interpretSentence(words);
	}
}
