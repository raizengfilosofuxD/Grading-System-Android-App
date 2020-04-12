package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StudentActivty extends AppCompatActivity {
    private static final String TAG="StudentActivity";
    private Button btn_Save;
    private TextView lname, fname,mname,id,sci, fil, eng, math,soc,general;
    DatabaseHelper mDatabaseHelper;
    //private EditText sci, fil, eng, math,soc,general;
    private String selectedStudID, selLname, selFname, selMname,selEng,selFil,selMath,selSci,selSoc;;
    private int  selectedID;
    private Button btn_AddGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_activty);

        mDatabaseHelper= new DatabaseHelper(this);
        setContentView(R.layout.activity_student_activty);
        id=findViewById(R.id.txt_studID1);
        lname=findViewById(R.id.txt_LastName1);
        fname=findViewById(R.id.txt_FirstName1);
        mname=findViewById(R.id.txt_MiddleName1);

        //Subjects
        sci=findViewById(R.id.et_SciTech1);
        fil=findViewById(R.id.et_Filipino1);
        eng=findViewById(R.id.et_English1);
        math=findViewById(R.id.et_Math1);
        soc=findViewById(R.id.et_SocialScience1);
        //Button
        general=findViewById(R.id.et_GenAverage);





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

       int sc= Integer.parseInt(sci.getText().toString());
        int m=Integer.parseInt(math.getText().toString());
        int engs=Integer.parseInt(eng.getText().toString());
        int fils=Integer.parseInt(fil.getText().toString());
        int socs=Integer.parseInt(soc.getText().toString());
        int sum= sc+m+engs+fils+socs;
        long gen= sum/5;


        general.setText(""+gen);

    }
}
