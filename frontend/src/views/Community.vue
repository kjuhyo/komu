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
          <div class="comm-searchbar">
            <SearchBar />
          </div>
          <div class="tab-write">
            <div class="comm_sorting_container">
              <span class="comm_sorting">인기순</span>
              <span> | </span>
              <span class="comm_sorting">최신순</span>
            </div>
            <div>
              <router-link class="comm_write_btn" to="/communitywrite"
                >글 작성</router-link
              >
            </div>
          </div>
          <div class="community-tabs">
            <div class="md-layout"></div>
            <Article />
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
    return {};
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
};
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
@import url('https://fonts.googleapis.com/css?family=Rakkas');

.section {
  padding: 0;
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
  padding-bottom: 2rem;
}

.comm-searchbar {
  height: 90px;
}
</style>
