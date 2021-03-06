package com.whodundid.enhancedmc.hotkeyapp.control;

import com.whodundid.enhancedmc.core.app.AppType;
import com.whodundid.enhancedmc.core.app.RegisteredApps;
import com.whodundid.enhancedmc.core.util.storage.EArrayList;
import com.whodundid.enhancedmc.hotkeyapp.HotKeyApp;
import com.whodundid.enhancedmc.hotkeyapp.control.util.HotkeyCategory;
import com.whodundid.enhancedmc.hotkeyapp.control.util.KeyComboAction;
import com.whodundid.enhancedmc.hotkeyapp.control.util.exceptions.HotkeyActionException;
import net.minecraft.client.Minecraft;

//Last edited: 9-30-18
//First Added: 9-14-18
//Author: Hunter Bragg

public abstract class Hotkey {
	
	protected Minecraft mc = Minecraft.getMinecraft();
	protected HotKeyApp man = (HotKeyApp) RegisteredApps.getApp(AppType.HOTKEYS);
	protected String keyName = "";
	protected String description = "No description set.";
	protected KeyComboAction keyCombo;
	protected HotkeyCategory category;
	protected boolean isEnabled = true;
	protected boolean builtIn = false;
	protected String builtInAppType = "";
	protected EArrayList<KeyActionType> actions;
	protected KeyActionType hotKeyType;
	
	protected Hotkey() {}
	public Hotkey(String keyNameIn, KeyComboAction keyCodeIn, KeyActionType typeIn) { this(keyNameIn, keyCodeIn, true, typeIn, null); }
	public Hotkey(String keyNameIn, KeyComboAction keyCodeIn, KeyActionType typeIn, String builtInSubModTypeIn) { this(keyNameIn, keyCodeIn, true, typeIn, builtInSubModTypeIn); }
	public Hotkey(String keyNameIn, KeyComboAction keyCodeIn, boolean builtInVal, KeyActionType typeIn) { this(keyNameIn, keyCodeIn, builtInVal, typeIn, null); }
	public Hotkey(String keyNameIn, KeyComboAction keyCodeIn, boolean builtInVal, KeyActionType typeIn, String builtInAppTypeIn) { this(keyNameIn, keyCodeIn, builtInVal, typeIn, null, builtInAppTypeIn); }
	public Hotkey(String keyNameIn, KeyComboAction keyCodeIn, boolean builtInVal, KeyActionType typeIn, String categoryNameIn, String builtInAppTypeIn) {
		keyName = keyNameIn;
		keyCombo = keyCodeIn;
		builtIn = builtInVal;
		hotKeyType = typeIn;
		builtInAppType = builtInAppTypeIn;
		if (categoryNameIn != null) { category = new HotkeyCategory(categoryNameIn); }
	}
	
	//getters
	public String getKeyName() { return keyName; }
	public String getKeyDescription() { return description; }
	public KeyComboAction getKeyCombo() { return keyCombo; }
	public KeyActionType getHotKeyType() { return hotKeyType; }
	public HotkeyCategory getKeyCategory() { return category; }
	public String getBuiltInAppType() { return isAppKey() ? builtInAppType : "not app key"; }
	
	//setters
	public void setEnabled(boolean enable) { isEnabled = enable; }
	public void setKeyCategory(String categoryNameIn) { category = new HotkeyCategory(categoryNameIn); }
	public void setKeyDescription(String descriptionIn) { description = descriptionIn; }
	
	//general
	public boolean isEnabled() { return isEnabled; }
	public boolean isAppKey() { return builtIn; }
	
	public Hotkey addAction(KeyActionType typeIn) throws HotkeyActionException {
		if (actions.contains(typeIn)) { throw new HotkeyActionException("Cannot have multiple actions of the same type in one key!"); }
		actions.add(typeIn);
		return this;
	}
	
	public Hotkey removeAction(KeyActionType typeIn) throws HotkeyActionException {
		if (!actions.contains(typeIn)) { throw new HotkeyActionException("HotKey: " + keyName + " does not contain the action: " + KeyActionType.getStringFromType(typeIn)); }
		actions.remove(typeIn);
		return this;
	}
	
	public Hotkey changeAction(KeyActionType oldAction, KeyActionType newAction) throws HotkeyActionException {
		if (!actions.contains(oldAction)) { throw new HotkeyActionException("Cannot replace an action that doesn't exist!"); }
		if (actions.contains(newAction)) { throw new HotkeyActionException("Cannot have multiple actions of the same type in one key!"); }
		actions.remove(oldAction);
		actions.add(newAction);
		return this;
	}
	
	public String getHotKeyStatistics() {
		StringBuilder returnStats = new StringBuilder(keyName + "; ");
		returnStats.append(hotKeyType.toString()).append("; ");
		for (int i : keyCombo.hotKeyCodes) returnStats.append(i).append(" ");
		returnStats.append("; ");
		returnStats.append(isEnabled).append("; ");
		if (category != null) returnStats.append(category.getCategoryName());
		returnStats.append(builtIn);
		return returnStats.toString();
	}
	
	public abstract void executeHotKeyAction();
	
}
