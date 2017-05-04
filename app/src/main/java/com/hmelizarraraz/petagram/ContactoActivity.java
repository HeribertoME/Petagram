package com.hmelizarraraz.petagram;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hmelizarraraz.petagram.mail.Mail;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class ContactoActivity extends AppCompatActivity {

    private EditText etFrom;
    private EditText etSubject;
    private EditText etMessage;

    // TODO: Change values
    public static final String USER = "";
    public static final String PASS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Button button = (Button) findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        etFrom = (EditText) findViewById(R.id.etFrom);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etMessage = (EditText) findViewById(R.id.etMessage);
    }

    private void sendMessage() {
        String[] recipients = {USER};
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail(USER, PASS);
        email.m.set_from(etFrom.getText().toString());
        email.m.set_body(etMessage.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject(etSubject.getText().toString());
        email.execute();
    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.btnSend), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Ir al inicio", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).show();
    }

}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    ContactoActivity activity;

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                activity.displayMessage("Correo enviado");
            } else {
                activity.displayMessage("Falló el envio del correo");
            }

            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Configuración incorrecta de email");
            e.printStackTrace();
            activity.displayMessage("Falló la autenticación");
            return false;
        } catch (MessagingException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Falló de email");
            e.printStackTrace();
            activity.displayMessage("Falló el envio del correo");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            activity.displayMessage("Ocurrió un error inesperado");
            return false;
        }
    }
}
