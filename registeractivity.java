package ge.newton.myauthorisationappnewtonge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registeractivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Intent login = new Intent(registeractivity.this, MainActivity.class);

        Button create = findViewById(R.id.createAccount);

        EditText emReg = findViewById(R.id.regPassIn);
        EditText pasReg = findViewById(R.id.regPassIn);

        SharedPreferences info = getApplicationContext().getSharedPreferences("info", 0);
        SharedPreferences.Editor editor = info.edit();

        create.setOnClickListener( view -> {
            String email = emReg.getText().toString();
            String pass = pasReg.getText().toString();

            if (email.isEmpty()) {

                emReg.setError("Please fill in Email");

            } else if (pass.isEmpty()) {

                pasReg.setError("Please fill in Password");

            } else if (info.contains(email)) {

                emReg.setError("Email already in use");

            } else if (!valEm(email)) {

                emReg.setError("Non-valid email");

            } else if (!valPas(pass)) {

                pasReg.setError("password must be:\n8 to 20 characters.\none digit.\nupper case letter.\n" +
                        "lower case letter\ndoesn't contain white space");

            } else{

                editor.putString(email, pass);
                editor.apply();
                startActivity(login);
                Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();

            }
        });

        Button loginPage = findViewById(R.id.goBack);

        loginPage.setOnClickListener(view -> startActivity (login));




    }

    public static boolean
    valEm(String email)
    {

        String regex = "^(.+)@(.+)$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(email);

        return m.matches();

    }

    public static boolean
    valPas(String password)
    {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(password);

        return m.matches();

    }

}