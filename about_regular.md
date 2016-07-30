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
