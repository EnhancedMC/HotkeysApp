package com.whodundid.enhancedmc.hotkeyapp.storage;

import com.whodundid.enhancedmc.core.app.AppType;
import com.whodundid.enhancedmc.core.debug.IDebugCommand;
import com.whodundid.enhancedmc.core.util.storage.StorageBox;
import com.whodundid.enhancedmc.hotkeyapp.HotKeyApp;
import com.whodundid.enhancedmc.hotkeyapp.control.Hotkey;
import com.whodundid.enhancedmc.hotkeyapp.control.KeyActionType;
import com.whodundid.enhancedmc.hotkeyapp.control.types.*;
import com.whodundid.enhancedmc.hotkeyapp.control.util.KeyComboAction;
import net.minecraft.client.settings.KeyBinding;

//Sep 30, 2018
//Last edited: Feb 17, 2019
//Edit note: Added support for key categories
//First Added: Sep 30, 2018
//Author: Hunter Bragg

public class HotKeyBuilder {
	
	HotKeyApp man;
	Hotkey createdKey = null;
	KeyComboAction keys;
	String command;
	int testHeldItemId = -1;
	IDebugCommand debugCommand;
	Class gui;
	String[] scriptArgs;
	KeyBinding keyBind;
	boolean val;
	AppType mod;
	String builderErrorMessage = "";
	StorageBox<Class[], Object[]> additionalGuiArgs = null;
	
	public HotKeyBuilder(HotKeyApp manIn) {
		man = manIn;
	}
	
	public void setBuilderKeys(KeyComboAction keysIn) { keys = keysIn; }
	public void setBuilderCommand(String commandIn) { command = commandIn; }
	public void setBuilderCommandAndItemArgs(String commandIn, int id) { command = commandIn; testHeldItemId = id; }
	public void setBuilderDebugCommand(IDebugCommand commandIn) { debugCommand = commandIn; }
	public void setBuilderGuiToBeOpened(Class guiClassIn) { gui = guiClassIn; }
	public void setBuilderGuiToBeOpened(Class guiClassIn, StorageBox<Class[], Object[]> argsIn) { gui = guiClassIn; additionalGuiArgs = argsIn; }
	public void setBuilderKeyBindingIn(KeyBinding keyBindingIn, boolean newVal) { keyBind = keyBindingIn; val = newVal;}
	public void setBuilderSubMod(AppType modIn) { mod = modIn; }
	
	public void clearBuilderArgs() {
		keys = null;
		command = null;
		testHeldItemId = -1;
		debugCommand = null;
		gui = null;
		scriptArgs = null;
		keyBind = null;
		val = false;
		mod = null;
	}
	
	public boolean buildHotKey(String keyName, String categoryIn, String keyDescription, boolean keyEnabled, KeyActionType keyType) {
		try {
			switch (keyType) {
			case COMMANDSENDER: createdKey = new CommandSenderHotkey(keyName, keys, command, keyDescription); break;
			case CONDITIONAL_COMMAND_ITEMTEST: createdKey = new ConditionalCommandSenderHotkey(keyName, keys, command, testHeldItemId, keyDescription); break;
			case DEBUG: createdKey = new DebugHotkey(keyName, keys, debugCommand, keyDescription); break;
			case GUI_OPENER:
				if (additionalGuiArgs != null) {
					createdKey = new GuiOpenerHotkey(keyName, keys, gui, keyDescription).setParamTypes(additionalGuiArgs.getObject()).setParamValues(additionalGuiArgs.getValue());
				}
				else { createdKey = new GuiOpenerHotkey(keyName, keys, gui, keyDescription); }
				break;
			case MC_KEYBIND_MODIFIER: createdKey = new KeyBindModifierHotkey(keyName, keys, keyBind, val, keyDescription); break;
			case APP_ACTIVATOR: createdKey = new ModActivatorHotkey(keyName, keys, mod, keyDescription); break;
			case APP_DEACTIVATOR: createdKey = new ModDeactivatorHotkey(keyName, keys, mod, keyDescription); break;
			case CATEGORY_ACTIVATOR: createdKey = new KeyCategoryActivatorHotkey(keyName, keys, command, keyDescription); break;
			case CATEGORY_DEACTIVATOR: createdKey = new KeyCategoryDeactivatorHotkey(keyName, keys, command, keyDescription); break;
			default: clearBuilderArgs(); return false;
			}
			
			createdKey.setKeyCategory(categoryIn);
			clearBuilderArgs();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Hotkey getBuiltKey() { return createdKey; }
	
}
