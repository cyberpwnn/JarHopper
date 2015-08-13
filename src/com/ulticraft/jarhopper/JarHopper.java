package com.ulticraft.jarhopper;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class JarHopper extends JavaPlugin
{
	private Logger logger;
	private Hopper hopper;
		
	@Override
	public void onEnable()
	{
		logger = getLogger();
		hopper = new Hopper(this);
		hopper.canHop();
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
	{
		if(cmd.getName().equalsIgnoreCase(Final.CMD_JARHOP))
		{
			if(sender.hasPermission(Final.PERM_JARHOP))
			{
				hopper.hop(sender);
			}
			
			else
			{
				sender.sendMessage(ChatColor.RED + "You have no permission here!");
			}
		}
		
		return false;
	}
	
	public void info(String msg)
	{
		logger.info(msg);
	}
	
	public Hopper getHopper()
	{
		return hopper;
	}
}
