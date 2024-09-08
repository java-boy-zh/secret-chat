package com.itchat.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName RegistUserVO.java
 * @create 2024年09月07日 上午9:15
 * @Description 登录/注册用户请求对象
 * @Version V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistUserVO {
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号长度不正确")
    private String mobile;
    @NotBlank(message = "验证码不能为空")
    private String smsCode;
    private String nickname;
}
