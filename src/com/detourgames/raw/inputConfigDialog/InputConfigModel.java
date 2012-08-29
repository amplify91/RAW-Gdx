package com.detourgames.raw.inputConfigDialog;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class InputConfigModel {
	
	public InputConfigModel(String[]commands){
		for(int i=0;i<commands.length;i++)
			keyTable.put(commands[i], null);
	}
	public InputConfigModel(String[]commands, Character[] defaultKeys)
	{
		if(commands.length!=defaultKeys.length)
		{
			throw new IndexOutOfBoundsException();
		}
		for(int i=0;i<commands.length;i++)
		{
			keyTable.put(commands[i], defaultKeys[i]);
		}
	}
	
	Hashtable<String,Character> keyTable=new Hashtable<String,Character>();

	public Enumeration<String> getCommandNames(){
		return keyTable.keys();
	}

	public Enumeration<Character> getCommandKeys(){
		return keyTable.elements();
	}

	public boolean setCommandKeys(Enumeration<Character> keys)
	{
		Hashtable<String,Character> newTable=new Hashtable<String, Character>();
		Enumeration<String> commands=keyTable.keys();
		while(commands.hasMoreElements())
		{
			newTable.put(commands.nextElement(), null);
		}
		while(keys.hasMoreElements())
		{
			Character nextKey=keys.nextElement();
			if(newTable.containsValue(nextKey))
				return false;
			newTable.put(keyTable.keys().nextElement(),nextKey);
		}
		if(newTable.size()!=keyTable.size())
			return false;
		
		keyTable=newTable;
		return true;
		
	}
}
