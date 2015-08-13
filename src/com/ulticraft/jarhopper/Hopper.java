package com.ulticraft.jarhopper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.UnknownDependencyException;

public class Hopper
{
	private JarHopper jh;
	
	public Hopper(JarHopper jh)
	{
		this.jh = jh;
	}
	
	public void hop(CommandSender sender)
	{
		if(!canHop())
		{
			sender.sendMessage(ChatColor.RED + "plugins/JarHopper/ is EMPTY! Add stuff to hop it!");
			return;
		}
		
		File pFolder = jh.getDataFolder();
		File pDest = pFolder.getParentFile();
		PluginManager pm = jh.getServer().getPluginManager();
		
		for(File i : pFolder.listFiles())
		{
			if(i.isDirectory() || !i.getName().toLowerCase().endsWith(".jar"))
			{
				continue;
			}
			
			String pluginName = StringUtils.removeEndIgnoreCase(i.getName(), ".jar");
			Plugin pluginInstance = pm.getPlugin(pluginName);
			File pDestFile = new File(pDest.getPath() + File.separator + i.getName());
			
			try
			{
				copyFile(i, pDestFile);
			}
			
			catch(IOException e)
			{
				sender.sendMessage(ChatColor.RED + "Failed to Copy File: " + i.getName());
				e.printStackTrace();
			}
			
			if(pDestFile.exists())
			{
				try
				{
					pluginInstance = pm.loadPlugin(pDestFile);
					pm.enablePlugin(pluginInstance);
				}
				
				catch(UnknownDependencyException e)
				{
					sender.sendMessage(ChatColor.RED + "Failed to Load Plugin: " + pDestFile.getName() + "(UDE)");
					e.printStackTrace();
					continue;
				}
				
				catch(InvalidPluginException e)
				{
					try
					{
						pm.disablePlugin(pluginInstance);
						pm.enablePlugin(pluginInstance);
					}
					
					catch(Exception ne)
					{
						sender.sendMessage(ChatColor.RED + "Failed to Load Plugin: " + pDestFile.getName() + "(INE/IPE)");
						e.printStackTrace();
						ne.printStackTrace();
						continue;
					}
				}
				
				catch(InvalidDescriptionException e)
				{
					sender.sendMessage(ChatColor.RED + "Failed to Load Plugin: " + pDestFile.getName() + "(IDE)");
					e.printStackTrace();
					continue;
				}
				
				catch(Exception e)
				{
					sender.sendMessage(ChatColor.RED + "Failed to Load Plugin: " + pDestFile.getName() + "(UPE)");
					e.printStackTrace();
					continue;
				}
			}
			
			if(pm.isPluginEnabled(pluginInstance))
			{
				sender.sendMessage(ChatColor.GREEN + "Hopped " + pluginInstance.getName() + " v" + pluginInstance.getDescription().getVersion());
				i.delete();
			}
		}
	}
	
	public boolean canHop()
	{
		File pFolder = jh.getDataFolder();
		
		if(!pFolder.exists())
		{
			pFolder.mkdir();
		}
		
		return pFolder.listFiles().length > 0;
	}
	
	public static void copyFile(File sourceFile, File destFile) throws IOException
	{
		if(!destFile.exists())
		{
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try
		{
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		
		finally
		{
			if(source != null)
			{
				source.close();
			}
			
			if(destination != null)
			{
				destination.close();
			}
		}
	}
}
