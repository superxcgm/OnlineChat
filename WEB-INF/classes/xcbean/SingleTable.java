package xcbean;
public abstract class SingleTable
{
	public abstract boolean update();
	public abstract boolean delete();
	protected boolean dirty = false; /* 当前数据项的数据是否被修改过 */
	protected String primaryKey = null;
	protected String tableName = null; /* 相关联的数据表名 */
	protected boolean raw = false; /* 是否是裸数据 */
	protected String strErr = null; /* 错误信息 */
}