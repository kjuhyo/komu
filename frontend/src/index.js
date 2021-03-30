import Vue from "vue";
import Router from "vue-router";
import Index from "./views/Index.vue";
import Landing from "./views/Landing.vue";
import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Community from "./views/Community.vue";
import CommunityDetail from "./views/CommunityDetail.vue";
import SingerDetail from "./views/SingerDetail.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/index",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/landing",
      name: "landing",
      components: { default: Landing, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/community",
      name: "community",
      components: { default: Community, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/communityDetail",
      name: "communityDetail",
      components: { default: CommunityDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/singerdetail",
      name: "singerdetail",
      components: { default: SingerDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
