<template>

    <div class="container">

    <form class="form-signin" role="form">
        <h2 class="form-signin-heading">HomFit</h2>

        <form class="sign-in-form__form" id="new_user" accept-charset="UTF-8" method="post">

<!--            <b-icon icon="person"></b-icon> -->
            <input placeholder="이메일" autofocus="autofocus" class="sign-in-form__form__input form-control sign-in-form__form__email" type="text" name="user[email]" id="user_email" />
            <div class="sign-in-form__form__input-wrap sign-in-form__form__password">
                <input placeholder="비밀번호" autocomplete="off" class="sign-in-form__form__input form-control" type="password" name="user[password]" id="user_password" />
            </div>
            
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> 로그인유지
            </label>

            <input type="hidden" name="remember_me" id="remember_me" value="checked" />
            <input type="hidden" name="is_pro" id="is_pro" value="false" />
            <input type="submit" name="commit" value="로그인" class="sign-in-form__form__submit btn btn-priority" data-disable-with="로그인" />
        </form>
        
        <div class="hr"></div>

        <div class="sign-in-form__action">
            <router-link to="/forgot-password" class="sign-in-form__action__entry">비밀번호재설정</router-link>
            <router-link to="/signup" class="sign-in-form__action__entry">회원가입</router-link>
        </div>
    </form>

    </div>

</template>

<script>
import "../assets/css/login.css"

    export default {
        data() {
            return {}
        }
    }
</script>