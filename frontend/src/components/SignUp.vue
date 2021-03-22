<template>
    <div class="vue-tempalte">
        <form class="user-sign-up__form">

            <h3> 회원가입</h3>
            <br>
            <br>

            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">이메일</div>
                <div class="user-sign-up-form__form-group__input">
                    <div class="input-group email-input">

                        <input class="form-control" value="" name="emailid" placeholder="이메일" size="1">

                        <span class="email-input__separator">@</span>

                        <select class="form-control" name="emaildomain" size="1">
                            <option selected="" value="" disabled="">선택해주세요</option>
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="daum.net">daum.net</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="nate.com">nate.com</option>>
                            <option value="icloud.com">icloud.com</option>

                        </select>

                    </div>
                </div>
            </div>
            
            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">비밀번호</div>
                <div class="user-sign-up-form__form-group__description">비밀번호는 8자 이상 입력해주세요.</div>
                <div class="user-sign-up-form__form-group__input">
                    <input type="password" placeholder="비밀번호" value="" class="form-control">
                </div>
            </div>
            
            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">비밀번호 확인</div>
                <div class="user-sign-up-form__form-group__input"><input type="password" placeholder="비밀번호 확인" value="" class="form-control">
                </div>
            </div>

            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">휴대폰 번호</div>
                <div class="user-sign-up-form__form-group__input">
                    <div class="input-group email-input">

                        <select class="form-control" name="phonenumber1" size="1">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="012">012</option>
                            <option value="013">013</option>
                            <option value="014">014</option>
                            <option value="015">015</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                            <option value="070">070</option>
                            <option value="02">02</option>
                        </select>

                        <span class="email-input__separator">-</span>
                        <input class="form-control" value="" name="phonenumber2" size="1">
                        <span class="email-input__separator">-</span>
                        <input class="form-control" value="" name="phonenumber3" size="1">
                        <input type="submit" value="인증번호 발송" class="phonecode-btn">

                    </div>

                    
                </div>
            </div>

            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">인증번호</div>
                <div class="user-sign-up-form__form-group__input">
                    <div class="input-group email-input">
                        <input type="password" placeholder="인증번호" value="" class="form-control">
                        <input type="submit" value="인증번호 확인" class="phonecode-btn">
                    </div>
                    
                </div>
            </div>
            
            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">별명</div>
                <div class="user-sign-up-form__form-group__description">다른 유저와 겹치지 않는 별명을 입력해주세요. (2~15자)</div>
                <div class="user-sign-up-form__form-group__input">
                    <input placeholder="별명 (2~15자)" value="" class="form-control">
                </div>
            </div>

            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">연령대</div>
                <div class="user-sign-up-form__form-group__input">
                    <div class="input-group email-input">
                        <select class="form-control" name="age">
                            <option value="10대">10대</option>
                            <option value="20대">20대</option>
                            <option value="30대">30대</option>
                            <option value="40대">40대</option>
                            <option value="50대 이상">50대 이상</option>
                        </select>
                    </div>
                    
                </div>
            </div>

            <div class="user-sign-up-form__form-group">
                <div class="user-sign-up-form-label">성별</div>
                <div class="user-sign-up-form__form-group__input">
                    
                    <b-form-radio v-model="genderselected" :aria-describedby="ariaDescribedby" name="some-radios" value="A">여자</b-form-radio>
                    <b-form-radio v-model="genderselected" :aria-describedby="ariaDescribedby" name="some-radios" value="B">남자</b-form-radio>

                </div>
            </div>
            
            
        </form>

        <input type="submit" name="commit" value="회원가입" class="sign-up-form__form__submit btn btn-priority"  />

        <div class="hr"></div>

        <div class="sign-in-form__action">
            <router-link to="/login" class="sign-in-form__action">로그인하러가기</router-link>
        </div>

    </div>
</template>

<script>
import "../assets/css/signUp.css";

export default {

    data: () => ({
        genderselected: ''
    }),

    methods: {

    }

}

</script>