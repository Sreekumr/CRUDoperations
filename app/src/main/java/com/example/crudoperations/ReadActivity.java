package com.example.crudoperations;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    public TextView tvstd;
    public DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvstd=findViewById(R.id.tvstd);
        dbhelper=new DbHelper(this);

        readstud();

    }

    public void readstud(){
        List<StudentModel> studentModelList= dbhelper.readStudents();
        StringBuilder builder=new StringBuilder();

        for(StudentModel studentModel : studentModelList){
            builder.append("ID:" ).append(studentModel.getId())
                    .append("Name: ").append(studentModel.getName())
                    .append("Age: ").append(studentModel.getAge())
                    .append("Course: ").append(studentModel.getCourse())
                    .append("\n\n");
        }
        tvstd.setText(builder.toString());
    }
}