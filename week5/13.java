/**
 * 罗马数字规则：
1， 罗马数字共有7个，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。
罗马数字中没有“0”。
2， 重复次数：一个罗马数字最多重复3次。
3， 右加左减：
在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
4， 左减的数字有限制，仅限于I、X、C，且放在大数的左边只能用一个。
(*) V 和 X 左边的小数字只能用Ⅰ。
(*) L 和 C 左边的小数字只能用X。
(*) D 和 M 左 边的小数字只能用C。
 **/
public class Solution {
    public int romanToInt(String s) {
    HashMap<Character,Integer> map = new HashMap<Character,Integer>();
    char[] c=s.toCharArray();
         map.put('I',1);
        map.put('i',1);
        map.put('V',5);
        map.put('v',5);
        map.put('X',10);
        map.put('x',10);
        map.put('L',50);
        map.put('l',50);
        map.put('C',100);
        map.put('c',100);
        map.put('D',500);
        map.put('d',500);
        map.put('M',1000);
        map.put('m',1000);
        int res=0;
        int j;
        for(int i=0;i<c.length;i++){
            j=i+1;
            if(j<c.length && map.get(c[j]) > map.get(c[i])){
                res+=map.get(c[j])-map.get(c[i]);
                i=j;
            }else{
                res+=map.get(c[i]);
                
            }
        }
        return res;
 
    }
}