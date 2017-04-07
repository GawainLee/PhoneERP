package com.tapwater.phoneerp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Tapwater on 16-12-13.
 */

public class LogButton extends Button
{

    private String message = "";
    private String clickTime = "";
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

    public LogButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public LogButton(Context context) {
        super(context);
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String time = df.format(System.currentTimeMillis());
        if (clickTime.equals(time))
        {
            System.out.println("Button over click");
        }
        else
        {
            clickTime = time;
            System.out.println("Touch Event"+ clickTime);
            ConnectServer connectServer = new ConnectServer(message);
            connectServer.start();
        }
        return super.onTouchEvent(event);
    }
}
