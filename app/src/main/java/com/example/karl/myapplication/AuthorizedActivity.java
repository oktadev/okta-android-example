package com.example.karl.myapplication;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;

import com.okta.appauth.android.OktaAppAuth;
import static com.okta.appauth.android.OktaAppAuth.getInstance;
import net.openid.appauth.AuthorizationException;

public class AuthorizedActivity extends Activity {

    private OktaAppAuth mOktaAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		mOktaAuth = getInstance(this);

        setContentView(R.layout.activity_authorized);
        
        Button button = (Button) findViewById(R.id.sign_out);
        button.setOnClickListener(new View.OnClickListener()
			{
			    @Override
			    public void onClick(View v)
			    {
			        mOktaAuth.logout();

			        Intent mainIntent = new Intent(v.getContext(), LoginActivity.class);
			        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        startActivity(mainIntent);
			        finish();
			    }
			}
		);
    }
}
