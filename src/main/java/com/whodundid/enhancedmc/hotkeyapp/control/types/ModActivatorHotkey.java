package com.whodundid.enhancedmc.hotkeyapp.control.types;

import com.whodundid.enhancedmc.core.app.AppType;
import com.whodundid.enhancedmc.core.app.util.AppEnabler;
import com.whodundid.enhancedmc.core.util.chat.EChatUtil;
import com.whodundid.enhancedmc.hotkeyapp.control.Hotkey;
import com.whodundid.enhancedmc.hotkeyapp.control.KeyActionType;
import com.whodundid.enhancedmc.hotkeyapp.control.util.KeyComboAction;
import net.minecraft.util.EnumChatFormatting;

//Last edited: 9-30-18
//First Added: 9-14-18
//Author: Hunter Bragg

public class ModActivatorHotkey extends Hotkey {
	
	public AppType appType;
	
	public ModActivatorHotkey(String keyNameIn, KeyComboAction keysIn, AppType appIn) { this(keyNameIn, keysIn, appIn, false, "", null); }
	public ModActivatorHotkey(String keyNameIn, KeyComboAction keysIn, AppType appIn, boolean builtInVal) { this(keyNameIn, keysIn, appIn, builtInVal, "", null); }
	public ModActivatorHotkey(String keyNameIn, KeyComboAction keysIn, AppType appIn, String descriptionIn) { this(keyNameIn, keysIn, appIn, false, descriptionIn, null); }
	public ModActivatorHotkey(String keyNameIn, KeyComboAction keysIn, AppType appIn, boolean builtInVal, String descriptionIn, String builtInAppTypeIn) {
		super(keyNameIn, keysIn, builtInVal, KeyActionType.APP_ACTIVATOR, builtInAppTypeIn);
		if (descriptionIn != null && !descriptionIn.isEmpty()) { description = descriptionIn; }
		appType = appIn;
	}
	
	public AppType getApp() { return appType; }
	public ModActivatorHotkey setApp(AppType typeIn) { appType = typeIn; return this; }
	
	@Override
	public void executeHotKeyAction() {
		AppEnabler.enableApp(appType, null);
		EChatUtil.show(EnumChatFormatting.GREEN + "Hokeys Enabled App: " + appType);
	}
	
	@Override
	public String getHotKeyStatistics() {
		String base = super.getHotKeyStatistics();
		base += ("; " + AppType.getAppName(appType));
		return base;
	}
	
}
