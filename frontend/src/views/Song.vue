<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          
          <div class="title">
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

                <tr v-for="(item) in this.songList" :key="item.id">
                 <td><img :src= "item.album_cover" alt="앨범사진"></td>
                 <td> <router-link :to="{
                    name:'songdetail',
                    query:{
                        id:item.id,
                    },
                }">{{
                      item.song_name
                    }}</router-link>
                
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
import SearchBar from '../components/SearchBar.vue';
import { Pagination } from '@/components';
//import { getlist_new, getlist_pop, getlist_genre } from '@/api/song.js';
import { getlist_new} from '@/api/song.js';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';

export default {
  components: {
    SearchBar,
    Pagination,
  },
    data(){
      return {
        uid: '',
        songList:{
            id: '', //노래 id
            song_name: '',
            singer_name:'',
            album_cover:'',
            song_like_cnt:'', //총 좋아요 갯수
            genre:'발라드',
            issue_date:'',
            page:2,
        }
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
    this.initUser(),
     /* getlist_genre(
        this.items.genre,
        this.items.page,
        (response) => {
          console.log("장르별");
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      ),*/
    getlist_new(
        this.songList.page,
        (response) => {
         // console.log("최신순");
         // console.log(response.data);
          this.songList=response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      )
   /* getlist_pop(
        this.items.page,
        (response) => {
            console.log("인기순");
          console.log(response.data);
        },
        (error) => {
          console.log(error);
        }
      )*/
  },
  computed: {
    ...mapState(['isLogin','loggedInUserData']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    initUser() {
      this.uid = getuidCookie();
    }
  }
}
</script>

<style lang="scss" scoped>
  .title {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
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
</style>