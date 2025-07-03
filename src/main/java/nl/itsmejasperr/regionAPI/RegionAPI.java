package nl.itsmejasperr.regionAPI;

import nl.itsmejasperr.regionAPI.listener.RegionListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegionAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);
    }
}
