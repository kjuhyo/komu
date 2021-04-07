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
          <div class="tab-write">
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
import { getboard, getlist,search_list } from '@/api/komu.js';
// import { Tabs } from '@/components';
// import { Pagination } from '@/components';
import Article from '../components/Article.vue';
import SearchBar from '../components/SearchBar.vue';

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
      searchpage:1,
      uid: 'uuu',
      // namu_title: '7',
      searchdata:"",
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
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    getboard: function() {
      getboard(
        this.namu_title,
        this.uid,
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    setsearchdata:function(data){
    this.searchdata = data;
    console.log(this.searchdata);
    console.log("여기는 상위컴포넌트");
    search_list(
      this.searchpage,
      this.searchdata,
      (response)=>{
        console.log(response.data);
        this.pagination = response.data.pagination;
        this.list = response.data.list;
      },
      (error)=>{
        console.log(error);
      }

    )

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
            this.list = response.data.list;

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
            this.list = response.data.list;

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
  
  created() {
    getlist(
      this.page,
      (response) => {
        console.log(response.data);
        this.pagination = response.data.pagination;
        this.list = response.data.list;
        this.listmaker = parseInt(
          this.pagination.listCnt / this.pagination.listSize + 1
        );
        console.log(this.pagination)
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

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
