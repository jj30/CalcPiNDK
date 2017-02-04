#include <jni.h>
#include <string.h>
#include <android/log.h>
#include <stdio.h>
#include <math.h>

/*
#define DEBUG_TAG "NDK_HWNDKActivity"

void Java_com_hw_ndk_HWNDKActivity_helloLog(JNIEnv * env, jobject this, jstring logThis)
{
    jboolean isCopy;
    const char * szLogThis = (*env)->GetStringUTFChars(env, logThis, &isCopy);

    __android_log_print(ANDROID_LOG_DEBUG, DEBUG_TAG, "NDK:LC: [%s]", szLogThis);

    (*env)->ReleaseStringUTFChars(env, logThis, szLogThis);
}*/

long double a(int m);
long double b(int k);
long double t(int j);
long double p(int m);

jdouble div2(jdouble x, jdouble y) {
	return x/y;
}

jlong factorial(jint k) {
	if (k == 0)
		return 1;
	else
		return k * factorial(k - 1);
}

long double a(int m) {
	//arithmetic mean
	if (m == 0)
		return 1.0;
	else
		return div2(a(m - 1) + b(m - 1), 2);
}

long double b(int k) {
	//geometric mean
	if (k == 0)
		return 1.0 / sqrt(2.0);
	else
		return pow(a(k - 1) * b(k - 1), 0.5);
}

long double t(int j) {
	if (j == 0)
		return 1.0 / 4.0;
	else
		return t(j - 1) - p(j - 1) * pow((a(j - 1) - a(j)), 2);
}

long double p(int m) {
	if (m == 0)
		return 1.0;
	else
		return 2 * p(m - 1);
}

jstring Java_com_buildingfive_hwndk_HWNDKActivity_getChudnovskyPi(JNIEnv * env, jobject this, jint value1)
{
	long double ourPi = 0.0;
	long double delta = 0.0;

	for (unsigned int n = 0; n <= value1; n++) {
		delta = pow(-1, n);
		delta *= factorial(6 * n);
		delta *= (13591409 + (545140134 * n));

		delta = div2(delta, factorial(n * 3));
		delta = div2(delta, pow(factorial(n), 3));
		delta = div2(delta, pow(640320, ((3 * n) + 1.5)));

		ourPi += delta;
	}

	ourPi = 1 / (12 * ourPi);
	char *szFormat = "%1.17f";
	char *szResult;

	// malloc room for the resulting string
	szResult = malloc(sizeof(szFormat) + 20);
	sprintf(szResult, szFormat, ourPi);

	// get an object string
	jstring result = (*env)->NewStringUTF(env, szResult);
	return result;
}

long double iterate2(int n) {
	return (long double) div2(pow((a(n) + b(n)), 2), 4 * t(n));
}

jstring Java_com_buildingfive_hwndk_HWNDKActivity_getGLPi(JNIEnv * env, jobject this, jint value1)
{
	double ourPi = 0.0;
	ourPi = iterate2(value1);
	char *szFormat = "%1.17f";
	char *szResult;

	// malloc room for the resulting string
	szResult = malloc(sizeof(szFormat) + 20);
	sprintf(szResult, szFormat, ourPi);

	// get an object string
	jstring result = (*env)->NewStringUTF(env, szResult);
	return result;
}

jstring Java_com_buildingfive_hwndk_HWNDKActivity_getBellardsPi(JNIEnv * env, jobject this, jint value1)
{
	long double ourPi = 0.0;
	long double delta = 0.0;

	for (unsigned int n = 0; n <= value1; n++) {
		delta = div2(pow(-2, 5), ((4 * n) + 1))
			- div2(1.0, ((4 * n) + 3))
			+ div2(pow(2, 8), ((10 * n) + 1))
			- div2(pow(2, 6), ((10 * n) + 3))
			- div2(pow(2, 2), ((10 * n) + 5))
			- div2(pow(2, 2), ((10 * n) + 7))
			+ div2(1.0, ((10 * n) + 9));

		delta *= div2(pow(-1, n), pow(2, 10 * n));
		delta *= pow(2, -6);
		ourPi += delta;
	}

	char *szFormat = "%1.17f";
	char *szResult;

	// malloc room for the resulting string
	szResult = malloc(sizeof(szFormat) + 20);
	sprintf(szResult, szFormat, ourPi);

	// get an object string
	jstring result = (*env)->NewStringUTF(env, szResult);
}


jstring Java_com_buildingfive_hwndk_HWNDKActivity_getLeibnizPi(JNIEnv * env, jobject this, jlong value1)
{
	double ourPi = 0.0;
	double delta = 0.0;

	for (int j = 0; j <= value1; j++)
	{
		ourPi += div2(pow(-3, -j), 2 * j + 1);
	}
	ourPi = sqrt(12) * ourPi;

	char *szFormat = "%1.17f";
	char *szResult;

	// malloc room for the resulting string
	szResult = malloc(sizeof(szFormat) + 20);
	sprintf(szResult, szFormat, ourPi);

	// get an object string
	jstring result = (*env)->NewStringUTF(env, szResult);
	return result;
}
