package com.guild.altruists;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class TotalDo {
    public static void Do(TotalPacket packet){
        for(int i=0;i<100;i++){
            if (packet.d[i].t!=-1) {
                FileHandle handle = new Gdx.files.local(packet.d[i].file);
                if(packet.d[i].t==0){//Очистка файла
                    handle.writeString("", false);
                }
                if(packet.d[i].t==1){//Создание файла
                    handle.writeString("", true);
                }
            }
        }
    }
}
