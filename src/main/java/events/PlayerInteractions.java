package events;

import de.tr7zw.nbtapi.NBTItem;
import main.BackPack;
import main.PickaxeOverviewInventory;
import main.Enchants;
import main.Pickaxe;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerInteractions implements Listener {

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        if (event.getItem() != null && Pickaxe.isPrisonPickaxe(event.getItem()) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            PickaxeOverviewInventory.OpenInventory(event.getPlayer());
        }
    }
    @EventHandler
    public void swapHandEvent(PlayerSwapHandItemsEvent event) {
        if (event.getOffHandItem() != null && (Pickaxe.isPrisonPickaxe(event.getOffHandItem()) || BackPack.isPrisonBackPack(event.getOffHandItem())))
                event.setCancelled(true);
    }
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof PickaxeOverviewInventory)
            event.setCancelled(true);
        if (event.getHotbarButton()  == 0)
            event.setCancelled(true);
        if(event.getCurrentItem() != null && (Pickaxe.isPrisonPickaxe(event.getCurrentItem()) || BackPack.isPrisonBackPack(event.getCurrentItem()))) {
            if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
                event.setCancelled(true);
            if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE) && event.getAction() != InventoryAction.HOTBAR_SWAP) {
                event.getWhoClicked().getInventory().setItem(0, Pickaxe.UpdatePick(event.getWhoClicked(), event.getWhoClicked().getInventory().getItem(0)));
                PickaxeOverviewInventory.OpenInventory(event.getWhoClicked());
            }
        } else if(event.getRawSlot() == event.getSlot() && event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof PickaxeOverviewInventory) {
            if (event.getCurrentItem() != null) {
                NBTItem NBT = new NBTItem(event.getCurrentItem());
                if (NBT.getString("Enchant") != null) {
                    if (event.getClick().equals(ClickType.LEFT)) {
                        switch (NBT.getString("Enchant")) {
                            case "Efficiency":
                                if (Enchants.Efficiency.CanUpgrade(event.getWhoClicked(), 1)) {
                                    Enchants.Efficiency.Upgrade(event.getWhoClicked(), 1);
                                }
                                break;
                            case "Fortune":
                                if (Enchants.Fortune.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Fortune.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Explosive":
                                if (Enchants.Explosive.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Explosive.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Jackhammer":
                                if (Enchants.Jackhammer.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Jackhammer.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Tokenfinder":
                                if (Enchants.Tokenfinder.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Tokenfinder.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Keyfinder":
                                if (Enchants.Keyfinder.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Keyfinder.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Lucky":
                                if (Enchants.Lucky.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Lucky.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Beaconfinder":
                                if (Enchants.Beaconfinder.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Beaconfinder.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Sparechange":
                                if (Enchants.Sparechange.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Sparechange.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Xpfinder":
                                if (Enchants.Xpfinder.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Xpfinder.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Treasurehunter":
                                if (Enchants.Treasurehunter.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Treasurehunter.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Sellmultiplier":
                                if (Enchants.Sellmultiplier.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Sellmultiplier.Upgrade(event.getWhoClicked(), 1);
                                break;
                            case "Alchemy":
                                if (Enchants.Alchemy.CanUpgrade(event.getWhoClicked(), 1))
                                    Enchants.Alchemy.Upgrade(event.getWhoClicked(), 1);
                                break;
                        }
                    }
                }
            }
        }

    }
    @EventHandler
    public void itemDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType().equals(Material.NETHERITE_PICKAXE))
            event.setCancelled(true);
    }
}
