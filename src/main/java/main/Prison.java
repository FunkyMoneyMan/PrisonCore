package main;

import commands.TokenBalance;
import commands.TokenBalanceAdmin;
import events.DeathRespawn;
import events.JoinLeave;
import events.MineBlock;
import events.PlayerInteractions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Prison extends JavaPlugin {

    public static HashMap<UUID, Integer> PlayerTokenBalance = new HashMap<>();
    public final static String[] PickaxeEnchantsNames = {"Efficiency", "Fortune", "Explosive", "Tokenfinder", "Keyfinder", "Lucky", "Beaconfinder", "Sparechange", "Xpfinder", "Treasurehunter", "Sellmultiplier", "Alchemy", "Jackhammer"};
    public final static String[] BackPackEnchantsNames = {"Capacity", "Autosell"};

    @Override
    public void onEnable() {
        //Register Events
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PrisonCore]: Registering Events");
        getServer().getPluginManager().registerEvents(new PlayerInteractions(), this);
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        getServer().getPluginManager().registerEvents(new MineBlock(), this);
        getServer().getPluginManager().registerEvents(new DeathRespawn(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PrisonCore]: Registering Commands");
        this.getCommand("tokenbal").setExecutor(new TokenBalance());
        this.getCommand("tokenbal").setTabCompleter(new TokenBalance());
        this.getCommand("token").setExecutor(new TokenBalanceAdmin());
        this.getCommand("token").setTabCompleter(new TokenBalanceAdmin());
        if (!this.getDataFolder().exists()) {
            this.saveDefaultConfig();
            parseFile();
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "[PrisonCore]: Folder and files not found. Making one now.");
        } else{
            parseFile();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PrisonCore]: Folder and files found. Parsing data.");
        }

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[PrisonCore]: Plugin is enabled!");
        //PlayerInteractions.InitializeInventory();

    }
    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[PrisonCore]: Plugin is disabled!");
    }

    public void parseFile() {
        ConfigurationSection cs = this.getConfig().getConfigurationSection("Enchants");
        //Efficiency
        try {
            Enchants.Efficiency.slot = cs.getInt("Efficiency.slot");
            Enchants.Efficiency.startingLevel = cs.getInt("Efficiency.startinglevel");
            Enchants.Efficiency.maxLevel = cs.getInt("Efficiency.maxlevel");
            Enchants.Efficiency.baseCost = cs.getInt("Efficiency.basecost");
            Enchants.Efficiency.costIncrease = cs.getInt("Efficiency.costincrease");
            Enchants.Efficiency.lore = cs.getStringList("Efficiency.lore");
            Enchants.Efficiency.item = Material.matchMaterial(cs.getString("Efficiency.item"));
            Enchants.Efficiency.displayName = cs.getString("Efficiency.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Efficiency.slot"), "Efficiency");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Efficiency.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Efficiency)");
        }
        //Fortune
        try {
            Enchants.Fortune.slot = cs.getInt("Fortune.slot");
            Enchants.Fortune.startingLevel = cs.getInt("Fortune.startinglevel");
            Enchants.Fortune.maxLevel = cs.getInt("Fortune.maxlevel");
            Enchants.Fortune.baseCost = cs.getInt("Fortune.basecost");
            Enchants.Fortune.costIncrease = cs.getInt("Fortune.costincrease");
            Enchants.Fortune.lore = cs.getStringList("Fortune.lore");
            Enchants.Fortune.item = Material.matchMaterial(cs.getString("Fortune.item"));
            Enchants.Fortune.displayName = cs.getString("Fortune.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Fortune.slot"), "Fortune");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Fortune.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Fortune)");
        }
        //Explosive
        try {
            Enchants.Explosive.slot = cs.getInt("Explosive.slot");
            Enchants.Explosive.startingLevel = cs.getInt("Explosive.startinglevel");
            Enchants.Explosive.maxLevel = cs.getInt("Explosive.maxlevel");
            Enchants.Explosive.baseCost = cs.getInt("Explosive.basecost");
            Enchants.Explosive.costIncrease = cs.getInt("Explosive.costincrease");
            Enchants.Explosive.lore = cs.getStringList("Explosive.lore");
            Enchants.Explosive.item = Material.matchMaterial(cs.getString("Explosive.item"));
            Enchants.Explosive.displayName = cs.getString("Explosive.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Explosive.slot"), "Explosive");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Explosive.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Explosive)");
        }
        //Tokenfinder
        try {
            Enchants.Tokenfinder.slot = cs.getInt("Tokenfinder.slot");
            Enchants.Tokenfinder.startingLevel = cs.getInt("Tokenfinder.startinglevel");
            Enchants.Tokenfinder.maxLevel = cs.getInt("Tokenfinder.maxlevel");
            Enchants.Tokenfinder.baseCost = cs.getInt("Tokenfinder.basecost");
            Enchants.Tokenfinder.costIncrease = cs.getInt("Tokenfinder.costincrease");
            Enchants.Tokenfinder.lore = cs.getStringList("Tokenfinder.lore");
            Enchants.Tokenfinder.item = Material.matchMaterial(cs.getString("Tokenfinder.item"));
            Enchants.Tokenfinder.displayName = cs.getString("Tokenfinder.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Tokenfinder.slot"), "Tokenfinder");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Tokenfinder.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Tokenfinder");
        }
        //Keyfinder
        try {
            Enchants.Keyfinder.slot = cs.getInt("Keyfinder.slot");
            Enchants.Keyfinder.startingLevel = cs.getInt("Keyfinder.startinglevel");
            Enchants.Keyfinder.maxLevel = cs.getInt("Keyfinder.maxlevel");
            Enchants.Keyfinder.baseCost = cs.getInt("Keyfinder.basecost");
            Enchants.Keyfinder.costIncrease = cs.getInt("Keyfinder.costincrease");
            Enchants.Keyfinder.lore = cs.getStringList("Keyfinder.lore");
            Enchants.Keyfinder.item = Material.matchMaterial(cs.getString("Keyfinder.item"));
            Enchants.Keyfinder.displayName = cs.getString("Keyfinder.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Keyfinder.slot"), "Keyfinder");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Keyfinder.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Keyfinder)");
        }
        //Lucky
        try {
            Enchants.Lucky.slot = cs.getInt("Lucky.slot");
            Enchants.Lucky.startingLevel = cs.getInt("Lucky.startinglevel");
            Enchants.Lucky.maxLevel = cs.getInt("Lucky.maxlevel");
            Enchants.Lucky.baseCost = cs.getInt("Lucky.basecost");
            Enchants.Lucky.costIncrease = cs.getInt("Lucky.costincrease");
            Enchants.Lucky.lore = cs.getStringList("Lucky.lore");
            Enchants.Lucky.item = Material.matchMaterial(cs.getString("Lucky.item"));
            Enchants.Lucky.displayName = cs.getString("Lucky.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Lucky.slot"), "Lucky");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Lucky.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Lucky)");
        }
        //Beaconfinder
        try {
            Enchants.Beaconfinder.slot = cs.getInt("Beaconfinder.slot");
            Enchants.Beaconfinder.startingLevel = cs.getInt("Beaconfinder.startinglevel");
            Enchants.Beaconfinder.maxLevel = cs.getInt("Beaconfinder.maxlevel");
            Enchants.Beaconfinder.baseCost = cs.getInt("Beaconfinder.basecost");
            Enchants.Beaconfinder.costIncrease = cs.getInt("Beaconfinder.costincrease");
            Enchants.Beaconfinder.lore = cs.getStringList("Beaconfinder.lore");
            Enchants.Beaconfinder.item = Material.matchMaterial(cs.getString("Beaconfinder.item"));
            Enchants.Beaconfinder.displayName = cs.getString("Beaconfinder.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Beaconfinder.slot"), "Beaconfinder");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Beaconfinder.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Beaconfinder)");
        }
        //Sparechange
        try {
            Enchants.Sparechange.slot = cs.getInt("Sparechange.slot");
            Enchants.Sparechange.startingLevel = cs.getInt("Sparechange.startinglevel");
            Enchants.Sparechange.maxLevel = cs.getInt("Sparechange.maxlevel");
            Enchants.Sparechange.baseCost = cs.getInt("Sparechange.basecost");
            Enchants.Sparechange.costIncrease = cs.getInt("Sparechange.costincrease");
            Enchants.Sparechange.lore = cs.getStringList("Sparechange.lore");
            Enchants.Sparechange.item = Material.matchMaterial(cs.getString("Sparechange.item"));
            Enchants.Sparechange.displayName = cs.getString("Sparechange.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Sparechange.slot"), "Sparechange");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Sparechange.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Sparechange)");
        }
        //Xpfinder
        try {
            Enchants.Xpfinder.slot = cs.getInt("Xpfinder.slot");
            Enchants.Xpfinder.startingLevel = cs.getInt("Xpfinder.startinglevel");
            Enchants.Xpfinder.maxLevel = cs.getInt("Xpfinder.maxlevel");
            Enchants.Xpfinder.baseCost = cs.getInt("Xpfinder.basecost");
            Enchants.Xpfinder.costIncrease = cs.getInt("Xpfinder.costincrease");
            Enchants.Xpfinder.lore = cs.getStringList("Xpfinder.lore");
            Enchants.Xpfinder.item = Material.matchMaterial(cs.getString("Xpfinder.item"));
            Enchants.Xpfinder.displayName = cs.getString("Xpfinder.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Xpfinder.slot"), "Xpfinder");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Xpfinder.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Xpfinder)");
        }
        //Treasurehunter
        try {
            Enchants.Treasurehunter.slot = cs.getInt("Treasurehunter.slot");
            Enchants.Treasurehunter.startingLevel = cs.getInt("Treasurehunter.startinglevel");
            Enchants.Treasurehunter.maxLevel = cs.getInt("Treasurehunter.maxlevel");
            Enchants.Treasurehunter.baseCost = cs.getInt("Treasurehunter.basecost");
            Enchants.Treasurehunter.costIncrease = cs.getInt("Treasurehunter.costincrease");
            Enchants.Treasurehunter.lore = cs.getStringList("Treasurehunter.lore");
            Enchants.Treasurehunter.item = Material.matchMaterial(cs.getString("Treasurehunter.item"));
            Enchants.Treasurehunter.displayName = cs.getString("Treasurehunter.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Treasurehunter.slot"), "Treasurehunter");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Treasurehunter.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Treasurehunter)");
        }
        //Sellmultiplier
        try {
            Enchants.Sellmultiplier.slot = cs.getInt("Sellmultiplier.slot");
            Enchants.Sellmultiplier.startingLevel = cs.getInt("Sellmultiplier.startinglevel");
            Enchants.Sellmultiplier.maxLevel = cs.getInt("Sellmultiplier.maxlevel");
            Enchants.Sellmultiplier.baseCost = cs.getInt("Sellmultiplier.basecost");
            Enchants.Sellmultiplier.costIncrease = cs.getInt("Sellmultiplier.costincrease");
            Enchants.Sellmultiplier.lore = cs.getStringList("Sellmultiplier.lore");
            Enchants.Sellmultiplier.item = Material.matchMaterial(cs.getString("Sellmultiplier.item"));
            Enchants.Sellmultiplier.displayName = cs.getString("Sellmultiplier.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Sellmultiplier.slot"), "Sellmultiplier");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Sellmultiplier.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Sellmultiplier)");
        }
        //Alchemy
        try {
            Enchants.Alchemy.slot = cs.getInt("Alchemy.slot");
            Enchants.Alchemy.startingLevel = cs.getInt("Alchemy.startinglevel");
            Enchants.Alchemy.maxLevel = cs.getInt("Alchemy.maxlevel");
            Enchants.Alchemy.baseCost = cs.getInt("Alchemy.basecost");
            Enchants.Alchemy.costIncrease = cs.getInt("Alchemy.costincrease");
            Enchants.Alchemy.lore = cs.getStringList("Alchemy.lore");
            Enchants.Alchemy.item = Material.matchMaterial(cs.getString("Alchemy.item"));
            Enchants.Alchemy.displayName = cs.getString("Alchemy.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Alchemy.slot"), "Alchemy");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Alchemy.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Alchemy)");
        }
        //Jackhammer
        try {
            Enchants.Jackhammer.slot = cs.getInt("Jackhammer.slot");
            Enchants.Jackhammer.startingLevel = cs.getInt("Jackhammer.startinglevel");
            Enchants.Jackhammer.maxLevel = cs.getInt("Jackhammer.maxlevel");
            Enchants.Jackhammer.baseCost = cs.getInt("Jackhammer.basecost");
            Enchants.Jackhammer.costIncrease = cs.getInt("Jackhammer.costincrease");
            Enchants.Jackhammer.lore = cs.getStringList("Jackhammer.lore");
            Enchants.Jackhammer.item = Material.matchMaterial(cs.getString("Jackhammer.item"));
            Enchants.Jackhammer.displayName = cs.getString("Jackhammer.displayname");
            //Enchants.slotToEnchant.put(cs.getInt("Jackhammer.slot"), "Jackhammer");
            //getServer().getConsoleSender().sendMessage(ChatColor.RED + Enchants.Jackhammer.item.toString());
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "[PrisonCore]: ERROR Config file made incorrectly! (Jackhammer)");
        }
    }
}