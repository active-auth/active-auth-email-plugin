package cn.glogs.active_auth.iam_plugin.email.repository;

import cn.glogs.active_auth.iam_plugin.email.entity.AuthenticationPrincipalEmail;
import cn.glogs.activeauth.iamcore.domain.AuthenticationPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationPrincipalEmailRepository extends JpaRepository<AuthenticationPrincipalEmail, AuthenticationPrincipal> {
}
