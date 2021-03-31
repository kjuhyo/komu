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
              <span>
                <h3><strong>작은 것들을 위한 시</strong></h3>
                <h5>방탄소년단</h5>
              </span>
          </div>

          <div class="video-list">
            <div class="video-items">
              <div class="video-item">
                <a href="https://www.youtube.com/watch?v=XsX3ATc3FbA">
                  <img src="https://i.ytimg.com/vi/XsX3ATc3FbA/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAzjeJ1TqEib1np5etuP_oBKhAIcw" alt="작은 것들을 위한 시">
                </a>
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
        id:31737197, //노래 id
        lyric:'',
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
          this.lyric= response.data.song.lyric;
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
    ...mapState(['loggedInUserData']),
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
  .summary_thumb {
    width: 176px;
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
</style>