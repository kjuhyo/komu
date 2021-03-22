<template>
    <div class="vue-tempalte">
        <form>
            <div class="justtext">
                비밀번호를 재설정하시려면 아래에 인증 코드를 입력하세요.
            </div>

            <input
                placeholder="인증번호를 입력하세요" 
                autofocus="autofocus" 
                class="sign-in-form__form__input form-control sign-in-form__form__email" 
                type="text" 
                id="changepwcode"
            >

            <div class="sign-in-form__action">
                <router-link to="/forgot-password-final" class="change_pw_btn">인증</router-link>
                <router-link to="/forgot-password" class="change_pw_btn_back">취소</router-link>
            </div>

        </form>
    </div>
</template>

<script>
import "../assets/css/forgotpassword.css";

export default {

    data: () => ({

    })

}

</script>