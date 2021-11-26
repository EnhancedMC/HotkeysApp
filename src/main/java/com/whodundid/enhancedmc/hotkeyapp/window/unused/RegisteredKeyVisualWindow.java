package com.whodundid.enhancedmc.hotkeyapp.window.unused;

import com.whodundid.enhancedmc.core.util.render.EColors;
import com.whodundid.enhancedmc.core.window.types.WindowParent;

//Last edited: Dec 21, 2018
//First Added: Dec 21, 2018
//Author: Hunter Bragg

public class RegisteredKeyVisualWindow extends WindowParent {
	
	@Override
	public void initWindow() {
		setDimensions(200, 256);
		setObjectName("RegisteredKeyVisualGui");
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
	
}
