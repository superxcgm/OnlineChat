package xcbean;
import java.sql.*;

public class ChatRoom extends SingleTable
{
	public static void main(String[] args)
	{
		
	}

	public static ChatRoom find(int flag, String arg)
	{
		ChatRoom crAns = null;
		XCDatabase xcDatabase = new XCDatabase();
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		switch(flag){
			case FIND_BY_ID:
				int aCr_id = Integer.parseInt(arg);
				pst = xcDatabase.prepareStatement(String.format("SELECT cr_id, cr_name FROM %s WHERE %s = ?", tableName, primaryKey));
				pst.setInt(1, aCr_id);
				rs = pst.executeQuery();
				if(rs.next()){
					crAns = new ChatRoom();
					crAns.cr_id = rs.getInt("cr_id");
					crAns.cr_name = rs.getString("cr_name"); 
				}
				break;
		}
		xcDatabase.close();
		return uAns;
	}
	public ChatRoom()
	{
		
	}

	public ChatRoom(int aCr_id, String aCr_name)
	{
		this();
		setCr_id(aCr_id);
		setCr_name(aCr_name);
	}
	public ChatRoom(int aCr_id, String aCr_name, int aRaw)
	{
		raw = true;
		cr_id = aCr_id;
		cr_name = aCr_name;
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
			pst.setInt(1, cr_id);
			pst.setString(2, cr_name);
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
		return false;
	}
	public void setCr_id(int aCr_id)
	{
		cr_id = aCr_id;
	}
	public int getCr_id()
	{
		return cr_id;
	}
	public void setCr_name(String aCr_name)
	{
		String tmp = aCr_name.substring(0, (int)Math.min(20, aCr_name.length()));
		cr_name = tmp;
	}
	public String getCr_name()
	{
		return cr_name;
	}
	public static final int FIND_BY_ID = 1;

	private static final String tableName = "chat_room";
	private static final String primaryKey = "cr_id";

	private int cr_id = -1;
	private String cr_name;
}