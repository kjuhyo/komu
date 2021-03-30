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
          <div class="komuwiki_container">
            <div class="komuwiki_header">
              <div class="komuwiki_title">
                전지현
              </div>
              <div class="komuwiki_option_area">
                <b-icon
                  class="wiki_option_icon"
                  icon="heart"
                  font-scale="2"
                ></b-icon>
                <b-icon
                  class="wiki_option_icon"
                  icon="pencil"
                  font-scale="2"
                ></b-icon>
              </div>
            </div>

            <hr />
            <!-- 내용 -->
            <div class="komuwiki_body">
              <!-- 이미지 -->
              <div class="m_komuwiki_body_img" v-if="isMobile">
                <img class="k_body_img" src="@/assets/img/jihyeon.jpg" />
              </div>

              <div class="komuwiki_body_img" v-else>
                <img class="k_body_img" src="@/assets/img/jihyeon.jpg" />
              </div>

              <!-- 내용 -->

              <div class="m_komuwiki_body_content" v-if="isMobile">
                전지현은 한국의 배우이다. 너무예뻐
              </div>
              <div class="komuwiki_body_content" v-else>
                전지현은 한국의 배우이다. 너무예뻐
              </div>
            </div>
            <hr />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/komuwikidetail.scss';
export default {
  components: {},
  data() {
    return {
      isMobile: false,
    };
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
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
};
</script>
