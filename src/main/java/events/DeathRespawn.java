package events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DeathRespawn implements Listener {

    private static HashMap<UUID, List<ItemStack>> RespawnItems = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        List<ItemStack> items = new ArrayList<>();
        items.add(event.getEntity().getInventory().getItem(9));
        items.add(event.getEntity().getInventory().getItem(0));
        event.getDrops().remove(event.getEntity().getInventory().getItem(9));
        event.getDrops().remove(event.getEntity().getInventory().getItem(0));
        RespawnItems.put(event.getEntity().getUniqueId(), items);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        List<ItemStack> items = RespawnItems.get(event.getPlayer().getUniqueId());
        for (ItemStack item : items) {
            if (item.getType().equals(Material.NETHERITE_PICKAXE))
                event.getPlayer().getInventory().setItem(0, item);
            else
                event.getPlayer().getInventory().setItem(9, item);
        }
        RespawnItems.remove(event.getPlayer().getUniqueId());
    }
}
