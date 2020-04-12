package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String passw;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username= findViewById(R.id.et_Username);
        final EditText password= findViewById(R.id.et_MyPassword);
        final DatabaseHelper db= new DatabaseHelper(this);

        final Button btn_dashboard= findViewById(R.id.btn_dashboard);

        /****/



        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                Cursor data=db.getItemID(user);
                userid="";
                passw="";
                String last_name="";
                    String first_name="";
                    String m_name="";

                    String eng="";
                    String fil="";
                    String math="";
                    String sci="";
                    String soc="";
                while (data.moveToNext()) {
                    userid = data.getString(10);
                    passw = data.getString(9);
                    last_name = data.getString(1);
                    first_name = data.getString(2);
                    m_name = data.getString(3);
                    eng = data.getString(4);
                    fil = data.getString(5);
                    math = data.getString(6);
                    sci = data.getString(7);
                    soc = data.getString(8);
                }


                if(user.equals("admin")&&pass.equals("123")){
                    Intent a= new Intent (MainActivity.this, Dashboard.class);
                    startActivity(a);
                    finish();
                }

                else if(user.equals(userid)&&pass.equals(passw)){

                    Intent toAddGrade= new Intent (MainActivity.this, StudentActivty.class);
                    toAddGrade.putExtra("studID", userid);
                    toAddGrade.putExtra("lname", last_name);
                    toAddGrade.putExtra("fname", first_name);
                    toAddGrade.putExtra("mname", m_name);
                    toAddGrade.putExtra("eng", eng);
                    toAddGrade.putExtra("fil",fil);
                    toAddGrade.putExtra("math",math);
                    toAddGrade.putExtra("sci", sci);
                    toAddGrade.putExtra("soc", soc);
                    startActivity(toAddGrade);
                    finish();

                }
                else if(user.equals("")&&pass.equals("")){
                    Toast.makeText(getApplicationContext(), "LORD tulungan mo akong huwag sumuko AMEN",
                            Toast.LENGTH_SHORT).show();

                }

               /** if((user.equals("ADMIN"))&&(pass.equals("admin123"))){
                Intent a= new Intent (MainActivity.this, Dashboard.class);
                startActivity(a);
                }

                else if ((user.equals(123))&&(pass.equals(passw))){
                    Intent a= new Intent (MainActivity.this, StudentActivty.class);

                }
                else{
                    Toast.makeText(getApplicationContext(), "LORD tulungan mo akong huwag sumuko AMEN",
                            Toast.LENGTH_SHORT).show();

                }**/
            }
        });
    }
}
