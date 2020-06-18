package com.javaex.phone;

import java.io.*;
import java.util.*;

public class PhoneApp {
	public static void main(String[] args) throws IOException {
		PhoneDao phoneDao = new PhoneDao();
		Scanner sc = new Scanner(System.in);
		String name, hp, company;
		int personID;

		System.out.println("*************************************************************************");
		System.out.println("*                         전화번호 관리 프로그램                        *");
		System.out.println("*************************************************************************");

		while (true) {
			System.out.println("\n-------------------------------------------------------------------------");
			System.out.println("  1. 리스트  |  2. 등록  |  3. 수정  |  4. 삭제  |  5. 검색  |  6. 종료  ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.print("메뉴 번호 >> ");
			int menuNum = sc.nextInt();

			// 기능별 반복문
			switch (menuNum) {
			
			// 1번 선택 시 리스트 출력
			case 1:
				List<PhoneVo> phoneData = phoneDao.dataSearch("");

				for (PhoneVo vo : phoneData) {
					System.out.println(
							vo.getPersonID() + ".  " + vo.getName() + "   " + vo.getHp() + "   " + vo.getCompany());
				}
				continue;

			// 2번 선택 시 정보 등록
			case 2:
				System.out.println("\n-------------------------------------------------------------------------");
				System.out.println("                            정보를 입력해주세요.                           ");
				System.out.println("-------------------------------------------------------------------------");

				System.out.print("이름 >> ");
				name = sc.next();

				System.out.print("전화번호 >> ");
				hp = sc.next();

				System.out.print("회사번호 >> ");
				company = sc.next();

				PhoneVo pVo1 = new PhoneVo(name, hp, company);
				phoneDao.dataInsert(pVo1);

				continue;

			// 3번 선택 시 정보 수정
			case 3:
				System.out.println("\n-------------------------------------------------------------------------");
				System.out.println("                 수정할 번호 선택 후 정보를 입력해주세요.                 ");
				System.out.println("-------------------------------------------------------------------------");

				System.out.print("번호 >> ");
				personID = sc.nextInt();

				sc.nextLine();

				System.out.print("이름 >> ");
				name = sc.next();

				System.out.print("전화번호 >> ");
				hp = sc.next();

				System.out.print("회사번호 >> ");
				company = sc.next();

				PhoneVo pVo2 = new PhoneVo(personID, name, hp, company);
				phoneDao.dataUpdate(pVo2);
				continue;

			// 4번 선택 시 정보 삭제
			case 4:
				System.out.println("\n-------------------------------------------------------------------------");
				System.out.println("                       삭제할 번호를 입력해주세요.                       ");
				System.out.println("-------------------------------------------------------------------------");
				System.out.print("번호 >> ");
				personID = sc.nextInt();

				phoneDao.dataDelete(personID);
				continue;

			// 5번 선택 시 검색
			case 5:
				System.out.println("\n-------------------------------------------------------------------------");
				System.out.println("                       검색할 글자를 입력해주세요.                       ");
				System.out.println("              (해당 글자가 포함된 모든 목록을 불러옵니다.)               ");
				System.out.println("-------------------------------------------------------------------------");
				System.out.print("입력 >> ");
				String search = sc.next();
				
				phoneData = phoneDao.dataSearch(search);
				
				for (PhoneVo vo : phoneData) {
					System.out.println(
							vo.getPersonID() + ".  " + vo.getName() + "   " + vo.getHp() + "   " + vo.getCompany());
				}
				
				continue;

			// 6번 선택 시 종료
			case 6:
				System.out.println("\n*************************************************************************");
				System.out.println("*                              감사합니다.                              *");
				System.out.println("*************************************************************************");

				break;
				
			// 메뉴 번호와 무관한 번호 입력 시 재입력 요청
			default:
				System.out.print("\n                        [   다시 입력해주세요.   ]                       ");
				continue;
			} // case 끝
			
			break;
			
		} // while 끝
		
		sc.close();
	} // main 끝
}
