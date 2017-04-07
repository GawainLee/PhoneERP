package com.tapwater.phoneerp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Tapwater on 16-12-14.
 */

public class ConnectServer extends Thread {


    String message;

    public ConnectServer(String message)
    {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("1.16.92.105", 12345);
            //设置10秒之后即认为是超时
            socket.setSoTimeout(10000);
            //发送数据给服务端
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes("UTF-8"));
            socket.shutdownOutput();
            //读取数据
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String line = br.readLine();
            //打印读取到的数据
            Log.e("MainActivity", ">>>>>>>>>>>>>>>>>>>>>>>>>" + line);
            System.out.println(line);
            br.close();
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            Log.e("UnknownHost", "来自服务器的数据");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOException", "来自服务器的数据");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
