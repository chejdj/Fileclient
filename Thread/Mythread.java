package Thread;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64.Encoder;
import java.util.Map;

import entity.MyFile;

public class Mythread extends Thread
{
	private String UUID;
	private File file;
	private String command;
	private Map<String, String> Local;
	public Mythread(String pathname,String command,Map<String, String> Local) 
	{
		this.file = new File("Local/"+pathname);
		this.command = command;
		this.Local = Local;
		
	}
	public Mythread(String uuid,String command) 	// ���ع��캯��
	{
		this.UUID = uuid;
		this.command = command;
	}
	public void run()
	{
		try
		{	
			switch (command) // ���ݲ�ͬ�Ŀͻ�Э�����ִ�в�ͬ�ĳ�����
			{
				case "Upload": // ִ�мӷ�����
				try{
					Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
					
					OutputStream  outputStream = socket.getOutputStream();
					
					PrintWriter pw = new PrintWriter(outputStream);					// �����      ����	
					
					System.out.println("���ͣ���2");
					String k = new String("Upload"+"#"+file.getName()+"#"+String.valueOf(file.length())+"\n");
					
					
					pw.write(k); 
					pw.flush();
					
					
					InputStream is = socket.getInputStream();
					System.out.println("���ն���");
					ObjectInputStream in = new ObjectInputStream(is);		// ������
					System.out.println("���ն���");
					MyFile Mf1 =(MyFile)in.readObject();
					socket.close();
					System.out.println("���ڵ�"+Mf1.getM_node().getName()+" ���ڵ� "+Mf1.getB_node1().getName());
					String k1 = new String("Upload");
					MyFile f2 = new MyFile();
					f2.setCommand(k1);
					f2.setID(Mf1.getID());
					f2.setB_node1(Mf1.getB_node1());
					f2.setM_node(Mf1.getM_node());	
					f2.setSize((int)file.length());
					
					try{				
						Socket socket1 = new Socket(InetAddress.getByName(Mf1.getM_node().getIp()), Mf1.getM_node().getPort());
						Local.put(file.getName(), Mf1.getID());					// ����Ϣ�ŵ�map���棬��ÿ�ιرյ�ʱ�� ��д�����ص��ļ�����
						System.out.println("�ļ����ǣ�"+file.getName()+"UUID�ǣ�"+Local.get(file.getName()));
											
						OutputStream OutputStream1 = socket1.getOutputStream();							
						// ��װ һ�� ���� node ���ļ���Ϣ						
						System.out.println("��ʼ���Ͷ���");
						ObjectOutputStream obs = new ObjectOutputStream(OutputStream1);	
						obs.writeObject(f2);
						System.out.println("���Ͷ���ɹ�");
						obs.flush();
					
						System.out.println(file.getName());
						System.out.println("��ʼ�����ļ���");
						FileInputStream fis = new FileInputStream(file.getAbsolutePath());			// �������ļ�					
						DataOutputStream dos = new DataOutputStream(OutputStream1);	//�����  ����
						int c = 0;
						byte[] buffer = new byte[1024]; // �����ֽ�������Ϊ������
						while ((c = fis.read(buffer)) != -1) 
						{ // һ�ζ���һ�����飬Ч�ʸ���
							dos.write(buffer, 0, c); // ������������������������
							dos.flush();
						}
						fis.close();
						dos.close();
						System.out.println("������ϣ�");
						socket1.close();	
					}
					catch(Exception e)
					{
						e.printStackTrace();
						try{
						Socket socket2 = new Socket(InetAddress.getByName(Mf1.getB_node1().getIp()), Mf1.getB_node1().getPort());
						Local.put(file.getName(), Mf1.getID());					// ����Ϣ�ŵ�map���棬��ÿ�ιرյ�ʱ�� ��д�����ص��ļ�����
						System.out.println("�ļ����ǣ�"+file.getName()+"UUID�ǣ�"+Local.get(file.getName()));
											
						OutputStream OutputStream2 = socket2.getOutputStream();							
						// ��װ һ�� ���� node ���ļ���Ϣ						
						System.out.println("��ʼ���Ͷ���");
						ObjectOutputStream obs = new ObjectOutputStream(OutputStream2);	
						obs.writeObject(f2);
						System.out.println("���Ͷ���ɹ�");
						obs.flush();
					
						System.out.println(file.getName());
						System.out.println("��ʼ�����ļ���");
						FileInputStream fis = new FileInputStream(file.getAbsolutePath());			// �������ļ�					
						DataOutputStream dos = new DataOutputStream(OutputStream2);	//�����  ����
						int c = 0;
						byte[] buffer = new byte[1024]; // �����ֽ�������Ϊ������
						while ((c = fis.read(buffer)) != -1) 
						{ // һ�ζ���һ�����飬Ч�ʸ���
							dos.write(buffer, 0, c); // ������������������������
							dos.flush();
						}
						fis.close();
						dos.close();
						System.out.println("������ϣ�");
						socket2.close();
						}catch(Exception e1)
						{
							e.printStackTrace();
						}
						
					}
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
						break;
				case "Download":
					try{
						Socket socket1 = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
						
						OutputStream  DoutputStream = socket1.getOutputStream();
						
						
						PrintWriter Dpw = new PrintWriter(DoutputStream);					// �����      ����	
						System.out.println("��������");
						String Dk = new String("Download"+"#"+UUID+"\n");
						
						Dpw.write(Dk); 
						Dpw.flush();
						
						InputStream Dis = socket1.getInputStream();
						System.out.println("���ն���");
						ObjectInputStream Din = new ObjectInputStream(Dis);		// ������
						System.out.println("���ն���");
						
						try{
							
							MyFile f1 =(MyFile)Din.readObject();
							socket1.close();
							System.out.println("��ʼ���Ͷ���");
							System.out.println(f1.getM_node().getIp()+"#"+f1.getM_node().getPort());
							Socket socket2 = new Socket(InetAddress.getByName(f1.getM_node().getIp()), f1.getM_node().getPort());
							System.out.println("��ʼ���Ͷ���");
							String k1 = new String("Download");	
							System.out.println("��ʼ���Ͷ���");
							ObjectOutputStream obs = new ObjectOutputStream(socket2.getOutputStream());
							MyFile f2 = new MyFile();
							f2.setCommand(k1);
							f2.setID(f1.getID());
							
							obs.writeObject(f2);
							System.out.println("���Ͷ���ɹ�");
							obs.flush();
							
							InputStream is1 = socket2.getInputStream();
							DataInputStream dis = new DataInputStream(is1);
							FileOutputStream fos = new FileOutputStream("./"+UUID);
							int c = 0;
							byte[] buffer = new byte[1024];
							while((c=dis.read(buffer))!=-1)
							{
								fos.write(buffer,0,c);
							}
							fos.close();
							socket2.close();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						break;
				case "Remove":
					try{
					Socket socket1 = new Socket(InetAddress.getByName("127.0.0.1"), 7777);
					
					OutputStream  DoutputStream = socket1.getOutputStream();
					
					
					PrintWriter Dpw = new PrintWriter(DoutputStream);					// �����      ����	
					System.out.println("��������");
					String Dk = new String("Remove"+"#"+UUID+"\n");
					
					Dpw.write(Dk); 
					Dpw.flush();
					System.out.println("���ͳɹ���");
					socket1.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					break;
					
			}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		
	}
 
}