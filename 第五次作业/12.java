public class Solution {
    public String intToRoman(int num) {
		// 最原始简单粗暴的解法
        int q=num/1000; // 千位
        int b=num/100%10; // 百位
        int s=num/10%10; // 十位
        int g=num%10; // 个位
        final String I="I",V="V",X="X",C="C",L="L",D="D",M="M";
        StringBuffer result=new StringBuffer();
        for(int i=0;i<q;i++){
            result.append(M);
        }
        
        if(b<4){
            for(int i=0;i<b;i++){
                result.append(C);
            }
        }else if(b==4){
            result.append(C);
            result.append(D);
        }else if(b==5){
            result.append(D);
        }else if(b<9){
            result.append(D);
            for(int i=0;i<(b-5);i++){
                result.append(C);
            }
        }else if(b==9){
            result.append(C);
            result.append(M);
        }
        if(s<4){
            for(int i=0;i<s;i++){
                result.append(X);
            }
        }else if(s==4){
            result.append(X);
            result.append(L);
        }else if(s==5){
            result.append(L);
        }else if(s<9){
            result.append(L);
            for(int i=0;i<(s-5);i++){
                result.append(X);
            }
        }else if(s==9){
            result.append(X);
            result.append(C);
        }
        
        if(g<4){
            for(int i=0;i<g;i++){
                result.append(I);
            }
        }else if(g==4){
            result.append(I);
            result.append(V);
        }else if(g==5){
            result.append(V);
        }else if(g<9){
            result.append(V);
            for(int i=0;i<(g-5);i++){
                result.append(I);
            }
        }else if(g==9){
            result.append(I);
            result.append(X);
        }
        return result.toString();
    }
}