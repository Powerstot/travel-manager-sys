package com.powerstot.travels.utils;

import com.powerstot.travels.entity.Monitor;
import com.powerstot.travels.service.MonitorService;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
public class ImageServer {
	public static void main(String[] args) throws Exception {
		new Thread(new ImageServer01()).start();
	}
}
*/

public class ImageServer  implements Runnable {
	//设置为静态变量，使Handle类也能使用
	@Resource
	public static MonitorService monitorService;


	private static final long serialVersionUID = 2839564863495205814L;

	public static ServerSocket ss;

	public ImageServer() throws Exception {

		//监听端口
		// ss = portUtils.ss;

		new Thread(this).start();
	}

	@Override
	public void run() {

		int i = 0;
		System.out.println("server startup.");
		while (true) {
			try {
				Socket s = ss.accept();
				// 每个客户端一个处理线程
				new Handler(s, i).start();
				i++;
			} catch (IOException e) {
				// e.printStackTrace();
				// System.out.println("OK");
				return;
			}
		}

	}
}




class Handler extends Thread {


	Socket s;
	int id;

	public Handler(Socket s, int id) {
		this.s = s;
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println(ImageServer.monitorService);

		System.out.println("in handling..");



		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = s.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));


			String filename = System.currentTimeMillis()+".jpg";

			// Strin
			// g filepath = "target/classes/static/monitorImg/";
			String filepath = "../travel_vue/public/monitorImg/";

			File dirFile = new File(filepath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			System.out.println("read line " + id + " :" + filename);
			File file = new File(filepath+filename);

			int len = 0;
			int BUFSIZE = 1*1024;

			byte[] bytes = new byte[BUFSIZE];

			fos = new FileOutputStream(file);
			while ((len = is.read(bytes, 0, bytes.length)) != -1) {
				fos.write(bytes, 0, len);
				fos.flush();
			}

			Monitor monitor = new Monitor();
			monitor.setPicPath("../monitorImg/" + filename);
			monitor.setShotTime(new Date());
			monitor.setChecked(false);
			System.out.println(monitor);
			ImageServer.monitorService.save(monitor);
			System.out.println("Monitor对象存储成功!");
			System.out.println("done.");

			// MailUtil.sendMail("496975950@qq.com","管理员你好，在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
			// 		"，系统监测到有新的入侵记录。请即使登录管理平台查看！","新监测记录提醒");
			// System.out.println("发送邮件成功");
			// MsgUtil.sendRegCode(new String[]{new SimpleDateFormat("MM月dd日HH时mm分").format(new Date())},
			// 		"15386006064", "1019381");
			// System.out.println("发送短信成功");

			FileInputStream fs= new FileInputStream(filepath+filename);



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 服务端就不要手贱 关了socket否则Python 会出现错误Errno 10054让客户端关掉就行啦
			try {
				System.out.println("close");
				fos.close();
				is.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
