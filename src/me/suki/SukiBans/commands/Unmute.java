package me.suki.SukiBans.commands;

import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.Mutes.MuteSystem;
import me.suki.SukiBans.UUID.UUIDNameConverter;
import me.suki.SukiBans.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by DatSukii on 15.05.2015 at 20:28
 */
public class Unmute extends Command{
    public Unmute() {
        super("unmute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Runnable run = new Runnable(){
            @Override
            public void run() {
                if(!sender.hasPermission("SukiBans.unmute")){
                    sender.sendMessage(new TextComponent(MessageManager.getNoPerm().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                    return;
                }
                if(args.length != 1){
                    sender.sendMessage(new TextComponent(MessageManager.getUnMuteSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                    return;
                }
                String toUnMute = UUIDNameConverter.getUUID(args[0]);
                if(toUnMute.equals("NO_UUID_FOUND")){
                    sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                    return;
                }
                MuteSystem.unMute(toUnMute);
                sender.sendMessage(new TextComponent(MessageManager.getUnMuteSucces().replaceAll("%PREFIX%", MessageManager.getPrefix()).replaceAll("%USER%", args[0])));
            }
        };
        Utils.executeAsync(run);
    }
}
