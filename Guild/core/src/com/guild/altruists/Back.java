package com.guild.altruists;
//Класс, отвечающий за листы с задачами
public class Back {
    Guild g;
    String task;
    String bigtext;
    int year;
    int month;
    int day;
    int hour;
    int index=0;
    int x = 0;
    int y = 0;
    int s = 1;
    int state=3;
    int account = 0;
    int category=0;
    int level=1;
    int doit=-1;
    Back(Guild game, String task, int x, int y, int year, int month, int day, int hour, int state){
        g=game;
        this.x=x;
        this.y=y;
        this.task = task;
        bigtext="";
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.state=state;
    }
    public void draw(){//Отрисовка листов
        g.drawer.draw(g.back_2, g.stepx/(g.backn*3)+x* g.stepx, -g.cy+g.height-g.stepy-y*g.stepy, g.stepx, g.stepy);
        g.drawer.draw(g.dark, g.stepx / (g.backn * 3) + x * g.stepx-2, -g.cy + g.height - g.stepy - y * g.stepy-2, g.stepx / 10+4, g.stepy / 10+4);

        if(category==0) {
            g.drawer.draw(g.dio_1, g.stepx / (g.backn * 3) + x * g.stepx, -g.cy + g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==1) {
            g.drawer.draw(g.dio_2, g.stepx / (g.backn * 3) + x * g.stepx, -g.cy + g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==2) {
            g.drawer.draw(g.dio_3, g.stepx / (g.backn * 3) + x * g.stepx, -g.cy + g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==3) {
            g.drawer.draw(g.dio_4, g.stepx / (g.backn * 3) + x * g.stepx, -g.cy + g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==4) {
            g.drawer.draw(g.dio_5, g.stepx / (g.backn * 3) + x * g.stepx, -g.cy + g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        String text1 = task.substring(0, task.length());
        String text2 = "";
        String text3 = "";
        String text4 = "";
        if(task.length()>=20&&task.length()<40){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, task.length());
        }
        if(task.length()>=40&&task.length()<60){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, 40);
            text3 = task.substring(40, task.length());
        }
        if(task.length()>=60&&task.length()<=80){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, 40);
            text3 = task.substring(40, 60);
            text4 = task.substring(60, task.length());
        }
        if (g.account_id==account) {
            g.font_7.draw(g.batch, "Ваша заявка!", (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy/8) * g.hph);
        }
        if (task.length()<=10) {
            g.font_1.draw(g.batch, task, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy / 4) * g.hph);
        }else{
            g.font_3.draw(g.batch, text1, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy / 4) * g.hph);
            g.font_3.draw(g.batch, text2, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy / 4 - g.stepy / 8) * g.hph);
            g.font_3.draw(g.batch, text3, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy / 4 - g.stepy / 8 - g.stepy / 8) * g.hph);
            g.font_3.draw(g.batch, text4, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, (-g.cy + g.height - y * g.stepy - g.stepy / 4  - g.stepy / 8 - g.stepy / 8 - g.stepy / 8) * g.hph);

        }
        g.font_1.draw(g.batch, "До "+day+"."+month+"."+year, (g.stepx/(g.backn*3)+x* g.stepx+g.stepx/8) * g.wpw, (-g.cy+g.height-y*g.stepy-g.stepy/4*3) * g.hph);
    }
    public void draw(float x, float y){//Отрисовка листов
        g.drawer.draw(g.back_2, g.stepx/(g.backn*3)+x* g.stepx, g.height-g.stepy-y*g.stepy, g.stepx, g.stepy);
        g.drawer.draw(g.dark, g.stepx / (g.backn * 3) + x * g.stepx-2,  g.height - g.stepy - y * g.stepy-2, g.stepx / 10+4, g.stepy / 10+4);
        if(category==0) {
            g.drawer.draw(g.dio_1, g.stepx / (g.backn * 3) + x * g.stepx,  g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==1) {
            g.drawer.draw(g.dio_2, g.stepx / (g.backn * 3) + x * g.stepx,  g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==2) {
            g.drawer.draw(g.dio_3, g.stepx / (g.backn * 3) + x * g.stepx,  g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==3) {
            g.drawer.draw(g.dio_4, g.stepx / (g.backn * 3) + x * g.stepx,  g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        if(category==4) {
            g.drawer.draw(g.dio_5, g.stepx / (g.backn * 3) + x * g.stepx,  g.height - g.stepy - y * g.stepy, g.stepx / 10, g.stepy / 10);
        }
        String text1 = task.substring(0, task.length());
        String text2 = "";
        String text3 = "";
        String text4 = "";
        if(task.length()>=20&&task.length()<40){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, task.length());
        }
        if(task.length()>=40&&task.length()<60){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, 40);
            text3 = task.substring(40, task.length());
        }
        if(task.length()>=60&&task.length()<=80){
            text1 = task.substring(0, 20);
            text2 = task.substring(20, 40);
            text3 = task.substring(40, 60);
            text4 = task.substring(60, task.length());
        }
        if (g.account_id==account) {
            g.font_7.draw(g.batch, "Ваша заявка!", (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy/8) * g.hph);
        }
        if (task.length()<=10) {
            g.font_1.draw(g.batch, task, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy / 4) * g.hph);
        }else{
            g.font_3.draw(g.batch, text1, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy / 4) * g.hph);
            g.font_3.draw(g.batch, text2, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy / 4 - g.stepy / 8) * g.hph);
            g.font_3.draw(g.batch, text3, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy / 4 - g.stepy / 8 - g.stepy / 8) * g.hph);
            g.font_3.draw(g.batch, text4, (g.stepx / (g.backn * 3) + x * g.stepx + g.stepx / 8) * g.wpw, ( + g.height - y * g.stepy - g.stepy / 4  - g.stepy / 8 - g.stepy / 8 - g.stepy / 8) * g.hph);

        }
        g.font_1.draw(g.batch, "До "+day+"."+month+"."+year, (g.stepx/(g.backn*3)+x* g.stepx+g.stepx/8) * g.wpw, (+g.height-y*g.stepy-g.stepy/4*3) * g.hph);
    }
}
