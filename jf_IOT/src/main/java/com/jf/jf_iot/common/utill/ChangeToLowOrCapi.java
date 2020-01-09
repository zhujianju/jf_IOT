package com.jf.jf_iot.common.utill;

import java.util.Locale;
import java.util.Scanner;

/**
 * 大小写转换
 */
public class ChangeToLowOrCapi {
    /**
     * 转换成大写
     * @return
     */
    public static String ToCapital(String str){
      return str.toUpperCase();
    }
    /**
     * 转换成小写写
     * @return
     */
    public static String ToLows(String str){
        return str.toLowerCase();
    }

    public static void myTest(){
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("0.退出  1.转换大写  2.转换小写");
            String scnex=sc.next();
            if("0".equals(scnex)){
                System.out.println("结束");
                return;
            }
            if("1".equals(scnex)){
                System.out.println("大写转换");
                System.out.println(ToCapital(sc.next()));
            }
            if("2".equals(scnex)){
                System.out.println("小写转换");
                System.out.println(ToLows(sc.next()));
            }

        }
    }

  public static void main(String[] args) {
        myTest();
    }



}
