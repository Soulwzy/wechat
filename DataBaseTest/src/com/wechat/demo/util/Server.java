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
import com.wechat.demo.util.Sever_Com.Client_Thread;

public class Server {
	
	public static Map<String, Socket> client_map = new HashMap<>();  //全局客户端信息
	public static int i = 1;                              //线程数

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ServerSocket server = new ServerSocket(9000);
		System.out.println("主线程开启,开始监听");
		while(true)
		{
			Socket socket = server.accept();
			
			/*
			 * 开启新线程
			*/                                   
			Client_Thread Cthread = new Client_Thread(socket);               //开启线程交互
			Thread thread = new Thread(Cthread);
			thread.start();	
		}
	}
	
	public static class Client_Thread implements Runnable{
		
		Socket Local_socket = null;            //本机套接字
		Socket To_socket = null;               //远程套接字
		int flag = -1;                         //请求码
		int num = -1;                          //进程标识号
		InputStream Local_in = null;           //本地输入输出流
		OutputStream Local_out = null;          
		OutputStream Purpose_out = null;       //目的输入输出i流
		String Rsv_msg = null;
		String User_name = null;               //用户ID 
		
		
		DataHandleUtil data_handle = new DataHandleUtil();
		Map<String,String> Smsg_hanle = new HashMap<>();       //数据库返回数据
		
		public Client_Thread(Socket socket) throws IOException {
			// TODO Auto-generated constructor stub
			
			this.Local_socket = socket;
			Local_in = Local_socket.getInputStream();
			Local_out = Local_socket.getOutputStream();
			num = i;
			System.out.println("第" + i + "个子线程开启");
			i++;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while(true)
			{

				System.out.println("开始接收消息");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(Local_in));
				try {
					Rsv_msg = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(Rsv_msg);
				
				if(Rsv_msg == "-1")
				{
					System.out.println("客户端未正确退出 , 线程退出");
					break;
				}
				
				if(Rsv_msg == null)
				{
					System.out.println("接受消息为空，线程退出");
					break;
				}
				
				JSONObject jsonReceive=JSONObject.parseObject(Rsv_msg);    //获取请求码
				flag = jsonReceive.getIntValue("flag");
				
				if(flag == -1)
				{
					System.out.println("获取请求码出错,重新接收消息");
					continue;
				}
				
				if(flag == 1103)
				{
					try {     
						System.out.println("第" + i + "个线程安全关闭");
						client_map.remove(User_name);
						Local_in.close();                       //循环结束或出错执行，释放资源
						Local_out.close();
						Local_socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				
				Smsg_hanle = data_handle.dataHandle(Rsv_msg);
				
				User_name = Smsg_hanle.get("id");
				
				System.out.println("处理后的消息为:" + Smsg_hanle);
				/*
				 * 
				 * 根据操作码进行相应操作
				 * 
				 */
				switch (flag) {
				
//				//安全退出
//				case 1103:
//					try {     
//						System.out.println("第" + i + "个线程关闭");
//						client_map.remove(User_name);
//						Local_in.close();                       //循环结束或出错执行，释放资源
//						Local_out.close();
//						Local_socket.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					break;
				
				//请求登陆信息
				case 1102:
					System.out.println("用户ID" + User_name);
					if(User_name!=null && Integer.parseInt(User_name) > 0)
					{
						System.out.println(User_name + "\t登陆成功");
						client_map.put(User_name, Local_socket);                                      
					}
					else {
						System.out.println(User_name + "\t登陆失败");
					}
					
					try {
						Local_out.write((Smsg_hanle.get("send") + "\n").getBytes());
						System.out.println("消息已返回");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
					
				//好友列表
				case 1201:
					try {
						Local_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				//添加好友
				case 1202:
					break;
				
				//发送消息
					
//			    	JSONObject jsonReceive=JSONObject.parseObject(receive);
//			    	int flag = jsonReceive.getIntValue("flag");
//			    	String main=jsonReceive.getString("main");
//			    	JSONObject jsonMain=JSONObject.parseObject(main);
				case 1301:
					System.out.println(client_map);
					
					JSONObject json=JSONObject.parseObject(Rsv_msg);
					String main=jsonReceive.getString("main");
					JSONObject jsonMain=JSONObject.parseObject(main);
					String list = jsonMain.getString("list");
					//int other_id = jsonMain.getInteger("other_id").toString();
					String other_id = Integer.toString(jsonMain.getInteger("other_id"));
					int identify = jsonMain.getInteger("identify_id");
					
					System.out.println(list);
					System.out.println(other_id);
					System.out.println(identify);
					
//					Map<String, Object> map = new HashMap<>();
//					map.put("other_id", other_id);
//					map.put("list", list);
//					map.put("identify", identify);
//					JSONObject jsonObject = new JSONObject();
//					jsonObject.toString();
					
					System.out.println(jsonReceive);
					
					if(other_id != null && client_map.get(other_id) !=null)
					{
						To_socket = client_map.get(other_id);
						if(To_socket != null)
						{
							try {
								Purpose_out = To_socket.getOutputStream();
								
								//Purpose_out.write((data_handle.dataHandle(Rsv_msg).get("send") + "\n").getBytes());
								
								Purpose_out.write((Rsv_msg + "\n").getBytes());
								System.out.println("收到消息为：" + Rsv_msg);
								System.out.println("发送消息为：" + list + "\n");

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								break;
							}
						}
					}
					else {
						System.out.println("请求好友不在在线");
					}
					break;
					
				default:
					break;
				
				}
			
			}
			//循环结束处
			
			client_map.remove(User_name);                //循环结束或出错执行，释放资源
			System.out.println("关闭线程后Hashmap中的hanshmap 为" + client_map);
			System.out.println("第" + num + "个线程关闭");
			i--;
			try {
				Local_out.close();
				Local_in.close();  
				Local_socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
