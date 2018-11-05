package com.wechat.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class Sever_Com {

	public static Map<String, Socket> client_map = new HashMap<>();  //全局客户端信息
	
	public static void main(String[] args) throws Exception{
		
		ServerSocket server=new ServerSocket(9000);
		DataHandleUtil data_handle = new DataHandleUtil();
		Map<String,String> Smsg_hanle = new HashMap<>();
		InputStream in_formClient = null;
		OutputStream out_formClient = null;
		
		int flag = -1;                                 //请求码
		String msg = null;
		int i = 0;
		
		while(true)
		{
			System.out.println("begin listen");
			Socket socket=server.accept();
			/*
			 * 查询数据库
			 */
			
			System.out.println("mobile" + i + "\nconnect the sercer");
			i++;
			

			in_formClient = socket.getInputStream();
			out_formClient = socket.getOutputStream();
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(in_formClient));
			msg = reader.readLine();
			//System.out.println(msg);
			//System.out.println(socket);
			
			if(msg == null)
			{
				System.out.println("登陆无效");
				in_formClient.close();
				out_formClient.close();
				socket.close();
			}
			
			JSONObject jsonReceive=JSONObject.parseObject(msg);    //获取请求码
			flag = jsonReceive.getIntValue("flag");
			
			System.out.println("recive message is :" + msg);
			
			Smsg_hanle = data_handle.dataHandle(msg);
			
			System.out.println("msg_hanle is :" + Smsg_hanle);
			/*
			 * 
			 * 成功连接后，将socket加入map，启动线程与客户端交互
			 * 
			 */
			if(flag == 1102)        //登陆请求信息
			{
				if(Smsg_hanle.get("id")!=null && Integer.parseInt(Smsg_hanle.get("id")) > 0)
				{
					System.out.println(Smsg_hanle.get("id") + "login in successfully");
					
					out_formClient.write((Smsg_hanle.get("send") + "\n").getBytes());
					System.out.println("消息已返回");
					
					client_map.put(Smsg_hanle.get("id"), socket);                                      
					Client_Thread Cthread = new Client_Thread(Smsg_hanle.get("id") , socket);               //开启线程交互
					Thread thread = new Thread(Cthread);
					thread.start();	
					//in_formClient.close();
				}
				
				else {
						System.out.println("login in error");
				}
			}
			else {
				System.out.println("非登陆数据不理睬");
			}
		}
	}
	
	public static class Client_Thread implements Runnable{
		
		Socket socket;
		String Local_client;
		Socket To_socket;
		int flag;
		
		DataHandleUtil data_handle = new DataHandleUtil();
		
		public Client_Thread(String client, Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
			this.Local_client = client;
			System.out.println("Son-Threadstart");
		}

		@Override
		public void run(){
			// TODO Auto-generated method stub
			
			InputStream Local_in = null;           //本地输入输出流
			OutputStream Local_out = null;
			
			OutputStream Purpose_out = null;       //目的输入输出i流
			String Rsv_msg = null;
			while(true)
			{  
				System.out.println(socket);
				try {
					Local_in = socket.getInputStream();
					Local_out = socket.getOutputStream();
					System.out.println("开始读消息");
					BufferedReader reader=new BufferedReader(new InputStreamReader(Local_in));
					Rsv_msg = reader.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(Rsv_msg == null)
				{
					System.out.println("Son--Thread exit");
					break;
				}
				
				JSONObject jsonReceive=JSONObject.parseObject(Rsv_msg);    //获取请求码
				flag = jsonReceive.getIntValue("flag");
				
				System.out.println("子线程收到消息为：" + Rsv_msg);
				
				switch (flag) {
				
				//安全退出
				case 1103:{
					
					try {
						client_map.remove(Local_client);
						Local_in.close();
						Local_out.close();
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
				}
				
				//好友列表
				case 1201:{
					try {
						Local_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				
				//添加好友
				case 1202:{
					continue;
				}
				
				//发送消息
				case 1301:{
					
					System.out.println(client_map);
					String other_id=data_handle.dataHandle(Rsv_msg).get("other_id");
					System.out.println("other id  = " + other_id);
					if(other_id != null && client_map.get(other_id) !=null)
					{
						To_socket = client_map.get(other_id);
						if(To_socket != null)
						{
							try {
								Purpose_out = To_socket.getOutputStream();
								
								Purpose_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
								System.out.println("收到消息为：" + Rsv_msg);
								System.out.println("发送消息为：" + data_handle.dataHandle(Rsv_msg).get("send") + "\n");

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								break;
							}
						}
					}
					
					else {
						System.out.println("other ID is no exit");
					}
				}
				
				//个人朋友圈
				case 1401:{
					try {
						Local_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				
				default:
					System.out.println("Son-Thread closed");
					
					try {
						client_map.remove(Local_client);
						Local_in.close();
						Local_out.close();
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
			}
			}
		}
	}
}

//try {
//System.out.println(socket);
//Local_in = socket.getInputStream();
//Local_out = socket.getOutputStream();
//
//BufferedReader reader=new BufferedReader(new InputStreamReader(Local_in));
//System.out.println("开始读消息");
//Rsv_msg = reader.readLine();
//
//System.out.println(Rsv_msg);
//
///*
// * 
// * query Hashmap
// *
// */
//assert data_handle != null;              //断言不为空
//
//System.out.println(data_handle.dataHandle(Rsv_msg));
//System.out.println("Client map:" + client_map);
//
//if(data_handle.dataHandle(Rsv_msg).get("other_id") != null && client_map.get("other_id") !=null)
//{
//	To_socket = client_map.get("other_id");
//	if(To_socket != null)
//	{
//		Purpose_out = To_socket.getOutputStream();
//		Purpose_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
//	}
//}
//
//else {
//	System.out.println("other ID is no exit");
//}
//
//} catch (IOException e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//break;
//}




