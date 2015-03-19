package org.vipsl.iqa;

import java.io.File;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.highgui.*;


public class Blurring {

	public static double main(String path) {
		// TODO Auto-generated method stub
		int i,j;
		double s,max,blurring;
		blurring=0;
		max=0;
		double[] data = {0};
		File dir = new File(path);
		if (!dir.exists()) {
            //dir.mkdirs();
        }
		Mat source_Mat = Highgui.imread(path);
		Mat contour_Mat = new Mat();
		Mat cont_Mat = new Mat();
		Imgproc.cvtColor(source_Mat, contour_Mat, Imgproc.COLOR_RGB2GRAY);
		Imgproc.Canny(contour_Mat, source_Mat, 50, 150, 3, false);
		for(i=0;i<source_Mat.rows();i++)
		{
			for(j=0;j<source_Mat.cols();j++)
			{
				if(source_Mat.col(i).row(j).equals(255))
					contour_Mat.get(i,j,data);
			}
		}
		for(i=0;i<source_Mat.rows();i++)
		{
			for(j=0;j<source_Mat.cols();j++)
			{
				cont_Mat.put(i, j, data);
			}
		}
		for(i=0;i<source_Mat.rows();i++)
		{
			for(j=0;j<source_Mat.cols();j++)
			{
				if(source_Mat.col(i).row(j).equals(255)&&source_Mat.col(i+1).row(j+1).equals(255))
				{
					s=0;
					max=0;
				    max=(cont_Mat.get(i, j, data)-cont_Mat.get(i+1, j, data))/2;
				    s=(cont_Mat.get(i, j, data)-cont_Mat.get(i, j+1, data))/2;
				    if(s>max)
				    	max=s;
				    s=(cont_Mat.get(i, j, data)-cont_Mat.get(i+1, j+1, data))/(2*Math.sqrt(2));
				    if(s>max)
				    	max=s;
				}
				if(max>blurring)
					blurring=max;
			}
		}
		return blurring;
	}

}
