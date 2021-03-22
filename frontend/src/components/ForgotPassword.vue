<template>
    <div class="vue-tempalte">
        <form>
            <h3>비밀번호 재설정</h3>

            <div>
                <b-form-group label="비밀번호 재설정 안내를 받을 방법을 선택하세요." v-slot="{ ariaDescribedby }"><br>
                <b-form-radio checked="checked" v-model="selected" :aria-describedby="ariaDescribedby" name="some-radios" value="이메일">이메일 주소</b-form-radio>
                <b-form-radio v-model="selected" :aria-describedby="ariaDescribedby" name="some-radios" value="문자">문자메시지(SMS)</b-form-radio>
                </b-form-group>

                <div class="mt-3">비밀번호 재설정 인증번호를 {{ selected }}로 안내드리겠습니다.</div>
            </div>

            <br>

            <template v-if="selected === '문자'">
                <input
                    placeholder="전화번호를 입력하세요" 
                    autofocus="autofocus" 
                    class="sign-in-form__form__input form-control sign-in-form__form__email" 
                    type="text"
                    v-model="contact"
                    @keyup="getPhoneMask(contact)"
                />
            </template>

            <template v-else>
                <input
                    placeholder="이메일 주소를 입력하세요" 
                    autofocus="autofocus" 
                    class="sign-in-form__form__input form-control sign-in-form__form__email" 
                    type="text" 
                    id="user_email"
                >
            </template>

            <router-link to="/forgot-password-code" class="sign-in-form__form__submit btn btn-priority">인증번호 받기</router-link>

        </form>
    </div>
</template>

<script>
import "../assets/css/forgotpassword.css";

export default {

    data: () => ({
        selected: '이메일',
        contact:null,
    }),

    methods: {

        getPhoneMask(val) {
            let res = this.getMask(val)
            this.contact = res
            //서버 전송 값에는 '-' 를 제외하고 숫자만 저장
            this.model.contact = this.contact.replace(/[^0-9]/g, '')
        },
        
        getMask( phoneNumber ) {
            if(!phoneNumber) return phoneNumber
            phoneNumber = phoneNumber.replace(/[^0-9]/g, '')
        
            let res = ''
            if(phoneNumber.length < 3) {
                res = phoneNumber
            }
            else {
                if(phoneNumber.substr(0, 2) =='02') {
            
                    if(phoneNumber.length <= 5) {//02-123-5678
                        res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
                    }
                    else if(phoneNumber.length > 5 && phoneNumber.length <= 9) {//02-123-5678
                        res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
                    }
                    else if(phoneNumber.length > 9) {//02-1234-5678
                        res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
                    }
            
                } else {
                    if(phoneNumber.length < 8) {
                        res = phoneNumber
                    }
                    else if(phoneNumber.length == 8)
                    {
                        res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
                    }
                    else if(phoneNumber.length == 9)
                    {
                        res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
                    }
                    else if(phoneNumber.length == 10)
                    {
                        res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
                    }
                    else if(phoneNumber.length > 10) { //010-1234-5678
                        res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
                    }
                }
            }
                    
            return res
            }
    }

}

</script>