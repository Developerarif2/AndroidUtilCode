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

### MD5����
``` java
/**
* MD5����
*/
public static String passwordMD5(String password) {
    StringBuilder sb = new StringBuilder();
    try {
        //1.��ȡ����ժҪ��
        //arg0 : ���ܵķ�ʽ
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //2.��һ��byte������м���,���ص���һ�����ܹ���byte����,�����ƵĹ�ϣ����,md5���ܵĵ�һ��
        byte[] digest = messageDigest.digest(password.getBytes());
        //3.����byte����
        for (int i = 0; i < digest.length; i++) {
            //4.MD5����
            //byteֵ    -128-127
            int result = digest[i] & 0xff;
            //���õ�int����ת����16�����ַ���
            //String hexString = Integer.toHexString(result)+1;//���������,����
            String hexString = Integer.toHexString(result);
            if (hexString.length() < 2) {
                //                    System.out.print("0");
                sb.append("0");
            }
            //System.out.println(hexString);
            //e10adc3949ba59abbe56e057f20f883e
            sb.append(hexString);
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        //�Ҳ������ܷ�ʽ���쳣
        e.printStackTrace();
    }
    return null;
}
```
