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
    public static void TotalStart() throws Exception {
        Gdx.app.log("TotalServer", "Creating server...");
        server = new Server();
        server.getKryo().register(TotalPacket.class);
        server.bind(tcpPort, udpPort);
        server.start();
        server.addListener(new TotalServer());
    }
    public void connected(Connection c){
        Gdx.app.log("TotalServer", "Was connected: "+c.getRemoteAddressTCP().getHostName());
        connectionq+=1;
        last_connection = c;
        Thread online = new Thread() {
            @Override
            public void run() {
                int id = connectionq;
                Connection c = last_connection;
                while (c.isConnected()) {
                    TotalPacket packet = new TotalPacket(id,"server");
                    c.sendTCP(packet);
                }
            }
        };
        online.start();
    }
    public void received(Connection c, Object p){
        if(p instanceof TotalPacket) {
            TotalPacket packet = (TotalPacket) p;
            TotalDo.Do(packet);
        }
    }

    public void disconnected(Connection c){
        Gdx.app.log("TotalServer", "Was left:"+c.getRemoteAddressTCP().getHostName());
    }
}
