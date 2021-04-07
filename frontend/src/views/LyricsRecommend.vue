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
          <div class="genrerecommend_container">
            <div class="recom-info-text-con">
              <span class="recom-info-text"
                >사용자님께 추천 드리는 음악입니다.</span
              >
            </div>
            <div v-if="uid">
              <div v-if="songList === undefined" class="text-center">
                <h3><strong>단어에 좋아요를 해주세요.</strong></h3>
              </div>
              <Song v-else :songList="songList" />
            </div>
            <div v-else class="text-center">
              <h3><strong>로그인을 해주세요</strong></h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/recommend.scss';
import Song from '../components/Song.vue';
import { getuidCookie } from '@/util/cookie.js';
import { lyricRecommend } from '@/api/recommend.js';

export default {
  components: { Song },
  data() {
    return {
      uid: '',
      isMobile: false,
      songList: {
        album_cover: '',
        album_name: '',
        genre: '',
        id: 0,
        lyric: '',
        singer_img: '',
        singer_name: '',
        song_name: '',
      },
    };
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {
    this.initUser(),
      lyricRecommend(
        this.uid,
        (response) => {
          this.songList = response.data.dtoList;
          console.log(this.songList);
        },
        (error) => {
          console.log(error);
        }
      );
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    initUser() {
      this.uid = getuidCookie();
    },
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
};
</script>
