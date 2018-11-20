package com.maomiyibian.microservice.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * TODO: 类描述
 *
 * @author junyunxiao
 * @date 2018-9-12 16:18
 */
public class PasswordUtils {
    public static String getPassword(String password) {
        return password != null
                ? DigestUtils.md5Hex(DigestUtils.sha256(password))
                : DigestUtils.md5Hex(DigestUtils.sha256(""));
    }

    public static String encode(String data, String type) {
        if (data != null) {
            if ("MD5".equalsIgnoreCase(type)) {
                return DigestUtils.md5Hex(data).toUpperCase();
            }

            if ("SHA256".equalsIgnoreCase(type)) {
                return DigestUtils.sha256Hex(data).toUpperCase();
            }
        }

        return "";
    }

    public static SSLContext createSSLContext(String keyStoreURL, char[] keyStorePasswords, String trustStoreURL,
                                              char[] trustStorePasswords) {
        try {
            KeyManager[] exception = null;
            TrustManager[] trustManagers = null;
            File context;
            KeyStore keyStoreForTrustManager;
            if (keyStoreURL != null) {
                if (keyStoreURL.indexOf(File.separator) < 0) {
                    keyStoreURL = PasswordUtils.class.getResource("/").getFile().replace("%20", " ") + keyStoreURL;
                }

                context = new File(keyStoreURL);
                if (context.exists()) {
                    keyStorePasswords = handleDefaultPassword(keyStoreURL, keyStorePasswords);
                    if (keyStorePasswords == null) {
                        keyStorePasswords = "trustcacerts@keystore".toCharArray();
                    }

                    KeyManagerFactory trustManagerFactory = KeyManagerFactory.getInstance("SunX509");
                    keyStoreForTrustManager = KeyStore.getInstance("JKS");
                    keyStoreForTrustManager.load(new FileInputStream(context), keyStorePasswords);
                    trustManagerFactory.init(keyStoreForTrustManager, keyStorePasswords);
                    Arrays.fill(keyStorePasswords, ' ');
                    exception = trustManagerFactory.getKeyManagers();
                }
            }

            if (trustStoreURL != null) {
                if (trustStoreURL.indexOf(File.separator) < 0) {
                    trustStoreURL = PasswordUtils.class.getResource("/").getFile().replace("%20", " ") + trustStoreURL;
                }

                context = new File(trustStoreURL);
                if (context.exists()) {
                    trustStorePasswords = handleDefaultPassword(trustStoreURL, trustStorePasswords);
                    TrustManagerFactory trustManagerFactory1 = TrustManagerFactory.getInstance("SunX509");
                    keyStoreForTrustManager = KeyStore.getInstance("JKS");
                    keyStoreForTrustManager.load(new FileInputStream(context), trustStorePasswords);
                    Arrays.fill(trustStorePasswords, ' ');
                    trustManagerFactory1.init(keyStoreForTrustManager);
                    trustManagers = trustManagerFactory1.getTrustManagers();
                }
            }

            if (exception != null || trustManagers != null) {
                SSLContext context1 = SSLContext.getInstance("TLS");
                context1.init(exception, trustManagers, new SecureRandom());
                return context1;
            }
        } catch (Exception arg8) {
            arg8.printStackTrace();
        }

        return null;
    }

    private static char[] handleDefaultPassword(String url, char[] passwords) {
        if (passwords == null) {
            String result = TextUtils.getWithEmpty(url).toLowerCase();
            return !result.endsWith("keystore") && !result.endsWith("truststore")
                    ? "changeit".toCharArray()
                    : "trustcacerts@keystore".toCharArray();
        } else {
            return passwords;
        }
    }
}
