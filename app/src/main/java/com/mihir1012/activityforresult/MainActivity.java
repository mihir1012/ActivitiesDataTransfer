package com.mihir1012.activityforresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements DialogActivity.DialogListener {
    TextView textView1;
    TextView tx;
    Button but;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=(TextView)findViewById(R.id.Text1);
        button1=(Button)findViewById(R.id.ClickIt1);
        tx = findViewById(R.id.textToChange);
        but = findViewById(R.id.But);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openDialog();
            }
        });
    }

    private  void openDialog(){
        DialogActivity dialogActivity = new DialogActivity();
        dialogActivity.show(getSupportFragmentManager(),"dialog");
    }
    public void Clicked(View view){
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            textView1.setText(message);
        }
    }

    @Override
    public void applyText(String name) {
            tx.setText(name);
    }
}
