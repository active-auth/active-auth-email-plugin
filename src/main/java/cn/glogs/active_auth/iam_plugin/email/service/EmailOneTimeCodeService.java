package cn.glogs.active_auth.iam_plugin.email.service;

public interface EmailOneTimeCodeService {
    void newCode(String email);

    boolean checkCode(String email, String code);
}
