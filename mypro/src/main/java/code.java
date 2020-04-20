package com.study.practice.exercises.first;

/**
* 题目： 古典问题：有一对兔子， 从出生后第 3 个月起每个月都生一对兔子， 小兔子长到第三
* 个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子对数为多少？
*/
public class  {
public static void main(String[] args) {
Integer month = 99;
System.out.println("第" + month + "个月的对数为： " + num(month));
}

public static Integer num(Integer month) {
//月份小于1时
if (month < 1) {
return 0;
}
//月份为1月或者2月时
if (month == 1 || month == 2) {
return 1;
}
//月份为1月或者2月时
if (month > 2) {
return num(month - 1) + num(month - 2);
}
return 0;
}
}
