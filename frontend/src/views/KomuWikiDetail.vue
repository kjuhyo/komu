<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto"></div>
          </div>
          <div class="komuwiki_container">
            <div class="komuwiki_header">
              <div class="komuwiki_title">
                {{ namuwiki.namu_title }}
              </div>
              <div class="komuwiki_option_area">
                <div v-if="isLogin">
                <b-icon
                  v-if="LIKE == 0"
                  class="wiki_option_icon"
                  icon="heart"
                  font-scale="2"
                  @click="Like"
                ></b-icon>
                <b-icon
                  v-if="LIKE == 1"
                  class="wiki_option_icon"
                  icon="heart-fill"
                  font-scale="2"
                  @click="Like"
                ></b-icon>
                </div>
                <router-link :to="`/komuwikiupdate/${namuwiki.namu_title}`">
                <b-icon
                  class="wiki_option_icon"
                  icon="pencil"
                  font-scale="2"
                >
          </b-icon></router-link>
              </div>
            </div>

            <hr />
            <!-- 내용 -->
            <div class="komuwiki_body">
              <!-- 이미지 -->
              <div class="m_komuwiki_body_img" v-if="isMobile">
                <img class="k_body_img" :src="namuwiki.namu_img" />

                <!-- <img class="k_body_img" src="@/assets/img/jihyeon.jpg" /> -->
              </div>

              <div class="komuwiki_body_img" v-else>
                <img class="k_body_img" :src="namuwiki.namu_img" />
                <!-- <img class="k_body_img" src="@/assets/img/jihyeon.jpg" /> -->
              </div>

              <!-- 내용 -->

              <div class="m_komuwiki_body_content" v-if="isMobile">
                {{ namuwiki.namu_content }}
              </div>
              <div class="komuwiki_body_content" v-else>
                {{ namuwiki.namu_content }}
              </div>
            </div>
            <hr />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/komuwikidetail.scss';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import { getboard, letlike } from '@/api/komu.js';

export default {
  components: {},
  created() {
    this.name = this.$route.params.name;
    console.log(this.name);
    this.initUser();
    getboard(
      this.name,
      this.loginid,
      (response) => {
        this.LIKE = response.data.LIKE;
        this.namuwiki = response.data.namuwiki;
        this.message = response.data.message;
        console.log(this.message);
        console.log(this.namuwiki);
        console.log(this.LIKE);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  data() {
    return {
      isMobile: false,
      name: '',
      loginid: '',
      LIKE: '',
      message: '',
      namuwiki: {
        namu_id: 0,
        uid: '',
        namu_title: '',
        namu_content: '',
        namu_date: '',
        namu_img: '',
      },
      wordlike: {
        uid: '',
        namu_id: 0,
      },
    };
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
  computed: {
    ...mapState(['isLogin', 'loggedInUserData']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
    initUser() {
      this.loginid = getuidCookie();
      //this.loginid = 'namu';
    },
    Like: function() {
      this.wordlike.uid = this.loginid;
      this.wordlike.namu_id = this.namuwiki.namu_id;
      letlike(
        this.wordlike,
        (response) => {
          this.LIKE = response.data.LIKE;
          console.log(response.data.LIKE);
          console.log(response.data.message);
        },
        (error) => {
          console.log(error.data);
        }
      );
    },
  },
  // computed: { ...mapState(['isLogin', 'loggedInUserData']) },
};
</script>
