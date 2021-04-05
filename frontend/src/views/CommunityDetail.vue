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

          <!-- 커뮤니티헤더 -->
          <div class="community-detail-header">
            <!-- 제목 -->
            <div class="community-detail-title">
              <span class="community-detail-title-text">
                조금만 더 힘내요 여러분~~
              </span>
            </div>

            <!-- 작성자 정보 -->
            <div class="writer-header">
              <div class="writer-info">
                <div class="thumb">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image"
                    width="42px"
                    height="42px"
                  />
                </div>
                <div class="profile_area">
                  <div class="profile_info">
                    닉네임
                  </div>
                  <div class="article_info">
                    <span>2021.03.28</span>
                  </div>
                </div>
              </div>

              <!-- 삭제&수정 버튼 -->
              <div class="com-detail-btn">
                <span class="comdetail-btn-text">수정</span>
                <span class="comdetail-btn-text">삭제</span>
              </div>
            </div>
            <hr />
          </div>
          <!-- 커뮤니티헤더 end -->

          <div class="community-detail-container">
            <!-- 글 내용 -->
            <div class="comm-detail-main">
              <div class="community-detail-content">
                커뮤니티 디테일 페이지의 첫번째 글입니다. <br />
                하하하하하하하하하하하 얼마나 글을 많이 써야할까요?<br />
                하하하하하하하하하하하<br />
              </div>

              <!-- 좋아요, 댓글, 신고 -->
              <div class="reply-box">
                <div class="box-left">
                  <div class="like-article">
                    <b-icon class="comm-detail-icon" icon="heart"></b-icon>
                    <span class="com-detail-like-count">좋아요</span>
                    <span class="com-detail-like-count">0</span>
                  </div>
                  <div class="button-comment">
                    <b-icon class="comm-detail-icon" icon="chat-left"></b-icon>
                    <span class="com-detail-comment-count">댓글</span>
                    <span class="com-detail-comment-count">100</span>
                  </div>
                </div>
                <div class="box-right">
                  <div class="report-article">
                    <template>
                      <!-- class="md-primary md-round classic-modal" -->
                      <md-button class="md-danger" @click="classicModal = true"
                        >신고</md-button
                      >
                      <modal v-if="classicModal" @close="classicModalHide">
                        <template slot="header">
                          <h4 class="modal-title">신고하기</h4>
                          <md-button
                            class="md-simple md-just-icon md-round modal-default-button"
                            @click="classicModalHide"
                          >
                            <md-icon>clear</md-icon>
                          </md-button>
                        </template>

                        <template slot="body">
                          <p>정말 이 글을 신고하겠습니까?</p>
                        </template>

                        <template slot="footer">
                          <md-button class="md-danger md-simple"
                            >신고하기</md-button
                          >
                          <md-button class="md-simple" @click="classicModalHide"
                            >Close</md-button
                          >
                        </template>
                      </modal>
                    </template>
                  </div>
                </div>
              </div>
            </div>
            <hr />

            <!-- 댓글 -->
            <!-- 댓글 리스트 -->
            <ul class="comdetail-comment-option">
              <li class="comdetail-comment-item">
                <div class="comdetail-comment-area">
                  <img
                    :src="img"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
                    width="42px"
                    height="42px"
                  />
                  <div class="comment-box">
                    <div class="comdetail-comment-nick-box">
                      닉네임
                    </div>
                    <div class="comdetail-comment-text-box">
                      댓글입니다.
                    </div>
                    <div class="comdetail-comment-info-box">
                      2021.03.28
                    </div>
                  </div>
                </div>
              </li>
            </ul>

            <!-- 댓글 작성 -->
            <div class="comment-writer">
              <div class="comment-inbox">
                <span class="comment-inbox-name">닉네임</span>
                <textarea
                  placeholder="댓글을 남겨보세요."
                  class="comment-inbox-text"
                  rows="1"
                ></textarea>
              </div>
              <div class="comment-attach">
                <div class="register-box">
                  <a href="#" role="button">등록</a>
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
import { Modal } from '@/components';
// import Comment from '../components/Comment.vue';
import '../assets/css/commudetail.scss';

export default {
  components: {
    Modal,
    // Comment,
  },
  bodyClass: 'profile-page',
  data() {
    return {
      classicModal: false,
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
      this.classicModal = false;
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
</style>
