package com.guild.altruists;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.nio.sctp.Notification;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
//Основной класс
public class Guild extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shape;
	SpriteBatchRubber drawer;
	Preferences safes;
	Random random = new Random();
	Sound sound_1, sound_2, sound_3, sound_4, sound_5, sound_6;
	Texture back_1, back_2, back_2_2, back_3, back_4, back_5, back_6, back_7, back_8, back_9, back_10, back_11, back_12, back_13, back_14, dark, up, down, full, top, bottom, profile, white, dio_1, dio_2, dio_3, dio_4, dio_5, ding, press, star, star2, letter;//Текстуры
	BitmapFont font_1, font_2, font_3, font_4, font_5, font_6, font_7;
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
	float button_6_s=1;
	float downmenu_y=0;
	float scroll_y = 0;
	float scaley = 0;
	float updown_x=0;
	float profile_y=0;
	float press_s=1;
	int category=0;
	int level = 1;
	int mode=0;
	int reg = 1;
	int textmode=0;
	int choosed=-1;
	int downmenu_mode=0;
	int downmenu_touched_id=-1;
	int downmenu_touched_level = 1;
	String downmenu_touched_name;
	String downmenu_touched_login;
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
	int register_choose=0;
	String result;
	String file="";
	boolean touchedgreen=false;
	boolean keyboard=false;
	boolean downmenu=false;
	boolean profile_touched=false;
	volatile boolean waiting_server=false;
	boolean downmenu_touched=false;
	int waiting_ticks=0;
	int tickets = 0;
	int account_id = -1;
	String account_login = "";
	String account_password = "" ;
	String account_name = "";
	int account_money = 0;
	float[] account_exp = new float[]{0.1f, 0.1f, 0.1f, 0.1f, 0.1f};
	int[] account_tasks = new int[]{-1,-1,-1};
	String[] dfile = new String[100];
	String[] dtext = new String[100];
	String rtext = "";
	int[] dt = new int[100];
	Guild t;
	@Override
	public void create () {
		t=this;
		for (int i=0;i<100;i++){
			dfile[i]="";
			dtext[i]="";
			dt[i]=-1;
		}

		safes = Gdx.app.getPreferences("Save");
		GetSaves();
		int i = 0;
		/*enter = Gdx.files.local("enter.txt");
		enter.writeString("", true);
		back = Gdx.files.local("back.txt");
		back.writeString("", true);
		name = Gdx.files.local("name.txt");
		name.writeString("", true);
		money = Gdx.files.local("money.txt");
		money.writeString("", true);
		level = Gdx.files.local("level.txt");
		level.writeString("", true);
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
*/


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
/*
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
		}*/
		sound_1 = Gdx.audio.newSound(Gdx.files.internal("sound_1.mp3"));
		sound_2 = Gdx.audio.newSound(Gdx.files.internal("sound_2.mp3"));
		sound_3 = Gdx.audio.newSound(Gdx.files.internal("sound_3.mp3"));
		sound_4 = Gdx.audio.newSound(Gdx.files.internal("sound_4.mp3"));
		sound_5 = Gdx.audio.newSound(Gdx.files.internal("sound_5.mp3"));
		sound_6 = Gdx.audio.newSound(Gdx.files.internal("sound_6.mp3"));
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

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
		parameter.borderWidth =1f;
		parameter.borderColor = Color.BLACK;
		parameter.size = (int)(15.0*wpw);
		font_7 = generator.generateFont(parameter);
		font_7.setColor(Color.OLIVE);
		parameter.borderWidth =0.5f;
		parameter.borderColor = Color.WHITE;
		parameter.size = (int)(35.0*wpw);
		font_6 = generator.generateFont(parameter);
		font_6.setColor(Color.GOLD);
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
		back_13 = new Texture("back_13.png");
		back_14 = new Texture("back_14.png");
		dio_1 = new Texture("dio_1.png");
		dio_2 = new Texture("dio_2.png");
		dio_3 = new Texture("dio_3.png");
		dio_4 = new Texture("dio_4.png");
		dio_5 = new Texture("dio_5.png");
		dark = new Texture("dark.png");
		up = new Texture("up.png");
		down = new Texture("down.png");
		full = new Texture("full.png");
		top = new Texture("top.png");
		bottom = new Texture("bottom.png");
		profile = new Texture("profile.png");
		white = new Texture("white.png");
		ding = new Texture("ding.png");
		press = new Texture("press.png");
		star = new Texture("star.png");
		star2 = new Texture("star2.png");
		letter = new Texture("letter.png");
		Gdx.input.setInputProcessor(new GuildInput(this));
		Thread online = new Thread(){
			@Override
			public void run(){
				try {
					TotalClient.TotalStart(t);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		online.start();
	}
	public void UpdateBacks(){
		Thread register = new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < backq; i++) {
					if (backs[i].state != 3) {
						dt[3] = 0;
						int doit=backs[i].doit;
						if(account_tasks[0]==i){
							doit=account_id;
						}
						if(account_tasks[1]==i){
							doit=account_id;
						}
						if(account_tasks[2]==i){
							doit=account_id;
						}
						dtext[3] = backs[i].x + "&" + backs[i].y + "&" + backs[i].task + "&" + backs[i].bigtext + "&" + backs[i].year + "&" + backs[i].month + "&" + backs[i].day + "&" + backs[i].index + "&" + backs[i].account + "&" + backs[i].category + "&" + backs[i].level + "&" + doit;
						dfile[3] = "/backs/back_"+i+".txt";
					}
				}
			}
		};
		register.start();
	}
	public void UpdatePerson(){
		Thread register = new Thread(){
			@Override
			public void run(){
				dt[2]=0;
				dtext[2]=account_login+"&"+account_password+"&"+account_name+"&"+account_money+"&"+account_exp[0]+"&"+account_exp[1]+"&"+account_exp[2]+"&"+account_exp[3]+"&"+account_exp[4]+"&"+account_tasks[0]+"&"+account_tasks[1]+"&"+account_tasks[2];
				dfile[2]="/logins/"+account_id+".txt";
			}
		};
		register.start();
	}
	public String GetServer(String file){
		t.file = file;
		t.waiting_server=true;
		t.waiting_ticks=0;
		boolean wait=true;
		while(wait){wait=waiting_server;}
		t.file="";
		return result;
	}
	public void GetSaves(){
		if(safes.contains("account_id")) {
			account_id = safes.getInteger("account_id");
		}
		Gdx.app.log(account_id+"", "");
	}
	public void AddBack(){
		UpdateBacks();
	}
	public void AcceptTask(){
		int i=0;
		for (;i<3;i++){
			if(account_tasks[i]==-1){
				break;
			}
		}
		if(i!=3&&backs[choosed].doit==-1){
			account_tasks[i]=choosed;
			backs[choosed].doit=account_id;
			sound_2.play(0.5f);
			UpdatePerson();
			UpdateBacks();
		}
	}
	public void Register(){
		sound_6.play();
		Thread register = new Thread(){
			@Override
			public void run(){
				account_id=random.nextInt(3000)+1;
				dt[1]=0;
				dtext[1]=account_login+"&"+account_password+"&"+account_name+"&"+account_money+"&"+account_exp[0]+"&"+account_exp[1]+"&"+account_exp[2]+"&"+account_exp[3]+"&"+account_exp[4]+"&"+account_tasks[0]+"&"+account_tasks[1]+"&"+account_tasks[2];
				dfile[1]="/logins/"+account_id+".txt";
				safes.putInteger("account_id", account_id);
				safes.flush();
				Sleep(1000);
				reg=1;
			}
		};
		register.start();
	}
	@Override
	public void render () {//Рендер
		if (reg==2) {
			ScreenUtils.clear(0.1f, 0, 0, 1);
			batch.begin();
			drawer.draw(back_1, 0, 0, width, height/2);
			drawer.draw(back_1, 0, height/2, width, height/2);
			drawer.draw(back_3, 0, height/4*3, width, height/10);
			drawer.draw(back_2, 0, height/4, width, width);
			font_6.draw(batch, "Регистрация", (width/4)*wpw, (height/4*3+height/17.5f)*hph);
			drawer.draw(dark, 0, height/2+height/10f+height/20f-height/30f+height/60f, width, height/30);
			drawer.draw(dark, 0, height/2+height/10f-height/30f+height/60f, width, height/30);
			drawer.draw(dark, 0, height/2+height/20f-height/30f+height/60f, width, height/30);
			if (register_choose == 1) {
				drawer.draw(back_11, width/8, height/2+height/10f+height/20f-height/30f+height/60f+width/40, width/40, width/40);
			}
			if (register_choose == 2) {
				drawer.draw(back_11, width/8, height/2+height/10f-height/30f+height/60f+width/40, width/40, width/40);
			}
			if (register_choose == 3) {
				drawer.draw(back_11, width/8, height/2+height/20f-height/30f+height/60f+width/40, width/40, width/40);
			}
			font_5.draw(batch, "Ваше имя: "+account_name, (width/6)*wpw, (height/2+height/10f+height/20f)*hph);
			font_5.draw(batch, "Ваша почта: "+account_login, (width/6)*wpw, (height/2+height/10f)*hph);
			font_5.draw(batch, "Ваш пароль: "+account_password, (width/6)*wpw, (height/2+height/20f)*hph);
			drawer.draw(back_10, width/4, height/4, width/2, height/20);
			font_4.draw(batch, "Зарегистрироваться", (width/4+width/15)*wpw, (height/4+height/40f)*hph);
			batch.end();
		}
		if (reg==1) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
			font_4.draw(batch, "Подключение к серверу...", 100*wpw, 100*hph);
			batch.end();
			if (TotalClient.connected){
				if(account_id==-1){
					reg=2;
				}else{
					Thread load = new Thread(){
						@Override
						public void run(){
							String loads=GetServer("/logins/"+account_id+".txt");
							String[] splitted = loads.split("&");
							account_login = splitted[0];
							account_password = splitted[1];
							account_name = splitted[2];
							account_money = Integer.parseInt(splitted[3]);
							account_exp[0] = (float)Double.parseDouble(splitted[4]);
							account_exp[1] = (float)Double.parseDouble(splitted[5]);
							account_exp[2] = (float)Double.parseDouble(splitted[6]);
							account_exp[3] = (float)Double.parseDouble(splitted[7]);
							account_exp[4] = (float)Double.parseDouble(splitted[8]);
							account_tasks[0] = Integer.parseInt(splitted[9]);
							account_tasks[1] = Integer.parseInt(splitted[10]);
							account_tasks[2] = Integer.parseInt(splitted[11]);
							for(int i=0;i<backq;i++) {//dtext[3] = backs[i].x + "&" + backs[i].y + "&" + backs[i].task + "&" + backs[i].bigtext + "&" + backs[i].year + "&" + backs[i].month + "&" + backs[i].day + "&" + backs[i].index + "&" + backs[i].account;
								String load = GetServer("/backs/back_" + i + ".txt");
								if (!load.equals("")) {
									backo+=1;
									splitted = load.split("&");
									backs[i].x=Integer.parseInt(splitted[0]);
									backs[i].y=Integer.parseInt(splitted[1]);
									backs[i].task=splitted[2];
									backs[i].bigtext=splitted[3];
									backs[i].state=0;
									backs[i].year=Integer.parseInt(splitted[4]);
									backs[i].month=Integer.parseInt(splitted[5]);
									backs[i].day=Integer.parseInt(splitted[6]);
									backs[i].index=Integer.parseInt(splitted[7]);
									backs[i].account=Integer.parseInt(splitted[8]);
									backs[i].category=Integer.parseInt(splitted[9]);
									backs[i].level=Integer.parseInt(splitted[10]);
									backs[i].doit=Integer.parseInt(splitted[11]);
								}
							}


						}
					};
					load.start();
					reg=0;
				}
			}
		}
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
				if(profile_touched){
					profile_y+=(height-profile_y)/5;
				}else{
					profile_y+=(-profile_y)/5;
				}
				button_5_y += (-button_5_y)/5f;
				button_1_y += (-button_1_y) / 5f;
				button_1_s += (1-button_1_s)/5f;
				button_2_y+=(200-button_2_y)/5f;
				button_3_y+=(height-button_3_y)/5f;
				button_4_y+=(height-button_4_y)/5f;
				if(downmenu){
					downmenu_y+=(stepy+stepy/2+width/2-downmenu_y)/5;
				}else{
					downmenu_y+=(-downmenu_y)/5;
				}
			}
			if(downmenu_touched){
				button_6_s+=(1-button_6_s)/5;
			}else{
				button_6_s+=(0.5f-button_6_s)/5;
			}
			press_s+=(1-press_s);
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
						if(backs[choosed].doit==-1) {
							font_4.draw(batch, "Принять задание!", (width * 0.25f + width * 0.1f) * wpw, (-button_3_y - scroll_y + width / 20 + width / 80) * hph);
						}else{
							font_4.draw(batch, "Задание уже выбрано", (width * 0.25f) * wpw, (-button_3_y - scroll_y + width / 20 + width / 80) * hph);
						}
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
			int category1=category;
			int level1=level;
			if(choosed!=-1){
				category1=backs[choosed].category;
				level1=backs[choosed].level;
			}
			if (scale==1) {
				font_2.draw(batch, text1, (width / 2 - text1.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text2, (width / 2 - text2.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 20 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text3, (width / 2 - text3.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 10 + (1 - scale) * height / 4) * hph);
				font_2.draw(batch, text4, (width / 2 - text4.length() * 5.5f) * wpw, (-button_3_y-scroll_y+scaley + width / 2 + width / 3 - width / 20 - width / 10 + (1 - scale) * height / 4) * hph);
				drawer.draw(dark, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2, width/30);

				if(category1==0){
					font_1.draw(batch, "Социальная деятельность", (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width) * hph);
					drawer.draw(dio_1, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2*level1/5, width/30);
				}
				if(category1==1){
					font_1.draw(batch, "Медицинская деятельность", (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width) * hph);
					drawer.draw(dio_2, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2*level1/5, width/30);
				}
				if(category1==2){
					font_1.draw(batch, "Физическая деятельность", (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width) * hph);
					drawer.draw(dio_3, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2*level1/5, width/30);
				}
				if(category1==3){
					font_1.draw(batch, "Интеллектуальная деятельность", (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width) * hph);
					drawer.draw(dio_4, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2*level1/5, width/30);
				}
				if(category1==4){
					font_1.draw(batch, "Творческая деятельность", (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width) * hph);
					drawer.draw(dio_5, width / 4,-button_3_y-scroll_y+scaley + width-width/16, width/2*level1/5, width/30);
				}
				drawer.draw(press, 0,-button_3_y-scroll_y+scaley + width-width/6, width/4, width/4);
				font_1.draw(batch, "Требования по уровню: "+level1, (width / 4) * wpw, (-button_3_y-scroll_y+scaley + width-width/12) * hph);

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
				font_2.draw(batch, days + "." + months + "." + years, (width-width / 4) * wpw, (-button_3_y - scroll_y + width / 7.0f) * hph);
			}
			if (keyboard) {
				drawer.draw(top, width - width / 4, -button_3_y - scroll_y + width / 7.0f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4, -button_3_y - scroll_y + width / 7.0f - 45, 25, 25);
				drawer.draw(top, width - width / 4+35, -button_3_y - scroll_y + width / 7.0f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4+35, -button_3_y - scroll_y + width / 7.0f - 45, 25, 25);
				drawer.draw(top, width - width / 4+70, -button_3_y - scroll_y + width / 7.0f + 5, 25, 25);
				drawer.draw(bottom, width - width / 4+70, -button_3_y - scroll_y + width / 7.0f - 45, 25, 25);
			}
			drawer.draw(back_13, 0, downmenu_y-height/2, width, height/2);
			drawer.draw(dark, 0, downmenu_y-height/2, width, height/2);
			if (downmenu_mode==0) {
				drawer.draw(back_10, width * 0.05f, downmenu_y - height / 40 - 4, width * 0.4f, height / 40);
				drawer.draw(back_14, width * 0.05f + width / 2, downmenu_y - height / 40 - 4, width * 0.4f, height / 40);
			}else{
				drawer.draw(back_14, width * 0.05f, downmenu_y - height / 40 - 4, width * 0.4f, height / 40);
				drawer.draw(back_10, width * 0.05f + width / 2, downmenu_y - height / 40 - 4, width * 0.4f, height / 40);
			}
			font_5.draw(batch, "Выставленные", (width*0.1f) * wpw, (downmenu_y-height/160-4) * hph);
			font_5.draw(batch, "Принятые", (width*0.15f+width/2) * wpw, (downmenu_y-height/160-4) * hph);
			drawer.draw(back_3, 0, downmenu_y, width, height/20);
			if (downmenu) {
				if (downmenu_mode == 1) {
					for (int i = 0; i < 3; i++) {
						if (account_tasks[i] != -1 && backs[account_tasks[i]].state!=3) {
							backs[account_tasks[i]].draw(i, 4 + (height / 2 - downmenu_y) / stepy);
						}
					}
				}
				if (downmenu_mode == 0) {
					int i2 = 0;
					for (int i = 0; i < backq; i++) {
						if (backs[i].account == account_id && backs[i].state!=3) {
							backs[i].draw(i2 % 3, 4 + (int) (i2 / 3) + (height / 2 - downmenu_y) / stepy);
							i2 += 1;
						}
					}
				}
			}
			if (choosed==-1) {
				drawer.draw(back_4, width / 2 - 75 * button_1_s-downmenu_y*5, -75 * button_1_s - button_1_y, 150 * button_1_s, 150 * button_1_s);
				drawer.draw(back_12, width - 100, 0 - button_5_y+downmenu_y, 100, 100);
				font_5.draw(batch, q+"/3", (width - 150) * wpw, (downmenu_y- button_5_y+height/30f) * hph);
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


			drawer.draw(back_1, 0, -profile_y+height, width, height/2);
			drawer.draw(back_1, 0, -profile_y+height+height/2, width, height/2);
			drawer.draw(dark, 0, -profile_y+height, width, height);
			drawer.draw(back_3, 0, -profile_y+height-height/20, width, height/20);
			drawer.draw(back_14, width/4, -profile_y+height+height / 80, width * 0.5f, height / 40);
			font_5.draw(batch, "Закрыть профиль", (width/4+width*0.05f) * wpw, (-profile_y+height+height / 30) * hph);
			drawer.draw(profile, 25, -profile_y+height-55, 50, 50);
			drawer.draw(back_2, -width*0.05f, -profile_y+height+height/4-height/8, width*1.1f, height/2);

			drawer.draw(dark, width/2, -profile_y+height+height/2-height/8, width/2.1f, width/15);
			drawer.draw(dark, width/2, -profile_y+height+height/2-width/10-height/8, width/2.1f, width/15);
			drawer.draw(dark, width/2, -profile_y+height+height/2-width/10-width/10-height/8, width/2.1f, width/15);
			drawer.draw(dark, width/2, -profile_y+height+height/2-width/10-width/10-width/10-height/8, width/2.1f, width/15);
			drawer.draw(dark, width/2, -profile_y+height+height/2-width/10-width/10-width/10-width/10-height/8, width/2.1f, width/15);



			drawer.draw(dio_1, width/2, -profile_y+height+height/2-height/8, width/2.1f*(float)account_exp[0], width/15);
			drawer.draw(dio_2, width/2, -profile_y+height+height/2-width/10-height/8, width/2.1f*(float)account_exp[1], width/15);
			drawer.draw(dio_3, width/2, -profile_y+height+height/2-width/10-width/10-height/8, width/2.1f*(float)account_exp[2], width/15);
			drawer.draw(dio_4, width/2, -profile_y+height+height/2-width/10-width/10-width/10-height/8, width/2.1f*(float)account_exp[3], width/15);
			drawer.draw(dio_5, width/2, -profile_y+height+height/2-width/10-width/10-width/10-width/10-height/8, width/2.1f*(float)account_exp[4], width/15);
			font_2.draw(batch, "Уровни видов деятельности:", (width/16) * wpw, (-profile_y+height+height/2) * hph);
			font_2.draw(batch, "Социальная ", (width/16) * wpw, (-profile_y+height+height/2+width/20-height/8) * hph);
			font_2.draw(batch, "Медицинская ", (width/16) * wpw, (-profile_y+height+height/2-width/10+width/20-height/8) * hph);
			font_2.draw(batch, "Физическая ", (width/16) * wpw, (-profile_y+height+height/2-width/10-width/10+width/20-height/8) * hph);
			font_2.draw(batch, "Интеллектуальная ", (width/16) * wpw, (-profile_y+height+height/2-width/10-width/10-width/10+width/20-height/8) * hph);
			font_2.draw(batch, "Творческая ", (width/16) * wpw, (-profile_y+height+height/2-width/10-width/10-width/10-width/10+width/20-height/8) * hph);


			drawer.draw(back_2, -width*0.05f, -profile_y+height+height/2+height/8, width*1.1f, height/4);
			font_2.draw(batch, "Имя: "+account_name, (width/16) * wpw, (-profile_y+height+height/4*3) * hph);
			drawer.draw(ding, width/4+width/4, -profile_y+height+height/4*3-height/15, width/15, width/15);
			font_2.draw(batch, "Монеты: "+account_money, (width/16f) * wpw, (-profile_y+height+height/4*3-height/20) * hph);
			drawer.draw(letter, 10, -profile_y+height+height/2+height/3.5f, 80, 80);

			if(downmenu_touched){
				drawer.draw(dark, 0, 0, width, height);
				drawer.draw(back_2, -width * (0.5f) + (1 - scale) * width / 2+(1 -button_6_s) * width / 2+ width / 2 - width / 20, (1 - scale) * height+(1 - button_6_s) * height / 2+height / 4, width * 1.1f * scale*button_6_s, (width+scaley) * 1.1f * scale*button_6_s);
				drawer.draw(back_9, width / 2 - 25,  50+height / 4+height / 4*(1-button_6_s), 50, 50);
				if(backs[downmenu_touched_id].doit!=-1) {
					font_2.draw(batch, "Имя исполнителя: " + downmenu_touched_name, (width / 16) * wpw, (height / 2 + height / 8) * hph);
					font_2.draw(batch, "Почта исполнителя: " + downmenu_touched_login, (width / 16) * wpw, (height / 2 + height / 8 - height / 20) * hph);
					font_2.draw(batch, "Оцените качество выполнения задачи.", (width / 16) * wpw, (height / 2 - height / 8) * hph);
					for (int i=0;i<5;i++){
						if(i+1<=downmenu_touched_level) {
							drawer.draw(star, width / 5 * i, height / 2-height/8, width / 5, width / 5);
						}else{
							drawer.draw(star2, width / 5 * i, height / 2-height/8, width / 5, width / 5);
						}
					}
					drawer.draw(back_10, width / 4,  200+height / 4+height / 4*(1-button_6_s)+height/40, width/2, height/20);
					font_4.draw(batch, "Наградить", (width / 4+width/8) * wpw, (200+height / 4+height / 4*(1-button_6_s)+height/40+height/40) * hph);
				}else{
					font_2.draw(batch, "Еще никто не взялся за эту работу.", (width / 16) * wpw, (height / 2 + height / 8) * hph);
				}
			}

			batch.end();
			/*
			shape.begin(ShapeRenderer.ShapeType.Filled);

			float x=width/2;
			float y=-profile_y+height+height/4;
			shape.setColor(0, 0, 0, 1);
			for(int i=0;i<5;i++) {
				shape.rectLine(x/wpw, y/hph, (x+(float)Math.sin(360f/5*i*Math.PI/180)*width/4)/wpw, (y+(float)Math.cos(360f/5*i*Math.PI/180)*width/4)/hph, 2);
			}
			shape.setColor(0.5f, 0, 0, 1);
			shape.rectLine((x+(float)account_exp[0]*(float)Math.sin(360f/5*0*Math.PI/180)*width/4)/wpw, (y+(float)account_exp[0]*(float)Math.cos(360f/5*0*Math.PI/180)*width/4)/hph, (x+(float)account_exp[4]*(float)Math.sin(360f/5*(4)*Math.PI/180)*width/4)/wpw, (y+(float)account_exp[4]*(float)Math.cos(360f/5*(4)*Math.PI/180)*width/4)/hph, 3);
			for(int i=0;i<4;i++) {
				shape.rectLine((x+(float)account_exp[i]*(float)Math.sin(360f/5*i*Math.PI/180)*width/4)/wpw, (y+(float)account_exp[i]*(float)Math.cos(360f/5*i*Math.PI/180)*width/4)/hph, (x+(float)account_exp[i+1]*(float)Math.sin(360f/5*(i+1)*Math.PI/180)*width/4)/wpw, (y+(float)account_exp[i+1]*(float)Math.cos(360f/5*(i+1)*Math.PI/180)*width/4)/hph, 3);
			}
			shape.end();*/
		}
	}
	public void Sleep(int v){
		try {
			Thread.sleep(v);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
