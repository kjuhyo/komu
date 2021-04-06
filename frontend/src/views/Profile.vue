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
            <div class="md-layout-item md-size-50 mx-auto">
              <div class="profile">
                <div class="avatar">
                  <!-- 사용자 프로필이미지 -->
                  <img
                    :src="this.profile"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image"
                  />

                </div>
                <div class="name">
                  <h3 class="title">{{ this.nickname }}</h3>
                </div>
                <div class="profile-edit-div">

                </div>
              </div>
            </div>
          </div>
          <div class="profile-tabs">
            <tabs
              :tab-name="['My Article', 'My Song', 'My Words']"
              :tab-icon="['article', 'music_note', 'favorite']"
              plain
              nav-pills-icons
              color-button="primary"
            >

              <!-- 내가 쓴 글 -->
              <template slot="tab-pane-1">
                <div class="md-layout">
                </div>
                <MyPost :commuList="commuList"/>
              </template>

              <!-- 좋아요한 노래 -->
              <template slot="tab-pane-2">
                <div class="md-layout"></div>
                <MySong />
              </template>

              <!-- 예쁜단어 -->
              <template slot="tab-pane-3"> <MyWord /></template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import '../assets/css/profile.css';
import { Tabs } from '@/components';
import MyPost from '../components/MyPost.vue';
import MySong from '../components/MySong.vue';
import MyWord from '../components/MyWord.vue';
import { getuidCookie } from '@/util/cookie.js';
import { profileByUid } from '@/api/user.js';
import {myPostList} from '@/api/my.js';
export default {
  components: {
    Tabs,
    MyPost,
    MySong,
    MyWord,
  },
  bodyClass: 'profile-page',
  created() {
    this.initUser(),
      profileByUid(
        this.uid,
        (response) => {
          this.nickname = response.data.info.nickname;
          this.profile = response.data.info.profile;
        },
        (error) => {
          console.log(error);
        }
      ),
      myPostList(
        this.uid,
        (response) =>{
          console.log('촤하하')
          console.log(response.data);
          this.commuList=response.data;
        },
        (error) => {
          console.log(error)
        }
      )
  },
  methods: {
    initUser() {
      this.uid = getuidCookie();
    },
  },
  data() {
    return {
      profile: '',
      nickname: '',
      uid: '',
      commuList:{
        profile:'',
        nickname:'',
        cid:'',
        uid:'',
        c_title:'',
        c_content:'',
        c_date:'',
        is_delete:'',
        c_view:'',
        c_like_cnt:'',
        c_img:'',
      },
    };
  },
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
    ...mapState(['loggedInUserData']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    // img {
    //   margin-bottom: 2.142rem;
    // }
  }
}
</style>
