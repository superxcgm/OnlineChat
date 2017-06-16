package xcbean;
public abstract class SingleTable
{
	public abstract boolean update();
	public abstract boolean delete();
	protected static final String split = "[superxc_split]";
	protected static final String line_split = "[superxc_line_split]";
	public static final int DATA_RAW = 1;
	protected boolean dirty = false; /* 当前数据项的数据是否被修改过 */
	protected boolean raw = false; /* 是否是裸数据 */
	protected String strErr = null; /* 错误信息 */
	protected XCDatabase xcDatabase = new XCDatabase();
}