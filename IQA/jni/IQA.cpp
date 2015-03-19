#include <jni.h>
#include <math.h>
#include <string.h>
#include <stdio.h>
#include <opencv2/opencv.hpp>
#include "highgui.h"
extern "C" {
JNIEXPORT jint JNICALL
Java_org_vipsl_iqa_MainActivity_IQA( JNIEnv* env,
                                                  jobject thiz,jstring capturePath )
{
	    const char* imagename  =(char *)env->GetStringUTFChars(capturePath, 0); //随便放一张jpg图片在D盘或另行设置目录
	    cv::Mat img = cv::imread(imagename);
		//cv::Mat img1 = cv::imread(imagename);
		int rows=img.rows;
		int cols=img.cols;
		cv::Mat img_gray = cv::imread(imagename,0);
		IplImage* image=cvLoadImage(imagename);
		IplImage* image_gray=cvCreateImage(cvGetSize(image),IPL_DEPTH_8U,1);
		env->ReleaseStringUTFChars(capturePath,imagename);
	    //if(img.empty()||img_gray.empty())return -1; //是否加载成功
	    //if(!img.data||!img_gray.data)return -1;
		cvCanny(image,image_gray,10,20);
		cv::Mat img1(image_gray,false);
		int i,j;
		int s,max,blur;
		int blurring;
		blurring=0;
		max=0;
		s=0;
		blur=0;
		for(i=0;i<rows-1;i++)
			for(j=0;j<cols-1;j++)
			{
				s=0;
				max=0;
				if(img1.at<uchar>(i,j)==255&&img1.at<uchar>(i+1,j)==255&&img1.at<uchar>(i,j+1)==255&&img1.at<uchar>(i+1,j+1)==255)
				{
					max=abs(img_gray.at<uchar>(i,j)-img_gray.at<uchar>(i+1,j))/2;
					s=abs(img_gray.at<uchar>(i,j)-img_gray.at<uchar>(i,j+1))/2;
					if(s>max)
					    max=s;
					s=abs(img_gray.at<uchar>(i,j)-img_gray.at<uchar>(i+1,j+1))/3;
					if(s>max)
					    max=s;
				}
			    if(max>blur)
					blur=max;
			}
		//blurring=(blur*100)/255;
		//printf("%d\n",blur);
		//cv::namedWindow("img", CV_WINDOW_AUTOSIZE);
	    //cv::imshow("img",img_gray);
		//cv::waitKey();
	    //return blur;
		//return env->Newinteger(blurring);
		return blur;
}
}
