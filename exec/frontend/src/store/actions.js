import axios from 'axios'
import {saveUserToCookie } from '@/util/cookie.js'

const actions = {
  // 구글 토큰 localstorage에 저장
    googleCallback(context, data) {
      console.log(data)
        axios
        .post("http://j4c104.p.ssafy.io/api/user/login/google", data.tc.id_token)
          .then(response => {
            let token = response.data.access_token
            let provider = response.data.user.provider
            localStorage.setItem('provider',provider)
            localStorage.setItem('access_token', token)
            context.dispatch("getMemberInfo")
          })
          .catch(function (error) {
            console.log(error);
          });
    },
    // 유저 정보 가져오기
    getMemberInfo({ commit }){
      let token = localStorage.getItem("access_token")
      if (token) {
        let config = {
          headers : {
            "Authorization" : token
          }
        }
        axios
            .get("http://j4c104.p.ssafy.io/api/user/getUserInfo", config)
            .then(response=> {
              let userInfo = {
                
                uid: response.data.user.uid,
                nickname: response.data.user.nickname,
                profile: response.data.user.profile
              }
              saveUserToCookie(response.data.user.uid)
              commit('fetchLoggedInUserData', userInfo)        
            })
            .catch(function (error) {
              console.log(error);
            });
      } 
    },
    // 로그아웃
    LOGOUT({ commit }) {
      commit("logout");
    },
  }
  export default actions