package org.vipsl.iqa;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class assessActivity extends ActionBarActivity {
    
	static Intent intent;
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
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        public PlaceholderFragment() { }
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                  Bundle savedInstanceState) {
//              View rootView = inflater.inflate(R.layout.fragment2_main,
//                      container, false);
//              return rootView;
//        }
//    }
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, new PlaceholderFragment()).commit();
//        }
        intent = getIntent();
        Bundle bundle = this.getIntent().getExtras();
        int blurring = bundle.getInt("blurring");
        blurring=100-blurring;
        String blur=Integer.toString(blurring);
//        textView.setText(message);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //TextView textView_blurring=(TextView) findViewById(R.id.text_blurring_present);
        LinearLayout mLayout = new LinearLayout(this);
        TextView textView_blurring= new TextView(this);
        textView_blurring.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
        		LayoutParams.WRAP_CONTENT));
        textView_blurring.setText(blur+'%');
        textView_blurring.setBackgroundColor(Color.GREEN);
		//textView_blurring.setWidth(100);
		//textView_blurring.setHeight(30);
		textView_blurring.setPadding(1, 0, 0, 0);//left, top, right, bottom
		mLayout.addView(textView_blurring);
		setContentView(mLayout);  
		//setContentView(textView_blurring);
//		setContentView(R.layout.fragment2_main);
    }
    
}
