package com.ztkj.wky.zhuantou.MyUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
public class QfpayUtil {
    /**
     * 加签
     * @param map
     * @return
     */
    //排序后需要加的乱码
    public static String sign(Map<String, Object> map, String signKey){
        if(map==null){
            return null;
        }
        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<keyList.size();i++){
            String key = keyList.get(i);
            Object value = map.get(key);
            sb.append(key+value);
        }
        //这个是没加那串乱码的
        String signStr = sb.substring(0, sb.length())+signKey;
        //这个是加完的
        //String zsingStr = signStr+"a3fd4e470f3f43133a9df0274a5c5be1";
        //MD5进行修改后的(不一定对啊)
        String md5Str = DigestUtils.md5(signStr);
        return md5Str;
    }
}