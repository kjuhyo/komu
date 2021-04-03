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
          <div class="singer-title text-center">
            <h1 class="main-singer-title">Singer</h1>
          </div>
          <div class="singer-searchbar">
            <SearchBar />
          </div>

          <!-- 아티스트 -->
          <div class="artist">
            <div class="artist-area">
              <img
                src="https://cdnimg.melon.co.kr/cm2/artistcrop/images/006/72/375/672375_20201109165043_500.jpg?e96a06242ecabc2cfad7e30c3445bdc8/melon/resize/416/quality/80/optimize"
                alt="Circle Image"
                class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
                width="42px"
                height="42px"
              />
              <div class="artist-box">
                <div class="">
                  <a href="#">방탄소년단</a>
                </div>
                <div class="">
                  <p>아티스트</p>
                </div>
              </div>
            </div>
            <div class="artist-area">
              <img
                src="https://musicmeta-phinf.pstatic.net/artist/000/500/500555.jpg?type=ff300_300&v=20201002130007"
                alt="Circle Image"
                class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
                width="42px"
                height="42px"
              />
              <div class="artist-box">
                <div class="">
                  <a href="#">블랙핑크</a>
                </div>
                <div class="">
                  <p>아티스트</p>
                </div>
              </div>
            </div>
            <div class="artist-area">
              <img
                src="https://musicmeta-phinf.pstatic.net/artist/000/460/460475.jpg?type=ff300_300&v=20201013145308"
                alt="Circle Image"
                class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
                width="42px"
                height="42px"
              />
              <div class="artist-box">
                <div class="">
                  <a href="#" color="black">트와이스</a>
                </div>
                <div class="">
                  <p>아티스트</p>
                </div>
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

export default {
  bodyClass: 'profile-page',
  components: {
    SearchBar,
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

.container {
  padding-bottom: 2rem;
  // margin: auto;
}

.singer-searchbar {
  height: 90px;
}

.main-singer-title {
  padding-top: 2.5rem;
  // font-family: 'Nanum Gothic', sans-serif;
  font-family: 'Rakkas';
  color: #9c27b0;
  text-shadow: 4px 4px 0px #bdbdbd;
  font-size: 4em;
}
.singer-title {
  margin-bottom: 20px;
}
.artist {
  margin-left: 15%;
  margin-right: 15%;
}
.artist-area {
  font-family: 'Nanum Gothic', sans-serif;
  position: relative;
  padding: 12px 23px 10px 0;
}
.artist-box {
  padding-left: 54px;
}
</style>
