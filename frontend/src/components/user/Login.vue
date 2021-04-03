<!--<template v-slot:default="dialog">-->
<template>
  <div id="login">
    <div class="loginBar">
      <GoogleLogin
        :params="params"
        :renderParams="renderParams"
        :onSuccess="googleCallback"
        :onFailure="onFailure"
      ></GoogleLogin>
    </div>
  </div>
</template>

<script>
import GoogleLogin from 'vue-google-login';
import { mapMutations, mapState } from 'vuex';

let onFailure = (data) => {
  console.log('실패' + data);
  console.log('failure');
};

export default {
  name: 'Login',
  components: {
    GoogleLogin,
  },
  data() {
    return {
      params: {
        client_id:
          '708865580158-3vqqvt6jap80ulb016p1jvve0nfvd0pf.apps.googleusercontent.com',
      },
      // only needed if you want to render the button with the google ui
      renderParams: {
        width: 300,
        height: 49,
        longtitle: true,
      },
    };
  },
  methods: {
    ...mapMutations(['fetchLoggedInUserData']),
    ...mapState(['loggedInUserData']),

    googleCallback(data) {
      //console.log("콜백데이터"+data)
      this.$store.dispatch('googleCallback', data);
      if (this.$route.path !== '/')
        this.$router.replace('/').catch((error) => {
          if (error.name != 'NavigationDuplicated') {
            throw error;
          }
        });
    },
    onFailure,
  },
};
</script>

<style>
#login {
  height: 300px;
}

.loginBar {
  cursor: pointer;
  width: 300px;
  height: 49px;
  margin: 25px;
  align-content: center;
}
</style>
