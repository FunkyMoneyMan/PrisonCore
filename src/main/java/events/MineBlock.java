package events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import de.tr7zw.nbtapi.NBTItem;
import main.Pickaxe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MineBlock implements Listener {

    @EventHandler
    public void mineBlock(BlockBreakEvent event) {
        if (!event.isCancelled() && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE)) {
            ItemStack pickaxe = event.getPlayer().getInventory().getItemInMainHand();
            NBTItem NBT = new NBTItem(pickaxe);
            int fortune = NBT.getInteger("Fortune");
            Location loc = event.getBlock().getLocation();
            RegionManager regions = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(event.getBlock().getWorld()));
            List<String> listOfRegions = regions.getApplicableRegionsIDs(BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
            String requiredRegion = "";
            for (String region : listOfRegions) {
                if (region.contains("pcmine")) {
                    requiredRegion = region;
                    break;
                }
            }
            int blocksMined = 1;
            List<Integer> offsets = new ArrayList<>(generate3D(9));
            for (int i = 0; i < offsets.size(); i += 3) {
                //event.getPlayer().sendMessage(offsets.get(i) + ", " + offsets.get(i+ 1) + ", " + offsets.get(i + 2));
                Location blockLocation = new Location(event.getBlock().getWorld(), loc.getBlockX() + offsets.get(i), loc.getBlockY() + offsets.get(i + 1), loc.getBlockZ() + offsets.get(i + 2));
                listOfRegions = regions.getApplicableRegionsIDs(BlockVector3.at(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ()));
                for (String region : listOfRegions) {
                    if (region.equals(requiredRegion)) {
                        requiredRegion = region;
                        Block block = Bukkit.getWorld(event.getBlock().getWorld().getName()).getBlockAt(blockLocation);
                        if (block.getType() != Material.BEDROCK && block.getType() != Material.AIR) {
                            event.getPlayer().getInventory().addItem(new ItemStack(block.getType(), fortune + 1));
                            block.setType(Material.AIR);
                            blocksMined++;
                        }
                        break;
                    }
                }
            }
            event.setDropItems(false);
            event.setExpToDrop(0);
            NBT.setInteger("BlocksMined", NBT.getInteger("BlocksMined") + blocksMined);
            NBT.setInteger("XP", NBT.getInteger("XP") + blocksMined);
            event.getPlayer().getInventory().setItem(0, Pickaxe.UpdatePick(event.getPlayer(), NBT.getItem()));
        }
    }

    public List<Integer> generate3D(int offset) {
        List<Integer> offsetList = new ArrayList<>();
        int size = offset * offset * offset;
        int max = (offset - 1) / 2;
        int min = -max;
        for (int x = min, y = min, z = min, i = 1; i <= size; i++) {
            offsetList.add(x);
            offsetList.add(y);
            offsetList.add(z);
            z = i % offset == 0 ? min : z + 1;
            if (i % offset == 0) y++;
            if (i % (offset * offset) == 0) { y = min; x++; }
        }
        return offsetList;
    }
}
