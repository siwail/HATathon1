package com.guild.altruists;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class Guild extends ApplicationAdapter {
	SpriteBatch batch;
	SpriteBatchRubber drawer;
	Preferences safes;
	Random random = new Random();
	Texture back_1, back_2, back_3;
	BitmapFont font_1;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	//Music music_1;
	//Sound sound_1;
	int mode = 0;
	public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>:";
	int backq = 50;
	int backn = 3;
	Back[] backs = new Back[backq];
	float wpw, hph;
	float width, height;
	float stepx =450f/backn;
	float stepy =450f/backn;
	float cy=0;
	boolean snd;
	boolean keyboard=false;
	@Override
	public void create () {
		batch = new SpriteBatch();
		safes = Gdx.app.getPreferences("Save");
		GetSaves();
		height = 1087.5f;
		width = 506.25f;
		cy=height/20;
		Gdx.graphics.setWindowedMode((int)width, (int)height);
		wpw = (float) Gdx.graphics.getWidth() / (float) width;
		hph = (float) Gdx.graphics.getHeight() / (float) height;
		batch = new SpriteBatch();
		drawer = new SpriteBatchRubber(this, batch);
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		parameter.characters = FONT_CHARACTERS;
		parameter.size = (int)(20.0*wpw);
		parameter.borderWidth = 0.5f;
		parameter.borderColor = Color.WHITE;
		font_1 = generator.generateFont(parameter);
		font_1.setColor(Color.BLACK);
		/*music_1 = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music_1.setVolume(0.25f);
		music_1.setLooping(true);
		if(snd) {
			music_1.play();
		}*/
		//sound_1 = Gdx.audio.newSound(Gdx.files.internal("sound_1.mp3"));
		back_1 = new Texture("back_1.png");
		back_2 = new Texture("back_2.png");
		back_3 = new Texture("back_3.png");
		for(int i=0;i<backq;i++){
			backs[i] = new Back(this, "Popa"+i, i%backn, i/backn,23, 10, 25, 20, 3);
		}
		backs[0].state=0;
		Gdx.input.setInputProcessor(new GuildInput(this));
	}
	public void GetSaves(){
		if(safes.contains("snd")) {
			snd = safes.getBoolean("snd");
		}
	}
	@Override
	public void render () {
		if (mode == 0) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
			drawer.draw(back_1, 0, -cy, width, height);
			if (cy<0){
				drawer.draw(back_1, 0, -cy, width, -height);
			}
			//font_1.draw(batch, "PLAY", (width / 2 - 150) * wpw, (350) * hph);
			for(int i=0;i<backq;i++){
				if (backs[i].state!=3) {
					backs[i].x = i % backn;
					backs[i].y = i / backn;
					backs[i].draw();
				}
			}
			drawer.draw(back_3, 0, 0, width, height/20);
			batch.end();
		}
	}
	public int hits(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
		if (x2 > x1 && x2 + w2 < x1 + w1 && y2 > y1 && y2 < y1 + h1) {
			return 1;
		}
		if (x2 < x1 + w1 && x2 > x1 && y2 > y1 && y2 + h2 < y1 + h1) {
			return 2;
		}
		if (x2 > x1 && x2 + w2 < x1 + w1 && y2 + h2 > y1 && y2 + h2 < y1 + h1) {
			return 3;
		}
		if (x2 + w2 > x1 && x2 + w2 < x1 + w1 && y2 > y1 && y2 + h2 < y1 + h1) {
			return 4;
		}
		if (x2 < x1 + w1 && x2 + w2 > x1 && y2 < y1 + h1 && y2 + h2 > y1) {
			return 5;
		}
		return 0;
	}
	public boolean hit(float x1, float y1, float r1, float x2, float y2, float r2) {
		float dx = Math.abs(x1 - x2);
		float dy = Math.abs(y1 - y2);
		return Math.sqrt(dx*dx+dy*dy)<=r1+r2;
	}
}
