package com.guild.altruists;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import javax.naming.Context;

import sun.awt.im.InputMethodManager;
//Основной класс
public class Guild extends ApplicationAdapter {
	SpriteBatch batch;
	SpriteBatchRubber drawer;
	Preferences safes;
	Random random = new Random();
	Texture back_1, back_2, back_2_2, back_3, back_4, back_5, back_6, back_7, back_8, back_9, back_10, dark;//Текстуры
	BitmapFont font_1, font_2, font_3;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>:";
	int backq = 50;
	int backn = 3;
	int backo = 0;
	Back[] backs = new Back[backq];
	float wpw, hph;
	float width, height;
	float stepx =450f/backn;
	float stepy =450f/backn;
	float cy=0;
	float vcy=0;
	float button_1_y=0;
	float button_1_s=1;
	float button_2_y=200;
	float button_2_s=1;
	float button_3_y=0;
	float button_3_s=1;
	float button_4_y=200;
	float button_4_s=1;
	int mode=0;
	int reg = 0;
	String text="";
	float scale = 1;
	boolean snd;
	boolean touchedgreen=false;
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
		parameter.size = (int)(15.0*wpw);
		parameter.borderWidth = 0.5f;
		parameter.borderColor = Color.WHITE;
		font_1 = generator.generateFont(parameter);
		font_1.setColor(Color.BLACK);
		parameter.size = (int)(20.0*wpw);
		font_2 = generator.generateFont(parameter);
		font_2.setColor(Color.BLACK);
		parameter.size = (int)(10.0*wpw);
		font_3 = generator.generateFont(parameter);
		font_3.setColor(Color.BLACK);
		back_1 = new Texture("back_1.png");//Текстуры
		back_2 = new Texture("back_2.png");
		back_2_2 = new Texture("back_2_2.png");
		back_3 = new Texture("back_3.png");
		back_4 = new Texture("back_4.png");
		back_5 = new Texture("back_5.png");
		back_6 = new Texture("back_6.png");
		back_7 = new Texture("back_7.png");
		back_8 = new Texture("back_8.png");
		back_9 = new Texture("back_9.png");
		back_10 = new Texture("back_10.png");
		dark = new Texture("dark.png");
		for(int i=0;i<backq;i++){
			backs[i] = new Back(this, "TEXT_"+i, i%backn, i/backn,23, 10, 25, 20, 3);//Листы
		}
		Gdx.input.setInputProcessor(new GuildInput(this));
	}
	public void GetSaves(){
		if(safes.contains("snd")) {
			snd = safes.getBoolean("snd");
		}
	}
	@Override
	public void render () {//Рендер
		if (reg == 0) {//Вся математика, анимации
			for(int i=0;i<backq;i++) {
				if (backs[i].state != 3) {
					backs[i].x = i % backn;
					backs[i].y = i / backn;
				}
			}
			if (mode==1) {
				if (!touchedgreen) {
					scale += (1 - scale) / 5;
					if (scale>0.9){
						scale=1;
					}
				}
				if (!keyboard) {
					button_1_y += (200-button_1_y) / 5f;
					button_1_s += (1-button_1_s)/5f;
				}else{
					button_1_y += (-height/2+100-button_1_y) / 5f;
					button_1_s += (0.5-button_1_s)/5f;
				}
				button_2_y += (-button_2_y) / 5f;
				if (keyboard){
					button_3_y += (-height / 2+75 - button_3_y) / 5f;
				}else {
					button_3_y += (-height / 20 - button_3_y) / 5f;
				}
				if (keyboard) {
					button_4_y += (200-button_4_y) / 5f;
				}else{
					button_4_y += (-button_4_y) / 5f;
				}
			}
			if (mode==0){
				scale+=(1-scale)/5;
				if (scale>0.9){
					scale=1;
				}
				button_1_y += (-button_1_y) / 5f;
				button_1_s += (1-button_1_s)/5f;
				button_2_y+=(200-button_2_y)/5f;
				button_3_y+=(height-button_3_y)/5f;
				button_4_y+=(height-button_4_y)/5f;
			}
			button_2_s += (1-button_2_s)/5f;
			button_3_s += (1-button_3_s)/5f;
			button_4_s += (1-button_4_s)/5f;
			vcy+=(-vcy)/5f;
			cy+=vcy;
			if (cy > height / 20) {
				cy = height / 20;
			}
			if (cy < -height) {
				cy = -height;
			}
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();//Отрисовка интерфейса
			drawer.draw(back_1, 0, -cy, width, height/2);
			drawer.draw(back_1, 0, -cy+height/2, width, height/2);
			if (cy<0){
				drawer.draw(back_1, 0, -cy, width, -height/2);
				drawer.draw(back_1, 0, -cy-height, width, height/2);
			}
			for(int i=0;i<backq;i++){
				if (backs[i].state!=3) {
					backs[i].draw();
				}
			}
			if (mode==1){
				drawer.draw(dark, 0, 0, width, height);
			}
			drawer.draw(back_3, 0, 0, width, height/20);
			if (scale==1) {
				drawer.draw(back_2, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y + (1 - scale) * height / 2, width * 1.1f * scale, width * 1.1f * scale);
				drawer.draw(back_8, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y + width / 4 + scale * width / 8f + (1 - scale) * height / 4, width * 1.1f * scale, width * 0.5f);
			}else{
				drawer.draw(back_2_2, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y + (1 - scale) * height / 2, width * 1.1f * scale, width * 1.1f * scale);
			}
			String text1 = text.substring(0, text.length());//Просчёт текста
			String text2 = "";
			String text3 = "";
			String text4 = "";
			if(text.length()>20&&text.length()<40){
				text1 = text.substring(0, 20);
				text2 = text.substring(20, text.length());
			}
			if(text.length()>=40&&text.length()<60){
				text1 = text.substring(0, 20);
				text2 = text.substring(20, 40);
				text3 = text.substring(40, text.length());
			}
			if(text.length()>=60&&text.length()<=80){
				text1 = text.substring(0, 20);
				text2 = text.substring(20, 40);
				text3 = text.substring(40, 60);
				text4 = text.substring(60, text.length());
			}
			if (scale==1) {
				font_2.draw(batch, text1, (width / 2 - text1.length() * 5.5f) * wpw, (-button_3_y + width / 2 + width / 3 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text2, (width / 2 - text2.length() * 5.5f) * wpw, (-button_3_y + width / 2 + width / 3 - width / 20 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text3, (width / 2 - text3.length() * 5.5f) * wpw, (-button_3_y + width / 2 + width / 3 - width / 10 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text4, (width / 2 - text4.length() * 5.5f) * wpw, (-button_3_y + width / 2 + width / 3 - width / 20 - width / 10 + (1 - scale) * height / 4) * hph);
			}
			drawer.draw(back_4, width / 2 - 75 * button_1_s, -75 * button_1_s - button_1_y, 150 * button_1_s, 150 * button_1_s);
			drawer.draw(back_9, width / 2 - 25,  - button_4_y, 50*button_4_s, 50*button_4_s);
			drawer.draw(back_5, width-100, -button_2_y, 100*button_2_s, 100*button_2_s);
			drawer.draw(back_6, 10, -button_2_y, 75*button_2_s, 75*button_2_s);
			drawer.draw(back_3, 0, height-height/20, width, height/20);
			batch.end();
		}
	}
}
