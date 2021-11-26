package com.whodundid.enhancedmc.hotkeyapp.settings;

import com.whodundid.enhancedmc.core.EnhancedMC;
import com.whodundid.enhancedmc.core.app.config.AppConfigSetting;
import net.minecraft.util.MathHelper;

public class InputDelay extends AppConfigSetting<Long> {
	
	public InputDelay() {
		super(Long.class, "keyInputDelay", "Key Input Delay", 1000L);
	}
	
	@Override
	public AppConfigSetting<Long> set(Object valIn) {
		long val = (long) valIn;
		if (!EnhancedMC.isDevMode()) { val = (long) MathHelper.clamp_double(val, 500L, Long.MAX_VALUE); }
		return super.set(val);
	}

}
