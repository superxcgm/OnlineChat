package xcbean;
import java.sql.*;

public class XCUser extends SingleTable
{
	public static void main(String[] args)
	{
		XCUser xcUser = XCUser.find(FIND_BY_ID, "1000001");
		System.out.print(xcUser.getFriendList());

	}
	public static XCUser find(int flag, String arg)
	{
		XCUser uAns = null;
		XCDatabase xcDatabase = new XCDatabase();
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		switch(flag){
			case FIND_BY_NAME:
				try{
					String aUser_name = arg;
					pst = xcDatabase.prepareStatement(String.format("SELECT user_id, user_name, user_nick, user_pwd, user_email FROM %s WHERE user_name = ?", tableName));
					pst.setString(1, aUser_name);	
					rs = pst.executeQuery();
					if(rs.next()){
						uAns = new XCUser();
						uAns.user_id = rs.getInt("user_id");
						uAns.user_name = rs.getString("user_name");
						uAns.user_nick = rs.getString("user_nick");
						uAns.user_pwd = rs.getString("user_pwd");
						uAns.user_email = rs.getString("user_email");
					}
				}catch(SQLException e){
					System.out.println(e);
				}
				break;
			case FIND_BY_ID:
				int aUser_id = Integer.parseInt(arg);
				try{
					pst = xcDatabase.prepareStatement(String.format("SELECT user_id, user_name, user_nick, user_pwd, user_email FROM %s WHERE user_id = ?", tableName));
					pst.setInt(1, aUser_id);
					rs = pst.executeQuery();
					if(rs.next()){
						uAns = new XCUser();
						uAns.user_id = rs.getInt("user_id");
						uAns.user_name = rs.getString("user_name");
						uAns.user_nick = rs.getString("user_nick");
						uAns.user_pwd = rs.getString("user_pwd");
						uAns.user_email = rs.getString("user_email");
					}
				}catch(SQLException e){
					System.out.println(e);
				}
				break;
		}
		xcDatabase.close();
		return uAns;
	}
	public static XCUser[] blurSearch(String keyword)
	{
		/* keyword must be deal with before call this */
		XCDatabase xcDatabase = new XCDatabase();
		XCUser[] uAns = null;
		xcDatabase.connect();
		PreparedStatement pst;
		ResultSet rs;
		try{
			pst = xcDatabase.prepareStatement(String.format("SELECT user_id, user_email, user_nick FROM %s WHERE user_id = ? OR user_nick LIKE ? OR user_nick = ? OR user_nick LIKE ? OR user_nick LIKE ? OR user_email LIKE ? OR user_email = ? OR user_email LIKE ? OR user_email LIKE ?", tableName));
			int iKeyword;
			try{
				iKeyword = Integer.parseInt(keyword);	
			}catch(NumberFormatException exp){
				iKeyword = 0;
			}
			
			pst.setInt(1, iKeyword);
			pst.setString(2, "%" + keyword + "%");
			pst.setString(3, keyword);
			pst.setString(4, "%" + keyword);
			pst.setString(5, keyword + "%");
			pst.setString(6, "%" + keyword + "%");
			pst.setString(7, keyword);
			pst.setString(8, "%" + keyword);
			pst.setString(9, keyword + "%");
			rs = pst.executeQuery();
			rs.last();
			int count = rs.getRow();
			uAns = new XCUser[count];
			rs.beforeFirst();
			int i = 0;
			// public XCUser(int aUser_id, String aUser_name, String aUser_pwd, String aUser_email, String aUser_nick, int aRaw)
			while(rs.next())
				uAns[i++] = new XCUser(rs.getInt("user_id"), "", "", rs.getString("user_email"), rs.getString("user_nick"), 1); /* raw */
		}catch(SQLException e){
			System.out.println(e);
		}
		xcDatabase.close();
		return uAns;
	}
	public XCUser()
	{
		
	}
	public XCUser(String aUser_name, String aUser_pwd, String aUser_email)
	{
		this(aUser_name, aUser_pwd, aUser_email, "Do not set nickName");
	}
	public XCUser(String aUser_name, String aUser_pwd, String aUser_email,  String aUser_nick)
	{
		this(); /* call empty argument constructor */
		
		setUser_name(aUser_name);
		setUser_pwd(aUser_pwd);
		setUser_email(aUser_email);
		setUser_nick(aUser_nick);
	}
	public XCUser(String aUser_name, String aUser_pwd, String aUser_email, String aUser_nick, int aRaw)
	{
		/* raw data can not update to database */
		raw = true;

		user_name = aUser_name;
		user_pwd = aUser_pwd;
		user_email = aUser_email;
		user_nick = aUser_nick;

	}
	public XCUser(int aUser_id, String aUser_name, String aUser_pwd, String aUser_email, String aUser_nick, int aRaw)
	{
		/* raw data can not update to database */
		this(aUser_name, aUser_pwd, aUser_email, aUser_nick, 1);
		user_id = aUser_id;
	}
	public String getMsg()
	{
		return MsgRecord.getMsg(this);
	}
	public int getFriendCount()
	{
		return Friend.getFriendCount(this);
	}
	public String getFriendList()
	{
		return Friend.getFriendList(this);
	}
	public boolean isFriend(XCUser user)
	{
		return Friend.find(user_id, user.getUser_id()) != null;
	}
	public boolean update()
	{
		if(raw)
			return false;
		boolean bAns = false;
		xcDatabase.connect();
		PreparedStatement pst;
		// System.out.println("user_id:" + user_id);
		if(user_id == -1){ /* new user */
			pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s (user_name, user_pwd, user_email, user_nick) VALUES(?, ?, ?, ?)", tableName));
			try{
				pst.setString(1, user_name);
				pst.setString(2, user_pwd);
				pst.setString(3, user_email);
				pst.setString(4, user_nick);
				int ans = pst.executeUpdate();
				// System.out.println("ans:" + ans);
				if(ans > 0)
					bAns = true;
			}catch(SQLException e){
				strErr = "用户名已经存在！";
				// System.out.println(e);
			}
		}else{
			if(dirty){
				pst = xcDatabase.prepareStatement(String.format("UPDATE %s SET user_pwd = ?, user_email = ?, user_nick = ? WHERE user_id = ?", tableName));
				try{
					pst.setString(1, user_pwd);
					pst.setString(2, user_email);
					pst.setString(3, user_nick);
					pst.setInt(4, user_id);
					int ans = pst.executeUpdate();
					if(ans > 0)
						bAns = true;
				}catch(SQLException e){
					strErr = "未知系统错误。";
					System.out.println(e);
				}
			}else{
				/* data not dirty, do not need to update*/
				bAns = true;
			}
		}
		xcDatabase.close();
		return bAns;
	}
	public boolean delete()
	{
		return false;
	}

	public boolean checkPwd(String aUser_pwd)
	{
		String tmp = xcDatabase.MD5(aUser_pwd);
		return tmp.equals(user_pwd);
	}

	/* setter and getter method */

	/* can not modify user_id */
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_email(String aUser_email)
	{
		/* 50 characters max */
		String tmp;
		tmp = aUser_email.substring(0, (int)Math.min(50, aUser_email.length()));
		if(!tmp.equals(user_email)){
			dirty = true;
			user_email = tmp;	
		}
	}
	public String getUser_email()
	{
		return user_email;
	}
	public void setUser_pwd(String aUser_pwd)
	{
		// System.out.println("aUser_pwd:" + aUser_pwd);
		String tmp = xcDatabase.MD5(aUser_pwd);
		// System.out.println("tmp:" + tmp);
		if(!tmp.equals(user_email)){
			dirty = true;
			user_pwd = tmp;	
		}
	}
	public String getUser_pwd()
	{
		return user_pwd;
	}
	public void setUser_name(String aUser_name)
	{
		/* 6-20 characters */
		String tmp = aUser_name.substring(0, (int)Math.min(20, aUser_name.length()));
		if(!tmp.equals(user_name)){
			dirty = true;
			user_name = tmp;	
		}
		
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_nick(String aUser_nick)
	{
		/* 6-20 characters */
		String tmp = aUser_nick.substring(0, (int)Math.min(20, aUser_nick.length()));
		if(!tmp.equals(user_nick)){
			dirty = true;
			user_nick = tmp;	
		}
		
	}
	public String getUser_nick()
	{
		return user_nick;
	}
	public void setStrErr(String aStrErr)
	{
		strErr = aStrErr;
	}
	public String getStrErr()
	{
		return strErr;
	}
	public static final int FIND_BY_NAME = 1;
	public static final int FIND_BY_ID = 2;
	private static final String primaryKey = "user_id";
	private static final String tableName = "users";
	private int user_id = -1;
	private String user_email;
	private String user_pwd; /* md5 + salt */
	private String user_name;
	private String user_nick;
}