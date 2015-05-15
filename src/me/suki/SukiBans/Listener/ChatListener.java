package me.suki.SukiBans.Listener;

import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.Mutes.Mute;
import me.suki.SukiBans.Mutes.MuteSystem;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DatSukii on 15.05.2015 at 17:24
 */
public class ChatListener  implements Listener{
    @EventHandler
    public void On(ChatEvent e){
        if(!(e.getSender() instanceof ProxiedPlayer)){
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        String UUID = p.getUniqueId().toString();
        if(MuteSystem.isMutened(UUID)){
            e.setCancelled(true);
            Mute mute = MuteSystem.getMute(UUID);
            if(MuteSystem.getMuteType(UUID).equals(MuteSystem.MuteType.PERMANENT)){
                p.sendMessage(new TextComponent(MessageManager.getMutePermMsg().replaceAll("%REASON%", mute.getReason()).replaceAll("%MUTER%", mute.getMutedby())));
            } else if (MuteSystem.getMuteType(UUID).equals(MuteSystem.MuteType.TEMPORARY)){
                SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy HH:mm");
                Date unmute = new Date(mute.getMuteTime() + mute.getMutedTime());
                p.sendMessage(new TextComponent(MessageManager.getMuteTempMsg().replaceAll("%REASON%", mute.getReason()).replaceAll("%MUTER%", mute.getMutedby()).replaceAll("%UNMUTETIME%", formatter.format(unmute))));
            }
        }
    }
}
