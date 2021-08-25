package main;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackPack {

    public static ItemStack UpdateBackPack(ItemStack pickaxe) {
        NBTItem NBT = new NBTItem(pickaxe);
        pickaxe = NBT.getItem();
        ItemMeta meta = pickaxe.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§e§lBackPack §7(Click)");
        int xpProgress = (int) Math.floor(20 * ((double) NBT.getInteger("BPCapacity") / NBT.getInteger("MaxCapacity")));
        String leftXP = new String(new char[xpProgress]).replace("\0", "|");
        if (xpProgress > 20) xpProgress = 20;
        String rightXP = new String(new char[20 - xpProgress]).replace("\0", "|");
        List<String> lore = new ArrayList<>(Arrays.asList(" ", "§a§lInformation", " §a§l| §fStorage §a" + " §8[§a" + leftXP + "§7" + rightXP + "§8]"));
        lore.addAll(Arrays.asList(" §a§l| §fBlocks Mined: §6" + NBT.getInteger("BlocksMined"),
                " §a§l| §fPrestige: §e" + NBT.getInteger("Prestige"), " ",
                "§c§lEnchantments"));
        for (String enchant : Prison.BackPackEnchantsNames)
            if (NBT.getInteger(enchant) > 0)
                lore.add(String.format(" §c§l| §f%s: ", enchant) + NBT.getInteger(enchant));
        lore.add(" ");
        meta.setLore(lore);
        pickaxe.setItemMeta(meta);
        return pickaxe;
    }

    public static ItemStack RegisterNewBackPack() {
        ItemStack backpack = new ItemStack(Material.CHEST);
        NBTItem NBT = new NBTItem(backpack);
        NBT.setInteger("MaxCapacity", 1000);
        NBT.setInteger("BPCapacity", 0);
        NBT.setBoolean("PrisonCore", true);
        NBT.setInteger("Capacity", 1000);
        backpack = NBT.getItem();
        ItemMeta meta = backpack.getItemMeta();
        assert meta != null;
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        backpack.setItemMeta(meta);
        return backpack;
    }

    public static boolean isPrisonBackPack(ItemStack pickaxe) {
        if (pickaxe.getType() != Material.AIR) {
            NBTItem NBT = new NBTItem(pickaxe);
            return NBT.getBoolean("PrisonCore") && pickaxe.getType().equals(Material.CHEST);
        }
        return false;
    }
}
