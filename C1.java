

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
			InputStreamReader isr = new InputStreamReader(fis);				// ת�����ַ���
			BufferedReader br = new BufferedReader(isr);
			
			String s = null;
			while((s = br.readLine())!=null)							    //����ȥLocal��
			{
				s = s.trim();
				String w = s.substring(0, s.indexOf(":"));					// ���ļ����ǰ��ա������ֿ�����ŵ�
				String t = s.substring(s.indexOf(":"));
				Local.put(w,t);
			}
			fis.close();
			isr.close();
			br.close();
			
			
			Scanner c = new Scanner(System.in);
			String scommand = null;
			//String scommand = "Upload#ֻ������-����.mp3";
			while(true)
			{
				System.out.println("����������");
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
  	  					System.out.println("������������ڽ����ļ���Ϣ��");
  	  					for(Map.Entry<String,String> entry : Local.entrySet())
  	  	  				{
  	  	  					System.out.println("�ļ������ǣ�" + entry.getKey() +"\t" + "UUID�ǣ�" + entry.getValue());		  
  	  	  				}
  	  					break;
  	  					case "display":
  	  						System.out.println("����������ص��ļ���Ϣ��");
  	  						new FileExample().printFile(new File("./Local"));		//	�����ļ��С�
  	  					break;
  	  				}
  	  			}
  	  			else
  	  			{
  	  	
  	  				FileOutputStream fos = new FileOutputStream("Local.txt");
  	  				PrintStream ps = new PrintStream(fos);
  	  				for(Map.Entry<String,String> entry : Local.entrySet())
  	  				{
  	  					System.out.println("�ļ������ǣ�" + entry.getKey() +"\t" + "UUID�ǣ�" + entry.getValue());
  	  					String stemp = entry.getKey() + ":" + entry.getValue();			
  	  					ps.println(stemp);
  	  				}
  	  				break;
  	  			}
		}
		
		
		
	}
}
