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
          
          <div class="komu-searchbar">
            <SearchBar @eventdata="setsearchdata" />
            <div>
              <router-link class="comm_write_btn" to="/komuwikiwrite"
                >글 작성</router-link
              >
            </div>
          </div>
          <div class="community-tabs">
            <div class="md-layout"></div>

            <Article :list="list" />
          </div>
          <div class="paging">
            <Pagination />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profile.css';
import { getboard, getlist,search_list } from '@/api/komu.js';
// import { Tabs } from '@/components';
import { Pagination } from '@/components';
import Article from '../components/Article.vue';
import SearchBar from '../components/SearchBar.vue';

export default {
  components: {
    // Tabs,
    Article,
    Pagination,
    SearchBar,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      page: 1,
      searchpage:1,
      uid: 'uuu',
      namu_title: '7',
      searchdata:"",
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
          // this.songList=response.data.songList;
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
  },
  
  created() {
    getlist(
      this.page,
      (response) => {
        console.log(response.data);
        this.pagination = response.data.pagination;
        this.list = response.data.list;
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

.container {
  padding-bottom: 2rem;
  // margin: auto;
}

.komu-searchbar {
  height: 90px;
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
</style>
