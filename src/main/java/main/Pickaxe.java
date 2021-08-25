package main;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pickaxe {

    public static ItemStack UpdatePick(HumanEntity player, ItemStack pickaxe) {
        NBTItem NBT = new NBTItem(pickaxe);
        List<String> lore = new ArrayList<>(Arrays.asList(" ", "§a§lStatistics", " §a§l| §fOwner: §9" + player.getName()));
        if (NBT.getInteger("XP") >= NBT.getInteger("Required")) {
            NBT.setInteger("XP", NBT.getInteger("XP") - NBT.getInteger("Required"));
            NBT.setInteger("Required", (int) (NBT.getInteger("Required") * 1.5));
            NBT.setInteger("Level", NBT.getInteger("Level") + 1);
        }
        pickaxe = NBT.getItem();
        ItemMeta meta = pickaxe.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§a§lMiner Pick");
        int xpProgress = (int) Math.floor(20 * ((double) NBT.getInteger("XP") / NBT.getInteger("Required")));
        String leftXP = new String(new char[xpProgress]).replace("\0", "|");
        if (xpProgress > 20) xpProgress = 20;
        String rightXP = new String(new char[20 - xpProgress]).replace("\0", "|");
        String level = " §a§l| §fLevel: §a" + NBT.getInteger("Level") + " §8[§a" + leftXP + "§7" + rightXP + "§8]";
        lore.add(level);
        lore.addAll(Arrays.asList(" §a§l| §fBlocks Mined: §6" + NBT.getInteger("BlocksMined"),
                " §a§l| §fPrestige: §e" + NBT.getInteger("Prestige"), " ",
                "§c§lEnchantments"));
        for (String enchant : Prison.PickaxeEnchantsNames)
            if (NBT.getInteger(enchant) > 0)
                lore.add(String.format(" §c§l| §f%s: ", enchant) + NBT.getInteger(enchant));
        lore.addAll(Arrays.asList(" ", "§7(Right Click to Open)"));
        meta.setLore(lore);
        pickaxe.setItemMeta(meta);
        return pickaxe;
    }
    public static ItemStack RegisterNewPickaxe() {
        ItemStack pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        if (Enchants.Efficiency.startingLevel != 0)
            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, Enchants.Efficiency.startingLevel);
        NBTItem NBT = new NBTItem(pickaxe);
        NBT.setInteger("Efficiency", Enchants.Efficiency.startingLevel);
        NBT.setInteger("Fortune", Enchants.Fortune.startingLevel);
        NBT.setInteger("Explosive", Enchants.Explosive.startingLevel);
        NBT.setInteger("Jackhammer", Enchants.Jackhammer.startingLevel);
        NBT.setInteger("Tokenfinder", Enchants.Tokenfinder.startingLevel);
        NBT.setInteger("Keyfinder", Enchants.Keyfinder.startingLevel);
        NBT.setInteger("Lucky", Enchants.Lucky.startingLevel);
        NBT.setInteger("Beaconfinder", Enchants.Beaconfinder.startingLevel);
        NBT.setInteger("Sparechange", Enchants.Sparechange.startingLevel);
        NBT.setInteger("Xpfinder", Enchants.Xpfinder.startingLevel);
        NBT.setInteger("Treasurehunter", Enchants.Treasurehunter.startingLevel);
        NBT.setInteger("Sellmultiplier", Enchants.Sellmultiplier.startingLevel);
        NBT.setInteger("Alchemy", Enchants.Alchemy.startingLevel);
        NBT.setInteger("Required", 1000);
        NBT.setInteger("XP", 0);
        NBT.setInteger("Level", 0);
        NBT.setInteger("Prestige", 0);
        NBT.setBoolean("PrisonCore", true);
        pickaxe = NBT.getItem();
        ItemMeta meta = pickaxe.getItemMeta();
        assert meta != null;
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        pickaxe.setItemMeta(meta);
        return pickaxe;
    }

    public static boolean isPrisonPickaxe(ItemStack pickaxe) {
        if (pickaxe.getType() != Material.AIR) {
            NBTItem NBT = new NBTItem(pickaxe);
            return NBT.getBoolean("PrisonCore") && pickaxe.getType().equals(Material.NETHERITE_PICKAXE);
        }
        return false;
    }
}
