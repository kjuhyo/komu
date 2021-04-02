<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <h3 class="md-title">
          <router-link to="/">
            <img class="md-title-img" src="@/assets/img/logo.png" />
          </router-link>
        </h3>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>
              <!-- <li class="md-list-item" v-if="!showDownload"> -->
              <li class="md-list-item">
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">music_video</i>
                        <p>추천서비스</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li>
                          <router-link
                            class="navbarrouting"
                            to="/genrerecommend"
                          >
                            <i class="material-icons">layers</i>
                            <p>장르별 추천</p>
                          </router-link>
                        </li>
                        <li>
                          <a
                            href="https://demos.creative-tim.com/vue-material-kit/documentation/"
                          >
                            <i class="material-icons">content_paste</i>
                            <p>가사별 추천</p>
                          </a>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              <!-- <md-list-item
                href="https://demos.creative-tim.com/vue-material-kit/documentation/"
                target="_blank"
                v-if="showDownload"
              >
                <i class="material-icons">content_paste</i>
                <p>Documentation</p>
              </md-list-item>

              <md-list-item
                href="javascript:void(0)"
                @click="scrollToElement()"
                v-if="showDownload"
              >
                <i class="material-icons">cloud_download</i>
                <p>Download</p>
              </md-list-item> -->

              <md-list-item
                href="https://demos.creative-tim.com/vue-material-kit/documentation/"
                target="_blank"
              >
                <i class="material-icons">queue_music</i>
                <p>노래리스트</p>
              </md-list-item>

              <md-list-item>
                <router-link class="navbarrouting" to="/komuwiki">
                  <i class="material-icons">view_carousel</i>
                  <p class="navfont">코뮤위키</p>
                </router-link>
              </md-list-item>

              <li class="md-list-item">
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">keyboard</i>
                        <p>커뮤니티</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li>
                          <router-link class="navbarrouting" to="/community">
                            <i class="material-icons">chat_bubble_outline</i>
                            <p>전체 커뮤니티</p>
                          </router-link>
                        </li>
                        <li>
                          <router-link class="navbarrouting" to="/singer">
                            <i class="material-icons">group</i>
                            <p>가수별 커뮤니티</p>
                          </router-link>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              <md-list-item v-if="!isLogin" @click="showModal = true">
                <i class="material-icons navicons">login</i>
                <p class="navfont">로그인</p>
                <div v-if="showModal">
                  <login-modal></login-modal>
                </div>
              </md-list-item>

              <li class="md-list-item" v-else>
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple dropdown-toggle"
                        data-toggle="dropdown"
                      >
                        <i class="material-icons">person</i>
                        <p>마이페이지</p>
                      </md-button>
                      <ul class="dropdown-menu dropdown-with-icons">
                        <li>
                          <router-link to="/profile">
                            <i class="material-icons">person</i>
                            <p>마이페이지</p>
                          </router-link>
                        </li>
                        <li>
                          <a href="#/profile" @click.prevent="logout">
                            <i class="material-icons">logout</i>
                            <p>로그아웃</p>
                          </a>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              <!-- <md-list-item
                href="https://www.instagram.com/CreativeTimOfficial"
                target="_blank"
              >
                <i class="fab fa-instagram"></i>
                <p class="hidden-lg">Instagram</p>
                <md-tooltip md-direction="bottom"
                  >Follow us on Instagram</md-tooltip
                >
              </md-list-item> -->
            </md-list>
          </div>
        </div>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from '@/layout/MobileMenu';
import { mapState } from 'vuex';
//import Modal from '../components/Modal';
import LoginModal from './LoginModal';

export default {
  components: {
    MobileMenu,
    //  Modal: Modal,
    LoginModal,
  },

  props: {
    type: {
      type: String,
      default: 'white',
      validator(value) {
        return [
          'white',
          'default',
          'primary',
          'danger',
          'success',
          'warning',
          'info',
        ].includes(value);
      },
    },
    colorOnScroll: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      extraNavClasses: '',
      toggledClass: false,
      showModal: false,
    };
  },
  // computed: {
  //   showDownload() {
  //     const excludedRoutes = ['login', 'landing', 'profile'];
  //     return excludedRoutes.every((r) => r !== this.$route.name);
  //   },
  // },
  computed: {
    ...mapState(['isLogin', 'loggedInUserData', 'userInfo']),
    //showLogout() {
    //  const excludedRoutes = ['index'];
    // return excludedRoutes.every((r) => r !== this.$route.name);
    // },
  },
  methods: {
    bodyClick() {
      let bodyClick = document.getElementById('bodyClick');

      if (bodyClick === null) {
        let body = document.querySelector('body');
        let elem = document.createElement('div');
        elem.setAttribute('id', 'bodyClick');
        body.appendChild(elem);

        let bodyClick = document.getElementById('bodyClick');
        bodyClick.addEventListener('click', this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById('toolbar');
      this.currentScrollValue = scrollValue;
      if (this.colorOnScroll > 0 && scrollValue > this.colorOnScroll) {
        this.extraNavClasses = `md-${this.type}`;
        navbarColor.classList.remove('md-transparent');
      } else {
        if (this.extraNavClasses) {
          this.extraNavClasses = '';
          navbarColor.classList.add('md-transparent');
        }
      }
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById('downloadSection');
      if (element_id) {
        element_id.scrollIntoView({ block: 'end', behavior: 'smooth' });
      }
    },

    logout() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {
          if (this.$route.path !== '/') this.$router.replace('/');
          console.log('네브바로그아웃');
          this.showModal = false;
        })
        .catch(() => {
          console.log('로그아웃 문제!');
        });
    },
  },
  mounted() {
    document.addEventListener('scroll', this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener('scroll', this.scrollListener);
  },
};
</script>
