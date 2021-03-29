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
          <div class="text-center">
            <h1>Community</h1>
          </div>
          <div class="community-detail-header">
            <div class="community-detail-title">
              <h2>Title : 디테일 페이지 첫번재 글입니다</h2>
            </div>
            <div class="writer-header">
              <div class="writer-info">
                <div class="thumb">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image"
                    width="42px" height="42px"
                  />
                </div>
                <div class="profile_area">
                  <div class="profile_info">
                    <a href="#">닉네임</a>
                  </div>
                  <div class="article_info">
                    <span>작성시간 : 2021.03.28</span>
                  </div>
                </div>
              </div>
              <div>
                <a href="#">수정</a>
                <a href="#" class="delete">삭제</a>
              </div>
            </div>
            <hr>
          </div>
          <div class="community-detail-container">
            <div class="community-detail-content">
              <p>커뮤니티 디테일 페이지의 첫번째 글입니다.</p>
              <p>하하하하하하하하하하하</p>
              <p>얼마나 글을 많이 써야할까요?</p>
              <p>하하하하하하하하하하하</p>
            </div>
            <div class="reply-box">
              <div class="box-left">
                <div class="like-article">
                  <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M16.5 3c-1.74 0-3.41.81-4.5 2.09C10.91 3.81 9.24 3 7.5 3 4.42 3 2 5.42 2 8.5c0 3.78 3.4 6.86 8.55 11.54L12 21.35l1.45-1.32C18.6 15.36 22 12.28 22 8.5 22 5.42 19.58 3 16.5 3zm-4.4 15.55l-.1.1-.1-.1C7.14 14.24 4 11.39 4 8.5 4 6.5 5.5 5 7.5 5c1.54 0 3.04.99 3.57 2.36h1.87C13.46 5.99 14.96 5 16.5 5c2 0 3.5 1.5 3.5 3.5 0 2.89-3.14 5.74-7.9 10.05z"/></svg>
                  <!-- <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/></svg> -->
                  <span class="like-count">좋아요</span>
                  <span class="like-count">0</span>
                </div>
                <div class="button-comment">
                  <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 0 24 24" width="24"><path d="M0 0h24v24H0z" fill="none"/><path d="M21.99 4c0-1.1-.89-2-1.99-2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h14l4 4-.01-18zM18 14H6v-2h12v2zm0-3H6V9h12v2zm0-3H6V6h12v2z"/></svg>
                  <span class="comment-count">댓글</span>
                  <span class="comment-count">100</span>
                </div>
              </div>
              <div class="box-right">
                <div class="report-article">
                  <template>
                    <!-- class="md-primary md-round classic-modal" -->
                    <md-button class="md-danger" @click="classicModal = true">신고</md-button>
                    <modal v-if="classicModal" @close="classicModalHide">
                      <template slot="header">
                        <h4 class="modal-title">신고하기</h4>
                        <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                          <md-icon>clear</md-icon>
                        </md-button>
                      </template>

                      <template slot="body">
                        <p>정말 이 글을 신고하겠습니까?</p>
                      </template>

                      <template slot="footer">
                        <md-button class="md-danger md-simple">신고하기</md-button>
                        <md-button class="md-simple" @click="classicModalHide">Close</md-button>
                      </template>
                    </modal>
                  </template>
                </div>
              </div>
            </div>
            <hr>
            <Comment />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Modal} from '@/components'
import Comment from '../components/Comment.vue';

export default {
  components: {
    Modal,
    Comment
  },
  bodyClass: 'profile-page',
  data() {
    return {
      classicModal: false
    };
  },
  props: {
    header: {
      type: String,
      default: require('@/assets/img/city-profile.jpg'),
    },
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  methods: {
    classicModalHide() {
      this.classicModal = false
    }
  }
};
</script>

<style lang="scss" scoped>
  .section {
    padding: 0;
  }
  .community-detail-header {
    margin-bottom: 20px;
    padding-bottom: 20px;
  }
  .community-detail-title {
    margin-bottom: 12px;
  }
  .writer-header {
    display: flex;
    justify-content: space-between;
  }
  .writer-info {
    display: flex;
  }
  .thumb {
    margin-right: 10px;
  }
  .delete {
    margin-left: 10px;
  }
  .community-detail-content {
    margin-bottom: 40px;
  }
  .reply-box {
    display: flex;
    justify-content: space-between;
  }
  .box-left {
    display: flex;
  }
  .like-article {
    margin-right: 16px;
  }
  .like-count {
    margin-left: 10px;
  }
  .comment-count {
    margin-left: 10px;
  }
</style>
