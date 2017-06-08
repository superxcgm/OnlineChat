package xcbean;

public class Checker
{
	public static boolean isUpper(char ch)
	{
		return ch >= 'A' && ch <= 'Z';
	}
	public static boolean isLower(char ch)
	{
		return ch >= 'a' && ch <= 'z';
	}
	public static boolean isDigit(char ch)
	{
		return ch >= '0' && ch <= '9';
	}
	public static boolean isAlpha(char ch)
	{
		return isUpper(ch) || isLower(ch);
	}
	public static boolean isAlNum(char ch)
	{
		return isAlpha(ch) || isDigit(ch);
	}

	/* used only in form input check */
	public static String check(String str, int policy)
	{
		if(str == null)
			return " 不能为空!";
		if((policy & CH_ALNUM) != 0){
			for(int i = 0; i < str.length(); ++i)
				if(!isAlNum(str.charAt(i)))
					return " 只能为数字字母!";
		}
		if((policy & CH_MAIL) != 0)
		{
			if(str.indexOf("@") == -1)
				return "不是有效的邮箱地址!";
		}
		return null;
	}
	public static String check(String str, int policy, int minLen, int maxLen)
	{
		String ans = check(str, policy);
		if(ans != null)
			return ans;
		if((policy & CH_LEN) != 0){
			if(str.length() < minLen || str.length() > maxLen)
				return String.format(" 的长度必须是%d-%d位!", minLen, maxLen);
		}
		return null;
	}

	public static final int CH_ACCEPT = 0;
	public static final int CH_NULL = 1;
	public static final int CH_LEN = 2;
	public static final int CH_ALNUM = 4;
	public static final int CH_MAIL = 8;
}