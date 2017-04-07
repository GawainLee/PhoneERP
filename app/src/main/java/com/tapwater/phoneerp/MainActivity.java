package com.tapwater.phoneerp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    Button button;
    private EditText editTextUserName,editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_login);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        button = (Button) findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button");
                ConnectServer connectServer = new ConnectServer(editTextUserName.getText().toString());
                connectServer.start();
                Intent intent = new Intent(MainActivity.this,mainInterface.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName",editTextUserName.getText().toString());
                bundle.putString("password",editTextPassword.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
