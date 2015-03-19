package org.vipsl.iqa;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class edgedetectionActivity extends ActionBarActivity {
    
	static Intent intent;
	static int wwidth;
    static int hheight;
	Bundle bundle;
	Bitmap ImgSrc1;
	Bitmap ImgSrc;
	Bitmap edgeImg;
	String ImgPath;
	static View rootView;
	ImageView image;
	
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
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() { }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              rootView = inflater.inflate(R.layout.fragment3_main,
                      container, false);
              return rootView;
        }
    }
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, new PlaceholderFragment()).commit();
//        }
        WindowManager wm=(WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        wwidth = wm.getDefaultDisplay().getWidth();//手机屏幕的宽度
        hheight = wm.getDefaultDisplay().getHeight();//手机屏幕的高度
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.fragment3_main);
        final LinearLayout layout2 = new LinearLayout(this);
        layout2.setOrientation(LinearLayout.VERTICAL);
        layout2.setBackgroundColor( 0xff00ffff );
        setContentView(layout2);
        intent = getIntent();
        ImageView image=new ImageView(this);
        image.setLayoutParams(new LinearLayout.LayoutParams(
				wwidth,hheight));
        if(intent!=null)
        {
//        	Uri uri = intent.getData();
//        	ContentResolver cr = this.getContentResolver();
//        	try {
//				bmp1 = BitmapFactory.decodeStream(cr.openInputStream(uri));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        	ImgPath=intent.getStringExtra("path");
            BitmapFactory.Options opts = new BitmapFactory.Options();
    	    opts.inSampleSize = 2;
            ImgSrc1 = BitmapFactory.decodeFile(ImgPath);
            ImgSrc = BitmapFactory.decodeFile(ImgPath, opts);
    	    Mat sourceMat = new Mat();
    	    Mat contourMat = new Mat();
    	    Utils.bitmapToMat(ImgSrc, sourceMat);
    	    Imgproc.cvtColor(sourceMat, contourMat, Imgproc.COLOR_RGB2GRAY);
    	    //Imgproc.Canny(contourMat, sourceMat, 5, 1);
    	    Imgproc.Canny(contourMat, sourceMat, 50, 150, 3, false);
    	    edgeImg = Bitmap.createBitmap(ImgSrc.getWidth(), ImgSrc.getHeight(), Config.ARGB_8888);
    	    Utils.matToBitmap(sourceMat, edgeImg);
            image.setImageBitmap(edgeImg);
        }
		layout2.addView(image);
		setContentView(layout2);
    }
    
}
