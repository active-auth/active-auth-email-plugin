package cn.glogs.active_auth.iam_plugin.email.service;

import cn.glogs.active_auth.iam_plugin.email.entity.AuthenticationPrincipalEmail;
import cn.glogs.activeauth.iamcore.domain.AuthenticationPrincipal;
import cn.glogs.activeauth.iamcore.exception.business.NotFoundException;

public interface AuthenticationPrincipalEmailService {
    AuthenticationPrincipalEmail getEmailOfPrincipal(AuthenticationPrincipal principal) throws NotFoundException;

    AuthenticationPrincipalEmail setEmailOfPrincipal(AuthenticationPrincipalEmail authenticationPrincipalEmail);
}
