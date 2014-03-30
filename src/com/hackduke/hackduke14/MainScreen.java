package com.hackduke.hackduke14;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

public class MainScreen extends ActionBarActivity {

    private static final int BUTTON_POSITIVE = -1;
    final Context context = this;
    public SpeechRecognizer speech;
    public Intent recognitionIntent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        // This is my own code:
        initSpeech();
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
    
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN) public void initSpeech() {
    	speech = SpeechRecognizer.createSpeechRecognizer(context);
    	recognitionIntent  = new Intent(RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE);
        recognitionIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        speech.setRecognitionListener(new RecognitionListener() {
        	@Override
        	public void onResults(Bundle results) {
        		//showMessage("results");
        		
        	}
        	@Override
        	public void onEndOfSpeech() {
        		showMessage("End of Speech");
        	}
			@Override
			public void onBeginningOfSpeech() {
				//showMessage("Start of Speech");
			}
			@Override
			public void onBufferReceived(byte[] buffer) {
				//showMessage("buffer received");
				
			}
			@Override
			public void onError(int error) {
				//showMessage(Integer.toString(error));
				
			}
			@Override
			public void onEvent(int eventType, Bundle params) {
				//showMessage("onevent");
				
			}
			@Override
			public void onPartialResults(Bundle partialResults) {
				ArrayList<String> something = partialResults.getStringArrayList("results_recognition");
				TextView basicText = (TextView)findViewById(R.id.basicText1);
				for (int i=0;i<something.size();i++) {
        			//showMessage(something.get(i));
        			
        			basicText.append(something.get(i));
        			basicText.append(" ");
        		}
				if (something.size() > 0) {
					basicText.setText(something.get(0));
				}
			}
			@Override
			public void onReadyForSpeech(Bundle params) {
				//showMessage("ready for speech");
				
			}
			@Override
			public void onRmsChanged(float rmsdB) {
				//showMessage("rms changed");
				
			}
        });
    }
    
    public void onSwitchCCClicked(View view) {
        if(((ToggleButton) view).isChecked()) {
            speech.startListening(recognitionIntent);
        } else {
           speech.stopListening();
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
