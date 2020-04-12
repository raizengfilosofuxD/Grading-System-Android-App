package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowStudentInfo extends AppCompatActivity {

    private static final String TAG="ShowStudentInfo";
    DatabaseHelper mDatabaseHelper;
    private Button btn_upStudent;
    private EditText id,lname, fname,mname,password,parents;
    private String selectedStudID, selLname, selFname, selMname,selPass,selParents;;
    private int  selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_info);

        id= (EditText) findViewById(R.id.et_StudentID);
        lname=(EditText)findViewById(R.id.et_LastName);
        fname=(EditText) findViewById(R.id.et_FirstName);
        mname=(EditText) findViewById(R.id.et_MiddleName);
        password=(EditText) findViewById(R.id.et_Password);
        parents=(EditText) findViewById(R.id.et_Parents);
        btn_upStudent=(Button) findViewById(R.id.btn_update);
        mDatabaseHelper=new DatabaseHelper(this);

        Intent receiveIntent= getIntent();
        selectedID=receiveIntent.getIntExtra("ID",-1);
        selectedStudID= receiveIntent.getStringExtra("studID");
        selLname=receiveIntent.getStringExtra("lname");
        selFname=receiveIntent.getStringExtra("fname");
        selMname=receiveIntent.getStringExtra("mname");
        selPass=receiveIntent.getStringExtra("password");
        selParents=receiveIntent.getStringExtra("guardian");

        id.setText(selectedStudID);
        lname.setText(selLname);
        fname.setText(selFname);
        mname.setText(selMname);
        password.setText(selPass);
        parents.setText(selParents);

        btn_upStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studid=id.getText().toString();
                String last=lname.getText().toString();
                String first=fname.getText().toString();
                String middle=mname.getText().toString();
                String passs=password.getText().toString();
                String par= parents.getText().toString();

                UpdateStudent(studid, last,first,middle,passs,par);




            }
        });


    }
    public void UpdateStudent(String studid, String last,String first,
                              String middle,String passs,String par){
        boolean a=mDatabaseHelper.updateStudent(studid, last,first,middle,passs,par);
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
