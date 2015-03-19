LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

OPENCV_CAMERA_MODULES:=off
OPENCV_INSTALL_MODULES:=off
OPENCV_LIB_TYPE:=STATIC
ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include D:\opencv4\OpenCV-2.4.9-android-sdk\sdk\native\jni\OpenCV.mk
else  
include $(OPENCV_MK_PATH)  
endif



LOCAL_MODULE    := IQA

LOCAL_C_INCLUDES += $(LOCAL_PATH)
LOCAL_LDLIBS     += -llog -ldl

LOCAL_SRC_FILES := IQA.cpp

include $(BUILD_SHARED_LIBRARY)
