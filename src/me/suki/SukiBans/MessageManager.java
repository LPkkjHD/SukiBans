package me.suki.SukiBans;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

public class MessageManager {
	private static String 
	Prefix;
    private static String NoPerm;
    private static String PermBanCreated;
    private static String TempBanCreated;
    private static String PermBanKickMsg;
    private static String TempBanKickMsg;
    private static String PermamentBanSyntax;
    private static String NoUUID;
    private static String UnbanSyntax;
    private static String UnbanSucces;
    private static String InvalidTimeFormat;
    private static String TempBanSyntax;
    private static String AlreadyBanned;
    private static String MutePermMsg;
    private static String MuteTempMsg;
    private static String PermanentMuteSyntax;
    private static String PermanentMuteCreated;
    private static String TemporaryMuteCreated;
    private static String UnMuteSucces;
    private static String AlreadyMuted;
    private static String UnMuteSyntax;

    private static String TimeYears;
    private static String TimeMonths;
    private static String TimeDays;
    private static String TimeHours;
    private static String TimeMinutes;
    private static String TimeSeconds;

    private static String TimeYear;
    private static String TimeMonth;
    private static String TimeDay;
    private static String TimeHour;
    private static String TimeMinute;
    private static String TimeSecond;

    public static void initMsg(Configuration cfg){
		if(cfg == null) return;
		Prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
		NoPerm = ChatColor.translateAlternateColorCodes('&', cfg.getString("NoPermission"));
		PermBanCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermamentBanSucces"));
		TempBanCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("TemporaryBanSucces"));
		PermBanKickMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickMessagePermanent"));
		TempBanKickMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickMessageTemporary"));
		PermamentBanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermanentBanSyntax"));
		TempBanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("TemporaryBanSyntax"));
		NoUUID = ChatColor.translateAlternateColorCodes('&', cfg.getString("NoUUIDFound"));
		UnbanSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnBanSyntax"));
		UnbanSucces = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnbanSucces"));
		InvalidTimeFormat = ChatColor.translateAlternateColorCodes('&', cfg.getString("InvalidTime"));
		AlreadyBanned = ChatColor.translateAlternateColorCodes('&', cfg.getString("AlreadyBanned"));
        MutePermMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessagePermanent"));
        MuteTempMsg = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessageTemporary"));
        PermanentMuteSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermanentMuteSyntax"));
        PermanentMuteCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("PermamentMuteSucces"));
        TemporaryMuteCreated = ChatColor.translateAlternateColorCodes('&', cfg.getString("TemporaryMuteSucces"));
        UnMuteSucces = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnmuteSucces"));
        UnMuteSyntax = ChatColor.translateAlternateColorCodes('&', cfg.getString("UnMuteSyntax"));
        AlreadyMuted = ChatColor.translateAlternateColorCodes('&', cfg.getString("AlreadyMuted"));
        TimeYears = ChatColor.translateAlternateColorCodes('&', cfg.getString("Years"));
        TimeMonths = ChatColor.translateAlternateColorCodes('&', cfg.getString("Moths"));
        TimeDays = ChatColor.translateAlternateColorCodes('&', cfg.getString("Days"));
        TimeHours = ChatColor.translateAlternateColorCodes('&', cfg.getString("Hours"));
        TimeMinutes = ChatColor.translateAlternateColorCodes('&', cfg.getString("Minutes"));
        TimeSeconds = ChatColor.translateAlternateColorCodes('&', cfg.getString("Seconds"));
        TimeYear = ChatColor.translateAlternateColorCodes('&', cfg.getString("Year"));
        TimeMonth = ChatColor.translateAlternateColorCodes('&', cfg.getString("Moth"));
        TimeDay = ChatColor.translateAlternateColorCodes('&', cfg.getString("Day"));
        TimeHour = ChatColor.translateAlternateColorCodes('&', cfg.getString("Hour"));
        TimeMinute = ChatColor.translateAlternateColorCodes('&', cfg.getString("Minute"));
        TimeSecond = ChatColor.translateAlternateColorCodes('&', cfg.getString("Second"));
	}

    public static String getUnMuteSyntax() {
        return UnMuteSyntax;
    }

    public static String getAlreadyMuted() {
        return AlreadyMuted;
    }

    public static String getPermanentMuteCreated() {
        return PermanentMuteCreated;
    }

    public static String getTemporaryMuteCreated() {
        return TemporaryMuteCreated;
    }

    public static String getUnMuteSucces() {
        return UnMuteSucces;
    }

    public static String getPermanentMuteSyntax() {
        return PermanentMuteSyntax;
    }

	public static String getPrefix() {
		return Prefix;
	}

    public static String getMutePermMsg() {
        return MutePermMsg;
    }

    public static String getMuteTempMsg() {
        return MuteTempMsg;
    }

    public static String getNoPerm() {
		return NoPerm;
	}

	public static String getPermBanCreated() {
		return PermBanCreated;
	}

	public static String getTempBanCreated() {
		return TempBanCreated;
	}

	public static String getPermBanKickMsg() {
		return PermBanKickMsg;
	}

	public static String getTempBanKickMsg() {
		return TempBanKickMsg;
	}

	public static String getPermamentBanSyntax() {
		return PermamentBanSyntax;
	}

	public static String getNoUUID() {
		return NoUUID;
	}

	public static String getUnbanSyntax() {
		return UnbanSyntax;
	}

	public static String getUnbanSucces() {
		return UnbanSucces;
	}

	public static String getInvalidTimeFormat() {
		return InvalidTimeFormat;
	}

	public static String getTempBanSyntax() {
		return TempBanSyntax;
	}

	public static String getAlreadyBanned() {
		return AlreadyBanned;
	}
}
