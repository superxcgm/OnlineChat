package xcbean;
import java.sql.*;

public class XCFile extends SingleTable
{
	public static void main(String[] args)
	{
		
	}
	public XCFile()
	{

	}
	public XCFile(int aF_id, String aF_name, String aF_passwd, int f_owner)
	{
		setF_id(aF_id);
		setF_name(aF_name);
		setF_passwd(aF_passwd);
		setF_owner(f_owner);
	}
	public XCFile(int aF_id, String aF_name, String aF_passwd, int f_owner, int aRaw)
	{
		raw = true;
		f_id = aF_id;
		f_name = aF_name;
		f_passwd = aF_passwd;
		f_owner = aF_owner;
	}
	public boolean update()
	{
		return false;
	}
	public boolean delete()
	{
		return false;
	}
	public void setF_id(int aF_id)
	{
		f_id = aF_id;
	}
	public int getF_id()
	{
		return f_id;
	}
	public void setF_name(String aF_name)
	{
		f_name = aF_name;
	}
	public String getF_name()
	{
		return f_name;
	}
	public void setF_passwd(String aF_passwd)
	{
		f_passwd = aF_passwd;
	}
	public String getF_passwd()
	{
		return f_passwd;
	}
	public void setF_owner(int aF_owner)
	{
		f_owner = aF_owner;
	}
	public int getF_owner()
	{
		return f_owner;
	}
	private static final String tableName = "files";
	private static final String primaryKey = "f_id";

	private int f_id = -1;
	private String f_name;
	private String f_passwd;
	private int f_owner = -1;
}