package com.mm214.sharedexample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mMyButton;
    private Button mShow;
    private String mDataString;
    private String mGetSavedData;
    /** Called when the activity is first created. 
     * 
     * 
*NOTE: the "pref" key and "savedData" key must be the same in onPause and in onCreate. The "default value" from mGetSavedData = preferences.getString("savedData", "default value");
*represents the value to return if the value does not exist.
*/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        mMyButton = (Button)findViewById(R.id.button);
        mShow = (Button)findViewById(R.id.show_saved_data);
 
        //restore the saved data
        SharedPreferences preferences = getSharedPreferences("pref", 0);
        mGetSavedData = preferences.getString("savedData", "default value");
 
        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //in mDataString we put the name of mMyButton when we click it
                mDataString = mMyButton.getText().toString();
            }
        });
 
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, mGetSavedData,1).show();
            }
        });
    }
 
    @Override
    protected void onPause() {
        super.onPause();
 
        //save the data
        SharedPreferences preferences = getSharedPreferences("pref", 0);
        SharedPreferences.Editor editor = preferences.edit();
         
        //"savedData" is the key that we will use in onCreate to get the saved data 
        //mDataString is the string we want to save
        editor.putString("savedData", mDataString);
 
        // commit the edits
        editor.commit();
 
    }

}
