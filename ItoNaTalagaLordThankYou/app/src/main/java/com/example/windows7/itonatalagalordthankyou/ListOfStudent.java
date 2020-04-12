package com.example.windows7.itonatalagalordthankyou;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfStudent extends AppCompatActivity {
    private static final String TAG="ListOfStudents";
    DatabaseHelper mDatabaseHelper;
    private ListView studentlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_student);
        studentlist= findViewById(R.id.list_of_Student);
        mDatabaseHelper= new DatabaseHelper(this);
        populateListView();
    }
    private void populateListView(){
        Log.d(TAG,"populateListView: Displaying data in the ListView.");
        Cursor data= mDatabaseHelper.getAllStudent();
        ArrayList<String> listData= new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));

        }
        final ListAdapter adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        studentlist.setAdapter(adapter);

        studentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name= adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on "+name);
                Cursor data= mDatabaseHelper.getItemID(name);
                int itemID= -1;
                String last_name="";
                String first_name="";
                String m_name="";

                String eng="";
                String fil="";
                String math="";
                String sci="";
                String soc="";

                while (data.moveToNext()){
                    itemID=data.getInt(0);
                    last_name=data.getString(1);
                    first_name=data.getString(2);
                    m_name=data.getString(3);
                    eng=data.getString(4);
                    fil=data.getString(5);
                    math=data.getString(6);
                    sci=data.getString(7);
                    soc=data.getString(8);






                }
                if(itemID> -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent toAddGrade= new Intent (ListOfStudent.this, Main2Activity.class);
                    toAddGrade.putExtra("ID",itemID );
                    toAddGrade.putExtra("studID", name);
                    toAddGrade.putExtra("lname", last_name);
                    toAddGrade.putExtra("fname", first_name);
                    toAddGrade.putExtra("mname", m_name);
                    toAddGrade.putExtra("eng", eng);
                    toAddGrade.putExtra("fil",fil);
                    toAddGrade.putExtra("math",math);
                    toAddGrade.putExtra("sci", sci);
                    toAddGrade.putExtra("soc", soc);

                    startActivity(toAddGrade);
                }
                else{
                    Toast.makeText(getApplicationContext(), "LORD tulungan mo akong huwag sumuko AMEN",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
