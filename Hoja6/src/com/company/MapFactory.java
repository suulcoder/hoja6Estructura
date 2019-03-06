package com.company;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapFactory{
	public Map<String, Carta> getMap(String needed){
		if (needed==null){
			return null;
		}
		else if(needed.equals("HashMap")){
			return new HashMap<String,Carta	>();
		}
		else if(needed.equals("TreeMap")){
			return new TreeMap<String, Carta>();
		}
		else if(needed.equals("LinkedHashMap")){
			return new LinkedHashMap<String	, Carta	>();
		}
		return	null;
	}
}