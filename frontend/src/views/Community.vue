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

          <div class="tab-write">
            <div class="comm_sorting_container">
              <span class="comm_sorting">인기순</span>
              <span> | </span>
              <span class="comm_sorting">최신순</span>
            </div>
            <div v-if="isLogin">
              <router-link class="comm_write_btn" to="/communitywrite"
                >글 작성</router-link
              >
            </div>
          </div>
          <div class="community-tabs">
            <div class="md-layout"></div>
            <CommunityArticle :list="list" />
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
                  v-for="(listm, idx) in this.listmaker"
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
          <!-- <div class="paging">
            <Pagination />
          </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profile.css';
import { mapState } from 'vuex';
import { getCommunityContents } from '@/api/search.js';
import { getboard, getlist } from '@/api/community.js';
// import { Tabs } from '@/components';
// import { Pagination } from '@/components';
import CommunityArticle from '../components/CommunityArticle.vue';
import { getSingerName } from '@/api/user.js';

export default {
  components: {
    // Tabs,
    CommunityArticle,
    // Pagination,
    // SearchBar,
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
        console.log(response.data);
        this.pagination = response.data.pagination;
        this.list = response.data.commList;
        this.listmaker = parseInt(
          this.pagination.listCnt / this.pagination.listSize + 1
        );
        console.log(this.pagination);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    // setsearchdata(data) {
    //   this.searchdata = data;
    //   console.log(this.searchdata);
    //   console.log('여기는 상위컴포넌트');
    //   search_list(
    //     this.searchdata,
    //     (response) => {
    //       console.log(response.data);
    //       this.list = response.data.list;
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },
    findname: function() {
      getCommunityContents(
        //검색결과 //최신순
        this.searchtext,
        (response) => {
          console.log(response.data);
          this.list = response.data;
        },
        (error) => {
          console.log(error);
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
          console.log(error);
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
            console.log(error);
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
            console.log(error);
          }
        );
      }
    },
    updateList: function(event) {
      var updatedText = event.target.value;
      this.currentPage = updatedText;
    },
  },
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

.section {
  padding: 0;
}

.container {
  padding-bottom: 2rem;
}

.comm_sorting_container {
  padding-left: 10px;
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
