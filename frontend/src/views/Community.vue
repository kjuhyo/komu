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
            <div class="md-layout-item md-size-50 mx-auto">
            </div>
          </div>
          <div class="community-title text-center">
            <h1>Community</h1>
          </div>
          <div>
            <SearchBar />
          </div>
          <div class="tab-write">
            <div>
              <span>최신순 | 인기순</span>
            </div>
            <div>
              <a href="#">글 작성</a>
            </div>
          </div>
          <div class="community-tabs">
            <div class="md-layout">
            </div>
            <Article />
            <!-- <tabs
              :tab-name="[
                'My Article',
                'My Song',
              ]"
              :tab-icon="[
                'article',
                'music_note',
              ]"
              plain
              nav-pills-icons
              color-button="primary"
            >
              <template slot="tab-pane-1">
                <div class="md-layout">
                </div>
                <Article />
              </template>
            </tabs> -->
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
import {Pagination} from '@/components'
import Article from '../components/Article.vue';
import SearchBar from '../components/SearchBar.vue'

export default {
  components: {
    // Tabs,
    Article,
    Pagination,
    SearchBar
  },
  bodyClass: 'profile-page',
  data() {
    return {
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
};
</script>

<style lang="scss" scoped>
  .section {
    padding: 0;
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
