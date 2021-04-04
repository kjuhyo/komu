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
                <form class="search-container">
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
            </div>
            <div class="track_section">
              <div>
                <div class="wrapper">
                  <!-- 메뉴바 -->
                  <div id="menu" v-if="menubar">
                    <div class="md-layout">
                      <div class="md-layout-item md-size-150 md-small-size-200">
                        <md-toolbar class="song-menu md-primary">
                          <div class="md-toolbar-row">
                            <div class="md-toolbar-section-start">
                              <md-list>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('all')"
                                >
                                  <p>전체</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('댄스')"
                                >
                                  <p>댄스</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('발라드')"
                                >
                                  <p>발라드</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('록/메탈')"
                                >
                                  <p>록/메탈</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('R&B')"
                                >
                                  <p>R&B</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('랩/힙합')"
                                >
                                  <p>랩/힙합</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('성인가요')"
                                >
                                  <p>트로트</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('국내')"
                                >
                                  <p>국내영화/드라마</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('애니메이션')"
                                >
                                  <p>애니메이션</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('인디')"
                                >
                                  <p>인디음악</p>
                                </md-list-item>
                                <md-list-item
                                  href="javascript:void(1)"
                                  @click="popular('포크')"
                                >
                                  <p>포크/블루스</p>
                                </md-list-item>
                              </md-list>
                            </div>
                            <div class="md-toolbar-section-end">
                              <md-button
                                class="md-just-icon md-simple md-white md-toolbar-toggle"
                              >
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                              </md-button>

                              <div class="md-collapse"></div>
                            </div>
                          </div>
                        </md-toolbar>
                      </div>
                    </div>
                  </div>
                  <!-- end menu -->

                  <div v-if="!menubar" @click="popular('all')">
                    <button id="returnbutton">장르별 목록으로 돌아가기</button>
                  </div>
                </div>
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
