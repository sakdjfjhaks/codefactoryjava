package com.study.practice.exercises.first;

/**
*${annotation}
*/
public class ${projectName} {
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
