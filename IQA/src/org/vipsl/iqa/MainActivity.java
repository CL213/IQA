package org.vipsl.iqa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	static Bitmap bmp;
	static Bitmap bmpp;
	static Uri imageUri;
	static Bitmap showBitmap;
	static View rootView1;
    static View rootView2 = null;
    static View rootView3;
    static int wwidth;
    static int hheight;
    static int sign=1;
    private Paint mPaint = new Paint();
    private static final int CAMERA_REQUEST = 1888;
	protected static final int SELECT_PICTURE = 0;
    private static String capturePath = null;
    private static String filePath = null;
    static Button returnButton;
    public final static String EXTRA_MESSAGE = "org.vipsl.iqa.MESSAGE";
	
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    static SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    static ViewPager mViewPager;
    ViewPager Pager=null;
    
    private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:{
                    //System.loadLibrary("imgproc");  
                } break;
                default:{
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        WindowManager wm=(WindowManager)getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        wwidth = wm.getDefaultDisplay().getWidth();//手机屏幕的宽度
        hheight = wm.getDefaultDisplay().getHeight();//手机屏幕的高度
    }

    /** Called when the user clicks the Send button */
    public void assess(View view) {
//    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){ ActionBar actionBar = getActionBar(); actionBar.hide();
//
//    	}
    	//double blurring=Blurring(capturePath);
    	int blurring=IQA(capturePath);
//    	Toast toast = Toast.makeText(this, 
//                ""+blurring, 
//                Toast.LENGTH_SHORT);
//toast.show();     
        Intent intent = new Intent(this, assessActivity.class);   
        Bundle bundle = new Bundle();
        bundle.putInt("blurring", blurring);
        intent.putExtras(bundle);
        //intent.putExtra(EXTRA_MESSAGE, "a");
        startActivity(intent);
    }

//	private static double Blurring(String path) {
//		// TODO Auto-generated method stub
//		int i,j;
//		double s,max,blurring;
//		blurring=0;
//		max=0;
//		double[] data = {0};
//		File dir = new File(path);
//		if (!dir.exists()) {
//            //dir.mkdirs();
//        }
//		Mat source_Mat = Highgui.imread(path);
//		Mat contour_Mat = new Mat();
//		Mat cont_Mat = new Mat();
//		Imgproc.cvtColor(source_Mat, contour_Mat, Imgproc.COLOR_RGB2GRAY);
//		Imgproc.Canny(contour_Mat, source_Mat, 50, 150, 3, false);
//		for(i=0;i<source_Mat.rows();i++)
//		{
//			for(j=0;j<source_Mat.cols();j++)
//			{
//				if(source_Mat.col(i).row(j).equals(255))
//					contour_Mat.get(i,j,data);
//			}
//		}
//		for(i=0;i<source_Mat.rows();i++)
//		{
//			for(j=0;j<source_Mat.cols();j++)
//			{
//				cont_Mat.put(i, j, data);
//			}
//		}
//		for(i=0;i<source_Mat.rows();i++)
//		{
//			for(j=0;j<source_Mat.cols();j++)
//			{
//				if(source_Mat.col(i).row(j).equals(255)&&source_Mat.col(i+1).row(j+1).equals(255))
//				{
//					s=0;
//					max=0;
//				    max=(cont_Mat.get(i, j, data)-cont_Mat.get(i+1, j, data))/2;
//				    s=(cont_Mat.get(i, j, data)-cont_Mat.get(i, j+1, data))/2;
//				    if(s>max)
//				    	max=s;
//				    s=(cont_Mat.get(i, j, data)-cont_Mat.get(i+1, j+1, data))/(2*Math.sqrt(2));
//				    if(s>max)
//				    	max=s;
//				}
//				if(max>blurring)
//					blurring=max;
//			}
//		}
//		return blurring;
//	}

	/** Called when the user clicks the Send button */
    public void edgedetection(View view) {
        Intent intent = new Intent(this, edgedetectionActivity.class);
        intent.putExtra("path",capturePath);
        startActivity(intent);
    }
    
    protected void lookOwnBoard() {
	// TODO Auto-generated method stub
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        Button takeButton;
        Button loadButton;
        Button saveButton;
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	View rootView;//= inflater.inflate(R.layout.fragment_main, container, false);                
        	
        	ImageView imageView_photo;
        	
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
			case 1:
				rootView = inflater.inflate(R.layout.fragment1_main, container,
						false);
				rootView1=rootView;           
				takeButton = (Button) rootView1.findViewById(R.id.button_take);
				loadButton = (Button) rootView1.findViewById(R.id.button_load);
            	takeButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {                                                            
                    	if(takeButton.getText().equals("重拍"))
                    	{
                    		File file1 = new File(capturePath);   
    		            	if (file1.exists()) {  
    		                     file1.delete();  
    		                 }
                    	}
                    	sign=1;
                    	getImageFromCamera();
                    	takeButton.setText("重拍");
                    }
                });
            	loadButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                    	sign=2;
//                    	File file1 = new File(capturePath);   
//		            	if (file1.exists()) {  
//		                     file1.delete();  
//		                 }
                    	getImageFromFile(); 
                    }
                }); 
				break;	
		case 2:
				rootView = inflater.inflate(R.layout.fragment_main, container,
						false);
				rootView2=rootView;
				saveButton =  (Button) rootView.findViewById(R.id.button_save);
		        imageView_photo =  (ImageView) rootView.findViewById(R.id.imageView_photo);	       
		        imageView_photo.setLayoutParams(new LinearLayout.LayoutParams(
        				wwidth, (int) (hheight*0.7)));
		        saveButton.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {		            	 		            	    
		            	if(sign==1){
		            		takeButton =  (Button) rootView1.findViewById(R.id.button_take);		            	
		            		takeButton.setText("拍照");
		            		bmp.recycle();
		            		mViewPager.setAdapter(mSectionsPagerAdapter);
		            	}
		            	if(sign==2){
		            		new  AlertDialog.Builder(getActivity())    
		            		                .setTitle("提示" )  
		            		                .setMessage("此图像为导入图像，已存在" )  
		            		                .setPositiveButton("确定" ,  null )  
		            		                .show();  
		            		bmp.recycle();
		            	}
//		            	int blurring=IQA(capturePath);
//		            	Toast toast = Toast.makeText(getActivity(), 
//		            			                ""+blurring, 
//		            			                Toast.LENGTH_SHORT);
//		            			toast.show();        
		            }
		        }); 
		        break;			
//			case 3:
//				rootView = inflater.inflate(R.layout.fragment2_main, container,
//						false);
//				rootView3 = rootView;
//				break;
			default:
				rootView = null;
				break;
			}
            return rootView;
        }
       
        protected void getImageFromCamera() {  
            String state = Environment.getExternalStorageState();
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            String out_file_path = "/sdcard/DCIM/Camera/";
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            capturePath = "/sdcard/DCIM/Camera/" + System.currentTimeMillis() +"abc"+ ".jpg";  
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(capturePath)));
            getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(getImageByCamera, CAMERA_REQUEST);
        }
        
        protected void getImageFromFile() {
        	Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/*");
			startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
        }
        
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
        	super.onActivityResult(requestCode, resultCode, data);
        	if (requestCode == CAMERA_REQUEST) {
        	    ImageView image_photo;
                image_photo=  (ImageView) rootView2.findViewById(R.id.imageView_photo);
        	    BitmapFactory.Options opts = new BitmapFactory.Options();
        	    opts.inSampleSize = 2;
        	    bmpp = BitmapFactory.decodeFile(capturePath); 
        	    bmp = BitmapFactory.decodeFile(capturePath, opts);          
        	    image_photo.setImageBitmap((Bitmap) bmp);
        	}
        	if (requestCode == SELECT_PICTURE) {
            	//选择图片
    			Uri uri = data.getData(); 
    			ContentResolver cr = getActivity().getContentResolver();
        	    ImageView ImageView_picture = (ImageView) rootView2.findViewById(R.id.imageView_photo);
        	    bmp = null;
    			if(bmp != null)
					((Bitmap) bmp).recycle();
				try {
					BitmapFactory.Options opts = new BitmapFactory.Options();
					opts.inSampleSize = 2;
					bmpp = BitmapFactory.decodeStream(cr.openInputStream(uri)); 
					bmp = BitmapFactory.decodeStream(cr.openInputStream(uri),null, opts);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageView_picture.setImageBitmap((Bitmap) bmp);
        	}
        }   
  
    }  
    
    public void onResume(){
        super.onResume();
        //通过OpenCV引擎服务加载并初始化OpenCV类库，所谓OpenCV引擎服务即是  
        //OpenCV_2.4.10_Manager_2.4_*.apk程序包，存在于OpenCV安装包的apk目录中  
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, getApplicationContext(), mLoaderCallback);
    }
    
    
    public native static int  IQA(String capturePath);
    static {
        System.loadLibrary("IQA");
    }
}
