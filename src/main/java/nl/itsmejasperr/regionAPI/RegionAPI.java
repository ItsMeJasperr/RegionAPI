package nl.itsmejasperr.regionAPI;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegionAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(" ");
        getLogger().info("§8§m==========§r §3RegionAPI §8§m==========");
        getLogger().info("§eStatus: §aEnabled");
        getLogger().info("§eVersion: §f" + getDescription().getVersion());
        getLogger().info("§eAuthor(s): §f" + String.join(", ", getDescription().getAuthors()));
        getLogger().info("§eFunction: §7Region enter tracking");
        getLogger().info("§eWorldGuard Hook: §a" + (Bukkit.getPluginManager().isPluginEnabled("WorldGuard") ? "Found" : "Missing"));
        getLogger().info("§eListener Registered: §a" + (HandlerList.getRegisteredListeners(this).size() > 0 ? "Yes" : "No"));
        getLogger().info("§8§m==================================");
        getLogger().info(" ");

    }

    @Override
    public void onDisable() {
        getLogger().info(" ");
        getLogger().info("§8§m==========§r §3RegionAPI §8§m==========");
        getLogger().info("§eStatus: §cDisabled");
        getLogger().info("§eVersion: §f" + getDescription().getVersion());
        getLogger().info("§eAuthor(s): §f" + String.join(", ", getDescription().getAuthors()));
        getLogger().info("§eThanks for using our Region API! ♥");
        getLogger().info("§8§m==================================");
        getLogger().info(" ");
    }
}
