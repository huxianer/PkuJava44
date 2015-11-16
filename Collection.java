package com.pku.meng.learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * 集合的使用
 * @author huanghuizhen
 * 
 */
public class Collection {
	/**
	 *读取文本中的单词存入集合中
	 * @param fileName
	 * @return 文本单词的list集合
	 * @throws IOException 
	 */
	public List<String> readFile(String fileName) throws IOException{
		File file =new File(fileName);
		//读取文本文件
		BufferedReader reader=new BufferedReader(new FileReader(file));
		String temp=null;
		//创建list集合
		List<String> strList= new ArrayList<String>();
		//按行读取文件内容，并存放到集合中
		while((temp=reader.readLine())!=null){
			
			strList.add(temp);
		}
		reader.close();
		return strList;
	}
	/**
	 * 主函数：求集合的交集并集和比例
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		Collection c1=new Collection();
		Collection c2=new Collection();
		List<String> strList1=c1.readFile("/Users/huanghuizhen/Desktop/1.txt");
		List<String> strList2=c2.readFile("/Users/huanghuizhen/Desktop/2.txt");
		//用strList3保存两集合的交集
		//List<String> strList3=strList2;
		List<String> strList3=new ArrayList<String>();
		//用strList4保存两集合的并集
		List<String> strList4=new ArrayList<String>();
		for(int i=0;i<strList2.size();i++){
			strList3.add(strList2.get(i));
			strList4.add(strList2.get(i));
		}	
		//求交集
		strList3.retainAll(strList1);
		System.out.println("交集的大小："+strList3.size());
		for(int i=0;i<strList3.size();i++){
			System.out.print(strList3.get(i)+"  ");
		}
		System.out.println();
		//求两集合的并集
		//List<String> strList4=strList2;
		 //System.out.println(strList3==strList2);
		strList4.removeAll(strList1);
		strList4.addAll(strList1);
		System.out.println("并集的大小："+strList4.size());
		for(int i=0;i<strList4.size();i++){
			System.out.print(strList4.get(i)+"  ");
		}
		System.out.println();
		//文件1.txt单词总数
		int num1=strList1.size();
		//文件2.txt单词总数
		int num2=strList2.size();
		//单词wd∈1 且wd ∉2的单词占1文件的百分比 
		//创建一个数值格式化对象
		NumberFormat numberFormat=NumberFormat.getInstance();
		//设置精确到小数点后3位
		numberFormat.setMaximumFractionDigits(3);
		String per1=numberFormat.format((float)(num1-strList3.size())/(float)num1*100);
		String per2=numberFormat.format((float)(num2-strList3.size())/(float)num2*100);
//		double per1=(num1-strList3.size())/num1;
//		double per2=(num2-strList3.size())/num2;
		System.out.println("文件1的单词数："+num1);
		System.out.println("文件2的单词数："+num2);
		System.out.println("单词属于文件1但不属于文件2所占文件1的比例"+per1+"%");
		System.out.println("单词属于文件2但不属于文件1所占文件2的比例"+per2+"%");
				
	}

}
