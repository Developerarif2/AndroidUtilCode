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
