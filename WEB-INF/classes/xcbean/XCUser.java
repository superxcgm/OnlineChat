package xcbean;
import java.sql.*;

public class XCUser extends SingleTable
{
	public XCUser()
	{
		primaryKey = "user_id";
		tableName = "users";
		xcDatabase = new XCDatabase();
	}
	public XCUser( String aUser_name, String aUser_pwd, String aUser_email,  String aUser_nick = "")
	{
		this(); /* call empty argument constructor */
		user_name = aUser_name;
		user_pwd = xcDatabase.MD5(aUser_pwd);
		user_email = aUser_email;
		user_nick = aUser_nick;
	}
	public boolean update()
	{
		
		xcDatabase.connect();
		PreparedStatement pst;
		if(user_id == -1){ /* new user */
			pst = xcDatabase.prepareStatement(String.format("INSERT INTO %s (user_name, user_pwd, user_email, user_nick) VALUES(?, ?, ?, ?)", tableName));
			pst.setString(1, user_name);
			pst.setString(2, user_pwd);
			pst.setString(3, user_email);
			pst.setString(4, user_nick);
			if(pst.executeUpdate())
				return true;
			else
				return false;
		}else{
			if(dirty){
				/* update */
			}else{
				/* data not dirty, do not need to update*/
				return true;
			}
		}
		xcDatabase.close();
	}
	public boolean delete()
	{

	}
	private int user_id = -1;
	private String user_email;
	private String user_pwd; /* md5 + salt */
	private String user_name;
	private String user_nick;
	private XCDatabase xcDatabase = null;
}