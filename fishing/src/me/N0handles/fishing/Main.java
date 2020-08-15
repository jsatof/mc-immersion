package me.N0handles.fishing;

import java.util.Random;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		System.out.println("=============================");
		System.out.println("Fishing Simulator Activated");
		System.out.println("=============================");
		
		this.getCommand("test").setExecutor(new TestCommand());
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	// Overload functions for getting string of formatted item name
	String getItemName(ItemStack itemStack) {
		return WordUtils.capitalizeFully(itemStack.getType().name().toLowerCase().replace('_', ' '));
	}
	
	String getItemName(PotionData potionData) {
		return WordUtils.capitalizeFully(potionData.getType().name().toLowerCase().replace('_', ' '));
	}
	
	// When player joins server
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Bukkit.broadcastMessage(ChatColor.GRAY + "Fellow Gamer " +
								ChatColor.YELLOW + e.getPlayer().getDisplayName() + 
								ChatColor.GRAY + " has joined.");
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent e) {
		// Items a player fishes show in chat for everyone
		// <playerName> has caught <Item Name> 
		if(!(e.getCaught() == null)) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() + 
					ChatColor.GRAY + " has caught " +
					ChatColor.YELLOW + e.getCaught().getName());
		}
		 
		// Notifies everyone in chat that a player is fishing in water
		if(e.getState() == State.FISHING && e.getState() != State.IN_GROUND) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() +
						ChatColor.GRAY + " immerses themself in the wonders of fishing");
			e.getPlayer().updateInventory();
		}
	}
	
	// When a player breaks an item
	@EventHandler
	public void onBreak(PlayerItemBreakEvent e) {
		String itemName = getItemName(e.getBrokenItem());
		
		Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() + 
					   		ChatColor.GRAY + " broke their " +
							ChatColor.AQUA + itemName);
		Bukkit.broadcastMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "How unfortunate.");
		
	}
}
