package com.guild.altruists;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GuildInput implements InputProcessor {
    Guild game;
    float ly=0;
    float ty=0;
    boolean touched=false;
    boolean backspaced=false;
    char backspace;
    public GuildInput(Guild game){
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (game.mode==1){
            if (game.keyboard){
                if (game.text.length()>0&&Input.Keys.BACKSPACE==keycode){
                    game.text = game.text.substring(0, game.text.length() - 1);
                    backspaced=true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        if (game.mode==1){
            if (game.keyboard){
                if (!backspaced) {
                    if (character != '\n' && game.text.length()<80) {
                        game.text += character;
                    }
                }
                backspaced=false;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (game.reg == 0){

            if (game.mode==1 && screenX >= SX(game.width-200) && screenX <= SX(game.width) && screenY >= SY(game.height/10) && screenY <= SY(0)) {
                game.keyboard=true;
                Gdx.input.setOnscreenKeyboardVisible(true);
                game.button_2_s -= 0.1f;
                return false;
            }
            if (game.mode==1 && screenX >= SX(game.width/2-100) && screenX <= SX(game.width/2+100) && screenY >= SY(game.height/2) && screenY <= SY(game.height/2-200)) {
                game.keyboard=false;
                Gdx.input.setOnscreenKeyboardVisible(false);
                game.button_1_s -= 0.1f;
                return false;
            }
            if (game.mode==0 && screenX >= SX(game.width/2-75) && screenX <= SX(game.width/2+75) && screenY >= SY(game.height/20) && screenY <= SY(0)) {
                game.mode=1;
                game.button_1_s -= 0.1f;
                return false;
                //Gdx.app.log("", "a");
            }if (game.mode==1 &&!game.keyboard && screenX >= SX(game.width/2-75) && screenX <= SX(game.width/2+75) && screenY >= SY(game.height/20) && screenY <= SY(0)) {
                game.mode=0;
                game.button_4_s -= 0.1f;
                return false;
                //Gdx.app.log("", "a");
            }

            if (game.mode==1 &&!game.keyboard && screenX >= SX(0) && screenX <= SX(game.width) && screenY >= SY(game.height/2+200) && screenY <= SY(game.height/2-200)) {
                game.touchedgreen=true;
            }

        }
        if (game.mode==0) {
            ly = game.cy;
            ty = screenY / game.hph;
            touched = true;
        }
        if (game.mode==1&&!game.keyboard) {
            ty = screenY / game.hph;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touched=false;
        game.touchedgreen=false;
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
        if (game.mode==0&&touched) {
            //ty=screenY/game.hph;
            game.cy = ly - (ty - screenY / game.hph);
            }
        if (game.mode==1&&!game.keyboard&&game.touchedgreen) {
            game.scale=(game.height-(ty-screenY/ game.hph)*4)/game.height;
            if (game.scale>1){
                game.scale=1;
            }
            if (game.scale*game.width<=game.stepx){
                game.mode=0;
                game.backs[game.backo].task = game.text;
                game.backs[game.backo].state = 0;
                game.backo+=1;
            }
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
