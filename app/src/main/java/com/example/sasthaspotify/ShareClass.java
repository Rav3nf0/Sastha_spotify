package com.example.sasthaspotify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import com.google.android.material.textfield.TextInputEditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShareClass extends AppCompatActivity {
    TextInputEditText editTextname, editTextcontent;
    Button share;
    private File file;
    private String fileName;
    private String fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_xml);


        editTextname = findViewById(R.id.file_name);
        editTextcontent = findViewById(R.id.content);
        share = findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
                writeFile();
                attachFile();
            }
        });
    }

    public void createFile() {
        fileName = editTextname.getText().toString();
        fileContent = editTextcontent.getText().toString();
        file = new File(getCacheDir(), fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ShareClass.this, "Error creating file", Toast.LENGTH_SHORT).show();
        }
    }

    public void writeFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(fileContent.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ShareClass.this, "Error writing to file", Toast.LENGTH_SHORT).show();
        }
    }

    public void attachFile() {
        Uri uri = FileProvider.getUriForFile(this, "com.example.sasthaspotify.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/msword");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setPackage("com.google.android.gm");
        startActivity(intent);
    }
}

