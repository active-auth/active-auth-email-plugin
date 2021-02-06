package cn.glogs.active_auth.iam_plugin.email.util;


public class OneTimeCodeUtil {
    public static int generate() {
        return (int) (Math.random() * 9 + 1) * 100000;
    }
}
