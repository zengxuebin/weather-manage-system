package com.weather.web.controller.system;

import com.weather.common.constant.Constants;
import com.weather.common.utils.ApiResult;
import com.weather.common.utils.RedisCache;
import com.weather.common.utils.RedisKeyUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 * @author linkaixuan
 * @since 2024/4/3 18:06
 */
@Slf4j
@RestController
@RequestMapping("/code")
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     * @return 验证码
     */
    @GetMapping("/captchaImage")
    public ApiResult getCode() {
        ApiResult apiResult = ApiResult.success();

        // 保存验证码信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String verifyKey = RedisKeyUtil.getCaptchaKey(uuid);

        String capStr;
        String code;
        BufferedImage image;

        capStr = code = captchaProducer.createText();
        image = captchaProducer.createImage(capStr);

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        log.info("captcha code is: {}", code);

        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            return ApiResult.error("获取验证码失败");
        }
        apiResult.put("uuid", uuid);
        apiResult.put("img", Base64.encodeBase64String(outputStream.toByteArray()));
        return apiResult;
    }
}
