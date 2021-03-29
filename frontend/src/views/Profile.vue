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

                  <!-- 프로필수정버튼 -->
                  <!-- <button class="profileedit-btn material-icons ">
                    <i class="material-icons">mode_edit</i>
                  </button> -->
                </div>
                <div class="name">
                  <h3 class="title">{{this.nickname}}</h3>
                </div>
                <div class="profile-edit-div">
                  <!-- 프로필수정버튼 -->
                  <button class="profile-edit-btn">
                    <i class="material-icons">mode_edit</i>
                  </button>
                </div>
              </div>
            </div>
          </div>
          <!-- <div class="description text-center">
            <p>
              An artist of considerable range, Chet Faker — the name taken by
              Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs
              and records all of his own music, giving it a warm, intimate feel
              with a solid groove structure.
            </p>
          </div> -->
          <div class="profile-tabs">
            <tabs
              :tab-name="[
                'My Article',
                'My Song',
                'My Singer',
                'Statistics',
                'My Words',
              ]"
              :tab-icon="[
                'article',
                'music_note',
                'person',
                'insert_chart_outlined',
                'favorite',
              ]"
              plain
              nav-pills-icons
              color-button="primary"
            >
              <!-- here you can add your content for tab-content -->
              <!-- 내가 쓴 글 -->
              <template slot="tab-pane-1">
                <div class="md-layout">
                  <!-- <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane1[0].image" class="rounded" />
                    <img :src="tabPane1[1].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane1[3].image" class="rounded" />
                    <img :src="tabPane1[2].image" class="rounded" />
                  </div> -->
                </div>
                <Article />
              </template>

              <!-- 좋아요한 노래 -->
              <template slot="tab-pane-2">
                <div class="md-layout"></div>
                <!-- <div class="md-layout">
                  <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane2[0].image" class="rounded" />
                    <img :src="tabPane2[1].image" class="rounded" />
                    <img :src="tabPane2[2].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane2[3].image" class="rounded" />
                    <img :src="tabPane2[4].image" class="rounded" />
                  </div>
                </div> -->

                <Song />
              </template>

              <!-- 좋아요한 가수 -->
              <template slot="tab-pane-3">
                <!-- <div class="md-layout">
                  <div class="md-layout-item md-size-25 ml-auto">
                    <img :src="tabPane3[0].image" class="rounded" />
                    <img :src="tabPane3[1].image" class="rounded" />
                  </div>
                  <div class="md-layout-item md-size-25 mr-auto">
                    <img :src="tabPane3[2].image" class="rounded" />
                    <img :src="tabPane3[3].image" class="rounded" />
                    <img :src="tabPane3[4].image" class="rounded" />
                  </div>
                </div> -->
                <Singer />
              </template>

              <!-- 통계 -->
              <template slot="tab-pane-4">
                통계
              </template>

              <!-- 예쁜단어 -->
              <template slot="tab-pane-5"> <MyWord /></template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import '../assets/css/profile.css';
import { Tabs } from '@/components';
import Article from '../components/Article.vue';
import Song from '../components/Song.vue';
import Singer from '../components/Singer.vue';
import MyWord from '../components/MyWord.vue';
import { getuidCookie } from "@/util/cookie.js";
import { profileByUid } from "@/api/user.js";
export default {
  components: {
    Tabs,
    Article,
    Song,
    Singer,
    MyWord,
  },
  bodyClass: 'profile-page',
  created(){
    this.initUser(),
    profileByUid(
      this.uid,
      //console.log(this.loggedInUserData.uid),
      (response) => {
        // console.log('프로피이이일');
        // console.log(response.data);
         this.nickname = response.data.info.nickname;
         this.profile = response.data.info.profile;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods:{
    initUser(){
     // console.log('사용자'),
     // console.log(getuidCookie());
      this.uid = getuidCookie();
    },
  },
  data() {
    return {
      // tabPane1: [
      //   { image: require('@/assets/img/examples/studio-1.jpg') },
      //   { image: require('@/assets/img/examples/studio-2.jpg') },
      //   { image: require('@/assets/img/examples/studio-4.jpg') },
      //   { image: require('@/assets/img/examples/studio-5.jpg') },
      // ],
      // tabPane2: [
      //   { image: require('@/assets/img/examples/olu-eletu.jpg') },
      //   { image: require('@/assets/img/examples/clem-onojeghuo.jpg') },
      //   { image: require('@/assets/img/examples/cynthia-del-rio.jpg') },
      //   { image: require('@/assets/img/examples/mariya-georgieva.jpg') },
      //   { image: require('@/assets/img/examples/clem-onojegaw.jpg') },
      // ],
      // tabPane3: [
      //   { image: require('@/assets/img/examples/mariya-georgieva.jpg') },
      //   { image: require('@/assets/img/examples/studio-3.jpg') },
      //   { image: require('@/assets/img/examples/clem-onojeghuo.jpg') },
      //   { image: require('@/assets/img/examples/olu-eletu.jpg') },
      //   { image: require('@/assets/img/examples/studio-1.jpg') },
      // ],
      profile: "",
      nickname: "",
      uid:'',
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
    ...mapState(["loggedInUserData"]),
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

    img {
      margin-bottom: 2.142rem;
    }
  }
}
</style>
