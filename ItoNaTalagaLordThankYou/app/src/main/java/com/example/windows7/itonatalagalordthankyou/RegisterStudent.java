package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterStudent extends AppCompatActivity {
    private static final String TAG="RegisterStudent";
    DatabaseHelper mDatabaseHelper;
    private Button btn_addStudent;
    private EditText id,lname, fname,mname,password,parents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        id= (EditText) findViewById(R.id.et_StudentID);
        lname=(EditText)findViewById(R.id.et_LastName);
        fname=(EditText) findViewById(R.id.et_FirstName);
        mname=(EditText) findViewById(R.id.et_MiddleName);
        password=(EditText) findViewById(R.id.et_Password);
        parents=(EditText) findViewById(R.id.et_Parents);
        btn_addStudent=(Button) findViewById(R.id.btn_addStudent);
        mDatabaseHelper=new DatabaseHelper(this);

        btn_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDs= id.getText().toString();
                int ID= Integer.parseInt(IDs);
                String LName=lname.getText().toString();
                String FName=fname.getText().toString();
                String MName=mname.getText().toString();
                String Pass= password.getText().toString();
                String Parents=parents.getText().toString();
                AddStudent(ID, LName, FName, MName, Pass, Parents);

                Intent a= new Intent (RegisterStudent.this, ListOfStudent.class);
                startActivity(a);
            }
        });




    }
    public void AddStudent(int ID,String LName,String FName,String Mname,String Pass,String Parents ){
        boolean insertStudent= mDatabaseHelper.addStudent(ID, LName, FName, Mname, Pass, Parents);
        if(insertStudent){
            Toast.makeText(getApplicationContext(),
                    "THANK YOU LORD the best Ka Talaga", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "LORD tulungan mo akong huwag sumuko AMEN",
                    Toast.LENGTH_SHORT).show();

        }
    }
}
