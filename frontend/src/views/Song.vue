<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          
          <div class="artist_summary_section">
            <div class="summary_thumb">
             <h2 class="title_area">
                  <span class="title">노래 리스트</span>
                  <br>
                </h2>
            </div>
          </div>
          <div class="song_list">
            <h3>
              <span><SearchBar /></span>
            </h3>
            <div class="track_section">
            <table>
                <colgroup>
                  <col :style="{ width: '15%' }" />
                  <col :style="{ width: '40%' }" />
                  <col :style="{ width: '15%' }" />
                  <col :style="{ width: '10%' }" />
                  <col :style="{ width: '15%' }" />
                  <col :style="{ width: '5%' }" />
                </colgroup>
                <tr>
                  <th>album_cover</th>
                  <th>song_name</th>
                  <th>singer_name</th>
                  <th>genre</th>
                  <th>issue_date</th>
                  <th>song_like_cnt</th>
                </tr>
               <!-- <tr v-for="(item, index) in items" :key="`${index}_items`">
                  <td>{{ item.no }}</td>
                  <td>
                    <router-link :to="`/read?no=${item.no}`">{{
                      item.title
                    }}</router-link>
                  </td>
                  <td>{{ item.writer }}</td>
                  <td>{{ getFormatDate(item.regtime) }}</td>
                </tr>-->
              </table>
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
import SearchBar from '../components/SearchBar.vue';
import { Pagination } from '@/components';
import { getlist_new, getlist_pop, getlist_genre } from '@/api/song.js';

export default {
  components: {
    SearchBar,
    Pagination,
  },
    data(){
      return {
        id: '', //노래 id
        song_name: '',
        singer_name:'',
        album_cover:'',
        song_like_cnt:'', //총 좋아요 갯수
        genre:'발라드',
        issue_date:'',
        page:1,
      }
  },
  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
  },
  created(){
      getlist_genre(
        this.genre,
        this.page,
        (response) => {
          console.log("장르별");
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      ),
    getlist_new(
        this.page,
        (response) => {
            console.log("최신순");
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      ),
    getlist_pop(
        this.page,
        (response) => {
            console.log("인기순");
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      )
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
}
</script>

<style lang="scss" scoped>
  .artist_summary_section {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
  }
  .summary_thumb {
    width: 200px;
    height: 176px;
  }
  .artist_summary {
    margin-left: 30px;
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
  thead {
    display: table-header-group;
    vertical-align: middle;
    border-color: inherit;
  }
  tbody {
    display: table-row-group;
    vertical-align: middle;
    border-color: inherit;
  }
  .blind {
    overflow: hidden;
    position: absolute;
    clip: rect(0 0 0 0);
    width: 1px;
    height: 1px;
    margin: -1px;
  }
  .inner {
    position: relative;
    width: 60px;
    height: 60px;
  }
  .video-list {
    margin-bottom: 20px;
  }
  .video-items {
    display: flex;
  }
  .video-item {
    margin-left: 10px;
  }
  .paging {
  display: flex;
  justify-content: center;
}
</style>