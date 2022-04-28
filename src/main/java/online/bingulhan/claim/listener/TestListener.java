package online.bingulhan.claim.listener;

import online.bingulhan.claim.events.ClaimEnterEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TestListener implements Listener {


    @EventHandler
    public void claimEnter(ClaimEnterEvent e) {
        e.getPlayer().sendMessage("Claim giriş yaptın!");
    }
}
