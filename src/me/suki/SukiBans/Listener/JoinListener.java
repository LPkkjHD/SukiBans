package me.suki.SukiBans.Listener;

import me.suki.SukiBans.Bans.Ban;
import me.suki.SukiBans.Bans.BanSystem;
import me.suki.SukiBans.Bans.BanSystem.BanType;
import me.suki.SukiBans.MessageManager;
import me.suki.SukiBans.Utils;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener{
	@EventHandler
	public void onJoin(LoginEvent e){
		String UUID = e.getConnection().getUniqueId().toString();
		if(BanSystem.isBanned(UUID)){
			Ban ban = BanSystem.getBan(UUID);
			if(ban == null){
				return;
			}
			e.setCancelled(true);
			if(BanSystem.getBanType(UUID) == BanType.PERMANENT){
				e.setCancelReason(MessageManager.getPermBanKickMsg().replaceAll("%REASON%", ban.getReason()).replaceAll("%BANNER%", ban.getBannedby()));
			} else if(BanSystem.getBanType(UUID) == BanType.TEMPORARY){
				e.setCancelReason(MessageManager.getTempBanKickMsg().replaceAll("%REASON%", ban.getReason()).replaceAll("%BANNER%", ban.getBannedby()).replaceAll("%BANNEDTIME%", Utils.translateLongToString(ban.getBanTime() + ban.getBannedTime())));
			}
		}
	}
}
