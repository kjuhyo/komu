import axios from 'axios'
import {saveUserToCookie } from '@/util/cookie.js'

const actions = {
  // 구글 토큰 localstorage에 저장
    googleCallback(context, data) {
      console.log(data)
        axios
          .post("http://j4c104.p.ssafy.io/api/user/login/google", data.tc.id_token)
          .then(response => {
            console.log(response.data.user)
            let token = response.data.access_token
            let provider = response.data.user.provider
            localStorage.setItem('provider',provider)
            localStorage.setItem('access_token', token)
            context.dispatch("getMemberInfo")
          })
          .catch(function (error) {
            console.log(error);
          });
         console.log("actions success");
    },
    // 유저 정보 가져오기
    getMemberInfo({ commit }){
      let token = localStorage.getItem("access_token")
      if (token) {
        let config = {
          headers : {
            "access_token" : token
          }
        }
        axios
            .get("http://j4c104.p.ssafy.io/api/user/getUserInfo", config)
            .then(response=> {
             // console.log("actions에서")
             // console.log(response.data)
              let userInfo = {
                
                uid: response.data.user.uid,
                nickname: response.data.user.nickname,
                profile: response.data.user.profile
              }
              saveUserToCookie(response.data.user.uid)
             // console.log('쿠키에넣었다')
             // console.log(response.data.user.uid)
              commit('fetchLoggedInUserData', userInfo)        
            })
            .catch(function (error) {
              console.log("actions데이터불러오기실패")
              console.log(error);
            });
      } 
    },
    // 로그아웃
    LOGOUT({ commit }) {
      console.log('actions 로그아웃')
      commit("logout");
    },
  }
  export default actions