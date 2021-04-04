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
                  <b-icon v-if="LIKE==0"
                  class="wiki_option_icon"
                  icon="heart"
                  font-scale="2"
                  @click="Like"
                ></b-icon>
                <b-icon v-if="LIKE==1"
                  class="wiki_option_icon"
                  icon="heart-fill"
                  font-scale="2"
                  @click="Like"
                ></b-icon>
                </div>
                
                <div class="like">
                    <h5>좋아요 {{this.song_like_count}}</h5>
                </div>
              </div>
          </div>

          <div class="video-list">
            <div class="video-items">
              <div class="video-item">
                <a :href="`https://www.youtube.com/results?search_query=${this.singer_name}+${this.song_name}`" target="_blank">
                  <img :src="this.album_cover" alt="album_cover">
                </a>
              </div>
              <div class="video-item" v-if="isLogin">
                <router-link class="navbarrouting" to="/komuwikiwrite">
                <p>단어 등록</p>
                </router-link>
              </div>
               <div v-for="(item,index) in this.wordList" :key="index">
                  <router-link :to="`/komuwikidetail/${item.namu_title}`">
                    {{item.namu_title}}
                  </router-link>
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
import { get_song, do_like } from '@/api/song.js';
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
        songlike:{
          song_id:'',
          uid:'',
        }
      }
  },
  created(){
      this.initUser(),
      get_song(
        this.id,
        this.uid,
        //console.log('uid'),
        //console.log(this.uid),
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
    },
  
    Like:function(){
      this.songlike.song_id=this.id;
      this.songlike.uid=this.uid;
      do_like(
        this.songlike,
        (response)=>{
          this.LIKE = response.data.LIKE;
          //console.log(response.data.LIKE);
          //console.log(response.data.message);
        },
        (error)=>{
          console.log(error.data);
        }
      )
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