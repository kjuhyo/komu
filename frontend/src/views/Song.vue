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
              <div class="wrapper">
    <div id="menu">
      <div class="md-layout">
        <div class="md-layout-item md-size-150 md-small-size-200">
          <md-toolbar class="md-primary">
            <div class="md-toolbar-row">
              <div class="md-toolbar-section-start">
                <h3 class="md-title">장르</h3>
                <md-list>
                  <md-list-item href="#/" @click="getlist_genre('댄스')">
                    <p>댄스</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('발라드')">
                    <p>발라드</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('록/메탈')">
                    <p>록/메탈</p>
                  </md-list-item>
                   <md-list-item href="javascript:void(1)" @click="getlist_genre('R&B')">
                    <p>R&B</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('랩/힙합')">
                    <p>랩/힙합</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('성인가요')">
                    <p>성인가요</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('국내')">
                    <p>국내영화/드라마</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('애니메이션')">
                    <p>애니메이션</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('인디')">
                    <p>인디음악</p>
                  </md-list-item>
                  <md-list-item href="javascript:void(1)" @click="getlist_genre('포크')">
                    <p>포크/블루스</p>
                  </md-list-item>
                </md-list>
              </div>
              <div class="md-toolbar-section-end">
                <md-button
                  class="md-just-icon md-simple md-white md-toolbar-toggle"
                >
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </md-button>

                <div class="md-collapse"></div>
              </div>
            </div>
          </md-toolbar>
        </div>
        
      </div>
    </div>
    <!-- end menu -->
  </div>
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
import { getlist_new, getlist_genre } from '@/api/song.js';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
//import {Small} from './components/SmallNavigationSection';

export default {
  components: {
    SearchBar,
    Pagination,
    //Small,
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
            genre:'',
            issue_date:'',
            page:2,
        },
        pickgenre:'',
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
    this.initUser(),
      getlist_new( //최신순
        this.songList.page,
        (response) => {
          this.songList=response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      )
      
    /*getlist_pop(
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
    },
    getlist_genre:function(genre){
      //console.log("함수실행");
      this.pickgenre=genre;
      getlist_genre(
        this.pickgenre,
        this.page,
        (response) => {
          this.songList=response.data.songList;
        },
        (error) => {
          console.log(error);
        }
      )
    },
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