package com.whodundid.enhancedmc.hotkeyapp.terminal;

import com.whodundid.enhancedmc.core.EnhancedMC;
import com.whodundid.enhancedmc.core.app.AppType;
import com.whodundid.enhancedmc.core.app.RegisteredApps;
import com.whodundid.enhancedmc.core.terminal.command.CommandType;
import com.whodundid.enhancedmc.core.terminal.command.TerminalCommand;
import com.whodundid.enhancedmc.core.terminal.window.ETerminal;
import com.whodundid.enhancedmc.core.util.storage.EArrayList;
import com.whodundid.enhancedmc.hotkeyapp.HotKeyApp;

public class CreateExampleKey extends TerminalCommand {
	
	public CreateExampleKey() {
		super(CommandType.APP_COMMAND);
		setCategory("Hotkeys");
		numArgs = 0;
	}
	
	@Override public String getName() { return "hkcreateexamplekey"; }
	@Override public boolean showInHelp() { return true; }
	@Override public EArrayList<String> getAliases() { return new EArrayList<>("hkcreateexample", "hkexample"); }
	@Override public String getHelpInfo(boolean runVisually) { return "Creates the example hotkey"; }
	@Override public String getUsage() { return "ex: hkexample"; }
	@Override public void handleTabComplete(ETerminal termIn, EArrayList<String> args) { }
	
	@Override
	public void runCommand(ETerminal termIn, EArrayList<String> args, boolean runVisually) {
		if (args.isNotEmpty()) { termIn.error("This command takes no arguments!"); }
		else {
			try {
				HotKeyApp app = (HotKeyApp) RegisteredApps.getApp(AppType.HOTKEYS);
				if (app != null) {
					app.createExampleKey();
					termIn.writeln("Creating ExampleHotKey..", 0x55ff55);
					EnhancedMC.reloadAllWindows();
				}
				else { termIn.error("Hotkeys is null!"); }
			}
			catch (Exception e) { error(termIn, e); }
		}
	}
	
}
