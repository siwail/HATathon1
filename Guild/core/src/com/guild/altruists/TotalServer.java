package com.guild.altruists;
import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class TotalServer extends Listener {
    static Server server;
    static int udpPort = 27960, tcpPort = 27960;
    static Connection last_connection;
    static int connectionq = 0;
    static boolean[] receiv = new boolean[100];
    static String[] d = new String[100];
    public static void TotalStart() throws Exception {
        for(int i=0;i<100;i++){
            receiv[i] = false;
            d[i] = "";
        }
        Gdx.app.log("TotalServer", "Creating server...");
        server = new Server();
        server.getKryo().register(String[].class);
        server.getKryo().register(int[].class);
        server.getKryo().register(TotalPacket.class);
        server.bind(tcpPort, udpPort);
        server.start();
        server.addListener(new TotalServer());
    }
    public void connected(Connection c){
        //Gdx.app.log("TotalServer", "Was connected: "+c.getRemoteAddressTCP().getHostName());
        receiv[connectionq] = true;
        last_connection = c;
        Thread online = new Thread() {
            @Override
            public void run() {
                int id = connectionq;
                Connection c = last_connection;
                connectionq+=1;
                while (c.isConnected()) {
                    if(receiv[id]) {
                        receiv[id]=false;
                        TotalPacket packet = new TotalPacket();
                        packet.text[0] = d[id];
                        packet.id=id;
                        c.sendTCP(packet);
                        d[id]="";
                    }
                }
            }
        };
        online.start();
    }
    public void received(Connection c, Object p){
        if(p instanceof TotalPacket) {
            TotalPacket packet = (TotalPacket) p;
            TotalDo.Do(packet);
            receiv[packet.id]=true;
        }
    }

    public void disconnected(Connection c){
        //Gdx.app.log("TotalServer", "Was left:"+c.getRemoteAddressTCP().getHostName());
    }
}
