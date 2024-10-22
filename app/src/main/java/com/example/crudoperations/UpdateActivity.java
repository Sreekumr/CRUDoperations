package com.example.crudoperations;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    public EditText edt1,edt2,edt3,edt4;
    public Button ubtn;
    public DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        ubtn=findViewById(R.id.ubtn);
        dbHelper=new DbHelper(this);

        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatestud();
            }
        });


    }


    public void updatestud(){
        int id=Integer.parseInt(edt1.getText().toString());
        String name=edt2.getText().toString();
        int age=Integer.parseInt(edt3.getText().toString());
        String course=edt4.getText().toString();

        StudentModel studentModel=new StudentModel(name,age,course);
        studentModel.setId(id);
        int result=dbHelper.updateStudent(studentModel);

        if(result>0){
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "ID not found!", Toast.LENGTH_SHORT).show();
        }
    }
}