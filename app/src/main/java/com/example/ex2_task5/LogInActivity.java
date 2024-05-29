package com.example.ex2_task5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogInActivity extends AppCompatActivity{

    protected static final String logger = LogInActivity.class
        .getName();
    boolean invalidInputUn = true;
    private String InputUnValue = null;
    boolean invalidInputPw = true;
    private String InputPwValue = null;

    private TextView textView;
    private TextView textViewPw;
    private TextView textViewAll;
    private EditText editTextUn;
    private EditText editTextPw;
    private Button buttonLogIn;
    private ProgressBar pBar;

    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextUn = findViewById(R.id.editTextUsername);
        editTextPw = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogin);
        textView = findViewById(R.id.textView);
        textViewPw = findViewById(R.id.textViewPw);
        textViewAll = findViewById(R.id.textViewAll);
        pBar = findViewById(R.id.pBar);

        buttonLogIn.setEnabled(false);

      editTextUn.setOnEditorActionListener(this::processEditTextUnEditorAction);

        editTextUn.setOnKeyListener((v, keyCode, event) -> {
            Log.d(logger, "okKey() for editText1: " + keyCode
                + ". KeyEvent is: " + event);
            processTextInputUnChanged();
            return false;
        });

        editTextPw.setOnEditorActionListener(this::processEditTextPwEditorAction);

        editTextPw.setOnKeyListener((v, keyCode, event) -> {
            Log.d(logger, "okKey() for editText1: " + keyCode
                + ". KeyEvent is: " + event);
            processTextInputPwChanged();
            return false;
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pBar.setVisibility(View.VISIBLE);
                mExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        boolean falseInput = InputUnValue.equals("abc@th.de") && InputPwValue.equals("123456");
                        //Background work here
                        try {
                            // sleep and try it again...
                            Thread.sleep(3000);
                        } catch (final Throwable t) {
                            final String err = "got exception while doing work in background: " + t;
                            Log.e(LogInActivity.this.getClass().getName(), err, t);
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (falseInput) {
                                    Log.i(logger, "EMail or Password input is wrong");
                                    textViewAll.setText("Wrong EMail or Password!");
                                } else {
                                    startActivity(new Intent(LogInActivity.this, TodoListActivity.class));
                                }
                                pBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                });
            }
        });
    }
    private boolean processEditTextUnEditorAction(TextView view, int arg1, KeyEvent arg2){
        Log.d(logger, "onEditorAction() for editText1: " + arg1
            + " on textview " + view + ". KeyEvent is: " + arg2);
        if (arg1 == EditorInfo.IME_ACTION_NEXT) {
            // Zugriff auf den eingegebenen Text
            final String text = view.getText().toString();

            processTextInputUn(text);

            return false;
        }
        return false;
    }

    //isValidEmail method-code from stackoverflow
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void processTextInputUn(String text) {
        Log.i(logger, "process textInput2: " + text);

        // if we have an invalid input, we display an error message...
        if (!isValidEmail(text)) {
            Log.i(logger, "text input is invalid");
            textView.setText("Invalid input.");
            invalidInputUn = true;
        } else {
            // if we have a valid input, the ok button is set to enabled
            Log.i(logger, "text input is valid");
            InputUnValue = text;
            invalidInputUn = false;
        }
        updateLoginButtonState();
    }
    private void processTextInputUnChanged() {
        Log.i(logger, "resetting wrong email");
        textViewAll.setText("");
        // as soon as the user starts typing, we reset any existing value for
        // textInput1Value
        if (InputUnValue != null) {
            InputUnValue = null;
        }

        // we also reset the invalid flag
        if (invalidInputUn) {
            resetInvalidUnInputState();
        }
        invalidInputUn = true;
        updateLoginButtonState();
    }
    private void resetInvalidUnInputState() {
        Log.i(logger, "resetting invalid state for text input");
        invalidInputUn = true; //has to be true, else there would be invalid states allowed
        textView.setText("");
    }

    private boolean processEditTextPwEditorAction(TextView view, int arg1, KeyEvent arg2){
        Log.d(logger, "onEditorAction() for editText1: " + arg1
            + " on textview " + view + ". KeyEvent is: " + arg2);
        if (arg1 == EditorInfo.IME_ACTION_DONE) {
            // Zugriff auf den eingegebenen Text
            final String text = view.getText().toString();

            processTextInputPw(text);

            return false;
        }
        return false;
    }

    private void processTextInputPw(String text) {
        Log.i(logger, "process textInput2: " + text);

        // if we have an invalid input, we display an error message...
        if (text.length()!=6) {
            Log.i(logger, "password input is invalid");
            textViewPw.setText("Invalid input, password is not 6 digits long.");
            invalidInputPw = true;
        } else {
            // if we have a valid input, the ok button is set to enabled
            Log.i(logger, "text input is valid");
            InputPwValue = text;
            invalidInputPw = false;
        }
        updateLoginButtonState();
    }
    private void processTextInputPwChanged() {
        Log.i(logger, "resetting wrong password");
        textViewAll.setText("");
        // as soon as the user starts typing, we reset any existing value for
        // textInput1Value
        if (InputPwValue != null) {
            InputPwValue = null;
        }

        // we also reset the invalid flag
        if (invalidInputPw) {
            resetInvalidPwInputState();
        }
        invalidInputPw = true;
        updateLoginButtonState();
    }
    private void resetInvalidPwInputState() {
        Log.i(logger, "resetting invalid state for text input");
        invalidInputPw = true; //has to be true, else there would be invalid states allowed
        textViewPw.setText("");
    }

    private void updateLoginButtonState() {
        buttonLogIn.setEnabled(!invalidInputPw && !invalidInputUn);
        //only works, if the user presses continue on the keyboard, else the button would not get enabled or there would be invalid states allowed
    }
}
