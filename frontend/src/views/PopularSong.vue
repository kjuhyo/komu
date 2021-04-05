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
          <div class="community-title text-center">
            <h1 class="main-popluarsong-title">Popular Song</h1>
          </div>

          <div class="song_list">
            <!-- 검색바 -->
            <div class="song-searchbar">
              <div v-if="isMobile">
                <form class="m-search-container">
                  <input
                    type="text"
                    id="search-bar"
                    @keyup.enter="findname"
                    placeholder="노래 제목이나 가수명을 검색해주세요"
                    v-model="searchtext"
                  />
                  <div @click="findname">
                    <img
                      class="search-icon"
                      src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"
                    />
                  </div>
                </form>
              </div>

              <div v-else>
                <form class="search-container" @submit.prevent="findname">
                  <input
                    type="text"
                    id="search-bar"
                    placeholder="노래 제목이나 가수명을 검색해주세요"
                    v-model="searchtext"
                  />
                  <div @click="findname">
                    <img
                      class="search-icon"
                      src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"
                    />
                  </div>
                </form>
              </div>
            </div>
            <div></div>
            <nav class="song_menubar">
              <a class="purple" @click="popular('all')">전체</a>
              <a class="purple" @click="popular('댄스')">댄스</a>
              <a class="purple" @click="popular('발라드')">발라드</a>
              <a class="purple" @click="popular('록/메탈')">록/메탈</a>
              <a class="purple" @click="popular('R&B')">R&B</a>
              <a class="purple" @click="popular('랩/힙합')">랩/힙합</a>
              <a class="purple" @click="popular('성인가요')">성인가요</a>
              <a class="purple" @click="popular('국내')">국내</a>
              <a class="purple" @click="popular('애니메이션')">애니메이션</a>
              <a class="purple" @click="popular('인디')">인디</a>
              <a class="purple" @click="popular('포크')">포크</a>
            </nav>

            <div class="track_section">
              <div>
                <table>
                  <colgroup>
                    <col :style="{ width: '15%' }" />
                    <col :style="{ width: '40%' }" />
                    <col :style="{ width: '15%' }" />
                    <col :style="{ width: '10%' }" />
                    <col :style="{ width: '15%' }" />
                    <col :style="{ width: '5%' }" />
                  </colgroup>

                  <tr v-for="item in this.songList" :key="item.id">
                    <td><img :src="item.album_cover" alt="앨범사진" /></td>
                    <td>
                      <router-link
                        :to="{
                          name: 'songdetail',
                          query: {
                            id: item.id,
                          },
                        }"
                        >{{ item.song_name }}</router-link
                      >
                    </td>
                    <td>{{ item.singer_name }}</td>
                    <td>{{ item.genre }}</td>
                    <td>{{ item.issue_date }}</td>
                    <td>{{ item.song_like_cnt }}</td>
                  </tr>
                </table>
              </div>
              <div class="paging">
                <Pagination />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import SearchBar from '../components/SearchBar.vue';
import { Pagination } from '@/components';
import { getlist_pop, getListPopularGenre } from '@/api/song.js';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import { getPopularSongName } from '@/api/search.js';
import '../assets/css/searchbar.scss';
import '../assets/css/article.css';
import '../assets/css/songcategory.scss';

export default {
  components: {
    //SearchBar,
    Pagination,
    //Small,
  },
  data() {
    return {
      uid: '',
      songList: {
        id: '', //노래 id
        song_name: '',
        singer_name: '',
        album_cover: '',
        song_like_cnt: '', //총 좋아요 갯수
        genre: '',
        issue_date: '',
        page: 1,
      },
      pickgenre: '',
      page: 1,
      isMobile: false,
      searchtext: '',
      menubar: true,
    };
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
  },
  created() {
    this.initUser(),
      getlist_pop(
        this.page,
        (response) => {
          console.log('인기순');
          // console.log(response.data);
          this.songList = response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      );
  },
  computed: {
    ...mapState(['isLogin', 'loggedInUserData']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    initUser() {
      this.uid = getuidCookie();
    },
    popular: function(genre) {
      this.searchtext = '';
      this.menubar = true;
      if (genre == 'all') {
        //인기순 //전체
        getlist_pop(
          this.page,
          (response) => {
            console.log('인기순');
            // console.log(response.data);
            this.songList = response.data.songList;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.pickgenre = genre;
        getListPopularGenre(
          //인기순 //장르별
          this.pickgenre,
          this.page,
          (response) => {
            console.log('인기순');
            // console.log(response.data);
            this.songList = response.data.songList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    findname: function() {
      this.menubar = false;
      getPopularSongName(
        //검색결과 //인기순
        this.searchtext,
        (response) => {
          this.songList = response.data;
          //this.songList=response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
    // window.addEventListener('keyup', function(event) {
    //   if (event.keyCode === 13) {
    //     app.findname();
    //   }
    // });
  },
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

.section {
  padding: 0;
}

.main-popluarsong-title {
  padding-top: 2.5rem;
  // font-family: 'Nanum Gothic', sans-serif;
  font-family: 'Rakkas';
  color: #9c27b0;
  text-shadow: 4px 4px 0px #bdbdbd;
  font-size: 4em;
}
.song-searchbar {
  height: 90px;
}

.song_list {
  margin-top: 2rem;
  padding-bottom: 30px;
}

#menu {
  margin: auto;
  text-align: center;
}
.song-menu {
  width: 100% !important;
}

.summary_thumb {
  width: 200px;
  height: 176px;
}
.item {
  font-size: 20px;
}
.track_section {
  padding: 5px 0 32px;
}
.track_list {
  position: relative;
  margin: 0 -14px;
  color: #232323;
}
table {
  width: 100%;
  text-align: center;
  border: 0;
  // border: 1px solid black;
}
.paging {
  display: flex;
  justify-content: center;
}
.returnbutton {
  display: flex;
  justify-content: right;
}
</style>
