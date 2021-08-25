package main;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PickaxeOverviewInventory implements InventoryHolder {

    private Inventory inv;

    public PickaxeOverviewInventory(HumanEntity player) {
        inv = Bukkit.createInventory(this, 54, "Upgrade Pickaxe");
        init(player);
    }

    public static void OpenInventory(HumanEntity player) {
        PickaxeOverviewInventory inv = new PickaxeOverviewInventory(player);
        player.openInventory(inv.getInventory());
    }

    private void init(HumanEntity player) {
        NBTItem NBT = new NBTItem(player.getInventory().getItem(0));
        ItemStack background = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < 54; i++)
            inv.setItem(i, background);
        inv.setItem(Enchants.Efficiency.slot, constructItem(Enchants.Efficiency.item, "Efficiency", Enchants.Efficiency.displayName, Enchants.Efficiency.lore, Enchants.Efficiency.baseCost, Enchants.Efficiency.costIncrease, NBT.getInteger("Efficiency")));
        inv.setItem(Enchants.Fortune.slot, constructItem(Enchants.Fortune.item, "Fortune", Enchants.Fortune.displayName, Enchants.Fortune.lore, Enchants.Fortune.baseCost, Enchants.Fortune.costIncrease, NBT.getInteger("Fortune")));
        inv.setItem(Enchants.Explosive.slot, constructItem(Enchants.Explosive.item, "Explosive", Enchants.Explosive.displayName, Enchants.Explosive.lore, Enchants.Explosive.baseCost, Enchants.Explosive.costIncrease, NBT.getInteger("Explosive")));
        inv.setItem(Enchants.Jackhammer.slot, constructItem(Enchants.Jackhammer.item, "Jackhammer", Enchants.Jackhammer.displayName, Enchants.Jackhammer.lore, Enchants.Jackhammer.baseCost, Enchants.Jackhammer.costIncrease, NBT.getInteger("Jackhammer")));
        inv.setItem(Enchants.Tokenfinder.slot, constructItem(Enchants.Tokenfinder.item, "Tokenfinder", Enchants.Tokenfinder.displayName, Enchants.Tokenfinder.lore, Enchants.Tokenfinder.baseCost, Enchants.Tokenfinder.costIncrease, NBT.getInteger("Tokenfinder")));
        inv.setItem(Enchants.Keyfinder.slot, constructItem(Enchants.Keyfinder.item, "Keyfinder", Enchants.Keyfinder.displayName, Enchants.Keyfinder.lore, Enchants.Keyfinder.baseCost, Enchants.Keyfinder.costIncrease, NBT.getInteger("Keyfinder")));
        inv.setItem(Enchants.Lucky.slot, constructItem(Enchants.Lucky.item, "Lucky", Enchants.Lucky.displayName, Enchants.Lucky.lore, Enchants.Lucky.baseCost, Enchants.Lucky.costIncrease, NBT.getInteger("Lucky")));
        inv.setItem(Enchants.Beaconfinder.slot, constructItem(Enchants.Beaconfinder.item, "Beaconfinder", Enchants.Beaconfinder.displayName, Enchants.Beaconfinder.lore, Enchants.Beaconfinder.baseCost, Enchants.Beaconfinder.costIncrease, NBT.getInteger("Beaconfinder")));
        inv.setItem(Enchants.Sparechange.slot, constructItem(Enchants.Sparechange.item, "Sparechange", Enchants.Sparechange.displayName, Enchants.Sparechange.lore, Enchants.Sparechange.baseCost, Enchants.Sparechange.costIncrease, NBT.getInteger("Sparechange")));
        inv.setItem(Enchants.Xpfinder.slot, constructItem(Enchants.Xpfinder.item, "Xpfinder", Enchants.Xpfinder.displayName, Enchants.Xpfinder.lore, Enchants.Xpfinder.baseCost, Enchants.Xpfinder.costIncrease, NBT.getInteger("Xpfinder")));
        inv.setItem(Enchants.Treasurehunter.slot, constructItem(Enchants.Treasurehunter.item, "Treasurehunter", Enchants.Treasurehunter.displayName, Enchants.Treasurehunter.lore, Enchants.Treasurehunter.baseCost, Enchants.Treasurehunter.costIncrease, NBT.getInteger("Treasurehunter")));
        inv.setItem(Enchants.Sellmultiplier.slot, constructItem(Enchants.Sellmultiplier.item, "Sellmultiplier", Enchants.Sellmultiplier.displayName, Enchants.Sellmultiplier.lore, Enchants.Sellmultiplier.baseCost, Enchants.Sellmultiplier.costIncrease, NBT.getInteger("Sellmultiplier")));
        inv.setItem(Enchants.Alchemy.slot, constructItem(Enchants.Alchemy.item, "Alchemy", Enchants.Alchemy.displayName, Enchants.Alchemy.lore, Enchants.Alchemy.baseCost, Enchants.Alchemy.costIncrease, NBT.getInteger("Alchemy")));
        inv.setItem(13, player.getInventory().getItem(0));
    }

    public ItemStack constructItem(Material material, String enchant, String displayName, List<String> lore, int cost, int increase, int level) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName.replaceAll("&", "§"));
        List<String> newLore = new ArrayList<String>();
        for (int i = 0; i < lore.size(); i++) {
            String loreLine = lore.get(i);
            if (loreLine.contains("&"))
                loreLine = lore.get(i).replaceAll("&", "§");
            if (loreLine.contains("{cost}"))
                loreLine = loreLine.replace("{cost}",  "" + (cost + increase * level));
            if (loreLine.contains("{level}"))
                loreLine = loreLine.replace("{level}", "" + level);
            newLore.add(loreLine);
        }
        meta.setLore(newLore);
        item.setItemMeta(meta);
        NBTItem newItem = new NBTItem(item);
        newItem.setString("Enchant", enchant);
        return newItem.getItem();
    }


    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
