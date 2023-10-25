package com.guild.altruists;

public class Back {
    Guild g;
    String task;
    int year;
    int month;
    int day;
    int hour;
    int index=0;
    int x = 0;
    int y = 0;
    int state=3;
    Back(Guild game, String task, int x, int y, int year, int month, int day, int hour, int state){
        g=game;
        this.x=x;
        this.y=y;
        this.task = task;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.state=state;
    }
    public void draw(){
        g.drawer.draw(g.back_2, g.stepx/(g.backn*3)+x* g.stepx, -g.cy+g.height-g.stepy-y*g.stepy, g.stepx, g.stepy);
        g.font_1.draw(g.batch, task, (g.stepx/(g.backn*3)+x* g.stepx+g.stepx/4) * g.wpw, (-g.cy+g.height-y*g.stepy-g.stepy/4) * g.hph);
    }
}
