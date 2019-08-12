#include<studio.c>

/**
二进制的加法用位运算模拟
分三步：
第一步：不考虑进位，对每一位相加。------相当于异或
第二步：考虑进位，只有1+1时会有进位；------相当于与运算
            再向左移动一位。
第三步：将前两步结果相加。------相加过程仍然重复前两步，直至不产生进位为止。
*/
int add (int num1, int num2)
{
     int sum, carry;
     do{
          sum = num1^num2;     //第一步，异或
          carry = (num1 & num2) << 1;          //第二步，与运算，然后左移
          //第三步，重复前面两步
          num1 = sum;
          num2 = carry;
     }
     while(num2 != 0);     //当不在有进位时，停止
     return num1;
}


int main(){
	int a;
	a = add(30,5);
	printf("%d",a);
	return 0;
}