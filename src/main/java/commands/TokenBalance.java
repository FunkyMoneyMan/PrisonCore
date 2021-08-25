package commands;

import main.Prison;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TokenBalance implements TabCompleter, CommandExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String arg, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1 && sender.hasPermission("PrisonCore.viewothertokens")) {
            for (Player p : Bukkit.getServer().getOnlinePlayers())
                if (p.getName().toLowerCase().contains(args[0].toLowerCase()))
                    completions.add(p.getName());
            return completions;
        }
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1 && sender.hasPermission("PrisonCore.viewothertokens"))
                try {
                    sender.sendMessage("TokenBalance of " + args[0] + ": " + Prison.PlayerTokenBalance.get(Bukkit.getServer().getPlayer(args[0]).getUniqueId()));
                } catch (NullPointerException e) {
                    sender.sendMessage("Can only check balance of online players");
                }
            else
                sender.sendMessage("TokenBalance: " + Prison.PlayerTokenBalance.get(((Player) sender).getUniqueId()));
        }// else {
        //    if (args[0] != null)
        //        sender.sendMessage("TokenBalance of " + args[0] + ": ");
        //    else
        //       sender.sendMessage("TokenBalance: ");
        //}
        return true;
    }
}
