# RenderThreadCrash
Showcase of a crash in libhwui.so on Samsung devices.
https://issuetracker.google.com/issues/110061526
https://developer.samsung.com/forum/board/thread/view.do?boardName=SDK&messageId=351855&startId=zzzzz~

Device: Samsung Galaxy S8 (possibly other devices with same OS version are affected too).

OS: 8.0.0, Security patch from May 1.

Steps to reproduce: 
1) Create a DialogFragment, override onCreateDialog to return any dialog containing EditText or selectable TextView.
2) Select text in this dialog.
3) Rotate the screen.

Result: 
Crash in ibhwui.so

Stacktrace:
```
E: [PopupWindow:5865b7f#0] dequeueBuffer: BufferQueue has been abandoned
A: Failed to set damage region on surface 0x7cc941f9e0, error=EGL_BAD_ACCESS
A: Fatal signal 6 (SIGABRT), code -6 in tid 27338 (RenderThread)
A: *** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***
    Build fingerprint: 'samsung/dreamltexx/dreamlte:8.0.0/R16NW/G950FXXU2CRED:user/release-keys'
    Revision: '10'
    ABI: 'arm64'
    pid: 27285, tid: 27338, name: RenderThread  >>> eightbitlab.renderthreadcrash <<<
    signal 6 (SIGABRT), code -6 (SI_TKILL), fault addr --------
A: Abort message: 'Failed to set damage region on surface 0x7cc941f9e0, error=EGL_BAD_ACCESS'
        x0   0000000000000000  x1   0000000000006aca  x2   0000000000000006  x3   0000000000000008
        x4   1b00000000000000  x5   1b00000000000000  x6   1b00000000000000  x7   000000000000801b
        x8   0000000000000083  x9   638e670190b38c93  x10  0000000000000000  x11  0000000000000000
        x12  ffffffffffffffff  x13  0000000000000003  x14  ffffffffffffffff  x15  1b00000000000000
        x16  0000007cd63b02f8  x17  0000007cd635083c  x18  0000000000000100  x19  0000000000006a95
        x20  0000000000006aca  x21  0000007ca2eac098  x22  0000007cc5f10330  x23  0000007ca2ea82c8
        x24  0000007ca2ea825c  x25  0000007cba06c218  x26  0000007cba06c200  x27  0000007cba06c328
        x28  000000000000000a  x29  0000007cba06b730  x30  0000007cd62fd05c
        sp   0000007cba06b6f0  pc   0000007cd6350844  pstate 0000000060000000
A: backtrace:
        #00 pc 0000000000071844  /system/lib64/libc.so (tgkill+8)
        #01 pc 000000000001e058  /system/lib64/libc.so (abort+88)
        #02 pc 0000000000008248  /system/lib64/liblog.so (__android_log_assert+328)
        #03 pc 0000000000052430  /system/lib64/libhwui.so (_ZN7android10uirenderer12renderthread10EglManager11damageFrameERKNS1_5FrameERK6SkRect+320)
        #04 pc 000000000004f9dc  /system/lib64/libhwui.so (_ZN7android10uirenderer12renderthread14OpenGLPipeline4drawERKNS1_5FrameERK6SkRectS8_RKNS0_12FrameBuilder13LightGeometryEPNS0_16LayerUpdateQueueERKNS0_4RectEbRKNS0_15BakedOpRenderer9LightInfoERKNSt3__16vectorINS_2spINS0_10RenderNodeEEENSM_9allocatorISQ_EEEEPNS0_19FrameInfoVisualizerE+76)
        #05 pc 000000000004d7e0  /system/lib64/libhwui.so (_ZN7android10uirenderer12renderthread13CanvasContext4drawEv+176)
        #06 pc 00000000000511e8  /system/lib64/libhwui.so (_ZN7android10uirenderer12renderthread13DrawFrameTask3runEv+184)
        #07 pc 0000000000058494  /system/lib64/libhwui.so (_ZN7android10uirenderer12renderthread12RenderThread10threadLoopEv+356)
        #08 pc 0000000000011be8  /system/lib64/libutils.so (_ZN7android6Thread11_threadLoopEPv+280)
        #09 pc 00000000000fd688  /system/lib64/libandroid_runtime.so (_ZN7android14AndroidRuntime15javaThreadShellEPv+136)
        #10 pc 000000000006de34  /system/lib64/libc.so (_ZL15__pthread_startPv+36)
        #11 pc 000000000001f9a4  /system/lib64/libc.so (__start_thread+68)
```
