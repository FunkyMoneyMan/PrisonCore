package commands;

import main.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TokenBalanceAdmin implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String arg, String[] args) {
        List<String> completions = new ArrayList<String>();
        if (args.length == 2 && (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set"))) {
            for (Player p : Bukkit.getServer().getOnlinePlayers())
                if (p.getName().toLowerCase().contains(args[0].toLowerCase()))
                    completions.add(p.getName());
            return completions;
        }
        if (args.length == 1) {
            completions.addAll(Arrays.asList("give", "remove", "set"));
            return completions;
        }
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("PrisonCore.viewothertokens")) {
                if (args.length >= 3 && (args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("set"))) {
                    int amount = Integer.parseInt(args[2]);
                    UUID player = Bukkit.getServer().getPlayer(args[1]).getUniqueId();
                    if (args[0].equalsIgnoreCase("give")) {
                        sender.sendMessage("Added " + amount + " to " + args[1] + "'s balance");
                        Prison.PlayerTokenBalance.replace(player, Prison.PlayerTokenBalance.get(player) + amount);
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        sender.sendMessage("Removed " + amount + " from " + args[1] + "'s balance");
                       Prison.PlayerTokenBalance.replace(player, Prison.PlayerTokenBalance.get(player) - amount);
                    } else if (args[0].equalsIgnoreCase("set")) {
                        sender.sendMessage("Set " + args[1] + "'s balance to " + amount);
                        Prison.PlayerTokenBalance.replace(player, amount);
                    }
                } else
                    sender.sendMessage("Incorrect usage: /token (give|remove|set) <player> <amount>");
            } else
                sender.sendMessage("You do not have permission!");
        } else {
            if (args[0] != null)
                sender.sendMessage("TokenBalance of " + args[0] + ": ");
            else
                sender.sendMessage("TokenBalance: ");
        }
        return true;
    }
}
