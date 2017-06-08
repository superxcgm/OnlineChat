package xcbean;
import java.sql.*;

public class XCUser extends SingleTable
{
	public XCUser()
	{
		primaryKey = "user_id";
		tableName = "users";
		raw = false;
		xcDatabase = new XCDatabase();
	}
	public XCUser(String aUser_name, String aUser_pwd, String aUser_email)
	{
		String nick = "Do not set nickName";
		this(aUser_name, aUser_pwd, aUser_email, nick);
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
	public boolean update()
	{
		if(raw)
			return false;
		xcDatabase.connect();
		PreparedStatement pst;
		if(user_id == -1){ /* new user */
			pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s (user_name, user_pwd, user_email, user_nick) VALUES(?, ?, ?, ?)", tableName));
			try{
				pst.setString(1, user_name);
				pst.setString(2, user_pwd);
				pst.setString(3, user_email);
				pst.setString(4, user_nick);
				if(pst.executeUpdate() > 0)
					return true;
				else
					return false;	
			}catch(SQLException e){}
		}else{
			if(dirty){
				/* update */
			}else{
				/* data not dirty, do not need to update*/
				return true;
			}
		}
		xcDatabase.close();
		return true;
	}
	public boolean delete()
	{
		if(raw)
			return false;
		return true;
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
		String tmp = xcDatabase.MD5(aUser_pwd);
		if(!tmp.equals(user_email)){
			dirty = true;
			user_email = tmp;	
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
	private int user_id = -1;
	private String user_email;
	private String user_pwd; /* md5 + salt */
	private String user_name;
	private String user_nick;
	private boolean raw = false;
	private XCDatabase xcDatabase = null;
}