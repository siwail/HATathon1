package com.guild.altruists;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
//Класс, обрабатывающий события
public class GuildInput implements InputProcessor {
    Guild game;
    float ly=0;
    float ty=0;
    boolean touched=false;
    boolean backspaced=false;
    int next_choosed=-1;
    public GuildInput(Guild game){
        this.game = game;
    }
    @Override
    public boolean keyDown(int keycode) {
        if (game.mode==1){
            if (game.keyboard){
                if(game.textmode==1) {
                    if (game.text.length() > 0 && Input.Keys.BACKSPACE == keycode) {
                        game.text = game.text.substring(0, game.text.length() - 1);
                        backspaced = true;
                    }
                }
                if(game.textmode==2) {
                    if (game.bigtext.length() > 0 && Input.Keys.BACKSPACE == keycode) {
                        game.bigtext = game.bigtext.substring(0, game.bigtext.length() - 1);
                        backspaced = true;
                    }
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
    public boolean keyTyped(char character) {//Набор символа
        if (game.mode==1){
            if (game.keyboard){
                if (!backspaced) {
                    if(game.textmode==1) {
                        if (character != '\n' && game.text.length() < 80) {
                            game.text += character;
                        }
                    }
                    if(game.textmode==2) {
                        if (character != '\n' && game.bigtext.length() < 1000) {
                            game.bigtext += character;
                        }
                    }
                }
                backspaced=false;
            }
        }
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {//Нажатие на экран
        if (game.reg == 0){
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4) && screenX <= SX(game.width - game.width / 4+25) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 + 25) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5)){
                game.dayd+=1;
                if(game.dayd>31){
                    game.dayd=1;
                }
                return false;
            }
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4) && screenX <= SX(game.width - game.width / 4+25) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 - 20) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f - 45)){
                game.dayd-=1;
                if(game.dayd<1){
                    game.dayd=31;
                }
                return false;
            }
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4 + 35) && screenX <= SX(game.width - game.width / 4+25 + 35) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 + 25) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5)){
                game.monthd+=1;
                if(game.monthd>12){
                    game.monthd=1;
                }
                return false;
            }
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4 + 35) && screenX <= SX(game.width - game.width / 4+25 + 35) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 - 20) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f - 45)){
                game.monthd-=1;
                if(game.monthd<1){
                    game.monthd=12;
                }
                return false;
            }
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4 + 70) && screenX <= SX(game.width - game.width / 4+25 + 70) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 + 25) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5)){
                game.yeard+=1;
                return false;
            }
            if(game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(game.width - game.width / 4 + 70) && screenX <= SX(game.width - game.width / 4+25 + 70) && screenY >= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f + 5 - 20) && screenY <= SY(-game.button_3_y - game.scroll_y + game.width / 7.5f - 45)){
                game.yeard-=1;
                return false;
            }
            if (game.choosed==-1&&game.mode==1&&game.keyboard  && screenX >= SX(0) && screenX <= SX(50) && screenY >= SY(game.height/10- 25-game.button_3_y+50) && screenY <= SY(game.height/10- 25-game.button_3_y-50)){
                if (game.textmode == 1){
                    game.textmode=2;
                }else{
                    game.textmode=1;
                }
                Gdx.input.setOnscreenKeyboardVisible(true);
                game.sound_6.play();
                return false;
            }
            //Верхний ввод
            if (game.choosed==-1&&game.mode==1&&game.keyboard  && screenX >= SX(0) && screenX <= SX(game.width) && screenY >= SY(game.height-game.scroll_y+game.scaley) && screenY <= SY(game.height/4*3-game.scroll_y+game.scaley)){
                game.textmode=1;
                Gdx.input.setOnscreenKeyboardVisible(true);
                game.sound_6.play();

            }
            //Нижний ввод
            if (game.choosed==-1&&game.mode==1 &&game.keyboard && screenX >= SX(0) && screenX <= SX(game.width) && screenY >= SY(game.height/4*3-game.scroll_y) && screenY <= SY(game.height/2-game.scroll_y)){
                game.textmode=2;
                Gdx.input.setOnscreenKeyboardVisible(true);
                game.sound_6.play();

            }
            //Нажатие на микроскоп
            if (game.choosed==-1&&game.mode==1 && screenX >= SX(game.width-200) && screenX <= SX(game.width) && screenY >= SY(game.height/10) && screenY <= SY(0)) {
                game.keyboard=true;
                game.textmode=1;
                game.sound_5.play(0.5f);
                //Gdx.input.setOnscreenKeyboardVisible(true);
                game.button_2_s -= 0.1f;
                return false;
            }
            //Нажатие на печать чтобы закончить редактирование
            if (game.choosed==-1&&game.mode==1 && screenX >= SX(game.width/2-100) && screenX <= SX(game.width/2+100) && screenY >= SY(game.height/2) && screenY <= SY(game.height/2-200)) {
                game.keyboard=false;
                Gdx.input.setOnscreenKeyboardVisible(false);
                game.button_1_s -= 0.1f;
                return false;
            }
            //Нажатие на печать чтобы начать выкладывать
            if (game.choosed==-1&&game.mode==0 && screenX >= SX(game.width/2-75) && screenX <= SX(game.width/2+75) && screenY >= SY(game.height/20) && screenY <= SY(0)) {
                game.mode=1;
                game.button_1_s -= 0.1f;
                return false;
            }
            //Нажатие на закрытие
            if (((game.mode==1 &&!game.keyboard)||game.choosed!=-1) && screenX >= SX(game.width/2-75) && screenX <= SX(game.width/2+75) && screenY >= SY(game.height/20) && screenY <= SY(0)) {
                game.mode=0;
                game.button_4_s -= 0.1f;
                game.choosed=-1;
                game.full_mode=false;
                return false;
            }
            if (game.choosed!=-1 && screenX >= SX(game.width*0.25f) && screenX <= SX(game.width*0.75f) && screenY >= SY(-game.button_3_y-game.scroll_y + game.width / 80+game.width/10) && screenY <= SY(-game.button_3_y-game.scroll_y + game.width / 80)) {
                boolean a=false;
                for (int i=0;i<3;i++){
                    if(game.account_tasks[i]==-1){
                        a=true;
                        break;
                    }
                }
                for (int i=0;i<3;i++){
                    if (game.account_tasks[i]==game.choosed){
                        a=false;
                        break;
                    }
                }
                if(a) {
                    game.mode = 0;
                    game.button_4_s -= 0.1f;
                    game.full_mode = false;
                    game.AcceptTask();
                    game.choosed = -1;
                }
                return false;
            }
            if (game.choosed==-1&&game.mode==1 &&!game.keyboard && screenX >= SX(0) && screenX <= SX(game.width) && screenY >= SY(game.height/2+200) && screenY <= SY(game.height/2-200)) {
                game.touchedgreen=true;
            }
            if (game.choosed==-1&&game.mode==1 &&!game.keyboard && screenX >= SX(25) && screenX <= SX(75) && screenY >= SY(150-game.scroll_y) && screenY <= SY(100-game.scroll_y)) {
                game.full_mode=!game.full_mode;
            }
        }
        if (game.mode==0){
            ly = game.cy;
            ty = screenY / game.hph;
        }
        if (game.mode==0&&game.choosed==-1) {
            touched = true;
            float x = screenX/game.wpw;
            float y = screenY/game.hph;
            int px = (int)Math.floor(x/game.stepx);
            int py = (int)Math.floor((y-game.cy)/game.stepy);
            int i;
            boolean access = false;
            for(i=0;i<game.backq;i++){
                if(game.backs[i].x==px&&game.backs[i].y==py&&game.backs[i].state!=3){
                    access = true;
                    break;
                }
            }
            if (!access){
                i=-1;
            }
            next_choosed=i;
        }
        if ((game.mode==1&&!game.keyboard)||game.choosed!=-1) {
            ly = game.scroll_y;
            ty = screenY / game.hph;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touched=false;
        game.touchedgreen=false;
        if (game.mode==0&&game.choosed==-1) {
            float x = screenX/game.wpw;
            float y = screenY/game.hph;
            int px = (int)Math.floor(x/game.stepx);
            int py = (int)Math.floor((y-game.cy)/game.stepy);
            int i;
            boolean access = false;
            for(i=0;i<game.backq;i++){
                if(game.backs[i].x==px&&game.backs[i].y==py&&game.backs[i].state!=3){
                    access = true;
                    break;
                }
            }
            if (!access){
                i=-1;
            }
            if(next_choosed==i){
                game.choosed=next_choosed;
            }
        }
        next_choosed=-1;
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {//Перетаскивание
        if (game.mode==0&&touched) {
            game.cy = ly - (ty - screenY / game.hph);
            }
        if (game.mode==1&&!game.keyboard&&game.touchedgreen&&!game.full_mode) {
            game.scale=(game.height-(ty-screenY/ game.hph)*4)/game.height;
            if (game.scale>1){
                game.scale=1;
            }
            if (game.scale*game.width<=game.stepx){
                game.mode=0;
                game.backs[game.backo].task = game.text;
                game.backs[game.backo].bigtext = game.bigtext;
                game.backs[game.backo].account = game.account_id;
                game.backs[game.backo].state = 0;
                game.backs[game.backo].year = game.yeard;
                game.backs[game.backo].month = game.monthd;
                game.backs[game.backo].day = game.dayd;
                game.AddBack();
                game.backo+=1;
                game.sound_1.play();

            }
        }
        if ((game.mode==1&&!game.keyboard&&game.full_mode)||game.choosed!=-1) {
            game.scroll_y = ly - (ty - screenY / game.hph);
            if(game.scroll_y>game.width/2){
                game.scroll_y=game.width/2;
            }
            if(game.scroll_y<0){
                game.scroll_y=0;
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
    public float SX(float x) {//Вспомогательные функции
        return (int) ( x * game.wpw);
    }
    public float SY(float y) {
        return (int) ((game.height - y) * game.hph);
    }
}
