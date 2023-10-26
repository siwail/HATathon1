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
            }else{
                TotalPacket packet = (TotalPacket) p;
                game.d[0] = new TotalMove("pisyapopa.txt", 1);
                packet = new TotalPacket(id, "client", game.d);
                for(int i=0;i<100;i++){
                    game.d[i] = new TotalMove("clear");
                };
                c.sendTCP(packet);
            }
        }
    }
}