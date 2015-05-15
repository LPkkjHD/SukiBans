package me.suki.SukiBans.Bans;

import me.suki.SukiBans.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class BanSystem {
	
	public static void createTable(){
        MySQL.update("CREATE TABLE IF NOT EXISTS bans(UUID varchar(64),Reason varchar(64),BanTime bigint,BannedTime bigint,BannedBy varchar(64), Unbanned boolean)");
    }

    /**
     * @param UUID Of Player to be checked
     * @return BanType of player or BanType.NOT_FOUND if Nothing is found
     */
    public static BanType getBanType(String UUID) {
        BanType typ = BanType.NOT_FOUND;
        Ban b = getBan(UUID);
        if (b.getBannedTime() != -1) {
            typ = BanType.TEMPORARY;
        } else {
            typ = BanType.PERMANENT;
        }
        return typ;
    }

    /**
     * @param UUID The UUID of the Player to be checked
     * @return True if Player is banned, false if not
     */
    public static Boolean isBanned(String UUID) {
        boolean toReturn = false;
        ResultSet rs = MySQL.query("SELECT * FROM bans WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
            while (rs.next()) {
                if (rs.getLong("BanTime") + rs.getLong("BannedTime") > System.currentTimeMillis() || rs.getLong("BannedTime") == -1) {
                    if (!rs.getBoolean("Unbanned")) {
                        toReturn = true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * @param UUID UUID of the Player to be banned
     * @param Reason Reason of the Ban
     * @param time Time of the ban in ms (-1 for permanent)
     * @param BannedBy UUID of the Player who Banned, or "CONSOLE"
     */
    public static void Ban(String UUID, String Reason, long time, String BannedBy) {
        MySQL.update("INSERT INTO `bans`(`UUID`, `Reason`, `BanTime`, `BannedTime`, `BannedBy`, `Unbanned`) VALUES ('" + UUID.replaceAll("-", "") + "','" + Reason + "','" + System.currentTimeMillis() + "','" + time + "','" + BannedBy.replaceAll("-", "") + "','0')");
    }

    public static Ban getBan(String UUID) {
        Ban toReturn = null;
        String UUID1, Reason, BannedBy;
        long BanTime, BannedTime;

        ResultSet rs = MySQL.query("SELECT * FROM bans WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
			while(rs.next()) {
                if (rs.getLong("BanTime") + rs.getLong("BannedTime") > System.currentTimeMillis() || rs.getLong("BannedTime") == -1) {
                    if (!rs.getBoolean("Unbanned")) {
                        UUID1 = rs.getString("UUID");
                        Reason = rs.getString("Reason");
                        BannedBy = rs.getString("BannedBy");
                        BanTime = rs.getLong("BanTime");
                        BannedTime = rs.getLong("BannedTime");
                        toReturn = new Ban(UUID1, Reason, BannedBy, BanTime, BannedTime, false);
                        break;
                    }
                }
			}
		} catch (SQLException e) {
			e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * @param UUID UUID of the Player to be unbanned
     */
    public static void unBan(String UUID) {
        MySQL.update("UPDATE bans SET Unbanned=1 WHERE UUID='" + UUID.replaceAll("-", "")+"'");
    }

    /**
     * @param UUID UUID of the Player to be checked
     * @return ArrayList of all bans a Player had, if he wasn't banned yet the ArrayList is Empty.
     */
    public ArrayList<Ban> getBans(String UUID) {
        ArrayList<Ban> bans = new ArrayList<Ban>();
        ResultSet rs = MySQL.query("SELECT * FROM bans WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
            while (rs.next()) {
                bans.add(new Ban(rs.getString("UUID"), rs.getString("Reason"), rs.getString("Bannedby"), rs.getLong("BanTime"), rs.getLong("BannedTime"), rs.getBoolean("Unbanned")));
            }
		} catch (SQLException e) {
			e.printStackTrace();
        }
        return bans;
    }

    public enum BanType {
        TEMPORARY,
        PERMANENT,
        NOT_FOUND
	}
	
}
