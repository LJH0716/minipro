package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhonebookApp {

	public static void main(String[] args) throws IOException {

		boolean action = true;
		Scanner sc = new Scanner(System.in);

		// 리스트 준비
		List<Person> personList = new ArrayList<Person>();

		// 읽기 준비
		Reader fr = new FileReader("./phoneDB.txt ");
		BufferedReader br = new BufferedReader(fr);

		// PhoneDB.txt --> List<Person>
		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}

			String[] personInfo = str.split(",");

			String name = personInfo[0];
			String hp = personInfo[1];
			String company = personInfo[2];

			Person person = new Person(name, hp, company);

			personList.add(person);
		}

		System.out.println("****************************************");
		System.out.println("*            전화번호 관리 프로그램           *");
		System.out.println("****************************************");
		System.out.println("");

		while (action) {
			System.out.print("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("");
			System.out.println("-----------------------------------------");
			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("<1.리스트>");
				for (int i = 0; i < personList.size(); i++) {
					System.out.print(i + 1 + ".    ");
					System.out.print(personList.get(i).getName() + "    ");
					System.out.print(personList.get(i).getHp() + "    ");
					System.out.println(personList.get(i).getCompany() + "    ");
				}
				break;

			case 2:
				System.out.println("<2.등록>");
				System.out.print(">이름");
				String name = sc.next();
				System.out.print(">휴대전화");
				String hp = sc.next();
				System.out.print(">회사전화");
				String company = sc.next();
				
				Person person = new Person(name, hp, company);

				personList.add(person);

				Writer fw = new FileWriter("./phoneDB.txt ");
				BufferedWriter bw = new BufferedWriter(fw);

				for (int i = 0; i < personList.size(); i++) {
					String str = personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany();
					bw.write(str);
					bw.newLine();
				}
				bw.close();
				System.out.println("[등록되었습니다.]");
				
				break;

			case 3:
				System.out.println("<3.삭제>");
				System.out.print("> 번호: ");
				int num1 = sc.nextInt();
				personList.remove(num1 - 1);
				
				Writer fw1 = new FileWriter("./phoneDB.txt ");
				BufferedWriter bw1 = new BufferedWriter(fw1);

				for (int i = 0; i < personList.size(); i++) {
					String str = personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany();
					bw1.write(str);
					bw1.newLine();
				}
				bw1.close();
				
				System.out.println("[삭제되었습니다.]");
	
				break;

			case 4:
				System.out.println("<4.검색>");
				break;

			case 5:
				System.out.println("<5.종료>");
				action = false;
				break;

			default:
				System.out.println("[다시 입력해 주세요.]");
				break;

			}

		}

		System.out.println("****************************************");
		System.out.println("*                감사합니다               *");
		System.out.println("****************************************");
		System.out.println("");

		
		br.close();
		sc.close();
	}

}
