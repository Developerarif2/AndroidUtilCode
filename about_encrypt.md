# �ӽ������
### MD5����
``` java
/**
* MD5����
*/
public static String encryptMD5(String data) throws Exception {
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    return new BigInteger(md5.digest(data.getBytes())).toString(16);
}
```

### SHA����
```
/**
* SHA����
*/
public static String encryptSHA(String data) throws Exception {
    MessageDigest sha = MessageDigest.getInstance("SHA");
    return new BigInteger(sha.digest(data.getBytes())).toString(32);
}
```
