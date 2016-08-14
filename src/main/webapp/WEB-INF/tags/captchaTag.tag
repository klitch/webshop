<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="captchaId" %>
<input name="captchaId" type="hidden" value="${captchaId}">

<div>
    <input type="text" class="captchaField" value="" name="captchaValue" autocomplete="off" placeholder="Captcha"/>
    <img class="captchaImage" src="captcha?captchaId=${captchaId}" alt="captcha" id="captchaImage"/>
</div>