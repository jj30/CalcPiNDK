LOCAL_PATH := $(call my-dir)
MY_PATH := $(LOCAL_PATH)
include $(call all-subdir-makefiles)
include $(CLEAR_VARS)
LOCAL_PATH := $(MY_PATH)
LOCAL_LDLIBS := -llog
LOCAL_MODULE    := ndk1
LOCAL_CFLAGS := -std=c99 
LOCAL_SRC_FILES := native.c
include $(BUILD_SHARED_LIBRARY)