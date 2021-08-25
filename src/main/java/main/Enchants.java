package main;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Enchants {

    //private Enchants() { }
    //static Enchants instance = new Enchants();
    //public static Enchants getInstance() {
    //    return instance;
    //}
    //public static HashMap<Integer, String> slotToEnchant = new HashMap<Integer, String>();

    private static boolean CanUpgrade(String enchant, HumanEntity player, int amount, int maxLevel, int baseCost, int costIncrease) {
        if (player.getInventory().getItem(0) != null) {
            NBTItem NBT = new NBTItem(player.getInventory().getItem(0));
            if (NBT.getInteger(enchant) >= maxLevel)
                return false;
            amount = NBT.getInteger(enchant) + amount > maxLevel ? maxLevel - NBT.getInteger(enchant) : amount;
            int cost = 0;
            for (int i = NBT.getInteger(enchant) + 1; i <= NBT.getInteger(enchant) + amount; i++)
                cost += baseCost + i * costIncrease;
            //Player has enough money
            return true;
        }
        return false;
    }
    private static ItemStack Upgrade(String enchant, HumanEntity player, int amount, int maxLevel, int baseCost, int costIncrease) {
        if (player.getInventory().getItem(0) != null) {
            NBTItem NBT = new NBTItem(player.getInventory().getItem(0));
            amount = NBT.getInteger(enchant) + amount > maxLevel ? maxLevel - ((NBT.getInteger(enchant) + amount) % maxLevel) : amount;
            if (amount != 0) {
                NBT.setInteger(enchant, NBT.getInteger(enchant) + amount);
                //remove tokens
                int cost = 0;
                for (int i = NBT.getInteger(enchant) + 1; i <= NBT.getInteger(enchant) + amount; i++)
                    cost += baseCost + i * costIncrease;
                return NBT.getItem();
            }
        }
        return null;
    }

    public static class Fortune {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Fortune", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            player.getInventory().setItem(0, Pickaxe.UpdatePick(player, Enchants.Upgrade("Fortune", player, amount, maxLevel, baseCost, costIncrease)));
            PickaxeOverviewInventory.OpenInventory(player);
        }
    }
    public static class Efficiency {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Efficiency", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            ItemStack pickaxe = Enchants.Upgrade("Efficiency", player, amount, maxLevel, baseCost, costIncrease);
            pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, new NBTItem(pickaxe).getInteger("Efficiency"));
            player.getInventory().setItem(0, Pickaxe.UpdatePick(player, pickaxe));
            PickaxeOverviewInventory.OpenInventory(player);
        }
    }
    public static class Tokenfinder {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Tokenfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Tokenfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Explosive {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Explosive", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            player.getInventory().setItem(0, Pickaxe.UpdatePick(player, Enchants.Upgrade("Explosive", player, amount, maxLevel, baseCost, costIncrease)));
            PickaxeOverviewInventory.OpenInventory(player);
        }
    }
    public static class Keyfinder {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Keyfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Keyfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Lucky {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Lucky", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Lucky", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Beaconfinder {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Beaconfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Beaconfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Sparechange {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Sparechange", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Sparechange", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Xpfinder {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Xpfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Xpfinder", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Treasurehunter {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Treasurehunter", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Treasurehunter", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Sellmultiplier {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Sellmultiplier", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Sellmultiplier", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Alchemy {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;
        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Alchemy", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Alchemy", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
    public static class Jackhammer {
        public static int slot;
        public static int startingLevel;
        public static int maxLevel;
        public static int baseCost;
        public static int costIncrease;
        public static Material item;
        public static String displayName;
        public static List<String> lore;

        public static boolean CanUpgrade(HumanEntity player, int amount) {
            return Enchants.CanUpgrade("Jackhammer", player, amount, maxLevel, baseCost, costIncrease);
        }
        public static void Upgrade(HumanEntity player, int amount) {
            Enchants.Upgrade("Jackhammer", player, amount, maxLevel, baseCost, costIncrease);
        }
    }
}
