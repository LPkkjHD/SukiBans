package me.suki.SukiBans.commands;

import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.UUID.UUIDNameConverter;
import me.suki.SukiBans.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class Unban extends Command{

	public Unban() {
		super("unban");
	}

	@Override
	public void execute(final CommandSender sender, final String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                if(!sender.hasPermission("SukiBans.unban")) {sender.sendMessage(new TextComponent(MessageManager.getNoPerm().replaceAll("%PREFIX%", MessageManager.getPrefix()))); return;}
                if(args.length != 1) {sender.sendMessage(new TextComponent(MessageManager.getUnbanSyntax().replaceAll("%PREFIX%", MessageManager.getPrefix()))); return;}
                String toUnban = UUIDNameConverter.getUUID(args[0]);
                if(toUnban.equals("NO_UUID_FOUND")){
                    sender.sendMessage(new TextComponent(MessageManager.getNoUUID().replaceAll("%PREFIX%", MessageManager.getPrefix())));
                    return;
                }
                BanSystem.unBan(toUnban);
                sender.sendMessage(new TextComponent(MessageManager.getUnbanSucces().replaceAll("%PREFIX%", MessageManager.getPrefix()).replaceAll("%USER%", args[0])));
            }
        };
        Utils.executeAsync(run);
    }
}
