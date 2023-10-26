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
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Pattern;

import javax.naming.Context;

import sun.awt.im.InputMethodManager;
//Основной класс
public class Guild extends ApplicationAdapter {
	SpriteBatch batch;
	SpriteBatchRubber drawer;
	Preferences safes;
	Random random = new Random();
	Sound sound_1, sound_2, sound_3, sound_4, sound_5, sound_6;
	Texture back_1, back_2, back_2_2, back_3, back_4, back_5, back_6, back_7, back_8, back_9, back_10, back_11, back_12, dark, up, down, full, top, bottom;//Текстуры
	BitmapFont font_1, font_2, font_3, font_4, font_5;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	public static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>:";
	int backq = 30;
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
	float button_5_y=0;
	float downmenu_y=0;
	float scroll_y = 0;
	float scaley = 0;
	float updown_x=0;
	int mode=0;
	int reg = 0;
	int textmode=0;
	int choosed=-1;
	String text="";
	String bigtext="";
	int year;
	int month;
	int day;

	int yeard;
	int monthd;
	int dayd;
	float scale = 1;
	boolean full_mode=false;
	boolean snd;
	boolean touchedgreen=false;
	boolean keyboard=false;
	FileHandle back, enter;
	int account_id = 0;
	String account_login = "femboychik228";
	String account_password = "lolkek2";
	int account_money = 0;
	int account_level = 0;
	int[] account_tasks = new int[]{-1,-1,-1};
	String[] logins = new String[300];
	String[] passwords = new String[300];
	int logpasq, backinfq;
	@Override
	public void create () {
		Gdx.app.log("", "myaaa");

		enter = Gdx.files.local("enter.txt");
		enter.writeString("", true);
		back = Gdx.files.local("back.txt");
		back.writeString("", true);
		String login_password=enter.readString();
		int i = 0;
		if (!login_password.equals("")) {
			Gdx.app.log("", "" + login_password);
			String[] splitted = login_password.split(Pattern.quote("}}}}"));
			for (i = 0; i < splitted.length; i++) {
				String[] splitted2 = splitted[i].split(Pattern.quote("----"));
				logins[i] = splitted2[0];
				passwords[i] = splitted2[1];
			}
			logpasq = i;
			Gdx.app.log("", "" + i);

			boolean access = false;
			for (i=0;i<logpasq;i++){
				if (logins[i].equals(account_login)){
					if (passwords[i].equals(account_password)){
						account_id = i;
						access=true;
						break;
					}
				}
			}
			if(!access){
				enter.writeString("}}}}"+account_login+"----"+account_password, true);
				account_id = i;
			}
		}
		else{
			enter.writeString(account_login+"----"+account_password, true);
		}


		Gdx.app.log("Entered by: "+account_login, ""+account_id);

		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		yeard=year%100;
		monthd=month;
		dayd=day;
		for(i=0;i<backq;i++){
			backs[i] = new Back(this, "TEXT_"+i, i%backn, i/backn,year%100, month, day, 20, 3);//Листы
		}

		String back_info = back.readString("windows-1251");
		if(!back_info.equals("")) {
			String[] splitted = back_info.split(Pattern.quote("}}}}"));
			i = 0;
			for (; i < splitted.length; i++) {
				String[] splitted2 = splitted[i].split(Pattern.quote("----"));
				backs[i].task = splitted2[0];
				backs[i].bigtext = splitted2[1];
				backs[i].year = Integer.parseInt(splitted2[2]);
				backs[i].month = Integer.parseInt(splitted2[3]);
				backs[i].day = Integer.parseInt(splitted2[4]);
				backs[i].x = Integer.parseInt(splitted2[5]);
				backs[i].y = Integer.parseInt(splitted2[6]);
				backs[i].account = Integer.parseInt(splitted2[7]);
				backs[i].state = 0;
			}
			backinfq = i;
			backo = backinfq;
		}
		sound_1 = Gdx.audio.newSound(Gdx.files.internal("sound_1.mp3"));
		sound_2 = Gdx.audio.newSound(Gdx.files.internal("sound_2.mp3"));
		sound_3 = Gdx.audio.newSound(Gdx.files.internal("sound_3.mp3"));
		sound_4 = Gdx.audio.newSound(Gdx.files.internal("sound_4.mp3"));
		sound_5 = Gdx.audio.newSound(Gdx.files.internal("sound_5.mp3"));
		sound_6 = Gdx.audio.newSound(Gdx.files.internal("sound_6.mp3"));
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
		parameter.borderWidth = 1f;
		parameter.borderColor = Color.BLACK;
		font_5 = generator.generateFont(parameter);
		font_5.setColor(Color.WHITE);
		parameter.size = (int)(15.0*wpw);
		parameter.borderWidth = 0.5f;
		parameter.borderColor = Color.WHITE;
		font_1 = generator.generateFont(parameter);
		font_1.setColor(Color.BLACK);
		parameter.size = (int)(20.0*wpw);
		font_2 = generator.generateFont(parameter);
		font_2.setColor(Color.BLACK);
		parameter.size = (int)(15.0*wpw);
		font_4 = generator.generateFont(parameter);
		font_4.setColor(Color.GOLD);
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
		back_11 = new Texture("back_11.png");
		back_12 = new Texture("back_12.png");
		dark = new Texture("dark.png");
		up = new Texture("up.png");
		down = new Texture("down.png");
		full = new Texture("full.png");
		top = new Texture("top.png");
		bottom = new Texture("bottom.png");
		Gdx.input.setInputProcessor(new GuildInput(this));
	}
	public void GetSaves(){
		if(safes.contains("snd")) {
			snd = safes.getBoolean("snd");
		}
	}
	public void AddBack(){
		Back b = backs[backo];
		back.writeString(b.task+"----"+b.bigtext+"----"+b.year+"----"+b.month+"----"+b.day+"----"+b.x+"----"+b.y+"----"+b.account+"}}}}", true);
	}
	public void AcceptTask(){
		int i=0;
		for (;i<3;i++){
			if(account_tasks[i]==-1){
				break;
			}
		}
		if(i!=3){
			account_tasks[i]=choosed;
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
					updown_x=(200-updown_x)/5;;
				}else{
					scroll_y+=(-scroll_y)/5f;
					updown_x=(-10-updown_x)/5;
					button_1_y += (-height/2+100-button_1_y) / 5f;
					button_1_s += (0.5-button_1_s)/5f;
				}
				button_5_y += (height-button_5_y)/5f;
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
				updown_x=(200-updown_x)/5;
				if(choosed==-1) {
					scroll_y += (-scroll_y) / 5f;
				}
				scale+=(1-scale)/5;
				if (scale>0.9){
					scale=1;
				}
				button_5_y += (-button_5_y)/5f;
				button_1_y += (-button_1_y) / 5f;
				button_1_s += (1-button_1_s)/5f;
				button_2_y+=(200-button_2_y)/5f;
				button_3_y+=(height-button_3_y)/5f;
				button_4_y+=(height-button_4_y)/5f;
			}
			if(choosed!=-1){
				button_3_y=-height / 20;
				button_4_y=0;
				full_mode=true;
			}
			if(!full_mode){
				scroll_y=0;
			}
			button_2_s += (1-button_2_s)/5f;
			button_3_s += (1-button_3_s)/5f;
			button_4_s += (1-button_4_s)/5f;
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
			if (mode==1||choosed!=-1){
				drawer.draw(dark, 0, 0, width, height);
			}
			if (scale==1||choosed!=-1) {
				drawer.draw(back_2, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y-scroll_y + (1 - scale) * height / 2, width * 1.1f * scale, (width+scaley) * 1.1f * scale);
				drawer.draw(back_8, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y-scroll_y+scaley + width / 4 + scale * width / 8f + (1 - scale) * height / 4, width * 1.1f * scale, width * 0.5f);
			}else{
				drawer.draw(back_2_2, -width * (0.5f) + (1 - scale) * width / 2 + width / 2 - width / 20, -button_3_y-scroll_y + (1 - scale) * height / 2, width * 1.1f * scale, (width+scaley) * 1.1f * scale);
			}
			int q=0;
			for (int i=0;i<3;i++){
				if(account_tasks[i]!=-1){
					q++;
				}
			}
			if (choosed!=-1) {
				if (backs[choosed].account!=account_id) {
					drawer.draw(back_10, width*0.25f, -button_3_y-scroll_y + width / 80, width * 0.5f * scale, (width/10));
					boolean a=false;
					for (int i=0;i<3;i++){
						if(account_tasks[i]==-1){
							a=true;
							break;
						}
					}
					for (int i=0;i<3;i++){
						if (account_tasks[i]==choosed){
							a=false;
							break;
						}
					}
					if(a) {
						font_4.draw(batch, "Принять задание!", (width * 0.25f + width * 0.1f) * wpw, (-button_3_y - scroll_y + width / 20 + width / 80) * hph);
					}else{
						font_4.draw(batch, "Взято заданий "+q+"/3", (width * 0.25f + width * 0.1f) * wpw, (-button_3_y - scroll_y + width / 20 + width / 80) * hph);
					}
				}
			}
			String text = this.text;
			if(choosed!=-1){
				text = backs[choosed].task.substring(0, backs[choosed].task.length());
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
				font_2.draw(batch, text1, (width / 2 - text1.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text2, (width / 2 - text2.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 20 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text3, (width / 2 - text3.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 10 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text4, (width / 2 - text4.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 20 - width / 10 + (1 - scale) * height / 4) * hph);
			}
			String bigtext1 = bigtext;
			if(choosed!=-1){
				bigtext1=backs[choosed].bigtext;

			}
			int bigq = (bigtext1.length()/30);
			if((!keyboard||textmode==1)&&!full_mode){
				bigq=Math.min(bigq, 7);
			}
			if(bigq>=5) {
				scaley = (bigq-5) * width / 20;
			}else{
				scaley = 0;
			}
			String[] big = new String[bigq+1];
			for (int i=0;i<bigq+1;i++){
				int last_length = Math.min((i+1)*30, bigtext1.length());
				big[i] = bigtext1.substring(i*30, last_length);
				if(((!keyboard||textmode==1)&&!full_mode)&&i==bigq&&!big[0].equals("")){
					big[i]=".....";
				}
			}
			if (scale==1) {
				for (int i=0;i<bigq+1;i++) {
					font_2.draw(batch, big[i], (width / 2 - big[i].length() * 5.5f-width/20) * wpw, (-button_3_y - scroll_y + scaley+ width / 2 + width / 5 - width / 20 - width / 10- (width / 20)*i + (1 - scale) * height / 4) * hph);
				}
			}
			if (choosed==-1) {
				if (full_mode) {
					drawer.draw(full, 35, -scroll_y - button_4_y + 110, 30, 30);
				} else {
					drawer.draw(full, 25, -scroll_y - button_4_y + 100, 50, 50);
				}
			}
			String days=""+dayd;
			String months=""+monthd;
			String years=""+yeard;
			if(dayd<10){
				days = "0"+days;
			}
			if(monthd<10){
				months = "0"+months;
			}
			if (scale==1) {
				font_2.draw(batch, days + "." + months + "." + years, (width-width / 4) * wpw, (-button_3_y - scroll_y + width / 7.5f) * hph);
			}
			if (keyboard) {
				drawer.draw(top, width - width / 4, -button_3_y - scroll_y + width / 7.5f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4, -button_3_y - scroll_y + width / 7.5f - 45, 25, 25);
				drawer.draw(top, width - width / 4+35, -button_3_y - scroll_y + width / 7.5f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4+35, -button_3_y - scroll_y + width / 7.5f - 45, 25, 25);
				drawer.draw(top, width - width / 4+70, -button_3_y - scroll_y + width / 7.5f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4+70, -button_3_y - scroll_y + width / 7.5f - 45, 25, 25);
			}
			drawer.draw(back_3, 0, 0, width, height/20);
			if (choosed==-1) {
				drawer.draw(back_4, width / 2 - 75 * button_1_s, -75 * button_1_s - button_1_y, 150 * button_1_s, 150 * button_1_s);
				drawer.draw(back_12, width - 100, 0 - button_5_y, 100, 100);
				font_5.draw(batch, q+"/3", (width - 150) * wpw, (- button_5_y+height/30f) * hph);
			}
			drawer.draw(back_9, width / 2 - 25,  -button_4_y, 50*button_4_s, 50*button_4_s);

			drawer.draw(back_5, width-100, -button_2_y, 100*button_2_s, 100*button_2_s);
			drawer.draw(back_6, 10, -button_2_y, 75*button_2_s, 75*button_2_s);
			if(keyboard) {
				if (textmode==2) {
					drawer.draw(down, -updown_x,  +height/10- 25-button_3_y, 50, 50);
				}
				if (textmode==1) {
					drawer.draw(up, -updown_x,  +height/10- 25-button_3_y, 50, 50);
				}

			}

			drawer.draw(back_3, 0, height-height/20, width, height/20);
			batch.end();
		}
	}
}
