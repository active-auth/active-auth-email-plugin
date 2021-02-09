package cn.glogs.active_auth.iam_plugin.email.entity;

import cn.glogs.active_auth.iam_plugin.email.util.OneTimeCodeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmailOneTimeCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;
    private String code;
    private Date expireAt;
    private Date createdAt;
    private Date updatedAt;
    private boolean valid;

    public EmailOneTimeCode(String email) {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        emailAddress = email;
        code = String.valueOf(OneTimeCodeUtil.generate());
        createdAt = now;
        calendar.add(Calendar.MINUTE, 15);
        expireAt = calendar.getTime();
        valid = true;
    }

    public boolean ifValid() {
        return expireAt != null && new Date().before(expireAt) && valid;
    }
}
