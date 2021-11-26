package com.whodundid.enhancedmc.hotkeyapp.window.unused;

import com.whodundid.enhancedmc.core.app.AppType;
import com.whodundid.enhancedmc.core.app.RegisteredApps;
import com.whodundid.enhancedmc.core.debug.IDebugCommand;
import com.whodundid.enhancedmc.core.util.render.EColors;
import com.whodundid.enhancedmc.core.window.objects.action.WindowButton;
import com.whodundid.enhancedmc.core.window.objects.action.WindowTextField;
import com.whodundid.enhancedmc.core.window.objects.advanced.textarea.WindowTextArea;
import com.whodundid.enhancedmc.core.window.types.WindowParent;
import com.whodundid.enhancedmc.core.window.types.interfaces.IActionObject;
import com.whodundid.enhancedmc.hotkeyapp.HotKeyApp;
import com.whodundid.enhancedmc.hotkeyapp.control.Hotkey;
import com.whodundid.enhancedmc.hotkeyapp.control.KeyActionType;
import com.whodundid.enhancedmc.hotkeyapp.window.util.KeyEntryTextField;
import net.minecraft.client.settings.KeyBinding;

//Last edited: Feb 14, 2019
//First Added: Feb 14, 2019
//Author: Hunter Bragg

public class HotKeyCreationStepByStep extends WindowParent {
	
	HotKeyApp man = (HotKeyApp) RegisteredApps.getApp(AppType.HOTKEYS);
	Hotkey creationKey;
	KeyEntryTextField keyEntry;
	WindowTextArea selectionList;
	WindowTextField nameEntry, commandEntry, argEntry;
	WindowButton cancelButton, nextButton, createButton, backButton;
	String keyName = "";
	boolean enabledVal = true;
	KeyActionType selectedHotKeyType;
	Class selectedGui;
	//EScript selectedScript;
	IDebugCommand selectedDebug;
	AppType selectedMod;
	KeyBinding selectedKeyBind;
	int currentScreen = 0;
	
	@Override
	public void initWindow() {
		loadScreen(currentScreen);
	}
	
	@Override
	public void initObjects() {
		defaultHeader(this);
	}
	
	@Override
	public void drawObject(int mXIn, int mYIn) {
		drawDefaultBackground();
		
		drawStringCS("Not ready :)", midX, midY, EColors.rainbow());
		
		super.drawObject(mXIn, mYIn);
	}
	
	@Override
	public void mousePressed(int mXIn, int mYIn, int button) {
		super.mousePressed(mXIn, mYIn, button);
	}
	
	@Override
	public void actionPerformed(IActionObject object, Object... args) {
		if (object == nextButton) {
			
		}
	}
	
	private void loadScreen(int screenNumIn) {
		switch (screenNumIn) {
		case 0:
		case 1:
		case 2:
		case 3:
		default: break;
		}
	}
	
	private void loadSelectionList() {
		
	}
	
	private void hideAllObjects() {
		
	}
	
	private void createKey() {
		
	}
	
}
