package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        final Button btn_show= findViewById(R.id.btn_AddMyStudent);//Btn_add
        final Button btn_exit=findViewById(R.id.btn_Exit); //exit
        final Button btn_list= findViewById(R.id.list_stud);
        final Button btn_Settings= findViewById(R.id.settings);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a= new Intent(Dashboard.this,RegisterStudent.class);
                startActivity(a);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b= new Intent(Dashboard.this, MainActivity.class );
                startActivity(b);

            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c= new Intent (Dashboard.this, ListOfStudent.class);
                startActivity(c);
                finish();
            }
        });
        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d= new Intent (Dashboard.this, Settings.class);
                startActivity(d);
            }
        });


    }
}
