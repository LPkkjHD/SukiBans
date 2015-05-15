package me.suki.SukiBans.Mutes;

/**
 * Created by DatSukii on 15.05.2015 at 13:20
 */
public class Mute {
    private String UUID, Reason, Mutedby;
    private Boolean Unmuted;
    private long MuteTime, MutedTime;

    public Mute(String UUID, String Reason, String Mutedby, long Mutetime, long Mutedtime, Boolean unmuted){
        this.UUID = UUID;
        this.Reason = Reason;
        this.Mutedby = Mutedby;
        this.MuteTime = Mutetime;
        this.MutedTime = Mutedtime;
        this.Unmuted = unmuted;
    }

    public String getUUID(){
        return UUID;
    }
    public String getReason(){
        return Reason;
    }
    public String getMutedby(){
        return Mutedby;
    }
    public boolean getUnmuted(){
        return Unmuted;
    }
    public long getMuteTime(){
        return MuteTime;
    }
    public long getMutedTime(){
        return MutedTime;
    }
}
