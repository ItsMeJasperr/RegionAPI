package nl.itsmejasperr.regionAPI.listener;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import nl.itsmejasperr.regionAPI.events.RegionEnterEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;
import java.util.stream.Collectors;

public class RegionListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().equals(event.getTo().getBlock())) return;

        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        RegionManager regionManager = WorldGuard.getInstance()
                .getPlatform()
                .getRegionContainer()
                .get(new BukkitWorld(player.getWorld()));
        if (regionManager == null) return;

        Set<String> fromRegions = regionManager.getApplicableRegions(BlockVector3.at(from.getX(), from.getY(), from.getZ()))
                .getRegions().stream().map(ProtectedRegion::getId).collect(Collectors.toSet());

        Set<String> toRegions = regionManager.getApplicableRegions(BlockVector3.at(to.getX(), to.getY(), to.getZ()))
                .getRegions().stream().map(ProtectedRegion::getId).collect(Collectors.toSet());

        for (String toRegion : toRegions) {
            if (!fromRegions.contains(toRegion)) {
                RegionEnterEvent regionEnterEvent = new RegionEnterEvent(player, toRegion);
                Bukkit.getPluginManager().callEvent(regionEnterEvent);

                if (regionEnterEvent.isCancelled()) {
                    event.setCancelled(true);
                    player.teleport(from);
                    break;
                }
            }
        }
    }

}
