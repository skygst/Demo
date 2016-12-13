package com.ebodoo.raz.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonStatistical {
	
	
	
	public final static String answer_yes_no = "answer";
	
	/** 
     * talkingData事件数据，压入Map 
     */  
    
    public static Map<String,Object> getTalkingData(String yes, int age,String letter) {  
    	Map<String,Object> kv = new HashMap<String, Object >();
    	kv.put("answer", yes); //级别区间，注意是字符串
        kv.put("age", age+""); //关卡的大类别
        kv.put("letter", letter); //关卡具体编号       
        
    /* // 
     * 事件名：answer_yes_no
		参数：［yes，no］，［age: int］,[letter],[type：letter(听音识别)，voice(语音识别)]
        */
        return kv;  
    }  

}
