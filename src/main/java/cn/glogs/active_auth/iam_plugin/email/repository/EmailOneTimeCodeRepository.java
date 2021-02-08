package cn.glogs.active_auth.iam_plugin.email.repository;

import cn.glogs.active_auth.iam_plugin.email.entity.EmailOneTimeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailOneTimeCodeRepository extends JpaRepository<EmailOneTimeCode, Long> {
    List<EmailOneTimeCode> findByEmailAddressAndCodeOrderByCreatedAtDesc(String emailAddress, String code);
}
