package me.suki.SukiBans.commands;

import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.Mutes.MuteSystem;
import me.suki.SukiBans.UUID.UUIDNameConverter;
import me.suki.SukiBans.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by DatSukii on 15.05.2015 at 17:57
 */
public class Mute extends Command{

    public Mute(){
        super("Mute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if (!sender.hasPermission("SukiBans.mute")) {sender.sendMessage(new TextComponent(MessageManager.getNoPerm().replaceAll("%PREFIX%", MessageManager.getPrefix())));return;}
                if (args.length < 2) {
                    sender.sendMessage(new TextComponent(MessageManager.getPermanentMuteSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                } else {
                    String toMute = UUIDNameConverter.getUUID(args[0]);
                    if (toMute.equals("NO_UUID_FOUND")) {
                        sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    if (MuteSystem.isMutened(toMute)) {
                        sender.sendMessage(new TextComponent(MessageManager.getAlreadyMuted().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                        return;
                    }
                    String Reason = "";
                    for (int i = 1; i < args.length; i++) {
                        Reason = Reason + args[i] + " ";
                    }
                    sender.sendMessage(new TextComponent(MessageManager.getPermanentMuteCreated().replaceAll("%PREFIX%", MessageManager.getPrefix()).replaceAll("%USER%", args[0]).replaceAll("%REASON%", Reason)));
                    ProxiedPlayer toMuteP = ProxyServer.getInstance().getPlayer(args[0]);
                    if(sender instanceof ProxiedPlayer){
                        ProxiedPlayer p = (ProxiedPlayer) sender;
                        MuteSystem.Mute(toMute, Reason, -1, p.getName());
                        if (toMuteP != null) {
                            toMuteP.sendMessage(new TextComponent(MessageManager.getMutePermMsg().replaceAll("%REASON%", Reason).replaceAll("%MUTER%", p.getName())));
                        }
                    } else {
                        MuteSystem.Mute(toMute, Reason, -1, "CONSOLE");
                        if (toMuteP != null) {
                            toMuteP.sendMessage(new TextComponent(MessageManager.getMutePermMsg().replaceAll("%REASON%", Reason).replaceAll("%MUTER%", "CONSOLE")));
                        }
                    }
                }
            }
        };
        Utils.executeAsync(run);
    }
}
