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
          <div class="komu_pagenotfound">
            <h1>PAGE NOT FOUND</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/profile.css';

export default {
  components: {},
  bodyClass: 'profile-page',
  data() {
    return {};
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
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

.komu_pagenotfound {
  text-align: center;
  font-weight: bold;
  font-family: 'Nanum Gothic', sans-serif !important;
}
</style>
