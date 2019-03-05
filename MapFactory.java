import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapFactory{
	public Map<Integer, String> getMap(String needed){
		if (needed==null){
			return null;
		}
		else if(needed.equals("HashMap")){
			return new HashMap<Integer, String>();
		}
		else if(needed.equals("TreeMap")){
			return new TreeMap<Integer, String>();
		}
		else if(needed.equals("LinkedHashMap")){
			return new LinkedHashMap<Integer, String>();
		}
	}
}