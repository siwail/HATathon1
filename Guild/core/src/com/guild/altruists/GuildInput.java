package com.guild.altruists;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GuildInput implements InputProcessor {
    Guild game;
    float ly=0;
    float ty=0;
    boolean touched=false;
    public GuildInput(Guild game){
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            game.keyboard=false;
            Gdx.input.setOnscreenKeyboardVisible(false);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (game.mode == 0){
            if (screenX >= SX(0) && screenX <= SX(game.width) && screenY >= SY(game.height/20) && screenY <= SY(0)) {
                game.keyboard=true;
                Gdx.input.setOnscreenKeyboardVisible(game.keyboard);
                //Gdx.app.log("", "a");
            }
        }
        ly=game.cy;
        ty=screenY/game.hph;
        touched=true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touched=false;
        return false;
    }
   /* public void SwitchSound(){
        game.snd=!game.snd;
        if(game.snd) {
            game.music_1.play();
            game.sound_1.play();
        }else{
            game.music_1.pause();
        }
        game.safes.putBoolean("snd", game.snd);
        game.safes.flush();
    }*/
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //ty=screenY/game.hph;
        game.cy=ly-(ty-screenY/game.hph);
        if (game.cy>game.height/20){
            game.cy=game.height/20;
        }
        if (game.cy<-game.height){
            game.cy=-game.height;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public float SX(float x) {
        return (int) ((float) x * game.wpw);
    }

    public float SY(float y) {
        return (int) ((float) (game.height - y) * game.hph);
    }
}
