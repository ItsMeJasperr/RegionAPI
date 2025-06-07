package nl.itsmejasperr.regionAPI.listener;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import nl.itsmejasperr.regionAPI.events.RegionEnterEvent;
import nl.itsmejasperr.regionAPI.utils.RegionAPIUtils;
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

        RegionManager regionManager = RegionAPIUtils.getRegionManager(player.getWorld());

        if(regionManager == null) return;

        BlockVector3 fromVec = BlockVector3.at(from.getX(), from.getY(), from.getZ());
        BlockVector3 toVec = BlockVector3.at(to.getX(), to.getY(), to.getZ());

        Set<String> fromRegions = regionManager.getApplicableRegions(fromVec).getRegions()
                .stream()
                .map(ProtectedRegion::getId)
                .collect(Collectors.toSet());

        for(ProtectedRegion region : regionManager.getApplicableRegions(toVec)){
            String regionId = region.getId();
            if(!fromRegions.contains(regionId)){
                Bukkit.getPluginManager().callEvent(new RegionEnterEvent(player, regionId));
                break;
            }
        }
    }

}
