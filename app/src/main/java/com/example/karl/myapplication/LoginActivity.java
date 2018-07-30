package com.example.karl.myapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import net.openid.appauth.AuthorizationException;

import com.okta.appauth.android.OktaAppAuth;

public class LoginActivity extends Activity {

    private OktaAppAuth mOktaAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOktaAuth = OktaAppAuth.getInstance(this);

        setContentView(R.layout.activity_login);

        mOktaAuth.init(
            this,
            new OktaAppAuth.OktaAuthListener() {
                @Override
                public void onSuccess() {
                    // Handle a successful initialization (e.g. display login button)
                    findViewById(R.id.auth_button).setVisibility(View.VISIBLE);
                    findViewById(R.id.progress_bar).setVisibility(View.GONE);
                }

                @Override
                public void onTokenFailure(@NonNull AuthorizationException ex) {
                    // Handle a failed initialization
                }
            }
        );

        Button button = (Button) findViewById(R.id.auth_button);
        button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent completionIntent = new Intent(v.getContext(), AuthorizedActivity.class);
                    Intent cancelIntent = new Intent(v.getContext(), LoginActivity.class);

                    cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    
                    mOktaAuth.login(
                        v.getContext(),
                        PendingIntent.getActivity(v.getContext(), 0, completionIntent, 0),
                        PendingIntent.getActivity(v.getContext(), 0, cancelIntent, 0)
                    );
                }
            }
        );
    }
}
