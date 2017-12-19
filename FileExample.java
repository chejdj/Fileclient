import java.io.*; //实现打印某个目录的全部子文件名称（包括子目录）

public class FileExample { // 命令行参数需要提供一个目录参数

	public void printFile(File f) throws Exception 
	{
		if (!f.isDirectory()) {
			System.out.println("FileExample only accept directory parameter.");
			//System.exit(0);
		}
		System.out.println(f.getCanonicalPath()); // 输出规范的绝对路径
		File[] children = f.listFiles(); // 得到目录的子文件（包括子目录）
		for (int i = 0; i < children.length; i++) 
		{
				System.out.println(children[i]);
		}
	}
}
