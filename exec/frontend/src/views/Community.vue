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
            <h1 class="commu-title">Community</h1>
          </div>

          <!-- 검색바 -->
          <div class="comm-searchbar">
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

          <div class="com-tab-write">
            <div v-if="isAlign" class="comm_sorting_container">
              <span class="comm_sorting" @click="popol">인기순</span>
              <span> | </span>
              <span class="comm_sorting" @click="newest">최신순</span>
            </div>
            <div class="comm-write-btn" v-if="isLogin">
              <router-link class="comm_write_btn" to="/communitywrite"
                >글 작성</router-link
              >
            </div>
          </div>

          <div v-if="isAlign" class="com-community-tabs">
            <div class="md-layout"></div>
            <CommunityArticle :list="list" />
          </div>

          <div v-else class="search-com-community-tabs">
            <div class="md-layout"></div>
            <CommunityArticle :list="list" />
          </div>

          <div v-if="list.length == 0" class="com-nosearch-result">
            검색 결과가 존재하지 않습니다.
          </div>

          <!-- <Pagination /> -->
          <div v-if="isSearchbar" class="Page-container">
            <nav class="page-nav" aria-label="Page navigation">
              <ul class="pagination-ul">
                <li class="page-item">
                  <input
                    type="button"
                    class="page-link"
                    @click="prevPage"
                    style="width: 40px; text-align: center; color: black"
                    value="<"
                  />
                </li>

                <li
                  class="page-item"
                  v-for="(listm, idx) in this.listmaker"
                  v-bind:key="idx"
                >
                  <input
                    type="button"
                    class="page-link"
                    @click="movePage"
                    v-bind:value="idx + 1"
                    style="width: 40px; text-align: center; color: black"
                  />
                  <input
                    type="text"
                    placeholder="listData"
                    v-bind:value="idx + 1"
                    @change="updateList"
                    disabled
                    style="display: none; color: black"
                  />
                </li>
                <li class="page-item">
                  <input
                    type="button"
                    class="page-link"
                    @click="nextPage"
                    style="width: 40px; text-align: center; color: black"
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
</template>

<script>
import '../assets/css/profile.css';
import { mapState } from 'vuex';
import '../assets/css/pagination.scss';
import { getCommunityContents } from '@/api/search.js';
import { getboard, getlist, poplist } from '@/api/community.js';
import swal from 'sweetalert';
import CommunityArticle from '../components/CommunityArticle.vue';

export default {
  components: {
    CommunityArticle,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      page: 1,
      searchtext: '',
      searchpage: 1,
      uid: 'uuu',
      // namu_title: '7',
      searchdata: '',
      isSearchbar: true,
      isAlign: true,
      listmaker: 0,
      prevnext: 0,
      pagination: {
        listSize: 0,
        rangeSize: 0,
        page: 0,
        range: 0,
        listCnt: 0,
        startPage: 0,
        startList: 0,
        endPage: 0,
        prev: false,
        next: false,
      },
      currentPage: 1,
      list: {
        c_content: '',
        c_date: '',
        c_img: '',
        c_like_cnt: 0,
        c_title: '',
        c_view: 0,
        cid: 0,
        uid: '',
        nickname: '',
        profile: '',
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
    },
  },
  computed: {
    ...mapState(['isLogin']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  created() {
    getlist(
      this.page,
      (response) => {
        // console.log(response.data);
        this.pagination = response.data.pagination;
        this.list = response.data.commList;
        this.listmaker = parseInt(
          this.pagination.listCnt / this.pagination.listSize + 1
        );
        // console.log(this.pagination);
      },
      (error) => {
        swal(`${error}`);
      }
    );
  },
  methods: {
    findname: function () {
      this.isAlign = false;
      if (this.searchtext.length != 0) {
        this.isSearchbar = false;
      } else {
        this.isSearchbar = true;
        this.isAlign = true;
        getlist(
          this.page,
          (response) => {
            // console.log(response.data);
            this.pagination = response.data.pagination;
            this.list = response.data.commList;
            this.listmaker = parseInt(
              this.pagination.listCnt / this.pagination.listSize + 1
            );
            // console.log(this.pagination);
          },
          (error) => {
            swal(`${error}`);
          }
        );
      }
      getCommunityContents(
        //검색결과 //최신순
        this.searchtext,
        (response) => {
          // console.log(response.data);
          this.list = response.data;
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    movePage(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
      this.prevnext = updatedText;

      getlist(
        this.currentPage,
        (response) => {
          this.list = response.data.commList;

          this.listm = parseInt(
            this.pagination.listCnt / this.pagination.listSize
          );
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    prevPage() {
      if (this.prevnext > 1) {
        this.prevnext -= 1;
        this.currentPage = this.prevnext;

        getlist(
          this.currentPage,
          (response) => {
            this.list = response.data.commList;

            this.listm = parseInt(
              this.pagination.listCnt / this.pagination.listSize
            );
          },
          (error) => {
            swal(`${error}`);
          }
        );
      }
    },
    nextPage() {
      if (this.prevnext <= this.listm) {
        this.prevnext++;
        this.currentPage = this.prevnext;
        // alert(this.prevnext);

        getlist(
          this.currentPage,
          (response) => {
            this.list = response.data.commList;

            this.listm = parseInt(
              this.pagination.listCnt / this.pagination.listSize
            );
          },
          (error) => {
            swal(`${error}`);
          }
        );
      }
    },
    updateList: function (event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
    },

    newest: function () {
      this.page = 1;
      getlist(
        this.page,
        (response) => {
          // console.log(response.data);
          this.pagination = response.data.pagination;
          this.list = response.data.commList;
          this.listmaker = parseInt(
            this.pagination.listCnt / this.pagination.listSize + 1
          );
          // console.log(this.pagination);
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    popol: function () {
      this.page = 1;
      poplist(
        this.page,
        (response) => {
          // console.log(response.data);
          this.pagination = response.data.pagination;
          this.list = response.data.commList;
          this.listmaker = parseInt(
            this.pagination.listCnt / this.pagination.listSize + 1
          );
          // console.log(this.pagination);
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
  },
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

.com-community-tabs {
  padding-bottom: 15px;
}

.search-com-community-tabs {
  padding-bottom: 15px;
  margin-top: 20px;
}
.com-nosearch-result {
  font-family: 'Nanum Gothic', sans-serif;
  text-align: center;
  padding-top: 35px;
  padding-bottom: 35px;
}

.comm-write-btn {
  position: absolute;
  right: 15px;
  // top: 0px;
  float: right;
}
.section {
  padding: 0;
}

.container {
  padding-bottom: 2rem;
}

.comm_sorting_container {
  padding-left: 10px;
  cursor: pointer;
}
.comm_write_btn {
  font-family: 'Nanum Gothic', sans-serif;
  padding-right: 10px;
}

.commu-title {
  padding-top: 2.5rem;
  // font-family: 'Nanum Gothic', sans-serif;
  font-family: 'Rakkas';
  color: #9c27b0;
  text-shadow: 4px 4px 0px #bdbdbd;
  font-size: 4em;
}
.community-tabs::v-deep {
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
.community-title {
  margin-bottom: 20px;
}
.com-tab-write {
  display: flex;
  justify-content: space-between;
  // margin-bottom: 10px;
}

.tab-write {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.paging {
  display: flex;
  justify-content: center;
}

.comm-searchbar {
  height: 90px;
}
</style>
