package com.maomiyibian.microservice.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import java.util.Map.Entry;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-9-12 16:12
 */
public class DataUtils {
    private static ResourceBundle resourceBundle = null;
    private static Map<String, String> platformPropertyData = new HashMap<String, String>();

    public static Map<String, Object> convertToMap(Object parameter) {
        if (parameter != null) {
            if (parameter instanceof Map) {
                return (Map) parameter;
            }

            try {
                return PropertyUtils.describe(parameter);
            } catch (Exception arg1) {
                System.out.println("DataUtils.convertToMap错误：" + arg1.getMessage());
            }
        }

        return new HashMap();
    }

    public static <T, V> Map<T, List<V>> mergeListByProperty(Collection<V> data, String propertyName) {
        HashMap map = new HashMap();
        Iterator arg2 = data.iterator();

        while (arg2.hasNext()) {
            Object currentData = arg2.next();
            Object name = getData(currentData, propertyName);
            if (map.containsKey(name)) {
                ((List) map.get(name)).add(currentData);
            } else {
                ArrayList list = new ArrayList();
                list.add(currentData);
                map.put(name, list);
            }
        }

        return map;
    }

    public static List<Map<String, Object>> mergeListByProperty(Collection<?> data, String name,
                                                                String... propertyName) {
        ArrayList list = new ArrayList();
        if (data != null && TextUtils.isNotEmpty(name) && TextUtils.isNotEmpty(propertyName)) {
            List propertyNameContainer = null;
            HashMap dataContainer = new HashMap();
            Object object = null;
            Iterator arg6 = data.iterator();

            while (arg6.hasNext()) {
                Object currentData = arg6.next();
                HashMap map = new HashMap();
                int count = propertyName.length;
                String[] ids = new String[count];
                String currentName = null;

                for (int id = 0; id < count; ++id) {
                    currentName = propertyName[id];
                    object = getData(currentData, currentName);
                    map.put(currentName, object);
                    if (object != null) {
                        ids[id] = object.toString();
                    } else {
                        ids[id] = "";
                    }
                }

                String arg13 = TextUtils.join(ids);
                propertyNameContainer = (List) dataContainer.get(arg13);
                if (propertyNameContainer != null) {
                    propertyNameContainer.add(currentData);
                } else {
                    ArrayList arg14 = new ArrayList();
                    dataContainer.put(arg13, arg14);
                    arg14.add(currentData);
                    map.put(name, arg14);
                    list.add(map);
                }
            }
        }

        return list;
    }

    public static List<Map<String, Object>> mergeListByProperty(Collection<?> data, String name,
                                                                Map<String, String> mapContainer, String... propertyName) {
        ArrayList list = new ArrayList();
        if (data != null && TextUtils.isNotEmpty(name) && TextUtils.isNotEmpty(propertyName) && mapContainer != null) {
            List propertyNameContainer = null;
            HashMap dataContainer = new HashMap();
            Object object = null;
            Iterator arg7 = data.iterator();

            while (arg7.hasNext()) {
                Object currentData = arg7.next();
                HashMap propertyDataContainer = new HashMap();
                Iterator map = mapContainer.entrySet().iterator();

                while (map.hasNext()) {
                    Entry count = (Entry) map.next();
                    propertyDataContainer.put(count.getValue(), getData(currentData, (String) count.getKey()));
                }

                HashMap arg16 = new HashMap();
                int arg17 = propertyName.length;
                String[] ids = new String[arg17];
                String currentName = null;

                for (int id = 0; id < arg17; ++id) {
                    currentName = propertyName[id];
                    object = getData((Map) propertyDataContainer, (String) currentName);
                    arg16.put(currentName, object);
                    propertyDataContainer.remove(currentName);
                    if (object != null) {
                        ids[id] = object.toString();
                    } else {
                        ids[id] = "";
                    }
                }

                String arg18 = TextUtils.join(ids);
                propertyNameContainer = (List) dataContainer.get(arg18);
                if (propertyNameContainer != null) {
                    propertyNameContainer.add(propertyDataContainer);
                } else {
                    ArrayList arg15 = new ArrayList();
                    dataContainer.put(arg18, arg15);
                    arg15.add(propertyDataContainer);
                    arg16.put(name, arg15);
                    list.add(arg16);
                }
            }
        }

        return list;
    }

    public static <T> Object getData(Object data, String propertyName) {
        if (data != null && data instanceof Map) {
            return getData((Map) data, propertyName);
        } else {
            Object result = null;

            try {
                result = PropertyUtils.getProperty(data, propertyName);
            } catch (Exception arg3) {
                System.out.println("DataUtils.getData(data,propertyName)错误：" + arg3.getMessage());
            }

            return result != null ? result : null;
        }
    }

    public static void setData(Object data, String propertyName, Object propertyData) {
        if (data != null && data instanceof Map) {
            ((Map) data).put(propertyName, propertyData);
        } else {
            try {
                PropertyUtils.setProperty(data, propertyName, propertyData);
            } catch (Exception arg3) {
                System.out.println("DataUtils.setData(data,propertyName,propertyData)错误：" + arg3.getMessage());
            }
        }

    }

    public static <T> Object getData(Map<String, ?> data, String name) {
        return data != null && data.containsKey(name) ? data.get(name) : null;
    }

    public static <T> Object getData(Map<String, ?> data, String... name) {
        Object object = data;
        String[] arg2 = name;
        int arg3 = name.length;

        for (int arg4 = 0; arg4 < arg3; ++arg4) {
            String currentName = arg2[arg4];
            if (object != null) {
                object = getData(object, currentName);
            }
        }

        if (object != null) {
            return object;
        } else {
            return null;
        }
    }

    public static void setData(Map<String, Object> data, Object propertyData, String... propertyName) {
        int count = propertyName.length;
        if (count == 1) {
            data.put(propertyName[0], propertyData);
        } else if (count > 0) {
            setData(getData(data, (String[]) Arrays.copyOfRange(propertyName, 0, count - 1)), propertyName[count - 1],
                    propertyData);
        }

    }

    public static void setData(Object data, Map<String, ?> parameter) {
        if (data != null && parameter != null) {
            try {
                BeanUtils.populate(data, parameter);
            } catch (Exception arg2) {
                System.out.println("DataUtils.setData(data,parameter)错误：" + arg2.getMessage());
            }
        }

    }

    public static <T> Object removeData(Map<String, ?> data, String name) {
        return data != null ? data.remove(name) : null;
    }

    public static Map<String, Object> createData(Map<String, ?> data, Map<String, String> mapContainer) {
        HashMap map = new HashMap();
        if (data != null && mapContainer != null) {
            Object object = null;
            String[] names = null;
            Object name = null;
            String nameFrom = null;
            String nameTo = null;
            boolean count = false;
            Iterator arg8 = mapContainer.entrySet().iterator();

            while (true) {
                do {
                    if (!arg8.hasNext()) {
                        return map;
                    }

                    Entry entry = (Entry) arg8.next();
                    nameFrom = (String) entry.getKey();
                    nameTo = (String) entry.getValue();
                } while (!TextUtils.isNotEmpty(new String[]{nameFrom, nameTo}));

                names = nameTo.split("-/");
                int arg11 = names.length;
                object = map;
                arg11 = names.length - 1;

                for (int lr = 0; lr < arg11; ++lr) {
                    object = getData((Object) object, (String) name);
                    if (object == null) {
                        object = new HashMap();
                        map.put(name, object);
                    }
                }

                setData(object, names[arg11], getData(data, nameFrom.split("-/")));
            }
        } else {
            return map;
        }
    }

    public static List<Map<String, Object>> createData(List<Map<String, Object>> data,
                                                       Map<String, String> mapContainer) {
        ArrayList list = new ArrayList();
        if (data != null && mapContainer != null) {
            Iterator arg2 = data.iterator();

            while (arg2.hasNext()) {
                Map map = (Map) arg2.next();
                list.add(createData(map, mapContainer));
            }
        }

        return list;
    }

    public static long getRandomKey() {
        return DataUtils.RandomKeyCreator.creator.getRandomKey();
    }

    public static int getRandom(int location) {
        return ThreadLocalRandom.current().nextInt(location);
    }

    public static int getRandom(int locationFrom, int locationTo) {
        return ThreadLocalRandom.current().nextInt(locationFrom, locationTo);
    }

    public static long getRandom(long location) {
        return ThreadLocalRandom.current().nextLong(location);
    }

    public static long getRandom(long locationFrom, long locationTo) {
        return ThreadLocalRandom.current().nextLong(locationFrom, locationTo);
    }

    public static String getRandom(String random, int location) {
        String result = random;
        if (random == null) {
            result = "";
        }

        return PasswordUtils.encode(result + getRandom(location), "MD5");
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getPlatformProperty(String name) {
        if (TextUtils.isNotEmpty(name)) {
            String result = (String) platformPropertyData.get(name);
            return result == null && resourceBundle != null && resourceBundle.containsKey(name)
                    ? resourceBundle.getString(name)
                    : result;
        } else {
            return null;
        }
    }

    public static String getPlatformProperty(String name, String data) {
        String currentData = getPlatformProperty(name);
        return currentData == null ? data : currentData;
    }

    public static String getPlatformProperty(String name, String dataFrom, String dataTo) {
        String result = getPlatformProperty(name);
        return TextUtils.isNotEmpty(new String[]{result, dataFrom}) && dataTo != null
                ? result.replaceAll("\\{" + dataFrom + "\\}", dataTo)
                : result;
    }

    public static Map<String, String> getPlatformProperty() {
        return platformPropertyData;
    }

    public static void setPlatformProperty(String name, String data) {
        platformPropertyData.put(name, data);
    }

    static {
        String type = TextUtils.getWithEmpty(System.getProperty("spring.profiles.active"));
        if (DataUtils.class.getClassLoader().getResource("platform" + type + ".properties") != null) {
            resourceBundle = ResourceBundle.getBundle("platform" + type);
        }

        try {
            InetAddress name = InetAddress.getLocalHost();
            platformPropertyData.put("localhostIP", name.getHostAddress());
        } catch (Exception arg1) {
            ;
        }

        if (resourceBundle == null || !resourceBundle.containsKey("clusterName")) {
            String name1 = "";
            if (resourceBundle != null && resourceBundle.containsKey("name")) {
                name1 = resourceBundle.getString("name");
            }

            platformPropertyData.put("clusterName", name1 + ManagementFactory.getRuntimeMXBean().getName());
        }

    }

    public static class RandomKeyCreator {
        private AtomicLong atomicLong = new AtomicLong();
        private static DataUtils.RandomKeyCreator creator = new DataUtils.RandomKeyCreator();

        private RandomKeyCreator() {
            try {
                String exception = DataUtils.getPlatformProperty("virtualIP");
                if (exception == null) {
                    exception = InetAddress.getLocalHost().getHostAddress();
                }

                int lastPoint = exception.lastIndexOf(".");
                exception = exception.substring(lastPoint + 1);
                this.setAtomicLong((long) Integer.parseInt(exception));
            } catch (Exception arg2) {
                System.out.println("DataUtils.RandomKeyCreator.randomKeyCreator()错误：" + arg2.getMessage());
            }

        }

        public static DataUtils.RandomKeyCreator getInstance() {
            return creator;
        }

        public long getRandomKey() {
            return this.atomicLong.incrementAndGet();
        }

        public void setAtomicLong(long data) {
            this.atomicLong.set(System.currentTimeMillis() | data << 48);
        }
    }
}
