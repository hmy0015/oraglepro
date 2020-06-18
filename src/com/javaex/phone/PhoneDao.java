package com.javaex.phone;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class PhoneDao {
	/* 필드 */
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb", pw = "phonedb";
	private List<PhoneVo> PhoneData;

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/* 생성자 */
	public PhoneDao() throws IOException {	}

	/* 메소드 */
	// 드라이버 로딩, Connection 얻어오기
	public void getConnect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 등록
	public void dataInsert(PhoneVo phoneVo) {
		try {
			// 1, 2. 드라이버 로딩, Connection 얻어오기
			getConnect();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO person ";
			query += " VALUES (seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, phoneVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, phoneVo.getHp()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, phoneVo.getCompany()); // ?(물음표) 중 3번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.print("\n                        [   등록되었습니다.   ]                        ");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close(); // 5. 자원 정리
	}

	// 수정
	public void dataUpdate(PhoneVo phoneVo) {
		try {
			// 1, 2. 드라이버 로딩, Connection 얻어오기
			getConnect();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update person ";
			query += " set name = ? , ";
			query += "     hp = ? , ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, phoneVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, phoneVo.getHp()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, phoneVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
			pstmt.setInt(4, phoneVo.getPersonID()); // ?(물음표) 중 3번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.print("\n                        [   수정되었습니다.   ]                        ");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close(); // 5. 자원 정리
	}

	// 삭제
	public void dataDelete(int personID) {
		try {
			// 1, 2. 드라이버 로딩, Connection 얻어오기
			getConnect();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from person ";
			query += " where person_id = ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, personID);// ?(물음표) 중 1번째, 순서중요

			pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.print("\n                        [   삭제되었습니다.   ]                        ");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close(); // 5. 자원 정리
	}

	// 검색 및 리스트 출력
	public List<PhoneVo> dataSearch(String search) {
		PhoneData = new ArrayList<PhoneVo>();
		try {
			// 1, 2. 드라이버 로딩, Connection 얻어오기
			getConnect();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " where name like ? ";
			query += " or hp like ? ";
			query += " or company like ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			search = '%' + search + '%';

			pstmt.setString(1, search);// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, search);// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(3, search);// ?(물음표) 중 1번째, 순서중요

			rs = pstmt.executeQuery();
			
			// 리스트에 추가
			while (rs.next()) {
				int personID = rs.getInt("person_id");
				String personName = rs.getString("name");
				String personHp = rs.getString("hp");
				String personCompany = rs.getString("company");

				// 리스트에 추가
				PhoneVo phoneVo = new PhoneVo(personID, personName, personHp, personCompany);
				PhoneData.add(phoneVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close(); // 5. 자원 정리
		return PhoneData;
	}
}