package com.guild.altruists;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
public class TotalDo {
    public static void Do(TotalPacket packet){
        for(int i=0;i<100;i++){
            if (packet.t[i]!=-1) {
                FileHandle handle = Gdx.files.local(packet.file[i]);
                if(packet.t[i]==0){//Очистка файла
                    handle.writeString(packet.text[i], false);
                }
                if(packet.t[i]==1){//Создание файла
                    handle.writeString(packet.text[i], true);
                }
                if(packet.t[i]==2){//Создание файла
                    TotalServer.d[packet.id] = handle.readString();
                }
            }
        }
    }
}
