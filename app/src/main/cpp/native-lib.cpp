#include <jni.h>
#include <string>

#include <android/log.h>

#define LOG_TAG "debug"

#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE,LOG_TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG ,__VA_ARGS__) // 定义LOGF类型

extern int iperf_main( int argc, char* argv[] );



extern "C"
JNIEXPORT jint JNICALL
Java_com_example_soyx_iperf_MainActivity_iperfMain(JNIEnv *env, jobject obj, jstring cmd_) {
    LOGV("new");
    LOGV("native-lib.cpp");
    const char *cmd = env->GetStringUTFChars(cmd_, 0);

    char* argv[100];
    int argc = 0;
    char *token = strtok(const_cast<char *>(cmd), " ");
    LOGV("native-lib.cpp mark1");
    while(token != NULL)
    {
        LOGV("token = %s", token);
        char *temp = token;
        argv[argc] = temp;
        LOGV("argv[argc] = %s", argv[argc]);
        token = strtok(NULL, " ");
        argc++;
    }
    int ans = iperf_main(argc, argv);

    env->ReleaseStringUTFChars(cmd_, cmd);
    return ans;
}extern "C"
JNIEXPORT jint JNICALL
Java_com_example_soyx_iperf_BandwidthThread_iperfMain(JNIEnv *env, jobject instance, jstring cmd_) {
    const char *cmd = env->GetStringUTFChars(cmd_, 0);

    char* argv[100];
    int argc = 0;
    char *token = strtok(const_cast<char *>(cmd), " ");
    LOGV("native-lib.cpp mark1");
    while(token != NULL)
    {
        LOGV("token = %s", token);
        char *temp = token;
        argv[argc] = temp;
        LOGV("argv[argc] = %s", argv[argc]);
        token = strtok(NULL, " ");
        argc++;
    }
    int ans = iperf_main(argc, argv);

    env->ReleaseStringUTFChars(cmd_, cmd);
    return ans;
}