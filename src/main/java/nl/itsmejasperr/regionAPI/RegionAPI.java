package nl.itsmejasperr.regionAPI;

import nl.itsmejasperr.regionAPI.listener.RegionListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegionAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);


        long start = System.currentTimeMillis();

        getLogger().info("========[ RegionAPI ]========");
        getLogger().info("Status            : ENABLED");
        getLogger().info("Version           : " + getDescription().getVersion());
        getLogger().info("Author(s)         : " + String.join(", ", getDescription().getAuthors()));
        getLogger().info("Function          : Region enter tracking");
        getLogger().info("WorldGuard Hook   : " + (Bukkit.getPluginManager().isPluginEnabled("WorldGuard") ? "Found" : "Missing"));
        getLogger().info("Listener Registered: " + (HandlerList.getRegisteredListeners(this).size() > 0 ? "Yes" : "No"));
        getLogger().info("Load Time         : " + (System.currentTimeMillis() - start) + " ms");
        getLogger().info("GitHub            : github.com/ItsMeJasperr/RegionAPI");
        getLogger().info("Discord Support   : discord.itsmejasperr.nl");
        getLogger().info("=============================");
    }

    @Override
    public void onDisable() {
        getLogger().info("========[ RegionAPI ]========");
        getLogger().info("Status            : DISABLED");
        getLogger().info("GitHub            : github.com/ItsMeJasperr/RegionAPI");
        getLogger().info("Discord Support   : discord.itsmejasperr.nl");
        getLogger().info("=============================");
    }
}
