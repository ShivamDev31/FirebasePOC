package com.shivamdev.firebasepoc;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shivamdev.firebasepoc.commons.BaseActivity;
import com.shivamdev.firebasepoc.commons.Logger;
import com.shivamdev.firebasepoc.fragments.UserProfileFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    // UI Stuff
    private EditText etUsername;
    private EditText etPassword;
    private Button bSignup;
    private Button bSignin;
    private ProgressDialog pdDialog;

    // Firebase stuff
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar(getString(R.string.app_name), false);
        firebaseAuth = FirebaseAuth.getInstance();
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        bSignup = (Button) findViewById(R.id.b_signup);
        bSignin = (Button) findViewById(R.id.b_signin);
        pdDialog = new ProgressDialog(this);
        bSignup.setOnClickListener(this);
        bSignin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.b_signup:
                registerUser();
                break;
            case R.id.b_signin:
                signinUser();
                break;
        }
    }

    private void signinUser() {

    }

    private void registerUser() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Logger.toast(this, getString(R.string.enter_username_error));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Logger.toast(this, getString(R.string.enter_password_error));
            return;
        }

        pdDialog.setTitle(getString(R.string.registering_user));
        pdDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(username, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Logger.toast(MainActivity.this, getString(R.string.registration_successful));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    UserProfileFragment profileFragment = UserProfileFragment.newInstance();
                    FragmentTransaction fragmentTransaction = fragmentManager
                            .beginTransaction().add(profileFragment, TAG);
                    fragmentTransaction.addToBackStack(null).commit();
                } else {
                    Logger.snackBar(getWindow().getDecorView().getRootView(), getString(R.string.registration_failed));
                }
                pdDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}