

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.DigestInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.standard.PrinterMessageFromOperator;

import org.omg.CORBA.PUBLIC_MEMBER;

import entity.MyFile;
import Thread.Mythread;
import java.io.InputStream;
import java.io.InputStreamReader;

public class C1 {	
	
	public static void main(String[] args) throws Exception 
	{	
			Map<String,String> Local = new <String,String>HashMap();
						
			FileInputStream fis = new FileInputStream("Local.txt");
			InputStreamReader isr = new InputStreamReader(fis);				// 转换成字符流
			BufferedReader br = new BufferedReader(isr);
			
			String s = null;
			while((s = br.readLine())!=null)							    //读进去Local中
			{
				s = s.trim();
				String w = s.substring(0, s.indexOf(":"));					// 在文件中是按照“：”分开来存放的
				String t = s.substring(s.indexOf(":"));
				Local.put(w,t);
			}
			fis.close();
			isr.close();
			br.close();
			
			
			Scanner c = new Scanner(System.in);
			String scommand = null;
			//String scommand = "Upload#只不过是-花粥.mp3";
			while(true)
			{
				System.out.println("请输入命令");
  	  			scommand = c.nextLine();
  	  			if(!scommand.equals("shutdown"))
  	  			{
  	  				String[] command = scommand.split("#");		
  	  				switch(command[0])
  	  				{
  	  					case "Upload":
					
  	  					new Mythread(command[1],"Upload",Local).start();					
  	  					break;
  	  					
  	  					case "Download":
  	  					
  	  					new Mythread(Local.get(command[1]), "Download").start();
  	  					break;
					
  	  					case "Remove" :
  	  					
  	  					new Mythread(Local.get(command[1]), "Remove").start();
  	  					Local.remove(command[1]);
  	  					break;
									
  	  					case "displayMap":
  	  					System.out.println("即将输出储存在结点的文件信息！");
  	  					for(Map.Entry<String,String> entry : Local.entrySet())
  	  	  				{
  	  	  					System.out.println("文件名字是：" + entry.getKey() +"\t" + "UUID是：" + entry.getValue());		  
  	  	  				}
  	  					break;
  	  					case "display":
  	  						System.out.println("即将输出本地的文件信息！");
  	  						new FileExample().printFile(new File("./Local"));		//	本地文件夹。
  	  					break;
  	  				}
  	  			}
  	  			else
  	  			{
  	  	
  	  				FileOutputStream fos = new FileOutputStream("Local.txt");
  	  				PrintStream ps = new PrintStream(fos);
  	  				for(Map.Entry<String,String> entry : Local.entrySet())
  	  				{
  	  					System.out.println("文件名字是：" + entry.getKey() +"\t" + "UUID是：" + entry.getValue());
  	  					String stemp = entry.getKey() + ":" + entry.getValue();			
  	  					ps.println(stemp);
  	  				}
  	  				break;
  	  			}
		}
		
		
		
	}
}
