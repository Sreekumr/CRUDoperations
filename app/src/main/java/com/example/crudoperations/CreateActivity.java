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

public class CreateActivity extends AppCompatActivity {
    public EditText etName,etAge,etCourse;
    public Button btn;
    public DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etName= findViewById(R.id.etName);
        etAge= findViewById(R.id.etAge);
        etCourse= findViewById(R.id.etCourse);
        btn = findViewById(R.id.btnInsert);
        dbHelper= new DbHelper(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStudent();
            }
        });
    }



    public void createStudent(){
        String name= etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String course= etCourse.getText().toString();

        StudentModel studentModel = new StudentModel(name,age,course);
        dbHelper.insertStudent(studentModel);
        Toast.makeText(this,"Student inserted",Toast.LENGTH_SHORT).show();
        //finish();

    }


}

