package com.guild.altruists;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

class TotalClient extends Listener {
    static Client client;
    static String ip = "26.204.188.156";
    static int tcpPort = 27960, udpPort = 27960;
    static boolean connected = false;
    static Connection connnection;
    static int id;
    static Guild game;
    public static void TotalStart(Guild game) throws IOException, InterruptedException {
        TotalClient.game=game;
        Gdx.app.log("TotalServer", "Connecting...");
        client = new Client();
        client.getKryo().register(String[].class);
        client.getKryo().register(int[].class);
        client.getKryo().register(TotalPacket.class);
        client.start();
        client.connect(5000, ip, tcpPort, udpPort);
        client.addListener(new TotalClient());
    }
    public void received(Connection c, Object p){

        if(p instanceof TotalPacket){
            if (!connected) {
                connnection = c;
                TotalPacket packet = (TotalPacket) p;
                id=packet.id;
                Gdx.app.log("TotalServer", "Server talking: " + packet.total);
                connected = true;
            }
            TotalPacket packet = (TotalPacket) p;
            if (!game.file.equals("")) {
                game.rtext = packet.text[0];
                if (game.waiting_server) {
                    if (game.waiting_ticks > 0) {
                        game.result = game.rtext;
                        game.waiting_server = false;
                    }else{
                        game.waiting_ticks++;
                    }
                }
            }
            packet = new TotalPacket();
            packet.id=id;
            if (!game.file.equals("")) {
                game.dfile[0] = game.file;
                game.dt[0] = 2;
            }
            for (int i=0;i<100;i++){
                packet.file[i]=game.dfile[i];
                packet.text[i]=game.dtext[i];
                packet.t[i]=game.dt[i];
                game.dfile[i]="";
                game.dtext[i]="";
                game.dt[i]=-1;
            }

            c.sendTCP(packet);
            game.tickets+=1;
            if(game.tickets>1000){
                game.tickets=0;
            }
        }
    }
}