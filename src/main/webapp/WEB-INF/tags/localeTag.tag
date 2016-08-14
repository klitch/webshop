<%@ tag language="java" pageEncoding="UTF-8" %>
<script src="style/js/locale-change.js"></script>
<form class="lang_select">
    <label>
        <input class="invisible" type="radio" name="language" value="ru" onchange="submit()"/>
        <img class="flag" src="style/images/Russia.png"/>
    </label>
    <label>
        <input class="invisible flag" type="radio"
               name="language" value="en" onchange="submit()"/>
        <img class="flag" src="style/images/United-States-of-America.png"/>
    </label>
</form>