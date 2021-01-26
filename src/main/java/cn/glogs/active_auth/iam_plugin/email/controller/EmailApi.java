package cn.glogs.active_auth.iam_plugin.email.controller;

import cn.glogs.active_auth.iam_plugin.email.entity.AuthenticationPrincipalEmail;
import cn.glogs.active_auth.iam_plugin.email.service.AuthenticationPrincipalEmailService;
import cn.glogs.activeauth.iamcore.api.helper.AuthCheckingHelper;
import cn.glogs.activeauth.iamcore.api.payload.AuthCheckingStatement;
import cn.glogs.activeauth.iamcore.api.payload.RestResultPacker;
import cn.glogs.activeauth.iamcore.config.properties.LocatorConfiguration;
import cn.glogs.activeauth.iamcore.domain.AuthenticationPrincipal;
import cn.glogs.activeauth.iamcore.exception.HTTP404Exception;
import cn.glogs.activeauth.iamcore.exception.HTTPException;
import cn.glogs.activeauth.iamcore.exception.business.NotFoundException;
import cn.glogs.activeauth.iamcore.service.AuthenticationPrincipalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class EmailApi {

    private final AuthenticationPrincipalService principalService;
    private final AuthenticationPrincipalEmailService emailAddressService;
    private final AuthCheckingHelper authCheckingHelper;
    private final LocatorConfiguration locatorConfiguration;

    public EmailApi(
            AuthenticationPrincipalService principalService,
            AuthenticationPrincipalEmailService emailAddressService,
            AuthCheckingHelper authCheckingHelper,
            LocatorConfiguration locatorConfiguration
    ) {
        this.principalService = principalService;
        this.emailAddressService = emailAddressService;
        this.authCheckingHelper = authCheckingHelper;
        this.locatorConfiguration = locatorConfiguration;
    }

    /**
     * 获取邮箱
     *
     * @param request     Servlet 请求对象
     * @param principalId principalId
     *
     * @return 获取结果
     *
     * @throws HTTPException HTTP 错误码
     */
    @GetMapping("/principals/{principalId}/email-address")
    public ResponseEntity<RestResultPacker<AuthenticationPrincipalEmail.Vo>> getEmail(
            HttpServletRequest request,
            @PathVariable Long principalId
    ) throws HTTPException {
        authCheckingHelper.systemResources(request, AuthCheckingStatement.checks("iam:GetEmail", locatorConfiguration.fullLocator(String.valueOf(principalId), "email")));
        try {
            AuthenticationPrincipal principal = principalService.findPrincipalById(principalId);
            AuthenticationPrincipalEmail emailAddress = emailAddressService.getEmailOfPrincipal(principal);
            return ResponseEntity.ok(RestResultPacker.success(emailAddress.vo()));
        } catch (NotFoundException e) {
            throw new HTTP404Exception(e);
        }
    }

    /**
     * 添加邮箱
     *
     * @param request     Servlet 请求对象
     * @param principalId principalId
     * @param form        邮箱地址
     *
     * @return 添加结果
     *
     * @throws HTTPException HTTP 错误码
     */
    @PostMapping("/principals/{principalId}/email-address")
    public ResponseEntity<RestResultPacker<AuthenticationPrincipalEmail.Vo>> addEmail(
            HttpServletRequest request,
            @PathVariable Long principalId,
            @RequestBody AuthenticationPrincipalEmail.Form form
    ) throws HTTPException {
        authCheckingHelper.systemResources(request, AuthCheckingStatement.checks("iam:AddEmail", locatorConfiguration.fullLocator(String.valueOf(principalId), "email")));
        try {
            AuthenticationPrincipal principal = principalService.findPrincipalById(principalId);
            AuthenticationPrincipalEmail emailAddress = emailAddressService.setEmailOfPrincipal(new AuthenticationPrincipalEmail(principal, form));
            return ResponseEntity.ok(RestResultPacker.success(emailAddress.vo()));
        } catch (NotFoundException e) {
            throw new HTTP404Exception(e);
        }
    }
}