package com.maxyao.test;

import java.io.*;
import java.util.*;

import com.google.protobuf.InvalidProtocolBufferException;
import com.maxyao.proto.PersonProto;
import com.maxyao.proto.PersonProto.Person;
import com.maxyao.proto.PersonProto.Person.PhoneNumber;

public class Test {

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @Title: main
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Person.Builder builder = Person.newBuilder();
		builder.setId(001);//传输为1
		builder.setName("max");
		builder.setMail("max@mail.com");
		builder.addPhone(Person.PhoneNumber.newBuilder().setPhone(12345678)
				.setType(Person.PhoneType.home).build());

		Person p = builder.build();

		byte[] buf = p.toByteArray();

		// 把序列化后的数据写入本地磁盘
		ByteArrayInputStream stream = new ByteArrayInputStream(buf);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream("F:/protobuf.txt"));// 设置输出路径
		BufferedInputStream bis = new BufferedInputStream(stream);
		int b = -1;
		while ((b = bis.read()) != -1) {
			bos.write(b);
		}
		bis.close();
		bos.close();

		// 读取序列化后写入磁盘的数据
		try {
			BufferedInputStream bis2 = new BufferedInputStream(
					new FileInputStream("F:/protobuf.txt"));
			byte b2 = -1;
			List<Byte> list = new LinkedList<Byte>();
			while ((b2 = (byte) bis2.read()) != -1) {
				list.add(b2);
			}
			bis2.close();
			int length = list.size();
			byte[] byt = new byte[length];
			for (int i = 0; i < length; i++) {
				byt[i] = list.get(i);
			}
			Person person01 = PersonProto.Person.parseFrom(byt);
			List<PhoneNumber> phones = person01.getPhoneList();
			String strPhone = "";
			for (PhoneNumber phone : phones) {
				strPhone += phone.getPhone() + "   ";
			}
			String strResult = person01.getName() + "," + person01.getId()
					+ "," + person01.getMail() + "," + strPhone;
			System.out.println(strResult);
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
	}

}
