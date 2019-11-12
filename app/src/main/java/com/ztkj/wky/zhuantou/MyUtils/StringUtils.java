package com.ztkj.wky.zhuantou.MyUtils;

import java.util.regex.Pattern;

//字符串 正则
public class StringUtils {
    public static String jiekouqianzui = "https://api.zhuantoukj.com/birck/index.php/Home/";
//    public static String string_version = "1.4.2";
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,18}+$";
//    public static String mm = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{8,18}";


    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }



    /**
     * 是否为空
     *
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 是否相等
     *
     * @param actual
     * @param expected
     * @return
     * @see ObjectUtils#isEquals(Object, Object)
     */


}
