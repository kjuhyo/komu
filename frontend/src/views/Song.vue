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
            <h1 class="main-popluarsong-title">Recent Song</h1>
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

            <!-- 메뉴 카테고리 -->
            <nav class="song_menubar">
              <a class="purple" @click="getlist_genre('all')">전체</a>
              <a class="purple" @click="getlist_genre('댄스')">댄스</a>
              <a class="purple" @click="getlist_genre('발라드')">발라드</a>
              <a class="purple" @click="getlist_genre('록/메탈')">록/메탈</a>
              <a class="purple" @click="getlist_genre('R&B')">R&B</a>
              <a class="purple" @click="getlist_genre('랩/힙합')">랩/힙합</a>
              <a class="purple" @click="getlist_genre('성인가요')">성인가요</a>
              <a class="purple" @click="getlist_genre('국내')">국내</a>
              <a class="purple" @click="getlist_genre('애니메이션')"
                >애니메이션</a
              >
              <a class="purple" @click="getlist_genre('인디')">인디</a>
              <a class="purple" @click="getlist_genre('포크')">포크</a>
            </nav>

            <!-- 최신노래 리스트 -->
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

                  <tr
                    v-for="item in this.songList"
                    :key="item.id"
                    :per-page="paginations.listSize"
                  >
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

              <!-- <Pagination /> -->
              <div class="Page" align="center">
                <nav aria-label="Page navigation">
                  <ul class="pagination">
                    <li class="page-item">
                      <input
                        type="button"
                        class="page-link"
                        @click="prevPage"
                        style="width:40px;text-align:center; color:black;"
                        value="<"
                      />
                    </li>

                    <li
                      class="page-item"
                      v-for="(list, idx) in this.listpage"
                      v-bind:key="idx"
                    >
                      <input
                        type="button"
                        class="page-link"
                        @click="movePage"
                        v-bind:value="idx + 1"
                        style="width:40px;text-align:center; color:black;"
                      />
                      <input
                        type="text"
                        placeholder="listData"
                        v-bind:value="idx + 1"
                        @change="updateList"
                        disabled
                        style="display:none; color:black;"
                      />
                    </li>
                    <li class="page-item">
                      <input
                        type="button"
                        class="page-link"
                        @click="nextPage"
                        style="width:40px;text-align:center; color:black;"
                        value=">"
                      />
                    </li>
                  </ul>
                </nav>
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
//import { Pagination } from '@/components';
import { getlist_new, getlist_genre } from '@/api/song.js';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import { getNewSongName } from '@/api/search.js';
import '../assets/css/searchbar.scss';
import '../assets/css/article.css';
import '../assets/css/songcategory.scss';

export default {
  components: {
    // SearchBar,
    // Pagination,
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
      pickgenre: 'all',
      //page:1,
      isMobile: false,
      searchtext: '',
      menubar: true,

      listmaker: 0,
      prevnext: 0,
      listpage: 10,
      paginations: {
        listSize: '',
        startPage: '',
        listCnt: '',
      },
      currentPage: 1,
      perPage: '',
      // listppp: function () {
      //     var listppp = [];
      //     for (var i = this.page - 10; i < this.page; i += 1) list.push(i);
      //     return listppp
      // },
    };
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
    },
  },
  created() {
    this.initUser(),
      getlist_new(
        //최신순 //장르전체
        this.songList.page,

        (response) => {
          this.paginations = response.data.pagination;
          this.songList = response.data.songList;
          this.listmaker = parseInt(
            this.paginations.listCnt / this.paginations.listSize + 1
          );
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
    getlist_genre: function(genre) {
      //console.log("함수실행");
      this.searchtext = '';
      this.menubar = true;
      if (genre == 'all') {
        getlist_new(
          //최신순 //장르전체
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.pickgenre = genre;
        getlist_genre(
          //최신순  //장르별
          this.pickgenre,
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    findname: function() {
      //this.searchInput = searchcontent.target.value;
      this.menubar = false;
      getNewSongName(
        //검색결과 //최신순
        this.searchtext,
        (response) => {
          //console.log(response.data);
          this.songList = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
    movePage(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
      this.prevnext = updatedText;
      this.currentPage *= 1
      this.prevnext *= 1
      console.log(this.prevnext)
      console.log(this.currentPage)

      getlist_new(
        //최신순 //장르전체
        //this.page,
        this.currentPage,
        (response) => {
          this.songList = response.data.songList;

          this.list = parseInt(
            this.paginations.listCnt / this.paginations.listSize
          );
        },
        (error) => {
          console.log(error);
        }
      );
    },
    prevPage() {
      if (this.prevnext > 10) {
        this.prevnext = this.prevnext - 10;
        this.currentPage = this.prevnext;
        this.listpage = this.listpage - 10;
        console.log(this.prevnext)
        console.log(this.currentPage)
        
        getlist_new(
          //최신순 //장르전체
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        this.prevnext = this.prevnext - 1;
        this.currentPage = this.prevnext;
        this.listpage = 10
        console.log(this.prevnext)
        console.log(this.currentPage)

        getlist_new(
          //최신순 //장르전체
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    nextPage() {
      if (this.prevnext <= this.listmaker - 10) {
        this.prevnext = this.prevnext + 10;
        this.currentPage = this.prevnext;
        console.log(typeof(this.prevnext))
        console.log(this.prevnext)
        console.log(this.currentPage)
        this.listpage = this.listpage + 10;

        getlist_new(
          //최신순 //장르전체
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          },
          (error) => {
            console.log(error);
          }
        );
      } else if ((this.prevnext > this.listmaker -10) && (this.prevnext < this.listmaker)) {
        this.prevnext = this.prevnext + 1;
        this.currentPage = this.prevnext;
        this.listpage = this.listmaker;
        console.log(this.prevnext)
        console.log(this.currentPage)

        getlist_new(
          //최신순 //장르전체
          //this.page,
          this.currentPage,
          (response) => {
            this.songList = response.data.songList;

            this.list = parseInt(
              this.paginations.listCnt / this.paginations.listSize
            );
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    updateList: function(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
    },
    MovePage: function(page) {
      console.log('안녕안녕');
      console.log(this.currentPage);

      this.currentPage = page;
      console.log(page);

      getlist_new(
        //최신순 //장르전체
        //this.page,
        this.currentPage,
        (response) => {
          this.songList = response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      );
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
