package com.vanstone.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vanstone.common.MyAssert;

public class SqlRemoveUtils {

    private static int indexOfByRegex(String input,String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if(m.find()) {
            return m.start();
        }
        return -1;
    }
    
	/**
	 * 去除select 子句，未考虑union的情况
	 * @param sql
	 * @return
	 */
    public static String removeSelect(String sql) {
    	MyAssert.hasText(sql);
        int beginPos = indexOfByRegex(sql.toLowerCase(),"\\sfrom\\s");
        MyAssert.isTrue(beginPos != -1, " sql : " + sql + " must has a keyword 'from'");
        return sql.substring(beginPos);
    }

    /**
     * 去除orderby 子句
     * @param sql
     * @return
     */
    public static String removeOrders(String sql) {
    	MyAssert.hasText(sql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    public static String removeFetchKeyword(String sql) {
    	return sql.replaceAll("(?i)fetch", "");
    }

	public static String removeXsqlBuilderOrders(String string) {
		MyAssert.hasText(string);
        Pattern p = Pattern.compile("/~.*order\\s*by[\\w|\\W|\\s|\\S]*~/", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return removeOrders(sb.toString());
	}

}
