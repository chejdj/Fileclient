import java.io.*; //ʵ�ִ�ӡĳ��Ŀ¼��ȫ�����ļ����ƣ�������Ŀ¼��

public class FileExample { // �����в�����Ҫ�ṩһ��Ŀ¼����

	public void printFile(File f) throws Exception 
	{
		if (!f.isDirectory()) {
			System.out.println("FileExample only accept directory parameter.");
			//System.exit(0);
		}
		System.out.println(f.getCanonicalPath()); // ����淶�ľ���·��
		File[] children = f.listFiles(); // �õ�Ŀ¼�����ļ���������Ŀ¼��
		for (int i = 0; i < children.length; i++) 
		{
				System.out.println(children[i]);
		}
	}
}
