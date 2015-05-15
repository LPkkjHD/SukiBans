package me.suki.SukiBans.Mutes;



import me.suki.SukiBans.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by DatSukii on 15.05.2015 at 13:10
 */
public class MuteSystem {

    public static void createTable(){
        MySQL.update("CREATE TABLE IF NOT EXISTS Mutes(UUID varchar(64),Reason varchar(64),MuteTime bigint,MutedTime bigint,MutenedBy varchar(64), Unmuted boolean)");
    }

    public enum MuteType{
        TEMPORARY,
        PERMANENT,
        NOT_FOUND;
    }
    /**
     * @param UUID Of Player to be checked
     * @return MuteType of player or MuteType.NOT_FOUND if Nothing is found
     */
    public static MuteType getMuteType(String UUID){
        MuteType typ = MuteType.NOT_FOUND;
        Mute b = getMute(UUID);
        if(b.getMutedTime() != -1){
            typ = MuteType.TEMPORARY;
        } else {
            typ = MuteType.PERMANENT;
        }
        return typ;
    }
    /**
     * @param UUID The UUID of the Player to be checked
     * @return True if Player is Muted, false if not
     */
    public static Boolean isMutened(String UUID){
        boolean toReturn = false;
        ResultSet rs = MySQL.query("SELECT * FROM Mutes WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
            while (rs.next()){
                if(rs.getLong("MuteTime") + rs.getLong("MutedTime") > System.currentTimeMillis() || rs.getLong("MutedTime") == -1){
                    if(!rs.getBoolean("Unmuted")){
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
     * @param UUID UUID of the Player to be checked
     * @return ArrayList of all Mutes a Player had, if he wasn't Muted yet the ArrayList is Empty.
     */
    public ArrayList<Mute> getMutes(String UUID){
        ArrayList<Mute> Mutes = new ArrayList<Mute>();
        ResultSet rs = MySQL.query("SELECT * FROM Mutes WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
            while(rs.next()){
                Mutes.add(new Mute(rs.getString("UUID"), rs.getString("Reason"), rs.getString("Mutedby"), rs.getLong("MuteTime"), rs.getLong("MutedTime"), rs.getBoolean("Unmuted")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Mutes;
    }

    /**
     * @param UUID UUID of the Player to be Mutened
     * @param Reason Reason of the Mute
     * @param time Time of the Mute in ms (-1 for permanent)
     * @param MutenedBy Name of the Player who Muted, or "CONSOLE"
     */
    public static void Mute(String UUID, String Reason, long time, String MutenedBy){
        MySQL.update("INSERT INTO `Mutes`(`UUID`, `Reason`, `MuteTime`, `MutedTime`, `Mutedby`, `Unmuted`) VALUES ('"+UUID.replaceAll("-", "")+"','"+Reason+"','"+System.currentTimeMillis()+"','"+time+"','"+MutenedBy.replaceAll("-", "")+"','0')");
    }

    public static Mute getMute(String UUID){
        Mute toReturn = null;
        String UUID1, Reason, MutedBy;
        long MuteTime, MutedTime;

        ResultSet rs = MySQL.query("SELECT * FROM Mutes WHERE UUID='" + UUID.replaceAll("-", "") + "'");
        try {
            while(rs.next()){
                if(rs.getLong("MuteTime") + rs.getLong("MutedTime") > System.currentTimeMillis() || rs.getLong("Unmuted") == -1){
                    if(!rs.getBoolean("Unmuted")){
                        UUID1 = rs.getString("UUID");
                        Reason = rs.getString("Reason");
                        MutedBy = rs.getString("Mutedby");
                        MuteTime = rs.getLong("MuteTime");
                        MutedTime = rs.getLong("MutedTime");
                        toReturn = new Mute(UUID1, Reason, MutedBy, MuteTime, MutedTime, false);
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
     * @param UUID UUID of the Player to be unMuted
     */
    public static void unMute(String UUID){
        MySQL.update("UPDATE Mutes SET Unmuted=1 WHERE UUID='"+UUID.replaceAll("-", "")+"'");
    }

}
