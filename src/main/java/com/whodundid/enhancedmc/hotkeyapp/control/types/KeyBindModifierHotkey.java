package com.whodundid.enhancedmc.hotkeyapp.control.types;

import com.whodundid.enhancedmc.hotkeyapp.control.Hotkey;
import com.whodundid.enhancedmc.hotkeyapp.control.KeyActionType;
import com.whodundid.enhancedmc.hotkeyapp.control.util.KeyComboAction;
import net.minecraft.client.settings.KeyBinding;

//Last edited: 9-30-18
//First Added: 9-14-18
//Author: Hunter Bragg

public class KeyBindModifierHotkey extends Hotkey {
	
	public KeyBinding keyBind;
	public boolean val;
	
	public KeyBindModifierHotkey(String keyNameIn, KeyComboAction keysIn, KeyBinding mcKeyIn, boolean newVal) { this(keyNameIn, keysIn, mcKeyIn, newVal, false, "", null); }
	public KeyBindModifierHotkey(String keyNameIn, KeyComboAction keysIn, KeyBinding mcKeyIn, boolean newVal, boolean builtInVal) { this(keyNameIn, keysIn, mcKeyIn, newVal, builtInVal, "", null); }
	public KeyBindModifierHotkey(String keyNameIn, KeyComboAction keysIn, KeyBinding mcKeyIn, boolean newVal, String descriptionIn) { this(keyNameIn, keysIn, mcKeyIn, newVal, false, descriptionIn, null); }
	public KeyBindModifierHotkey(String keyNameIn, KeyComboAction keysIn, KeyBinding mcKeyIn, boolean newVal, boolean builtInVal, String descriptionIn, String builtInAppTypeIn) {
		super(keyNameIn, keysIn, builtInVal, KeyActionType.MC_KEYBIND_MODIFIER, builtInAppTypeIn);
		if (descriptionIn != null && !descriptionIn.isEmpty()) { description = descriptionIn; }
		keyBind = mcKeyIn;
		val = newVal;
	}
	
	public KeyBinding getKeyBinding() { return keyBind; }
	public boolean getNewVal() { return val; }
	
	@Override
	public void executeHotKeyAction() {
		for (KeyBinding k : mc.gameSettings.keyBindings) {
			if (k.equals(keyBind)) {
				k.setKeyBindState(k.getKeyCode(), val);
				return;
			}
		}
		System.out.println("GAME SETTINGS DOES NOT CONTAIN KEY!~!~");
	}
	
	@Override
	public String getHotKeyStatistics() {
		String base = super.getHotKeyStatistics();
		base += ("; " + keyBind.toString());
		base += ("; " + val);
		return base;
	}
	
}
