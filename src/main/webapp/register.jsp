<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<script src="style/js/jqueryvalidator.js"></script>
<div class="register_account">
    <div class="wrap">
        <h4 class="title">Create an Account</h4>

        <div class="errorMessage">${errorMessage}</div>
        <form action="register" name="register_form" method="post" enctype="multipart/form-data">
            <div>
                <div class="col_1_of_2 span_1_of_2">
                    <div><input name="first_name" required type="text" placeholder="Name" value="${first_name}"></div>
                    <div class="errorMessage" required id="firstNameMessage">${firstNameMessage}</div>
                    <div><input name="last_name" required type="text" placeholder="Last name" value="${last_name}">
                    </div>
                    <div class="errorMessage" id="lastNameMessage">${lastNameMessage}</div>
                    <div><input name="email" required type="text" placeholder="E-mail" value="${email}"></div>
                    <div class="errorMessage" id="emailMessage">${emailMessage}</div>
                </div>
                <div class="col_1_of_2 span_1_of_2">
                    <div><input name="password" required type="text" placeholder="Password"></div>
                    <div class="errorMessage" id="passMessage">${passMessage}</div>
                    <div class="upload_div">
                        <div class="file_upload">
                            <button type="button">Choose</button>
                            <div>Photo not chosen</div>
                            <input onchange="this.previousSibling.previousSibling.innerHTML = this.value" type="file"
                                   name="file" id="file" accept="image/*"/>
                        </div>
                    </div>
                    <mytag:captchaTag captchaId="${captchaId}"/>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div>
            <div><label><input type="checkbox" name="newsCheckBox" ${isAgreeForNewsletter}/>Do you want to
                receive specials from us?</label></div>
            <input type="submit" class="grey button" value="Submit">

            <div class="clear"></div>
        </form>
    </div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>