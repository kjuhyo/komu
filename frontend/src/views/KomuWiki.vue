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
            코뮤위키
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
