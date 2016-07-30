# δ����
### ��ȡ�����Ƿ���
```
/**
* ��ȡ�����Ƿ���
*/
public static boolean isRunningService(String className, Context context) {
    //���̵Ĺ�����,��Ĺ�����
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    //��ȡ�������еķ���
    List<RunningServiceInfo> runningServices = activityManager.getRunningServices(1000);//maxNum �����������еķ�������޸���,��෵�ض��ٸ�����
    //��������
    for (RunningServiceInfo runningServiceInfo : runningServices) {
        //��ȡ�ؼ��ı�ʾ
        ComponentName service = runningServiceInfo.service;
        //��ȡ�������еķ����ȫ����
        String className2 = service.getClassName();
        //����ȡ�����������еķ����ȫ�����ʹ��ݹ����ķ����ȫ�����Ƚ�,һֱ��ʾ������������  ����true,��һ�±�ʾ����û������  ����false
        if (className.equals(className2)) {
            return true;
        }
    }
    return false;
}
```
