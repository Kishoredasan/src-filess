 #include "file_Filess.h"
  #include <string.h>
#include<stdio.h>
#include<stdlib.h>
 using namespace std;



JNIEXPORT jboolean JNICALL Java_file_Filess_createMethod
  (JNIEnv *env, jobject obj, jstring string, jstring string1)
 {
FILE *f;
 const char *str = env->GetStringUTFChars(string, 0);
 const char *str1 = env->GetStringUTFChars(string1, 0);
     char cap[128],cap1[128];
     strcpy(cap,"D:\\");
strcat(cap,str);
strcpy(cap1,str1);
     f=fopen(cap,"w");
if(f== NULL)
return false;
else
{
fprintf(f,"%s", cap1);
fclose(f);
return true;
}
 }
JNIEXPORT jboolean JNICALL Java_file_Filess_stringMethod
  (JNIEnv *env, jobject obj, jstring string, jstring string1)
{
    int status;
      const char *str = env->GetStringUTFChars(string, 0);
     const char *str1 = env->GetStringUTFChars(string1, 0);
     char cap[128],cap1[128];
strcpy(cap,"D:\\");
strcpy(cap1,"D:\\");
     strcat(cap, str);
     strcat(cap1,str1);
     env->ReleaseStringUTFChars(string, str);
 status=rename( cap , cap1 );
	if(status==0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

JNIEXPORT jboolean JNICALL Java_file_Filess_deleteMethod
  (JNIEnv *env, jobject obj, jstring string)
{
 const char *str = env->GetStringUTFChars(string, 0);
     char cap[128];
strcpy(cap,"D:\\");
     strcat(cap, str);
     env->ReleaseStringUTFChars(string, str);
 

  if ( remove(cap) == 0 )
   
return 1;
  else
  
  return 0;
    
}



 int main(){
return 0 ;}