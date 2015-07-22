package evilp0s;

import evilp0s.algorithmImpl.StringImpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供些常用的字符串相关的工具方法
 */
public class StringUtil {

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @param str 判断的字符串
     * @return 是否有效
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param list  需要处理的列表
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(List list, String symbol) {
        String result = "";
        if (list != null) {
            for(Object o:list){
                String temp = o.toString();
                if (temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     *
     * @param str1 测试的字符串
     * @param str2 字符串数组(用,分割)
     * @return 是否包含
     *
     */
    public static boolean requals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for(String t:arr){
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     *
     * @param str1  测试的字符串
     * @param str2  字符串数组
     * @param split str2字符串的分隔符
     * @return 是否包含
     */
    public static boolean requals(String str1, String str2, String split) {
        if (str1 != null && str2 != null) {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(split);
            for(String t:arr){
                if (t.equals(str1.trim())) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 字符串省略截取
     * 字符串截取到指定长度size+...的形式
     *
     * @param subject 需要处理的字符串
     * @param size 截取的长度
     * @return 处理后的字符串
     */
    public static String subStringOmit(String subject, int size) {
        if (subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }


    /**
     * 截取字符串　超出的字符用symbol代替
     *
     * @param str 需要处理的字符串
     * @param len 字符串长度　
     * @param symbol 最后拼接的字符串
     * @return 测试后的字符串
     */
    public static String subStringSymbol(String str, int len, String symbol) {
        String temp="";
        if (str != null && str.length() > len) {
            temp = str.substring(0, len) + symbol;
        }
        return temp;
    }


    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     *
     * @param array 需要处理的字符串数组
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(String[] array, String symbol) {
        String result = "";
        if (array != null) {
            for(String temp:array){
                if (temp != null && temp.trim().length() > 0)
                    result += (temp + symbol);
            }
            if (result.length() > 1 && ValidUtil.isValid(symbol)) {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }



    /**
     * 隐藏邮件地址前缀。
     *
     * @param email - EMail邮箱地址 例如: ssss@koubei.com 等等...
     * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
     */
    public static String getHideEmailPrefix(String email) {
        if (null != email) {
            int index = email.lastIndexOf('@');
            if (index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }

    /**
     * repeat - 通过源字符串重复生成N次组成新的字符串。
     *
     * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
     * @param num - 重复生成次数
     * @return 返回已生成的重复字符串
     */
    public static String repeat(String src, int num) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++)
            s.append(src);
        return s.toString();
    }

    /**
     * 截取字符串左侧的Num位截取到末尾
     *
     * @param str1 处理的字符串
     * @param num 开始位置
     * @return 截取后的字符串
     */
    public static String ltrim(String str1, int num) {
        String tt="";
        if (!isEmpty(str1) && str1.length() >= num) {
            tt = str1.substring(num, str1.length());
        }
        return tt;

    }

    /**
     * 截取字符串右侧第0位到第Num位
     *
     * @param str 处理的字符串
     * @param num 截取的位置
     * @return 截取后的字符串
     */
    public static String rtrim(String str, int num) {
        if (!isEmpty(str) && str.length() > num) {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }

    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param src 处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> parseString2List(String src,String pattern) {
        List<String> list = new ArrayList<>();
        if (src != null){
            String[] tt =src.split(pattern);
            for(int i=0;i<tt.length;i++){
                list.add(tt[i]);
            }
        }
        return list;
    }

    /**
     * 格式化一个float
     *
     * @param format 要格式化成的格式 such as #.00, #.#
     * @return 格式化后的字符串
     */
    public static String formatDouble(double f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }



    /**
     * 截取字符串左侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String left(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }

    /**
     * 截取字符串右侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     * @Summary 其他编码的有待测试
     */
    public static String right(String input, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }

    /**
     * 全角括号转为半角
     *
     * @param str
     * @return
     * @author shazao
     * @date 2007-11-29
     */
    public static String replaceBracketStr(String str) {
        if (str != null && str.length() > 0) {
            str = str.replaceAll("（", "(");
            str = str.replaceAll("）", ")");
        }
        return str;
    }

    /**
     * 全角字符变半角字符
     *
     * @param str
     * @return
     * @author shazao
     * @date 2008-04-03
     */
    public static String full2Half(String str) {
        if (str == null || "".equals(str))
            return "";
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c >= 65281 && c < 65373)
                sb.append((char) (c - 65248));
            else
                sb.append(str.charAt(i));
        }

        return sb.toString();

    }

    /**
     * 存文本替换
     *
     * @param s  源字符串
     * @param sf 子字符串
     * @param sb 替换字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String s, String sf, String sb) {
        int i = 0, j = 0;
        int l = sf.length();
        boolean b = true;
        boolean o = true;
        String str = "";
        do {
            j = i;
            i = s.indexOf(sf, j);
            if (i > j) {
                str += s.substring(j, i);
                str += sb;
                i += l;
                o = false;
            } else {
                str += s.substring(j);
                b = false;
            }
        } while (b);
        if (o) {
            str = s;
        }
        return str;
    }

    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @param str
     * @return
     * @author shazao
     * @date 2007-08-17
     */
    public static String replaceBlank(String str) {
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 解析字符串返回map键值对(例：a=1&b=2 => a=1,b=2)
     *
     * @param query   源参数字符串
     * @param split1  键值对之间的分隔符（例：&）
     * @param split2  key与value之间的分隔符（例：=）
     * @param dupLink 重复参数名的参数值之间的连接符，连接后的字符串作为该参数的参数值，可为null
     *                null：不允许重复参数名出现，则靠后的参数值会覆盖掉靠前的参数值。
     * @return map
     * @author sky
     */
    public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {
        if (!isEmpty(query) && query.indexOf(split2) > 0) {
            Map<String, String> result = new HashMap();

            String name = null;
            String value = null;
            String tempValue = "";
            int len = query.length();
            for (int i = 0; i < len; i++) {
                char c = query.charAt(i);
                if (c == split2) {
                    value = "";
                } else if (c == split1) {
                    if (!isEmpty(name) && value != null) {
                        if (dupLink != null) {
                            tempValue = result.get(name);
                            if (tempValue != null) {
                                value += dupLink + tempValue;
                            }
                        }
                        result.put(name, value);
                    }
                    name = null;
                    value = null;
                } else if (value != null) {
                    value += c;
                } else {
                    name = (name != null) ? (name + c) : "" + c;
                }
            }

            if (!isEmpty(name) && value != null) {
                if (dupLink != null) {
                    tempValue = result.get(name);
                    if (tempValue != null) {
                        value += dupLink + tempValue;
                    }
                }
                result.put(name, value);
            }

            return result;
        }
        return null;
    }

    /**
     * 从指定位置开始截取指定长度的字符串
     *
     * @param input 输入字符串
     * @param index 截取位置，左侧第一个字符索引值是1
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String middle(String input, int index, int count) {
        if (isEmpty(input)) {
            return "";
        }
        count = (count > input.length() - index + 1) ? input.length() - index + 1 : count;
        return input.substring(index - 1, index + count - 1);
    }


    /**
     * 判断字符串数组中是否包含某字符串元素
     *
     * @param substring 某字符串
     * @param source    源字符串数组
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String[] source) {
        if (source == null || source.length == 0) {
            return false;
        }
        for (int i = 0; i < source.length; i++) {
            String aSource = source[i];
            if (aSource.equals(substring)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @function:字符串转换unicode
     * @实现native2ascii.exe类似的功能
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * @fuunction:unicode 转字符串
     * @实现native2ascii.exe类似的功能
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }


    /**
     * 删除所有的标点符号
     *
     * @param str
     * @return
     */
    public static String trimPunct(String str) {
        return str.replaceAll("[\\pP\\p{Punct}]", "");
    }

    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarityRatio(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarityRatio(str1, str2);
        } else {
            return StringImpl.SimilarityRatio(str2, str1);
        }

    }

    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarDegree(String str1, String str2) {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length()) {
            return StringImpl.SimilarDegree(str1, str2);
        } else {
            return StringImpl.SimilarDegree(str2, str1);
        }
    }

    /**
     * 获取字符串的编码
     *
     * @param str
     * @return
     */
    public static String SimpleEncoding(String str) {
        return StringImpl.simpleEncoding(str);
    }

    /**
     * 获取字符串的编码
     */
    public static String cpDetector(String str) {
        return StringImpl.encoding(str);
    }

    /**
     * 获取字符串str在String中出现的次数
     *
     * @param string
     * @param str
     * @return
     */
    public static int countSubStr(String string, String str) {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0)) {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1) {
            count++;
            index += str.length();
        }
        return count;
    }


    /**
     * 替换一个出现的子串
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public static String replaceFirst(String s, String sub, String with) {
        int i = s.indexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 替换最后一次出现的字串
     * Replaces the very last occurrence of a substring with supplied string.
     *
     * @param s    source string
     * @param sub  substring to replace
     * @param with substring to replace with
     */
    public static String replaceLast(String s, String sub, String with) {
        int i = s.lastIndexOf(sub);
        if (i == -1) {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }


    /**
     * 删除所有的子串
     * Removes all substring occurrences from the string.
     *
     * @param s   source string
     * @param sub substring to remove
     */
    public static String remove(String s, String sub) {
        int c = 0;
        int sublen = sub.length();
        if (sublen == 0) {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do {
            sb.append(s.substring(c, i));
            c = i + sublen;
        } while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }
}
