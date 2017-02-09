package com.example.godoctor.myapplication1.RetroFit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.godoctor.myapplication1.R;

public class RetroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro);

       LinearLayout layout= (LinearLayout) findViewById(R.id.activity_retro);
        String data="I just watched #StarWars and it was incredible. It's a #MustWatch #StarWars";
        TextView textView;
        String [] s=data.split(" ");
        for(int i=0;i<s.length;i++){
//            if(s[i].matches("#([A-Za-z0-9_-]+)")){

                textView=new TextView(this);
                textView.setText(s[i]+" ");
                textView.setTextColor(Color.parseColor("#000763"));
                textView.setTag(s[i]);
                textView.setOnClickListener(viewClicked(textView));
//            }else{
//                textView=new TextView(this);
//                textView.setText(" "+s[i]);
//            }
            layout.addView(textView,i);
        }
//        TextView tv= (TextView) findViewById(R.id.text);
//        tv.setText("Hey This is Piyush.");
    }
    View.OnClickListener viewClicked(final TextView textView)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();

            }
        };
    }
}
    