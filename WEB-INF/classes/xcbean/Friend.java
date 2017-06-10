package xcbean;
import java.sql.*;

public class Friend extends SingleTable
{
	public static void main(String[] args)
	{

	}
	public static Friend find(int aUser_id_1, int aUser_id_2)
	{
		Friend fAns = null;
		XCDatabase xcDatabase = new XCDatabase();
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		try{
			pst = xcDatabase.prepareStatement(String.format("SELECT user_id_1, user_id_2 FROM %s WHERE user_id_1 = ? AND user_id_2 = ?", tableName));
			pst.setInt(1, aUser_id_1);
			pst.setInt(2, aUser_id_2);
			rs = pst.executeQuery();
			if(rs.last()){
				fAns = new Friend();
				fAns.user_id_1 = rs.getInt("user_id_1");
				fAns.user_id_2 = rs.getInt("user_id_2");
			}
		}catch(SQLException exp){

		}
		
		xcDatabase.close();
		return fAns;
	}
	public Friend()
	{
		
	}
	public Friend(int aUser_id_1, int aUser_id_2)
	{
		this();
		setUser_id_1(aUser_id_1);
		setUser_id_2(aUser_id_2);
	}
	public Friend(int aUser_id_1, int aUser_id_2, int aRaw)
	{
		raw = true;
		user_id_1 = aUser_id_1;
		user_id_2 = aUser_id_2;
	}
	public boolean update()
	{
		if(raw)
			return false;
		boolean bAns = false;
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		try{
			pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s VALUES (?, ?)", tableName));
			pst.setInt(1, user_id_1);
			pst.setInt(2, user_id_2);
			int ans = pst.executeUpdate();
			if(ans > 0)
				bAns = true;
		}catch(SQLException sql){

		}
		xcDatabase.close();
		if(bAns == false)
			strErr = "未知系统错误!";
		return bAns;
	}

	public boolean delete()
	{
		/* relation get by find that can be delete */
		if(raw)
			return false;
		boolean bAns = false;
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		try{
			pst = xcDatabase.prepareStatement(String.format("DELETE FROM %s WHERE user_id_1 = ? AND user_id_2 = ?", tableName));
			pst.setInt(1, user_id_1);
			pst.setInt(2, user_id_2);
			int ans = pst.executeUpdate();
			if(ans > 0)
				bAns = true;
		}catch(SQLException sql){

		}
		xcDatabase.close();
		if(bAns == false)
			strErr = "未知系统错误!";
		return bAns;
	}
	public void setUser_id_1(int aUser_id_1)
	{
		user_id_1 = aUser_id_1;
	}
	public int getUser_id_1()
	{
		return user_id_1;
	}
	public void setUser_id_2(int aUser_id_2)
	{
		user_id_2 = aUser_id_2;
	}
	public int getUser_id_2()
	{
		return user_id_2;
	}
	private static final String tableName = "friends";

	private int user_id_1;
	private int user_id_2;
}