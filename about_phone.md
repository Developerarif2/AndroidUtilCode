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
