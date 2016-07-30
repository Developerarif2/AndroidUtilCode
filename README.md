# Android������Ա���ò��ռ��Ĵ���(���ϸ���)
Ϊ������ң��ѽ��д��¹��࣬����������ʾ��  
- �ߴ����
	- dp��pxת��
	- sp��pxת��
	- ���ֵ�λת��
	- ��onCreate()���ɻ�ȡView�Ŀ��
	- ListView����ǰ����View�ߴ�
- �ֻ����
	- �ж��豸�Ƿ����ֻ�
	- ��ȡ��ǰ�豸��IMIE�����������isPhoneһ��ʹ��
	- ��ȡ�ֻ�״̬��Ϣ
	- �Ƿ���SD��
	- ��ȡMAC��ַ
	- ��ȡ�ֻ����̣���Xiaomi
	- ��ȡ�ֻ��ͺţ���MI2SC
	- ��ת�����Ž���
	- ����绰
	- ���Ͷ���
	- ��ȡ�ֻ���ϵ��
	- ֱ�Ӵ��ֻ���ϵ�˽��棬����ȡ��ϵ�˺���
	- ��ȡ�ֻ����Ų����浽xml��
- �������
	- ���������ý���
	- �ж��Ƿ���������
	- �ж�wifi�Ƿ�����״̬
	- ��ȡ�ƶ�������Ӫ�����ƣ����й���ͨ���й��ƶ����й�����
	- �����ƶ��ն�����
	- �ж��ֻ����ӵ���������(2G,3G,4G)
	- �жϵ�ǰ�ֻ�����������(WIFI����2,3,4G)
- App���
	- ��װָ��·���µ�Apk
	- ж��ָ��������App
	- ��ȡApp����
	- ��ȡ��ǰApp�汾��
	- ��ָ��������App
	- ��ָ��������AppӦ����Ϣ����
	- ����Apk��Ϣ
	- ��ȡApp��Ϣ��һ����װ��(�������汾�š�Ӧ����Ϣ��ͼ�ꡢ���Ƶ�)
	- �жϵ�ǰApp����ǰ̨���Ǻ�̨
- ��Ļ���
	- ��ȡ�ֻ��ֱ���
	- ��ȡ״̬���߶�
	- ��ȡ״̬���߶ȣ�������(ActionBar)�߶�
	- ��ȡ��Ļ��ͼ
	- ����͸��״̬��������setContentView֮ǰ����
- �������
	- �������뷨����ڵ�
	- ��̬���������
	- ��̬��ʾ�����
	- �л�������ʾ���״̬
- �������
	- ���򹤾���
- δ����
	- ��ȡ�����Ƿ���
	- MD5����

���ϴ���Github�������š�[�ڴ����Star](https://github.com/Blankj/AndroidUtilCode)  
# �ߴ����
### dp��pxת��
``` java
/**
* dpתpx
*/
public static int dp2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
}

/**
* pxתdp
*/
public static int px2dp(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
}
```
 
### sp��pxת��
``` java
/**
* spתpx
*/
public static int sp2px(Context context, float spValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
}

/**
* pxתsp
*/
public static int px2sp(Context context, float pxValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
}
```

### ���ֵ�λת��
``` java
// �÷���������TypedValue
/**
* ���ֵ�λת��
*/
public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
    switch (unit) {
        case TypedValue.COMPLEX_UNIT_PX:
            return value;
        case TypedValue.COMPLEX_UNIT_DIP:
            return value * metrics.density;
        case TypedValue.COMPLEX_UNIT_SP:
            return value * metrics.scaledDensity;
        case TypedValue.COMPLEX_UNIT_PT:
            return value * metrics.xdpi * (1.0f / 72);
        case TypedValue.COMPLEX_UNIT_IN:
            return value * metrics.xdpi;
        case TypedValue.COMPLEX_UNIT_MM:
            return value * metrics.xdpi * (1.0f / 25.4f);
    }
    return 0;
}
```

### ��onCreate()���ɻ�ȡView�Ŀ��
``` java
/**
* ��onCreate()���ɻ�ȡView�Ŀ��
*/
public static int[] getViewMeasure(View view) {
    int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    view.measure(widthMeasureSpec, heightMeasureSpec);
    return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
}
```

### ListView����ǰ����View�ߴ�
``` java
// ֪ͨ�����֣�ռ�õĿ��ߣ�
/**
* ListView����ǰ����View�ߴ磬��headerView
*/
private void measureView(View view) {
    ViewGroup.LayoutParams p = view.getLayoutParams();
    if (p == null) {
        p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
    int height;
    int tempHeight = p.height;
    if (tempHeight > 0) {
        height = MeasureSpec.makeMeasureSpec(tempHeight,
                MeasureSpec.EXACTLY);
    } else {
        height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    }
    view.measure(width, height);
}
```

********************************************************************************************
# �ֻ����
### �ж��豸�Ƿ����ֻ�
``` java
/**
* �ж��豸�Ƿ����ֻ�
*/
public static boolean isPhone(Context context) {
    TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    return telephony.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
}
```

### ��ȡ��ǰ�豸��IMIE�����������isPhoneһ��ʹ��
``` java
/**
* ��ȡ��ǰ�豸��IMIE�����������isPhoneһ��ʹ��
*/
public static String getDeviceIMEI(Context context) {
    String deviceId;
    if (isPhone(context)) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = telephony.getDeviceId();
    } else {
        deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    return deviceId;
}
```

### ��ȡ�ֻ�״̬��Ϣ
``` java
// �����Ȩ��<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
/**
* ��ȡ�ֻ�״̬��Ϣ
*/
public static String getPhoneStatus(Context context) {
    TelephonyManager tm = (TelephonyManager) context
            .getSystemService(Context.TELEPHONY_SERVICE);//
    String str = "";
    str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
    str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion()
            + "\n";
    str += "Line1Number = " + tm.getLine1Number() + "\n";
    str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
    str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
    str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
    str += "NetworkType = " + tm.getNetworkType() + "\n";
    str += "honeType = " + tm.getPhoneType() + "\n";
    str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
    str += "SimOperator = " + tm.getSimOperator() + "\n";
    str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
    str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
    str += "SimState = " + tm.getSimState() + "\n";
    str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
    str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
    return str;
}
```

### �Ƿ���SD��
``` java
/**
* �Ƿ���SD��
*/
public static boolean haveSDCard() {
    return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
}
```

### ��ȡMAC��ַ
``` java
// �����Ȩ��<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
/**
* ��ȡMAC��ַ
*/
public static String getMacAddress(Context context) {
    String macAddress;
    WifiManager wifi = (WifiManager) context
            .getSystemService(Context.WIFI_SERVICE);
    WifiInfo info = wifi.getConnectionInfo();
    macAddress = info.getMacAddress();
    if (null == macAddress) {
        return "";
    }
    macAddress = macAddress.replace(":", "");
    return macAddress;
}
```

### ��ȡ�ֻ����̣���Xiaomi
``` java
/**
* ��ȡ�ֻ����̣���Xiaomi
*/
public static String getOsName() {
    String MANUFACTURER = Build.MANUFACTURER;
    return MANUFACTURER;
}
```

### ��ȡ�ֻ��ͺţ���MI2SC
``` java
/**
* ��ȡ�ֻ��ͺţ���MI2SC
*/
private String getModel() {
    String model = android.os.Build.MODEL;
    if (model != null) {
        model = model.trim().replaceAll("\\s*", "");
    } else {
        model = "";
    }
    return model;
}
```

### ��ת�����Ž���
``` java
/**
* ��ת�����Ž���
*/
public static void callDial(Context context, String phoneNumber) {
    context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber)));
}
```

### ����绰
``` java
/**
* ����绰
*/
public static void call(Context context, String phoneNumber) {
    context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));
}
```

### ���Ͷ���
``` java
/**
* ���Ͷ���
*/
public static void sendSms(Context context, String phoneNumber, String content) {
    Uri uri = Uri.parse("smsto:" + (TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber));
    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
    intent.putExtra("sms_body", TextUtils.isEmpty(content) ? "" : content);
    context.startActivity(intent);
}
```

### ��ȡ�ֻ���ϵ��
``` java
/**
* ��ȡ�ֻ���ϵ��
*/
public static List<HashMap<String, String>> getAllContactInfo(Context context) {
    SystemClock.sleep(3000);
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    // 1.��ȡ���ݽ�����
    ContentResolver resolver = context.getContentResolver();
    // 2.��ȡ�����ṩ�ߵĵ�ַ:com.android.contacts
    // raw_contacts��ĵ�ַ :raw_contacts
    // view_data��ĵ�ַ : data
    // 3.���ɲ�ѯ��ַ
    Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
    Uri date_uri = Uri.parse("content://com.android.contacts/data");
    // 4.��ѯ����,�Ȳ�ѯraw_contacts,��ѯcontact_id
    // projection : ��ѯ���ֶ�
    Cursor cursor = resolver.query(raw_uri, new String[] { "contact_id" },
            null, null, null);
    // 5.����cursor
    while (cursor.moveToNext()) {
        // 6.��ȡ��ѯ������
        String contact_id = cursor.getString(0);
        // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
        // : ��ѯ�ֶ���cursor������ֵ,һ�㶼�����ڲ�ѯ�ֶαȽ϶��ʱ��
        // �ж�contact_id�Ƿ�Ϊ��
        if (!TextUtils.isEmpty(contact_id)) {//null   ""
            // 7.����contact_id��ѯview_data���е�����
            // selection : ��ѯ����
            // selectionArgs :��ѯ�����Ĳ���
            // sortOrder : ����
            // ��ָ��: 1.null.���� 2.����Ϊnull
            Cursor c = resolver.query(date_uri, new String[] { "data1",
                            "mimetype" }, "raw_contact_id=?",
                    new String[] { contact_id }, null);
            HashMap<String, String> map = new HashMap<String, String>();
            // 8.����c
            while (c.moveToNext()) {
                // 9.��ȡ����
                String data1 = c.getString(0);
                String mimetype = c.getString(1);
                // 10.��������ȥ�жϻ�ȡ��data1���ݲ�����
                if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                    // �绰
                    map.put("phone", data1);
                } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                    // ����
                    map.put("name", data1);
                }
            }
            // 11.��ӵ�����������
            list.add(map);
            // 12.�ر�cursor
            c.close();
        }
    }
    // 12.�ر�cursor
    cursor.close();
    return list;
}
```

### ֱ�Ӵ��ֻ���ϵ�˽��棬����ȡ��ϵ�˺���
``` java
// �ڰ�ť����¼�������Intent��
Intent intent = new Intent();
intent.setAction("android.intent.action.PICK");
intent.addCategory("android.intent.category.DEFAULT");
intent.setType("vnd.android.cursor.dir/phone_v2");
startActivityForResult(intent, 1);
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null) {
        Uri uri = data.getData();
        String num = null;
        // �������ݽ�����
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,
                null, null, null, null);
        while (cursor.moveToNext()) {
            num = cursor.getString(cursor.getColumnIndex("data1"));
        }
        cursor.close();
        num = num.replaceAll("-", "");//�滻�Ĳ���,555-6 -> 5556
    }
}
```

### ��ȡ�ֻ����Ų����浽xml��
``` java
/**
* ��ȡ�ֻ����Ų����浽xml��
*/
public static void getAllSMS(Context context) {
    //1.��ȡ����
    //1.1��ȡ���ݽ�����
    ContentResolver resolver = context.getContentResolver();
    //1.2��ȡ�����ṩ�ߵ�ַ   sms,sms��ĵ�ַ:null  ��д
    //1.3��ȡ��ѯ·��
    Uri uri = Uri.parse("content://sms");
    //1.4.��ѯ����
    //projection : ��ѯ���ֶ�
    //selection : ��ѯ������
    //selectionArgs : ��ѯ�����Ĳ���
    //sortOrder : ����
    Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);
    //����������
    int count = cursor.getCount();//��ȡ���ŵĸ���
    //2.���ݶ���
    //2.1��ȡxml������
    XmlSerializer xmlSerializer = Xml.newSerializer();
    try {
        //2.2����xml�ļ������·��
        //os : �����λ��
        //encoding : �����ʽ
        xmlSerializer.setOutput(new FileOutputStream(new File("/mnt/sdcard/backupsms.xml")), "utf-8");
        //2.3����ͷ��Ϣ
        //standalone : �Ƿ��������
        xmlSerializer.startDocument("utf-8", true);
        //2.4���ø���ǩ
        xmlSerializer.startTag(null, "smss");
        //1.5.����cursor
        while (cursor.moveToNext()) {
            SystemClock.sleep(1000);
            //2.5���ö��ŵı�ǩ
            xmlSerializer.startTag(null, "sms");
            //2.6�����ı����ݵı�ǩ
            xmlSerializer.startTag(null, "address");
            String address = cursor.getString(0);
            //2.7�����ı�����
            xmlSerializer.text(address);
            xmlSerializer.endTag(null, "address");
            xmlSerializer.startTag(null, "date");
            String date = cursor.getString(1);
            xmlSerializer.text(date);
            xmlSerializer.endTag(null, "date");
            xmlSerializer.startTag(null, "type");
            String type = cursor.getString(2);
            xmlSerializer.text(type);
            xmlSerializer.endTag(null, "type");
            xmlSerializer.startTag(null, "body");
            String body = cursor.getString(3);
            xmlSerializer.text(body);
            xmlSerializer.endTag(null, "body");
            xmlSerializer.endTag(null, "sms");
            System.out.println("address:" + address + "   date:" + date + "  type:" + type + "  body:" + body);
        }
        xmlSerializer.endTag(null, "smss");
        xmlSerializer.endDocument();
        //2.8������ˢ�µ��ļ���
        xmlSerializer.flush();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```

********************************************************************************************
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

***
# App���
### ��װָ��·���µ�Apk
``` java
/**
* ��װָ��·���µ�Apk
*/
public void installApk(String filePath) {
    Intent intent = new Intent();
    intent.setAction("android.intent.action.VIEW");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
    startActivityForResult(intent, 0);
}
```

### ж��ָ��������App
``` java
/**
* ж��ָ��������App
*/
public void uninstallApp(String packageName) {
    Intent intent = new Intent();
    intent.setAction("android.intent.action.DELETE");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.setData(Uri.parse("package:" + packageName));
    startActivityForResult(intent, 0);
}
```

### ��ȡApp����
```
/**
* ��ȡApp����
*/
public static String getAppName(Context context) {
    try {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
        int labelRes = packageInfo.applicationInfo.labelRes;
        return context.getResources().getString(labelRes);
    } catch (NameNotFoundException e) {
        e.printStackTrace();
    }
    return null;
}
```


### ��ȡ��ǰApp�汾��
``` java
/**
* ��ȡ��ǰApp�汾��
*/
public static String getVersonName(Context context) {
    String versionName = null;
    PackageManager pm = context.getPackageManager();
    PackageInfo info = null;
    try {
        info = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
    } catch (NameNotFoundException e) {
        e.printStackTrace();
    }
    if (info != null) {
        versionName = info.versionName;
    }
    return versionName;
}
```

### ��ָ��������App
```
/**
* ��ָ��������App
*/
public void openOtherApp(String packageName){
    PackageManager manager = getPackageManager();
    Intent launchIntentForPackage = manager.getLaunchIntentForPackage(packageName);
    if (launchIntentForPackage != null) {
        startActivity(launchIntentForPackage);
    }
}
```

### ��ָ��������AppӦ����Ϣ����
``` java
/**
* ��ָ��������AppӦ����Ϣ����
*/
public void showAppInfo(String packageName) {
    Intent intent = new Intent();
    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    intent.setData(Uri.parse("package:" + packageName));
    startActivity(intent);
}
```

### ����Apk��Ϣ
``` java
/**
* ����Apk��Ϣ
*/
public void shareApkInfo(String info) {
    Intent intent = new Intent();
    intent.setAction("android.intent.action.SEND");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, info);
    startActivity(intent);
}
```

### ��ȡApp��Ϣ��һ����װ��(�������汾�š�Ӧ����Ϣ��ͼ�ꡢ���Ƶ�)
``` java
/**
* ��ȡApp��Ϣ��һ����װ��(�������汾�š�Ӧ����Ϣ��ͼ�ꡢ���Ƶ�)
*/
public class AppEnging {
    public static List<AppInfo> getAppInfos(Context context) {
        List<AppInfo> list = new ArrayList<AppInfo>();
        //��ȡӦ�ó�����Ϣ
        //���Ĺ�����
        PackageManager pm = context.getPackageManager();
        //��ȡϵͳ�а�װ�����������Ϣ
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : installedPackages) {
            //��ȡ����
            String packageName = packageInfo.packageName;
            //��ȡ�汾��
            String versionName = packageInfo.versionName;
            //��ȡapplication
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            int uid = applicationInfo.uid;
            //��ȡӦ�ó����ͼ��
            Drawable icon = applicationInfo.loadIcon(pm);
            //��ȡӦ�ó��������
            String name = applicationInfo.loadLabel(pm).toString();
            //�Ƿ����û�����
            //��ȡӦ�ó����������Ϣ,�Ƿ���ϵͳ������Ƿ�װ��SD��
            boolean isUser;
            int flags = applicationInfo.flags;
            if ((applicationInfo.FLAG_SYSTEM & flags) == applicationInfo.FLAG_SYSTEM) {
                //ϵͳ����
                isUser = false;
            } else {
                //�û�����
                isUser = true;
            }
            //�Ƿ�װ��SD��
            boolean isSD;
            if ((applicationInfo.FLAG_EXTERNAL_STORAGE & flags) == applicationInfo.FLAG_EXTERNAL_STORAGE) {
                //��װ����SD��
                isSD = true;
            } else {
                //��װ���ֻ���
                isSD = false;
            }
            //��ӵ�bean��
            AppInfo appInfo = new AppInfo(name, icon, packageName, versionName, isSD, isUser);
            //��bean��ŵ�list����
            list.add(appInfo);
        }
        return list;
    }
}
 
// ��װ�����Ϣ��bean��
class AppInfo {
    //����
    private String name;
    //ͼ��
    private Drawable icon;
    //����
    private String packagName;
    //�汾��
    private String versionName;
    //�Ƿ�װ��SD��
    private boolean isSD;
    //�Ƿ����û�����
    private boolean isUser;
 
    public AppInfo() {
        super();
    }
 
    public AppInfo(String name, Drawable icon, String packagName,
                   String versionName, boolean isSD, boolean isUser) {
        super();
        this.name = name;
        this.icon = icon;
        this.packagName = packagName;
        this.versionName = versionName;
        this.isSD = isSD;
        this.isUser = isUser;
    }
}
```

### �жϵ�ǰApp����ǰ̨���Ǻ�̨
``` java
// �����<uses-permission android:name="android.permission.GET_TASKS"/>
// ���ұ�����ϵͳӦ�ø÷�������Ч
/**
* �жϵ�ǰApp����ǰ̨���Ǻ�̨
*/
public static boolean isApplicationBackground(final Context context) {
    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    @SuppressWarnings("deprecation")
    List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
    if (!tasks.isEmpty()) {
        ComponentName topActivity = tasks.get(0).topActivity;
        if (!topActivity.getPackageName().equals(context.getPackageName())) {
            return true;
        }
    }
    return false;
}
```

********************************************************************************************
# ��Ļ���
### ��ȡ�ֻ��ֱ���
``` java
/**
* ��ȡ��Ļ�Ŀ��px
*/
public static int getDeviceWidth(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();// ������һ�Ű�ֽ
    windowManager.getDefaultDisplay().getMetrics(outMetrics);// ����ֽ���ÿ��
    return outMetrics.widthPixels;
}

/**
* ��ȡ��Ļ�ĸ߶�px
*/
public static int getDeviceHeight(Context context) {
    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();// ������һ�Ű�ֽ
    windowManager.getDefaultDisplay().getMetrics(outMetrics);// ����ֽ���ÿ��
    return outMetrics.heightPixels;
}
```

### ��ȡ״̬���߶�
```
/**
* ��ȡ״̬���߶�
*/
public int getStatusBarHeight() {
    int result = 0;
    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
        result = getResources().getDimensionPixelSize(resourceId);
    }
    return result;
}
```

### ��ȡ״̬���߶ȣ�������(ActionBar)�߶�
``` java
/**
* ��ȡ״̬���߶ȣ�������(ActionBar)�߶�
*/
public static int getTopBarHeight(Activity activity) {
    return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
}
```

### ��ȡ��Ļ��ͼ
``` java
/**
* ��ȡ��ǰ��Ļ��ͼ������״̬��
*/
public static Bitmap snapShotWithStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
    view.destroyDrawingCache();
    return bp;
}

/**
* ��ȡ��ǰ��Ļ��ͼ��������״̬��
*/
public static Bitmap snapShotWithoutStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
            - statusBarHeight);
    view.destroyDrawingCache();
    return bp;
}
```

### ����͸��״̬��������setContentView֮ǰ����
``` java
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //͸��״̬��
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //͸��������
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
}

// ���ڶ����ؼ������м����������������ݳ�����״̬��֮��
android:clipToPadding="true" 
android:fitsSystemWindows="true"
```

********************************************************************************************
# �������
### �������뷨����ڵ�
``` java
// ��manifest.xml��activity������
android:windowSoftInputMode="stateVisible|adjustResize"
```

### ��̬���������
``` java
/**
* ��̬���������
*/
public static void hideSoftInput(Activity activity) {
    View view = activity.getWindow().peekDecorView();
    if (view != null) {
        InputMethodManager inputmanger = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

/**
* ��̬���������
*/
public static void hideSoftInput(Context context, EditText edit) {
    edit.clearFocus();
    InputMethodManager inputmanger = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputmanger.hideSoftInputFromWindow(edit.getWindowToken(), 0);
}
```

### ��̬��ʾ�����
``` java
/**
* ��̬��ʾ�����
*/
public static void showSoftInput(Context context, EditText edit) {
    edit.setFocusable(true);
    edit.setFocusableInTouchMode(true);
    edit.requestFocus();
    InputMethodManager inputManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputManager.showSoftInput(edit, 0);
}
```

### �л�������ʾ���״̬
``` java
/**
* �л�������ʾ���״̬
*/
public static void toggleSoftInput(Context context, EditText edit) {
    edit.setFocusable(true);
    edit.setFocusableInTouchMode(true);
    edit.requestFocus();
    InputMethodManager inputManager = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
}
```

********************************************************************************************
# �������
### ���򹤾���
``` java
public class RegularUtils {
    //��֤�ֻ���
    private static final String REGEX_MOBILE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    //��֤������,��ȷ��ʽ��xxx/xxxx-xxxxxxx/xxxxxxxx
    private static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    //��֤����
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //��֤url
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    //��֤����
    private static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    //��֤�û���,ȡֵ��ΧΪa-z,A-Z,0-9,"_",���֣�������"_"��β,�û���������6-20λ
    private static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    //��֤IP��ַ
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    //If u want more please visit http://toutiao.com/i6231678548520731137/
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ�����ֻ��Ÿ�ʽ
     */
    public static boolean isMobile(String string) {
        return isMatch(REGEX_MOBILE, string);
    }
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ�������������ʽ
     */
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ���������ʽ
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ������ַ��ʽ
     */
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ���Ϻ���
     */
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }
 
    /**
     * @param string ����֤�ı�
     * @return �Ƿ�����û���
     */
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }
 
    /**
     * @param regex  ������ʽ�ַ���
     * @param string Ҫƥ����ַ���
     * @return ���str ���� regex��������ʽ��ʽ,����true, ���򷵻� false;
     */
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }
}
```

********************************************************************************************
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
