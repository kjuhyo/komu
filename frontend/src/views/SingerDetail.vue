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
          <div class="artist_summary_section">
            <div class="summary_thumb">
              <img
                src="https://cdnimg.melon.co.kr/cm2/artistcrop/images/006/72/375/672375_20201109165043_500.jpg?e96a06242ecabc2cfad7e30c3445bdc8/melon/resize/416/quality/80/optimize"
                alt="Circle Image"
                class="img-raised rounded-circle img-fluid userprofile-image"
                width="100%" height="100%"
              />
            </div>
            <div class="artist_summary">
              <div class="text_area">
                <h2 class="title_area">
                  <span class="title">방탄소년단</span>
                  <br>
                  <span class="item">2013.06.12 데뷔</span>
                </h2>
              </div>
              <div class="option_area">
                <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"/></svg>
                <!-- <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/></svg> -->
              </div>
            </div>
          </div>
          <div>
            <hr>
          </div>
          <div class="song_list">
            <h3>
              <span>노래</span>
            </h3>
            <div class="track_section">
              <div>
                <div class="track_list">
                  <table border="1">
                    <thead>
                      <tr>
                        <th scope="col" class="thumb">
                          <span class="blind">자켓 이미지</span>
                        </th>
                        <th scope="col" class="song">
                          <span class="blind">곡명</span>
                        </th>
                        <th scope="col" class="artist">
                          <span class="blind">아티스트명</span>
                        </th>
                        <th scope="col" class="album">
                          <span class="blind">앨범명</span>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td class="thumb">
                          <div class="inner">
                            <img src="https://musicmeta-phinf.pstatic.net/album/004/820/4820425.jpg?type=r120Fll&v=20210118143211"
                            alt="" class="img_thumb">
                          </div>
                        </td>
                        <td class="song">
                          <div>
                            <span>Dynamite</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>방탄소년단</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>Dynamite (DayTime Version)</span>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td class="thumb">
                          <div class="inner">
                            <img src="https://musicmeta-phinf.pstatic.net/album/005/100/5100133.jpg?type=r120Fll&v=20210302185528"
                            alt="" class="img_thumb">
                          </div>
                        </td>
                        <td class="song">
                          <div>
                            <span>Life Goes On</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>방탄소년단</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>BE</span>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td class="thumb">
                          <div class="inner">
                            <img src="https://musicmeta-phinf.pstatic.net/album/001/781/1781318.jpg?type=r120Fll&v=20210219182938"
                            alt="" class="img_thumb">
                          </div>
                        </td>
                        <td class="song">
                          <div>
                            <span>봄날</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>방탄소년단</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>YOU NEVER WALK ALONE</span>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td class="thumb">
                          <div class="inner">
                            <img src="https://musicmeta-phinf.pstatic.net/album/004/991/4991304.jpg?type=r120Fll&v=20201118115806"
                            alt="" class="img_thumb">
                          </div>
                        </td>
                        <td class="song">
                          <div>
                            <span>Savage Love (Laxed - Siren Beat) (BTS Remix)</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>Jawsh 685, Jason Derulo, 방탄소년단</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>Savage Love (Laxed - Siren Beat) [BTS Remix]</span>
                          </div>
                        </td>
                      </tr>
                      <tr>
                        <td class="thumb">
                          <div class="inner">
                            <img src="https://musicmeta-phinf.pstatic.net/album/003/003/3003472.jpg?type=r120Fll&v=20210118143210"
                            alt="" class="img_thumb">
                          </div>
                        </td>
                        <td class="song">
                          <div>
                            <span>작은 것들을 위한 시 (Boy With Luv) (Feat. Halsey)</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>방탄소년단</span>
                          </div>
                        </td>
                        <td>
                          <div>
                            <span>MAP OF THE SOUL : PERSONA</span>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div>
            <hr>
          </div>
          <div class="video-list">
            <h3>
              <span>비디오</span>
            </h3>
            <div class="video-items">
              <div>
                <a href="https://www.youtube.com/watch?v=gdZLi9oWNZg">
                  <img src="https://i.ytimg.com/vi/gdZLi9oWNZg/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAxs7M8QibkuTg0GrqASP1-0CfaUQ" alt="Dynamite">
                </a>
                <h4><strong>Dynamite</strong></h4>
                <span>방탄소년단</span>
              </div>
              <div class="video-item">
                <a href="https://www.youtube.com/watch?v=XsX3ATc3FbA">
                  <img src="https://i.ytimg.com/vi/XsX3ATc3FbA/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAzjeJ1TqEib1np5etuP_oBKhAIcw" alt="작은 것들을 위한 시">
                </a>
                <h4><strong>작은 것들을 위한 시</strong></h4>
                <span>방탄소년단</span>
              </div>
              <div class="video-item">
                <a href="https://www.youtube.com/watch?v=pBuZEGYXA6E">
                  <img src="https://i.ytimg.com/vi/pBuZEGYXA6E/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAZDb9Ck8p42EA7hAWZOS71_dSS1g" alt="IDOL">
                </a>
                <h4><strong>IDOL</strong></h4>
                <span>방탄소년단</span>
              </div>
              <!-- <div class="">
                <iframe width="560px" height="560" src="https://www.youtube.com/embed/gdZLi9oWNZg" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <h4><strong>Dynamite</strong></h4>
                <span>방탄소년단</span>
              </div>
              <div class="video-item">
                <iframe width="560" height="560" src="https://www.youtube.com/embed/XsX3ATc3FbA" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <h4><strong>작은 것들을 위한 시</strong></h4>
                <span>방탄소년단</span>
              </div>
              <div class="video-item">
                <iframe width="560" height="560" src="https://www.youtube.com/embed/pBuZEGYXA6E" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                <h4><strong>IDOL</strong></h4>
                <span>방탄소년단</span>
              </div> -->
            </div>
          </div>
          <div>
            <hr>
          </div>
          <Comment />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Comment from '../components/Comment.vue';

export default {
  components: {
    Comment
  },
  bodyClass: 'profile-page',
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
}
</script>

<style lang="scss" scoped>
  .artist_summary_section {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
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