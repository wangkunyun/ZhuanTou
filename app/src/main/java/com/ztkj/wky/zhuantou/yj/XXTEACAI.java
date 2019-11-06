package com.ztkj.wky.zhuantou.yj;

//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class XXTEACAI {
    public final static int[] KEY = new int[]{//XXTEA encrypt KEY
            0x00543210, 0x00543210,
            0x00543210, 0x00543210
    };
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

/*
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	public static String getTimebefore5minute() {
		return sdfTime.format(new Date().getTime()-600000);
	}
*/

    public static void main(String[] args)
    {

        String[] strQRArray = {"1","2","3","4","5","6","7","8"};

        strQRArray[0] = "Lily" + "|";//用户自定义数据"
        strQRArray[1] = "0000002E";//关联卡号 ,用户生成二维码表，取此二维码在表中的ID序号 ，生成的二维码如果要实现限次功能，必须使此ID是递增的
        strQRArray[3] = "00852953";//用户秘钥

        String  strTmp1="01";//有时间限制
        String  strTmp2="03";//开门次数3次
        String  strTmp3="02";//以MAC地址后6位匹配方式开门
        String  strTmp4="0000";//默认时长
        String  strTmp5="000000";//保留字节
        strQRArray[4] = strTmp1 + strTmp2 + strTmp3 + strTmp4 + strTmp5;//管理字节

        //得到起始时间和终止时间，本例中是起始时间是当前时间，终止时间当前时间+三天后的时间
        //起始时间为当前时间提前5分钟，这样免去了1分钟的问题，
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        strTmp1 = df.format(date.getTime()-600000);//当前时间减10分钟，免得有未到生效时间的
        strTmp2 = strTmp1.substring(2, strTmp1.length());

        Calendar calendar1 = Calendar.getInstance();
        df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        calendar1.add(Calendar.DATE, 3);
        strTmp3 = df.format(calendar1.getTime());
        strTmp4 = strTmp3.substring(2, strTmp3.length());

        strQRArray[5] = strTmp2 + strTmp4;

        strTmp1 = "03";//二个匹配的MAC地址
        strTmp2 = "920992";//二个MAC地址后6位
        strTmp3 = "920993";
        strTmp4 = "920994";
        strQRArray[6] = strTmp1 + strTmp2 + strTmp3 + strTmp4;

        //需要加密的字符串
        String encryptCodeStr = strQRArray[4] + strQRArray[5] + strQRArray[6];

        //encryptCodeStr="010302000000000017041116080717041416080702920992";
        //得到加密长度 2位
        int   encryptCodeLength = encryptCodeStr.length();
        byte  encryptLength  = (byte)(encryptCodeLength/8);//XXTEA加密的整形个数=加密字符串长度/8;如果对8取余，余数不为0则+1
        byte  nRemainder1 = (byte) (encryptCodeLength%8);
        if(nRemainder1!=0)
        {
            encryptLength++;
        }

        int v = ((int)encryptLength) & 0xFF;
        strTmp1 = Integer.toHexString(v);
        if (strTmp1.length() < 2) {
            strTmp1 ="0"+strTmp1;
        }

        //得到加密字符串余数 加密字符串对8取余的一半
        byte nRemainder = (byte) ((encryptCodeLength%8)/2);

        v = ((int)nRemainder) & 0xFF;
        strTmp2 = Integer.toHexString(v);
        if (strTmp2.length() < 2) {
            strTmp2 ="0"+strTmp2;
        }
        //得到加密长度以及余数
        strQRArray[2] = strTmp1 + strTmp2;
        //将字符串转成16进制的整形数
        //先转前面前面8的整数个数，再转最后一个整数
        int[] EnCodeInt = new int[30];

        int stmp1 = 0;
        int stmp2 = 0;
        if(nRemainder!=0)
        {
            //将字符串分中化成一个一个整形值，由于JAVA Integer.valueOf（）整形值范围受限，将整形值分为二个4字符值处理
            for(int k=0;k<encryptLength-1;k++)
            {
                strTmp4 = encryptCodeStr.substring(k*8,k*8+4);
                strTmp5 = encryptCodeStr.substring(k*8+4,(k+1)*8);
                stmp1 = Integer.valueOf(strTmp4,16);     //00852953
                stmp2 = Integer.valueOf(strTmp5,16);
                EnCodeInt[k]  = (stmp1 << 16) | stmp2;
                //EnCodeInt[k] = Integer.valueOf(encryptCodeStr.substring(k*8,(k+1)*8),16);
            }
            EnCodeInt[encryptLength-1] = Integer.valueOf(encryptCodeStr.substring((encryptLength-1)*8,(encryptLength-1)*8+nRemainder1),16);
        }else{
            for(int k=0;k<encryptLength;k++)
            {
                strTmp4 = encryptCodeStr.substring(k*8,k*8+4);
                strTmp5 = encryptCodeStr.substring(k*8+4,(k+1)*8);
                stmp1 = Integer.valueOf(strTmp4,16);     //00852953
                stmp2 = Integer.valueOf(strTmp5,16);
                EnCodeInt[k]  = (stmp1 << 16) | stmp2;
                //EnCodeInt[k] = Integer.valueOf(encryptCodeStr.substring(k*8,(k+1)*8),16);
            }
        }
        //设置用户秘钥，设备秘钥固定为0x00543210，如果要修改设备密钥，需要对设备进行设备秘钥设置

        stmp1 = Integer.valueOf("0085",16);     //00852953
        stmp2 = Integer.valueOf("2953",16);
        KEY[0]  = (stmp1 << 16) | stmp2;
        KEY[2]  = KEY[0];

        int[] EnCodeResult = new int[20];
        EnCodeResult = encrypt(EnCodeInt, KEY,encryptLength);
        String EnQRResult="";
        for(int k=0;k<encryptLength;k++)
        {
            strTmp1 = pad(Integer.toHexString(EnCodeResult[k]).toUpperCase(), 8, true);
            EnQRResult += strTmp1;
        }
        System.out.println("加密前字符串"+encryptCodeStr);
        System.out.println("加密后的字符串"+EnQRResult);

        //测试，将加密的字符串解码出来
        int[] Decodeint = new int[20];
        Decodeint = decrypt(EnCodeResult, KEY,encryptLength);
        String DeQRResult="";
        //解码后的字符串，先解前面的，最后再解最后一个整形值外，最后一个整形数，
        //如果位数不是8的整数，有余数，则要根据二维码余数参数，去掉前面的多余的00

        for(int k=0;k<encryptLength-1;k++)
        {
            strTmp1 = pad(Integer.toHexString(Decodeint[k]).toUpperCase(), 8, true);
            DeQRResult += strTmp1;
        }
        if(nRemainder==1)//余数为1，表示最后一个字符串，有2位
        {
            strTmp1 = pad(Integer.toHexString(Decodeint[encryptLength-1]).toUpperCase(), 2, true);
            DeQRResult += strTmp1;
        }
        else if(nRemainder==2)//余数为2，表示最后一个字符串，有4位
        {
            strTmp1 = pad(Integer.toHexString(Decodeint[encryptLength-1]).toUpperCase(), 4, true);
            DeQRResult += strTmp1;
        }
        else if(nRemainder==3)//余数为3，表示最后一个字符串，有6位
        {
            strTmp1 = pad(Integer.toHexString(Decodeint[encryptLength-1]).toUpperCase(), 6, true);
            DeQRResult += strTmp1;
        }
        else  //余数为0，表示最后一个字符串，有8位，是完整的
        {
            strTmp1 = pad(Integer.toHexString(Decodeint[encryptLength-1]).toUpperCase(), 8, true);
            DeQRResult += strTmp1;
        }
        System.out.println("解密后字符串"+DeQRResult);

        String QREndStr = strQRArray[0] + strQRArray[1] + strQRArray[2] + strQRArray[3] + EnQRResult;
        System.out.println("完整二维码字符串："+QREndStr);



    }


    private static String pad(String str, int size, boolean isprefixed) {
        if (str == null)
            str = "";
        int str_size = str.length();
        int pad_len = size - str_size;
        StringBuffer retvalue = new StringBuffer();
        for (int i = 0; i < pad_len; i++) {
            retvalue.append("0");
        }
        if (isprefixed)
            return retvalue.append(str).toString();
        return retvalue.insert(0, str).toString();
    }

    private static java.lang.String toHexString(int encryptLength) {
        // TODO Auto-generated method stub
        return null;
    }

    public static int[] encrypt(int[] v, int[] k,int n) {
        int y;
        int p;
        int rounds = 6 + 52/n;
        int sum = 0;
        int z = v[n-1];
        int delta = 0x9E3779B9;
        do {
            sum += delta;
            int e = (sum >>> 2) & 3;
            for (p=0; p<n-1; p++) {
                y = v[p+1];
                z = v[p] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            }
            y = v[0];
            z = v[n-1] += (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
        } while (--rounds > 0);

        return v;

    }

    public static int[] decrypt(int[] v, int[] k,int n) {
        //int n = v.length;
        int z = v[n - 1], y = v[0], delta = 0x9E3779B9, sum, e;
        int p;
        int rounds = 6 + 52/n;
        sum = rounds*delta;
        y = v[0];
        do {
            e = (sum >>> 2) & 3;
            for (p=n-1; p>0; p--) {
                z = v[p-1];
                y = v[p] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
            }
            z = v[n-1];
            y = v[0] -= (z >>> 5 ^ y << 2) + (y >>> 3 ^ z << 4) ^ (sum ^ y) + (k[p & 3 ^ e] ^ z);
        } while ((sum -= delta) != 0);
        return v;
    }

}