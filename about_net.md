# �������
### ���������ý���
``` java
/**
* ���������ý���
*/
public static void openSetting(Activity activity) {
    Intent intent = new Intent("/");
    ComponentName cm = new ComponentName("com.android.settings",
            "com.android.settings.WirelessSettings");
    intent.setComponent(cm);
    intent.setAction("android.intent.action.VIEW");
    activity.startActivityForResult(intent, 0);
}
```

### �ж��Ƿ���������
``` java
/**
* �ж��Ƿ���������
*/
public static boolean isOnline(Context context) {
    ConnectivityManager manager = (ConnectivityManager) context
            .getSystemService(Activity.CONNECTIVITY_SERVICE);
    NetworkInfo info = manager.getActiveNetworkInfo();
    if (info != null && info.isConnected()) {
        return true;
    }
    return false;
}
```

### �ж�wifi�Ƿ�����״̬
``` java
/**
* �ж�wifi�Ƿ�����״̬
*/
public static boolean isWifi(Context context) {
    ConnectivityManager cm = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
    return cm != null && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
}
```

### ��ȡ�ƶ�������Ӫ�����ƣ����й���ͨ���й��ƶ����й�����
``` java
/**
* ��ȡ�ƶ�������Ӫ�����ƣ����й���ͨ���й��ƶ����й�����
*/
public static String getNetworkOperatorName(Context context) {
    TelephonyManager telephonyManager = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager.getNetworkOperatorName();
}
```

### �����ƶ��ն�����
``` java
// PHONE_TYPE_NONE :0 �ֻ���ʽδ֪
// PHONE_TYPE_GSM :1 �ֻ���ʽΪGSM���ƶ�����ͨ
// PHONE_TYPE_CDMA :2 �ֻ���ʽΪCDMA������
// PHONE_TYPE_SIP:3
/**
* �����ƶ��ն�����
*/
public static int getPhoneType(Context context) {
    TelephonyManager telephonyManager = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager.getPhoneType();
}
```

### �ж��ֻ����ӵ���������(2G,3G,4G)
``` java
// ��ͨ��3GΪUMTS��HSDPA���ƶ�����ͨ��2GΪGPRS��EGDE�����ŵ�2GΪCDMA�����ŵ�3GΪEVDO
public class Constants {
    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;
    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;
    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;
    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;
}
/**
* �ж��ֻ����ӵ���������(2G,3G,4G)
*/
public static int getNetWorkClass(Context context) {
    TelephonyManager telephonyManager = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);
    switch (telephonyManager.getNetworkType()) {
        case TelephonyManager.NETWORK_TYPE_GPRS:
        case TelephonyManager.NETWORK_TYPE_EDGE:
        case TelephonyManager.NETWORK_TYPE_CDMA:
        case TelephonyManager.NETWORK_TYPE_1xRTT:
        case TelephonyManager.NETWORK_TYPE_IDEN:
            return Constants.NETWORK_CLASS_2_G;
        case TelephonyManager.NETWORK_TYPE_UMTS:
        case TelephonyManager.NETWORK_TYPE_EVDO_0:
        case TelephonyManager.NETWORK_TYPE_EVDO_A:
        case TelephonyManager.NETWORK_TYPE_HSDPA:
        case TelephonyManager.NETWORK_TYPE_HSUPA:
        case TelephonyManager.NETWORK_TYPE_HSPA:
        case TelephonyManager.NETWORK_TYPE_EVDO_B:
        case TelephonyManager.NETWORK_TYPE_EHRPD:
        case TelephonyManager.NETWORK_TYPE_HSPAP:
            return Constants.NETWORK_CLASS_3_G;
        case TelephonyManager.NETWORK_TYPE_LTE:
            return Constants.NETWORK_CLASS_4_G;
        default:
            return Constants.NETWORK_CLASS_UNKNOWN;
    }
}
```

### �жϵ�ǰ�ֻ�����������(WIFI����2,3,4G)
``` java
/**
* �жϵ�ǰ�ֻ�����������(WIFI����2,3,4G)����Ҫ�õ�����ķ���
*/
public static int getNetWorkStatus(Context context) {
    int netWorkType = Constants.NETWORK_CLASS_UNKNOWN;
    ConnectivityManager connectivityManager = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected()) {
        int type = networkInfo.getType();
        if (type == ConnectivityManager.TYPE_WIFI) {
            netWorkType = Constants.NETWORK_WIFI;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            netWorkType = getNetWorkClass(context);
        }
    }
    return netWorkType;
}
```
