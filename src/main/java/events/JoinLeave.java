package events;

import main.BackPack;
import main.Pickaxe;
import main.Prison;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave implements Listener {

    @EventHandler
    public void joinCheck(PlayerJoinEvent event) {
        //Load player ID if exists
        Prison.PlayerTokenBalance.put(event.getPlayer().getUniqueId(), 0);
        //Player does not have pickaxe
        if (event.getPlayer().getInventory().getItem(0) == null || (event.getPlayer().getInventory().getItem(0) != null && !Pickaxe.isPrisonPickaxe(event.getPlayer().getInventory().getItem(0)))) {
            event.getPlayer().getInventory().setItem(0, Pickaxe.UpdatePick(event.getPlayer(), Pickaxe.RegisterNewPickaxe()));
        }
        if (event.getPlayer().getInventory().getItem(9) == null || (event.getPlayer().getInventory().getItem(9) != null && !BackPack.isPrisonBackPack(event.getPlayer().getInventory().getItem(9)))) {
            event.getPlayer().getInventory().setItem(9, BackPack.UpdateBackPack(BackPack.RegisterNewBackPack()));
        }
        else {
            event.getPlayer().getInventory().setItem(0, Pickaxe.UpdatePick(event.getPlayer(), event.getPlayer().getInventory().getItem(0)));
            event.getPlayer().getInventory().setItem(9, BackPack.UpdateBackPack(event.getPlayer().getInventory().getItem(9)));
        }
    }

    @EventHandler
    public void leaveCheck(PlayerQuitEvent event) {
        //Save player tokens to file
        Prison.PlayerTokenBalance.remove(event.getPlayer().getUniqueId());
    }
}
