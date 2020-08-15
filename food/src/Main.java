package me.N0handles.food;


class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        System.out.println("==========================");
        System.out.println("Food Enhancements Enabled");
        System.out.println("==========================");
    }

    String getItemName(ItemStack itemStack) {
        return WordUtils.capitalizeFully(itemStack.getType().name().toLowerCase().replace('_', ' '));
    }

    String getItemName(PotionData potionData) {
        return WordUtils.capitalizeFully(potionData.getType().name().toLowerCase().replace('_', ' '));
    }

    // When players eat food and drink potions
	@EventHandler
 	public void onEat(PlayerItemConsumeEvent e) {
		String itemName = getItemName(e.getItem());
		// Notifies everyone in chat when someone eats a potato
		if(itemName.equals("Potato Item") || itemName.equals("Baked Potato")) {
			String[] adjectives = { "T H I C C", "buttery", "juicy" };
			Random random = new Random();
			String adj = adjectives[random.nextInt(adjectives.length)];
			// special case for THICC potato
			if(adj.equals("T H I C C")) {
				Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() +
						ChatColor.GRAY + " chomps on a " +
						ChatColor.WHITE + ChatColor.BOLD + ChatColor.UNDERLINE + ChatColor.ITALIC + adj +
						ChatColor.UNDERLINE + ChatColor.GOLD + ChatColor.BOLD + " Gamer Tater" );
			} else {
				Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() +
						ChatColor.GRAY + " munches on a " + adj +
						ChatColor.GOLD + ChatColor.BOLD + " Gamer Tater");
			}
		}
		
		// On Bread Eat
		if(itemName.equals("Bread")) {
			Bukkit.broadcastMessage(ChatColor.GRAY + "Fellow gamer " + ChatColor.YELLOW + e.getPlayer().getDisplayName() +
						ChatColor.GRAY + " eats their " +
						ChatColor.GOLD + ChatColor.BOLD + "Gamer Bread");
		}
		
		// Handling potion drink
		if(e.getItem().hasItemMeta() && e.getItem().getType() == Material.POTION) {
			PotionMeta potionMeta = (PotionMeta) e.getItem().getItemMeta();
			PotionData potionData = potionMeta.getBasePotionData();
			if(getItemName(potionData).equals("Strength")) {
				Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() + 
										ChatColor.GRAY + " comsumed a " +
										ChatColor.DARK_RED + ChatColor.BOLD + "Potion of " +
										getItemName(potionData));
				Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + e.getPlayer().getDisplayName() + 
										" is filled with determination!");
			}
 		}
	}
}