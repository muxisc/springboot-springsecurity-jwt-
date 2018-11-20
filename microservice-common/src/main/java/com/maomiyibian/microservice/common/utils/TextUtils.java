package com.maomiyibian.microservice.common.utils;

import org.apache.commons.net.util.Base64;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-9-12 16:13
 */
public class TextUtils {
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !"".equals(value);
    }

    public static boolean isNotEmpty(String... values) {
        boolean flag = true;
        if (values != null) {
            String[] arg1 = values;
            int arg2 = values.length;

            for (int arg3 = 0; arg3 < arg2; ++arg3) {
                String value = arg1[arg3];
                flag &= isNotEmpty(value);
            }
        }

        return flag;
    }

    public static String getWithEmpty(String data) {
        return data == null ? "" : data;
    }

    public static String convertPropertyName(String name) {
        return name != null
                ? (name.equals(name.toUpperCase())
                ? name.toLowerCase()
                : name.substring(0, 1).toLowerCase() + name.substring(1))
                : null;
    }

    public static String convertPropertyName(String name, String symbol) {
        if (name != null && name.indexOf(symbol) > 0) {
            String[] names = name.split(symbol);
            int lr = 0;

            for (int count = names.length; lr < count; ++lr) {
                if (lr == 0) {
                    names[lr] = names[lr].toLowerCase();
                } else {
                    String result = names[lr].toLowerCase();
                    names[lr] = result.substring(0, 1).toUpperCase() + result.substring(1);
                }
            }

            return join(names, "");
        } else {
            return convertPropertyName(name);
        }
    }

    public static String join(String[] datas, String symbol) {
        StringBuffer result = null;
        String[] arg2 = datas;
        int arg3 = datas.length;

        for (int arg4 = 0; arg4 < arg3; ++arg4) {
            String data = arg2[arg4];
            if (data != null) {
                if (result == null) {
                    result = new StringBuffer(data);
                } else if (symbol == null) {
                    result.append(data);
                } else {
                    result.append(symbol).append(data);
                }
            }
        }

        if (result != null) {
            return result.toString();
        } else {
            return "";
        }
    }

    public static String join(String... data) {
        return join((String[]) data, (String) null);
    }

    public static String join(List<String> data, String symbol) {
        return data != null ? join((String[]) data.toArray(new String[0]), symbol) : "";
    }

    public static String join(List<String> data) {
        return data != null ? join((String[]) ((String[]) data.toArray(new String[0])), (String) null) : "";
    }

    public static String encode(String data) {
        return encode(data, "URL", "UTF-8");
    }

    public static String encode(String data, String charset) {
        return encode(data, "URL", charset);
    }

    public static String encode(String data, String type, String charset) {
        if (type == null) {
            return data;
        } else {
            String result = null;

            try {
                if (type != null && !"URL".equals(type)) {
                    if ("BASE64".equals(type.toUpperCase())) {
                        result = Base64.encodeBase64String(data.getBytes(charset));
                    }
                } else {
                    result = URLEncoder.encode(data, charset);
                }
            } catch (Exception arg4) {
                System.out.println("TextUtils.encode(data,type,charset)错误：" + arg4.getMessage());
            }

            return result;
        }
    }

    public static String decode(String data) {
        return decode(data, "URL", "UTF-8");
    }

    public static String decode(String data, String charset) {
        return decode(data, "URL", charset);
    }

    public static String decode(String data, String type, String charset) {
        if (type == null) {
            return data;
        } else {
            String result = null;

            try {
                if (type != null && !"URL".equals(type)) {
                    if ("BASE64".equals(type.toUpperCase())) {
                        result = new String(Base64.decodeBase64(data), charset);
                    }
                } else {
                    result = URLDecoder.decode(data, charset);
                }
            } catch (Exception arg4) {
                System.out.println("TextUtils.decode(data,type,charset)错误：" + arg4.getMessage());
            }

            return result;
        }
    }

    public static String handleURL(String url, String parameter, String defaultURL) {
        String result = "";
        if (isNotEmpty(url)) {
            result = url;
        } else if (isNotEmpty(defaultURL)) {
            result = defaultURL;
        }

        return result.lastIndexOf("?") > 0
                ? (result.lastIndexOf("=") > 0 ? result + "&" + parameter : result + parameter)
                : (isNotEmpty(parameter) ? result + "?" + parameter : result);
    }

    public static String handleURL(String url, String parameter) {
        return handleURL(url, parameter, (String) null);
    }

    public static boolean contain(String data, String includeName, String excludeName) {
        boolean available = true;
        String currentData = "," + data.toUpperCase() + ",";
        if (isNotEmpty(includeName)) {
            if (("," + includeName + ",").toUpperCase().indexOf(currentData) >= 0) {
                available = true;
            } else {
                available = false;
            }
        }

        if (isNotEmpty(excludeName)) {
            if (("," + excludeName + ",").toUpperCase().indexOf(currentData) >= 0) {
                available &= false;
            } else {
                available &= true;
            }
        }

        return available;
    }

    public static String handleMap(String... dataMap) {
        if (dataMap != null) {
            int count = dataMap.length;
            if (count > 0) {
                if (count == 1) {
                    return dataMap[0];
                }

                String dataFrom = dataMap[0];
                String dataTo;
                if (count == 2) {
                    dataTo = dataMap[1];
                    if (dataFrom != null) {
                        return dataFrom;
                    }

                    return dataTo;
                }

                dataTo = dataMap[count - 1];
                if ((count - 2) % 2 == 0) {
                    count -= 2;
                }

                if ((count - 1) % 2 == 0) {
                    dataTo = dataFrom;
                }

                for (int lr = 1; lr < count; ++lr) {
                    if (dataFrom == null) {
                        String dataCurrent = dataMap[lr];
                        if (dataCurrent == null) {
                            return dataMap[lr + 1];
                        }
                    } else if (dataFrom.equals(dataMap[lr])) {
                        return dataMap[lr + 1];
                    }
                }

                return dataTo;
            }
        }

        return null;
    }
}
