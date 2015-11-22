import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
/**
 * 求两个文档中单词表的交集
 * 文档中的单词用空格来分隔的
 * @author 陈宏业
 *
 */
public class FileIO {

	public static void main(String[] args) {
		File file1 = new File("file1");
		File file2 = new File("file2");
		System.out.println(file1.getAbsolutePath());
		HashSet<String> set1 = new HashSet<>();
		HashSet<String> set2 = new HashSet<>();
		HashSet<String> union = new HashSet<>();
		int count1 = 0; // 文件1的单词总数
		int count2 = 0; // 文件2的单词总数
		try {
			// 将文件读取流对象包装成缓冲流对象
			BufferedReader reader1 = new BufferedReader(new FileReader(file1));
			BufferedReader reader2 = new BufferedReader(new FileReader(file2));
			String str;
			while ((str = reader1.readLine()) != null) {
				String[] temp = str.split(" ");
				count1 += temp.length;
				for (int i = 0; i < temp.length; i++) {
					set1.add(temp[i]);
				}
			}
			while ((str = reader2.readLine()) != null) {
				String[] temp = str.split(" ");
				count2 += temp.length;
				for (int i = 0; i < temp.length; i++) {
					// 转换成小写加入set集中
					set2.add(temp[i].toLowerCase());
				}
			}
			// 求两个set集的交集
			Iterator<String> it = set1.iterator();
			while (it.hasNext()) {
				String s = it.next();
				if (set2.contains(s)) {
					union.add(s);
				}
			}
			System.out.println("文件1的词汇表：" + set1);
			System.out.println("文件2的词汇表：" + set2);
			System.out.println("同时出现在两个文件中的单词为：" + union);
			System.out.println("文件1的单词总数为：" + count1 + "个！");
			System.out.println("文件2的单词总数为：" + count2 + "个！");

			// 关闭输入流
			reader1.close();
			reader2.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
