package com.whodundid.enhancedmc.hotkeyapp.window.util;

import com.whodundid.enhancedmc.core.util.storage.EArrayList;
import com.whodundid.enhancedmc.core.window.objects.action.WindowTextField;
import com.whodundid.enhancedmc.core.window.types.interfaces.IWindowObject;
import com.whodundid.enhancedmc.hotkeyapp.window.HotKeyCreatorWindow;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

//Oct 2, 2018
//Last edited: Jan 16, 2019
//First Added: Oct 1, 2018
//Author: Hunter Bragg

public class KeyEntryTextField extends WindowTextField {

	public HotKeyCreatorWindow parentGui;
	public ArrayList<Integer> keys;
	private int lastEnteredKey = -1;
	
	public KeyEntryTextField(IWindowObject parentIn, int x, int y, int widthIn, int heightIn) { 
		super(parentIn, x, y, widthIn, heightIn);
		keys = new ArrayList();
		setMaxStringLength(100);
	}

	@Override
	public void keyPressed(char typedChar, int keyCode) {
		try {
			if (hasFocus() && keys != null) {
				if (keyCode != lastEnteredKey || keys.size() > 1) {
					enterKey(keyCode);
				}
			}
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public int[] getKeys() {
		if (keys.size() > 0) {
			int[] returnKeys = new int[keys.size()];
			for (int i = 0; i < keys.size(); i++) { returnKeys[i] = keys.get(i); }
			return returnKeys;
		}
		return new int[0];
	}
	
	public KeyEntryTextField setKeys(EArrayList<Integer> listIn) {
		for (int i : listIn) { enterKey(i); }
		return this;
 	}
	
	private void enterKey(int keyCode) {
		lastEnteredKey = keyCode;
		setText("");
		keys.clear();
		
		String displayMessage = "", key = "";
		boolean isCtrl = false, isShift = false, isAlt = false;
		
		switch (keyCode) {
		case Keyboard.KEY_LCONTROL:
		case Keyboard.KEY_RCONTROL: key = "Ctrl"; isCtrl = true; break;
		case Keyboard.KEY_LSHIFT:
		case Keyboard.KEY_RSHIFT: key = "Shift"; isShift = true; break;
		case Keyboard.KEY_LMENU:
		case Keyboard.KEY_RMENU: key = "Alt"; isAlt = true; break;
		default:
			String keyName = Keyboard.getKeyName(keyCode);
			keyName = keyName.toLowerCase();
			if (keyName.length() > 0) { keyName = keyName.substring(0, 1).toUpperCase() + keyName.substring(1); }
			key += keyName;
		}
		
		if (isCtrl) { displayMessage += key; keys.add(Keyboard.KEY_LCONTROL); }
		
		if (!isCtrl && parentGui.isCtrlKeyDown()) { displayMessage += "Ctrl + "; keys.add(Keyboard.KEY_LCONTROL); }
		if (!isShift && parentGui.isShiftKeyDown()) { displayMessage += isCtrl ? " + Shift" : "Shift + "; keys.add(Keyboard.KEY_LSHIFT); }
		if (!isAlt && parentGui.isAltKeyDown()) { displayMessage += isCtrl ? " + Alt" : "Alt + "; keys.add(Keyboard.KEY_LMENU); }
		
		if (!isCtrl) { displayMessage += key; keys.add(keyCode); }
		
		setText(displayMessage);
	}
	
}
