package com.hackduke.hackduke14;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

public class MainScreen extends ActionBarActivity {

    private static final int BUTTON_POSITIVE = -1;
    final Context context = this;
 

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    
    
    public SpeechRecognizer initSpeech() {
    	SpeechRecognizer speech = SpeechRecognizer.createSpeechRecognizer(context);
        speech.setRecognitionListener(new RecognitionListener() {
        	@Override
        	public void onResults(Bundle results) {
        		
        	}
        	@Override
        	public void onEndOfSpeech() {
        		showMessage("End of Speech");
        	}
			@Override
			public void onBeginningOfSpeech() {
				// TODO Auto-generated method stub
				showMessage("Start of Speech");
			}
			@Override
			public void onBufferReceived(byte[] buffer) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onError(int error) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onEvent(int eventType, Bundle params) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPartialResults(Bundle partialResults) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onReadyForSpeech(Bundle params) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onRmsChanged(float rmsdB) {
				// TODO Auto-generated method stub
				
			}
        });
        return speech;
    }
    
    public void onSwitchCCClicked(View view) {
        if(((ToggleButton) view).isChecked()) {
            
        } else {
           showMessage("Switched Off");
        }  
    }
    public void showMessage(String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		
		// Setting Dialog Title
		alertDialog.setTitle("Alert Dialog");
		
		// Setting Dialog Message
		alertDialog.setMessage(message);
		
		// Setting Icon to Dialog
		//alertDialog.setIcon(R.drawable.tick);
		
		// Setting OK Button
		alertDialog.setButton(BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});
		
		// Showing Alert Message
		alertDialog.show();
    }
    
    public SpeechRecognizer speech = initSpeech();
    
    
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
            return rootView;
        }
    }

}
