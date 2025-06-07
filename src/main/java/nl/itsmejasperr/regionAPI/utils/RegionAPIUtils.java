package nl.itsmejasperr.regionAPI.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class RegionAPIUtils {

    /**
     * Haalt alle regio IDs op in de wereld van de speler.
     */
    public static Set<String> getAllRegionIds(Player player) {
        RegionManager manager = getRegionManager(player.getWorld());
        if (manager == null) return Collections.emptySet();
        return manager.getRegions().keySet();
    }

    /**
     * Haalt de RegionManager op voor een bepaalde wereld.
     */
    public static RegionManager getRegionManager(World world) {
        return WorldGuard.getInstance()
                .getPlatform()
                .getRegionContainer()
                .get(BukkitAdapter.adapt(world));
    }

    /**
     * Haalt een specifieke regio op of gooit een foutmelding als hij niet bestaat.
     */
    public static boolean doesRegionExist(Player player, String regionId) {
        RegionManager manager = getRegionManager(player.getWorld());
        return manager != null && manager.hasRegion(regionId);
    }


    /**
     * Geeft een lijst van alle regio's waarin de speler zich op dat moment bevindt.
     */
    public static Set<String> getRegionsAt(Player player) {
        RegionManager manager = getRegionManager(player.getWorld());
        if (manager == null) return Collections.emptySet();

        BlockVector3 pos = BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        return manager.getApplicableRegions(pos)
                .getRegions()
                .stream()
                .map(ProtectedRegion::getId)
                .collect(Collectors.toSet());
    }
}
