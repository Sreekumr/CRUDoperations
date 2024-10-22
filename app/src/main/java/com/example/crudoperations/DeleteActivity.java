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

public class DeleteActivity extends AppCompatActivity {

    public EditText edDel;
    public Button dBtn;
    public DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edDel=findViewById(R.id.edDel);
        dBtn=findViewById(R.id.dBtn);
        dbHelper=new DbHelper(this);

        dBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delStud();
            }
        });
    }

    public void delStud(){
        int id=Integer.parseInt(edDel.getText().toString());
        int result=dbHelper.deleteStudent(id);

        if(result>0){
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
    }
}