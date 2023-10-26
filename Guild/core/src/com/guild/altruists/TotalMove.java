package com.guild.altruists;

public class TotalMove {
    String file;
    int t;
    public TotalMove(String file, int t){
        this.t=0;
        this.file=file;
    }
    public TotalMove(String clear){
        file=null;
        t=-1;
    }
}
