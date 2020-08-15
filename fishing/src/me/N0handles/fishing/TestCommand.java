package me.N0handles.fishing;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

public class TestCommand implements CommandExecutor {
	// Overloading methods for getting item name as string
	String getItemName(ItemStack itemStack) {
		return WordUtils.capitalizeFully(itemStack.getType().name().toLowerCase().replace('_', ' '));
	}
	
	String getItemName(PotionData potionData) {
		return WordUtils.capitalizeFully(potionData.getType().name().toLowerCase().replace('_', ' '));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(args.length == 0) {
				player.setFoodLevel(1);
				player.sendMessage("Hunger emptied");
			}
			else if(args.length == 1) {
				switch(args[0]) {
					case "item":
						ItemStack itemHand = player.getInventory().getItemInMainHand();
						String itemName = getItemName(itemHand);
						if(itemHand.hasItemMeta() && itemHand.getType() == Material.POTION) {
							PotionMeta potionMeta = (PotionMeta) itemHand.getItemMeta();
							PotionData potionData = potionMeta.getBasePotionData();
							player.sendMessage("Potion of " + getItemName(potionData));
						} else {	
							player.sendMessage(itemName);
						}
						break;
					default:
						player.sendMessage("Invalid args.");
						break;
				}
			}
		} else {
			sender.sendMessage("Only players may use this command");
		}
		return false;
	}
}
