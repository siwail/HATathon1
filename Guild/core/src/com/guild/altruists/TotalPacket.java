package com.guild.altruists;

public class TotalPacket {
    String total = "aaaaaa??";
    int id;
    TotalMove[] d;
    public TotalPacket(int id, String mode, TotalMove[] d){
        this.id=id;
        this.d=d;
    }
    public TotalPacket(int id, String mode){
        this.id=id;
    }
}
