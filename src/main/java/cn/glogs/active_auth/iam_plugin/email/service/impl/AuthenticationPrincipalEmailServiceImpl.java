package cn.glogs.active_auth.iam_plugin.email.service.impl;

import cn.glogs.active_auth.iam_plugin.email.entity.AuthenticationPrincipalEmail;
import cn.glogs.active_auth.iam_plugin.email.repository.AuthenticationPrincipalEmailRepository;
import cn.glogs.active_auth.iam_plugin.email.service.AuthenticationPrincipalEmailService;
import cn.glogs.activeauth.iamcore.domain.AuthenticationPrincipal;
import cn.glogs.activeauth.iamcore.exception.business.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationPrincipalEmailServiceImpl implements AuthenticationPrincipalEmailService {

    private final AuthenticationPrincipalEmailRepository emailAddressRepository;

    public AuthenticationPrincipalEmailServiceImpl(AuthenticationPrincipalEmailRepository emailAddressRepository) {
        this.emailAddressRepository = emailAddressRepository;
    }

    @Override
    public AuthenticationPrincipalEmail getEmailOfPrincipal(AuthenticationPrincipal principal) throws NotFoundException {
        return emailAddressRepository.findById(principal).orElseThrow(() -> new NotFoundException(String.format("Principal id:%s not found.", principal.getId())));
    }

    @Override
    public AuthenticationPrincipalEmail setEmailOfPrincipal(AuthenticationPrincipalEmail authenticationPrincipalEmail) {
        return emailAddressRepository.save(authenticationPrincipalEmail);
    }
}
