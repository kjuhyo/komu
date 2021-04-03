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
          <div class="songTitle_summary_section">
              <div>
                <h3><strong>{{this.song_name}}</strong></h3>
                <h5>{{this.singer_name}}</h5>
                <div v-if="isLogin">
                  {{this.LIKE}}
                </div>
                <div class="like">
                    <h5>좋아요 {{this.song_like_count}}</h5>
                </div>
                <md-button class="md-primary md-just-icon md-round"
            ><md-icon>favorite</md-icon></md-button
          >
              </div>
          </div>

          <div class="video-list">
            <div class="video-items">
              <div class="video-item">
                <a href="https://www.youtube.com/watch?v=XsX3ATc3FbA">
                  <img :src="this.album_cover" alt="album_cover">
                </a>
              </div>
              <div class="video-item">
                <div v-for="(item,index) in this.wordList" :key="index">
                    {{item.namu_title}}
                </div>
              </div>
            </div>
          </div>
          
          <div class="lyrics">
              {{this.lyric}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { get_song } from '@/api/song.js';
//import axios from "axios";
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';

export default {
  bodyClass: 'profile-page',
  data(){
      return {
        uid: '',
        id: this.$route.query.id, //노래 id
        lyric:'',
        album_cover:'',
        singer_name:'',
        song_name:'',
        song_like_count:'', //총 좋아요 갯수
        wordList:{
            song_id:'',
            namu_title:'',
        },
        LIKE:'', //내가 좋아요 눌렀는지
      }
  },
  created(){
      this.initUser(),
      /*axios
        .get(`http://localhost:9999/komu/song/info`, {
            params: {
                id: this.id,
                uid: this.uid
            }
        })
        .then(response =>{
            this.lyric= response.data.song.lyric;
        })
        .catch(()=>{
            console.log("가사읽어오기실패")
        })
       
      */
     get_song(
        this.id,
        this.uid,
        (response) => {
          //console.log(response.data);
          this.singer_name=response.data.song.singer_name;
          this.song_name=response.data.song.song_name;
          this.lyric= response.data.song.lyric;
          this.album_cover=response.data.song.album_cover;
          this.song_like_count=response.data.song_like_count;
          this.wordList=response.data.wordList;
          this.LIKE=response.data.LIKE;
        },
        (error) => {
          console.log(error);
        }
      );
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
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
  .songTitle_summary_section {
    display: flex;
    justify-content: left;
    margin-bottom: 20px;
    margin-left: 2%;
  }
 
  .item {
    font-size: 20px;
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
</style>