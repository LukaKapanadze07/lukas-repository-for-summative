package ge.newton.myauthorisationappnewtonge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class logedin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logedin);

        Intent login_screen = new Intent(logedin.this, MainActivity.class);
        Button logOut  = findViewById(R.id.button2);
        logOut.setOnClickListener(view -> startActivity(login_screen));

    }
}