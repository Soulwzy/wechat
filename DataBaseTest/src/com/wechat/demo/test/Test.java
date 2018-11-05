package com.wechat.demo.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.wechat.demo.bean.ChatContent;
import com.wechat.demo.util.DataHandleUtil;

public class Test {
	public static void main(String[] args) {
		DataHandleUtil dataHandleUtil=new DataHandleUtil();
		String str1101="{'flag': 1101,'main': {'phone': '15312345678','name': '张三','password': 'password'}}";
		System.out.println(dataHandleUtil.dataHandle(str1101));

		String str1102="{'flag': 1102,'main': {'phone': 'bgt','password': 'bgt'}}";
		System.out.println(dataHandleUtil.dataHandle(str1102));

		String str1103="{'flag': 1103,'main': {'mcode': 'wx_4561bbghfrv','token': 'sadhasdajd4554'}}";
		System.out.println(dataHandleUtil.dataHandle(str1103));
		
		String str1201="{'flag': 1201,'main': {'identify_id': 1}}";
		System.out.println(dataHandleUtil.dataHandle(str1201));
		
		
		//发消息
		String str1301="{'flag': 1301,'main': {'identify_id' : 123,'list': [{'content':'你好','friendId':2,'hostId':1,'id':4,'insertTime':555,'state':0}]}}";
		System.out.println(dataHandleUtil.dataHandle(str1301));
		
		String str1302="{'flag': 1302,'main': {'identify_id': 1}}";
		System.out.println(dataHandleUtil.dataHandle(str1302));
		
		String str1401="{'flag': 1401,'main': {'identify_id': 123,'other_id': 1}}";
		System.out.println(dataHandleUtil.dataHandle(str1401));
		
		String str1402="{'flag': 1402,'main': {'id': 1}}";
		System.out.println(dataHandleUtil.dataHandle(str1401));
		
//		List<ChatContent> list=new ArrayList<ChatContent>();
//		list.add(new ChatContent(4, 1, 2, 555, "你好", 0));
//		String abc=JSON.toJSONString(list);
//		System.out.println(abc);
		
	}
}
