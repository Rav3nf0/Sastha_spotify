package com.example.sasthaspotify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button button, btnlast;
    private TextView textView;
    private FirebaseUser user;
    RecyclerView recyclerView;
    List<Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        recyclerView = findViewById(R.id.recyclerSongs);
        btnlast = findViewById(R.id.options);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerSongsAdapter adapter = new RecyclerSongsAdapter(this, results );
        recyclerView.setAdapter(adapter);
        getSongs();

        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LastPage.class);
                startActivity(intent);
            }
        });
    }

    private void getSongs(){
        Call<DataResponse> apiCall = ApiClient.getInstance().getApis().getSongs();
        apiCall.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NonNull Call<DataResponse> call,@NonNull Response<DataResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Result> results = response.body().getData();
                    if (results != null && !results.isEmpty()) {
                        setAdapter(results);
                        Toast.makeText(MainActivity.this, "Feed Received", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<DataResponse> call,@NonNull Throwable throwable) {
                Log.e("API_FAILURE", "Failed to get data", throwable);
                Toast.makeText(MainActivity.this, "Feed Failed: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setAdapter(List<Result> results){
        RecyclerSongsAdapter adapter = new RecyclerSongsAdapter(this, results);
        recyclerView.setAdapter(adapter);
    }
}