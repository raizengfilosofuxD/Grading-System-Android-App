package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG="Main2Activity";
    private Button btn_Save;
    private TextView lname, fname,mname,id,general;
    DatabaseHelper mDatabaseHelper;
    private EditText sci, fil, eng, math,soc;
    private String selectedStudID, selLname, selFname, selMname,selEng,selFil,selMath,selSci,selSoc;;
    private int  selectedID;
    private Button btn_AddGrade, btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseHelper= new DatabaseHelper(this);
        setContentView(R.layout.activity_main2);
        id=findViewById(R.id.txt_studID);
        lname=findViewById(R.id.txt_LastName);
        fname=findViewById(R.id.txt_FirstName);
        mname=findViewById(R.id.txt_MiddleName);
        general=findViewById(R.id.et_GenAverage);
        btn_delete=findViewById(R.id.btn_delete);

        //Subjects
        sci=findViewById(R.id.et_SciTech);
        fil=findViewById(R.id.et_Filipino);
        eng=findViewById(R.id.et_English);
        math=findViewById(R.id.et_Math);
        soc=findViewById(R.id.et_SocialScience);
        //Button
        btn_AddGrade=findViewById(R.id.btn_AddGrade001);




        Intent receiveIntent= getIntent();
        selectedID=receiveIntent.getIntExtra("ID",-1);
        selectedStudID= receiveIntent.getStringExtra("studID");
        selLname=receiveIntent.getStringExtra("lname");
        selFname=receiveIntent.getStringExtra("fname");
        selMname=receiveIntent.getStringExtra("mname");
        selEng=receiveIntent.getStringExtra("eng");
        selFil=receiveIntent.getStringExtra("fil");
        selMath=receiveIntent.getStringExtra("math");
        selSci=receiveIntent.getStringExtra("sci");
        selSoc=receiveIntent.getStringExtra("soc");


        //Grades
        sci.setText(selSci);
        fil.setText(selFil);
        eng.setText(selEng);
        math.setText(selMath);
        soc.setText(selSoc);



        lname.setText(selLname);
        mname.setText(selMname);
        fname.setText(selFname);
        id.setText(selectedStudID);







        btn_AddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studid=id.getText().toString();
                String english=eng.getText().toString();
                String filipino= fil.getText().toString();
                String mathematics=math.getText().toString();
                String scitech=sci.getText().toString();
                String social=soc.getText().toString();


                AddTheGrade(studid, english, filipino, mathematics, scitech, social);
                Intent m= new Intent (Main2Activity.this, ListOfStudent.class);
                startActivity(m);
                finish();




            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedStudID);
                Toast.makeText(getApplicationContext(),
                        "THANK YOU LORD the best Ka Talaga", Toast.LENGTH_LONG).show();
                Intent m= new Intent (Main2Activity.this, ListOfStudent.class);
                startActivity(m);
                finish();

            }
        });


    }

    public void AddTheGrade(String studid,String english, String filipino,String mathematics, String scitech, String social){

        boolean a=mDatabaseHelper.updateData(studid, english, filipino, mathematics, scitech, social);
        if(a){
            Toast.makeText(getApplicationContext(),
                    "THANK YOU LORD the best Ka Talaga", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "LORD tulungan mo akong huwag sumuko AMEN",
                    Toast.LENGTH_SHORT).show();

        }


    }



    }


