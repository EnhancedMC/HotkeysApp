package com.whodundid.enhancedmc.hotkeyapp.control.types;

import com.whodundid.enhancedmc.core.util.player.PlayerTraits;
import com.whodundid.enhancedmc.hotkeyapp.control.Hotkey;
import com.whodundid.enhancedmc.hotkeyapp.control.KeyActionType;
import com.whodundid.enhancedmc.hotkeyapp.control.util.KeyComboAction;

//Last edited: Sep 30, 2018
//First Added: Sep 25, 2018
//Author: Hunter Bragg

public class ConditionalCommandSenderHotkey extends Hotkey {
	
	String command;
	int itemID;
	
	public ConditionalCommandSenderHotkey(String keyNameIn, KeyComboAction keysIn, String commandIn, int checkItemIDIn) { this(keyNameIn, keysIn, commandIn, checkItemIDIn, false, "", null); }
	public ConditionalCommandSenderHotkey(String keyNameIn, KeyComboAction keysIn, String commandIn, int checkItemIDIn, boolean builtInVal) { this(keyNameIn, keysIn, commandIn, checkItemIDIn, builtInVal, "", null); }
	public ConditionalCommandSenderHotkey(String keyNameIn, KeyComboAction keysIn, String commandIn, int checkItemIDIn, String descriptionIn) { this(keyNameIn, keysIn, commandIn, checkItemIDIn, false, descriptionIn, null); }
	public ConditionalCommandSenderHotkey(String keyNameIn, KeyComboAction keysIn, String commandIn, int checkItemIDIn, boolean builtInVal, String descriptionIn, String builtInAppTypeIn) {
		super(keyNameIn, keysIn, builtInVal, KeyActionType.CONDITIONAL_COMMAND_ITEMTEST, builtInAppTypeIn);
		if (descriptionIn != null && !descriptionIn.isEmpty()) { description = descriptionIn; }
		command = commandIn;
		itemID = checkItemIDIn;
	}
	
	public String getCommand() { return command; }
	public int getItemID() { return itemID; }
	public ConditionalCommandSenderHotkey setCommand(String commandIn) {
		command = commandIn;
		if (command == null) { command = "/"; }
		if (!command.startsWith("/")) { command = "/" + command; }
		return this;
	}
	public ConditionalCommandSenderHotkey setItemID(int idIn) { itemID = idIn; return this; }
	
	@Override
	public void executeHotKeyAction() {
		if (mc.thePlayer != null && PlayerTraits.isHoldingItem() && PlayerTraits.getHeldItemId() == itemID) {
			if (command == null) { command = "/"; }
			if (!command.startsWith("/")) { command = "/" + command; }
			mc.thePlayer.sendChatMessage(command);
		}
	}
	
	@Override
	public String getHotKeyStatistics() {
		String base = super.getHotKeyStatistics();
		base += ("; " + command);
		base += ("; " + itemID);
		return base;
	}
	
}
