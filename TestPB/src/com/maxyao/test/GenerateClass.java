package com.maxyao.test;

public class GenerateClass {
	/**
	 * 调用protoc.exe生成java数据访问类
	 * */
	public static void main(String[] args) throws Exception {
//		System.out.println("~~~");
		String protoFile = "person.proto";//如果要更换生成的数据访问类，只需在此进行更改
		//String protoFile = "person.proto";
		String strCmd = "protoc.exe --java_out=./src ./pb/" + protoFile;
		Runtime.getRuntime().exec("cmd /c " + strCmd).waitFor();//通过执行cmd命令调用protoc.exe程序

	}
}
