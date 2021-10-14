#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <jni.h>
#include <android/log.h>
#include "EGL/egl.h"
#include "xhook.h"

EGLBoolean (*eglSetDamageRegionKHR)(EGLDisplay dpy,
                                    EGLSurface surface,
                                    EGLint *rects,
                                    EGLint n_rects);

EGLBoolean eglSetDamageRegionKHRWorkaround(
        EGLDisplay dpy,
        EGLSurface surface,
        EGLint *rects,
        EGLint n_rects) {
    bool ret = eglSetDamageRegionKHR(dpy, surface, rects, n_rects);
    if (ret)
        return ret;
    else {
        __android_log_print(ANDROID_LOG_ERROR, "hook", "hook eglSetDamageRegionKHR");
        return true;
    }
}

extern "C" void
Java_eightbitlab_renderthreadcrash_MainActivity_fixSamsungHwuiCrash(JNIEnv *env, jclass obj) {
    (void) env;
    (void) obj;
    int ret = xhook_register(".*/libhwui\\.so$", "eglSetDamageRegionKHR",
                             (void *) eglSetDamageRegionKHRWorkaround,
                             (void **) &eglSetDamageRegionKHR);
    __android_log_print(ANDROID_LOG_ERROR, "hook", "hook eglSetDamageRegionKHR %d", ret);
}
