package cn.glogs.active_auth.iam_plugin.email.service.impl;

import cn.glogs.active_auth.iam_plugin.email.entity.EmailOneTimeCode;
import cn.glogs.active_auth.iam_plugin.email.repository.EmailOneTimeCodeRepository;
import cn.glogs.active_auth.iam_plugin.email.service.EmailOneTimeCodeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailOneTimeCodeServiceImpl implements EmailOneTimeCodeService {

    private final EmailOneTimeCodeRepository emailOneTimeCodeRepository;

    public EmailOneTimeCodeServiceImpl(EmailOneTimeCodeRepository emailOneTimeCodeRepository) {
        this.emailOneTimeCodeRepository = emailOneTimeCodeRepository;
    }

    @Override
    public void newCode(String email) {
        EmailOneTimeCode oneTimeCode = new EmailOneTimeCode(email);
        emailOneTimeCodeRepository.save(oneTimeCode);
    }

    @Override
    public boolean checkCode(String email, String code) {
        List<EmailOneTimeCode> codes = emailOneTimeCodeRepository.findByEmailAddressAndCodeOrderByCreatedAtDesc(email, code);
        if (codes.isEmpty()) return false;
        return codes.get(0).valid();
    }
}
