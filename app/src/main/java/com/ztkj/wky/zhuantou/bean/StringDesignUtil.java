package com.ztkj.wky.zhuantou.bean;

import android.text.Html;
import android.text.Spanned;

public class StringDesignUtil {
    /**     * 根据关键字，指定单个部分文字颜色     **/
    public static Spanned getSpanned(String text, String keyword, String colorValue) {
        return Html.fromHtml(text.replace(keyword, "<font color=" + colorValue + ">" + keyword + "</font>"));
    }

}
