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
          <div class="songTitle_summary_section">
            <!-- 노래/앨범이미지 카드-->
            <div class="songdetail-card">
              <img
                class="songdetail-card_image"
                :src="this.album_cover"
                alt="album_cover"
              />

              <div class="songdetail-card_info">
                <div class="songdetail-card_info_artist">
                  {{ this.song_name }}
                </div>
                <div class="songdetail-card_info_album">
                  {{ this.singer_name }}
                </div>
              </div>
              <a
                :href="
                  `https://www.youtube.com/results?search_query=${this.singer_name}+${this.song_name}`
                "
                target="_blank"
              >
                <div class="songdetail-card_play"></div>
              </a>
            </div>
            <!-- 노래/앨범이미지 카드 end -->

            <!-- 노래정보 -->
            <div class="songdetail-header-info">
              <tr>
                <th class="sd-h-title" scope="row">노래제목</th>
                <td class="sd-h-info">{{ this.song_name }}</td>
              </tr>
              <tr>
                <th class="sd-h-title" scope="row">가수</th>
                <td class="sd-h-info">{{ this.singer_name }}</td>
              </tr>
              <tr>
                <th class="sd-h-title" scope="row">앨범제목</th>
                <td class="sd-h-info">{{ this.album_name }}</td>
              </tr>
              <tr>
                <th class="sd-h-title" scope="row">장르</th>
                <td class="sd-h-info">{{ this.genre }}</td>
              </tr>
              <tr>
                <th class="sd-h-title" scope="row">발매일</th>
                <td class="sd-h-info">{{ this.issue_date }}</td>
              </tr>
              <tr>
                <th class="sd-h-title" scope="row">좋아요수</th>
                <td class="sd-h-info">{{ this.song_like_count }}</td>
              </tr>
            </div>

            <!-- 좋아요 버튼 -->
            <div class="songdetail-like" v-if="isLogin">
              <b-icon
                v-if="LIKE == 0"
                class="wiki_option_icon"
                icon="heart"
                font-scale="2"
                @click="Like"
              ></b-icon>
              <b-icon
                v-if="LIKE == 1"
                class="wiki_option_icon"
                icon="heart-fill"
                font-scale="2"
                @click="Like"
              ></b-icon>
            </div>
          </div>

          <!-- 탭바 -->
          <div class="songdetail-tabs">
            <tabs
              :tab-name="['Lyrics', 'Words']"
              :tab-icon="['article', 'music_note']"
              plain
              nav-pills-icons
              color-button="primary"
            >
              <!-- 가사 -->
              <template slot="tab-pane-1">
                <div class="md-layout">
                  <div class="songdetail-lyric">
                    {{ this.lyric }}
                  </div>
                </div>
              </template>

              <!-- 단어 -->
              <template slot="tab-pane-2">
                <div class="md-layout">
                  <div class="songdetail-word-container">
                    <!-- 곡에 있는 단어 리스트 -->
                    <div class="cards-container">
                      <div
                        class="no-card-question"
                        v-if="this.wordList.length == 0"
                      >
                        등록된 단어가 없습니다. 단어를 등록해주세요!
                      </div>
                      <div v-else class="card card-css songdetail-card-css">
                        <div
                          v-for="(item, index) in this.wordList"
                          :key="index"
                          class="card-question"
                        >
                          <router-link
                            :to="`/komuwikidetail/${item.namu_title}`"
                          >
                            {{ item.namu_title }}
                          </router-link>
                        </div>
                      </div>
                    </div>

                    <!-- 단어등록 -->
                    <!--<div class="songdetail-writekomu" v-if="isLogin">
                      <router-link
                        class="songdetail-writekomu-btn"
                        to="/komuwikiwrite"
                      >
                        <p class="wirte-komu-font">단어 등록</p>
                      </router-link>
                    </div>-->
                    <!-- 단어등록끝 --> 

                    <!-- 단어등록 -->
                    <div class="songdetail-writekomu" v-if="isLogin">
                        <p class="wirte-komu-font" @click="wordbar=true">단어 등록</p>
                  
                    </div>
                    <div v-if="wordbar=true">
                        <input type="text" id="word_bar" v-model="word_bar" placeholder="등록할 단어를 입력하세요">
                        <div @click="insertWord">등록</div>
                      </div>
                    <!-- 단어등록끝 -->
                  </div>
                </div>
              </template>
            </tabs>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { get_song, do_like, search_word } from '@/api/song.js';
import { Tabs } from '@/components';
//import axios from "axios";
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import '../assets/css/songdetail.scss';
import '../assets/css/myword.scss';

export default {
  components: {
    Tabs,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      uid: '',
      id: this.$route.query.id, //노래 id
      lyric: '',
      album_cover: '',
      singer_name: '',
      song_name: '',
      album_name: '',
      genre: '',
      issue_date: '',
      song_like_count: '', //총 좋아요 갯수
      wordList: {
        song_id: '',
        namu_title: '',
      },
      LIKE: '', //내가 좋아요 눌렀는지
      songlike: {
        song_id: '',
        uid: '',
      },
      wordbar:false,
      word_bar:'',
      sw:{
        song_id:'',
        namu_title:'',
      }
    };
  },
  created() {
    this.initUser(),
      get_song(
        this.id,
        this.uid,
        //console.log('uid'),
        //console.log(this.uid),
        (response) => {
          console.log('응답');
          this.id=response.data.song.id;
          this.singer_name = response.data.song.singer_name;
          this.song_name = response.data.song.song_name;
          this.lyric = response.data.song.lyric;
          this.album_cover = response.data.song.album_cover;
          this.album_name = response.data.song.album_name;
          this.genre = response.data.song.genre;
          this.issue_date = response.data.song.issue_date;
          this.song_like_count = response.data.song_like_count;
          this.wordList = response.data.wordList;
          this.LIKE = response.data.LIKE;
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
    ...mapState(['isLogin', 'loggedInUserData']),
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
    Like: function() {
      this.songlike.song_id = this.id;
      this.songlike.uid = this.uid;
      do_like(
        this.songlike,
        (response) => {
          this.LIKE = response.data.LIKE;
          //console.log(response.data.LIKE);
          //console.log(response.data.message);
        },
        (error) => {
          console.log(error.data);
        }
      );
    },
    insertWord:function(){
      this.sw.song_id=this.id;
      this.sw.namu_title=this.word_bar;
      search_word(
       this.sw,
       this.uid,
       //console.log(this.uid),
       (response) => {
          //console.log('search_word')
          //console.log(this.search_word)
          if(response.data.message==='goKomuwiki'){
            alert("코뮤위키에 없는 단어입니다. 코뮤위키에서 단어를 등록해주세요!");
            this.$router.push("/komuwikiwrite");
          }
          else if(response.data.message==='existInKomu'){
            alert("코뮤위키에서 단어의 의미를 파악해보세요!");
            this.$router.push("/komuwikidetail/"+this.sw.namu_title);
            
          }
          else if(response.data.message==='existInList'){
            alert("단어 목록에 이미 있는 단어에요!");
          }
        },
        (error) => {
          console.log(error.data);
        }
     )
    }
  },
};
</script>

<style lang="scss" scoped>
.songTitle_summary_section {
  display: flex;
  // justify-content: left;
  // margin-bottom: 20px;
  margin-left: 2%;
}

.songdetail-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.413rem;
    padding-bottom: 5px;
  }

  .md-card-tabs .tab-content {
    padding: 0px;
  }
}

.tab-pane-2 {
  .md-layout {
    display: inline;
  }
}

.a:link {
  text-decoration: none;
  color: black !important;
}

.a {
  color: black !important;
}
.a:visited {
  text-decoration: none;
  color: black !important;
}

.a:active {
  text-decoration: none;
  color: black !important;
}

.a:hover {
  text-decoration: none;
  color: black !important;
}
</style>
