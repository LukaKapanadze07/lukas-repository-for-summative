package ge.newton.myauthorisationappnewtonge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "ApplySharedPref"})
    @Override


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button log = findViewById(R.id.button);
        Button reg = findViewById(R.id.registerButton);

        EditText logEmail = findViewById(R.id.logEmailIn);
        EditText logPass = findViewById(R.id.logPasswordIn);

        SharedPreferences info = getApplicationContext().getSharedPreferences("info", 0);
        SharedPreferences.Editor editor = info.edit();

        if (info.getString("example@gmail.com", null) == null) {
            editor.putString("example@gmail.com", "Password#123");
            editor.apply();
        }

        Intent regPage = new Intent(MainActivity.this, registeractivity.class);
        Intent logedin = new Intent(MainActivity.this, logedin.class);

        log.setOnClickListener(view -> {

            String em = logEmail.getText().toString();
            String pass = logPass.getText().toString();

            if (em.isEmpty()) {
                logEmail.setError("Please fill in Email");

            } else if (pass.isEmpty()) {
                logPass.setError("Please fill in Password");

            } else if (!info.contains(em)){

                logEmail.setError("Wrong Email");

            }else if (info.contains(em) ){

                if (info.getString(em, null).equals(pass)){

                    startActivity(logedin);

                } else if (!(info.getString(em, null).equals(pass))) {

                    logPass.setError("Wrong Password");

                }

            } else {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

        reg.setOnClickListener(view -> startActivity (regPage));
    }
}