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
            <h1 class="main-komu-title">KOMUWiki</h1>
          </div>

          <!-- 검색바 -->
          <div class="komu-searchbar">
            <SearchBar @eventdata="setsearchdata" />
          </div>

          <!-- 글작성버튼 -->
          <div class="tab-write" v-if="isLogin">
            <div>
              <router-link class="komu_write_btn" to="/komuwikiwrite"
                >글 작성</router-link
              >
            </div>
          </div>

          <div class="community-tabs">
            <div class="md-layout"></div>

            <Article :list="list" />
          </div>

          <div v-if="list.length == 0" class="komu-nosearch-result">
            검색 결과가 존재하지 않습니다.
          </div>

          <!-- <Pagination /> -->
          <div v-if="isSearchbar" class="Page">
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
          <!-- 페이지네이션 끝 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profile.css';
import '../assets/css/pagination.scss';
import { getboard, getlist, search_list } from '@/api/komu.js';
// import { Tabs } from '@/components';
// import { Pagination } from '@/components';
import Article from '../components/Article.vue';
import SearchBar from '../components/SearchBar.vue';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import swal from 'sweetalert';

export default {
  components: {
    // Tabs,
    Article,
    // Pagination,
    SearchBar,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      page: 1,
      searchpage: 1,
      uid: '',
      // namu_title: '7',
      searchdata: '',
      isSearchbar: true,
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
        namu_id: 0,
        uid: '',
        namu_title: '',
        namu_content: '',
        namu_date: '',
        namu_img: '',
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
    getboard: function () {
      getboard(
        this.namu_title,
        this.uid,
        (response) => {
          swal(`${response.data}`);
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    setsearchdata: function (data) {
      this.searchdata = data;
      if (this.searchdata.length != 0) {
        this.isSearchbar = false;
      } else {
        this.isSearchbar = true;
      }
      // console.log('검색했찌롱');
      // console.log(this.searchdata.length);
      // console.log(this.searchdata);
      // console.log(this.isSearchbar);
      search_list(
        this.searchpage,
        this.searchdata,
        (response) => {
          // console.log(response.data);
          this.pagination = response.data.pagination;
          this.list = response.data.list;
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
          this.list = response.data.list;

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
            this.list = response.data.list;

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
            this.list = response.data.list;

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
  },

  created() {
    this.initUser(),
      getlist(
        this.page,
        (response) => {
          // console.log(response.data);
          this.pagination = response.data.pagination;
          this.list = response.data.list;
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
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

.komu-nosearch-result {
  font-family: 'Nanum Gothic', sans-serif;
  text-align: center;
  padding-top: 35px;
  padding-bottom: 35px;
}
.main-komu-title {
  padding-top: 2.5rem;
  // font-family: 'Nanum Gothic', sans-serif;
  font-family: 'Rakkas';
  color: #9c27b0;
  text-shadow: 4px 4px 0px #bdbdbd;
  font-size: 4em;
}
.section {
  padding: 0;
}

.komu_write_btn {
  font-family: 'Nanum Gothic', sans-serif;
  padding-right: 10px;
  text-align: right;
  position: absolute;
  right: 10px;
  // top: 0px;
}

.container {
  padding-bottom: 2rem;
  // margin: auto;
}

.komu-searchbar {
  height: 90px;
}

.community-tabs::v-deep {
  padding-top: 20px;
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
.tab-write {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
.paging {
  display: flex;
  justify-content: center;
}
</style>
