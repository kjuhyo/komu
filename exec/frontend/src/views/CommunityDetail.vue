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
                {{ community.c_title }}
              </span>
            </div>

            <!-- 작성자 정보 -->
            <div class="writer-header">
              <div class="writer-info">
                <div class="thumb">
                  <img
                    :src="writer.profile"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image"
                    width="42px"
                    height="42px"
                  />
                </div>
                <div class="profile_area">
                  <div class="profile_info">
                    {{ writer.nickname }}
                  </div>
                  <div class="article_info">
                    <span>{{ community.c_date }}</span>
                  </div>
                </div>
              </div>

              <!-- 삭제&수정 버튼 -->
              <div class="com-detail-btn">
                <span class="comdetail-btn-text-cnt">조회수</span>
                <span class="comdetail-btn-text">{{ community.c_view }}</span>

                <div v-if="this.community.uid == this.loginid && isLogin">
                  <router-link
                    :to="`/communityupdate/${community.cid}`"
                    v-if="isLogin"
                  >
                    <span class="comdetail-btn-text">수정</span>
                  </router-link>
                  <span class="comdetail-btn-text" @click="Delete">삭제</span>
                </div>
              </div>
            </div>
            <hr />
          </div>
          <!-- 커뮤니티헤더 end -->

          <div class="community-detail-container">
            <!-- 글 내용 -->
            <div class="comm-detail-main">
              <div class="community-detail-content">
                {{ community.c_content }}
              </div>

              <!-- <div
                class="community-detail-content"
                v-if="community.c_img != null"
              >
                <img
                  :src="community.c_img"
                  alt="Circle Image"
                  class="img-raised rounded-circle img-fluid userprofile-image"
                  width="42px"
                  height="42px"
                />
              </div> -->

              <div class="m_komuwiki_body_img" v-if="isMobile">
                <img class="k_body_img" :src="community.c_img" />

                <!-- <img class="k_body_img" src="@/assets/img/jihyeon.jpg" /> -->
              </div>

              <div class="komuwiki_body_img" v-else>
                <img class="k_body_img" :src="community.c_img" />
                <!-- <img class="k_body_img" src="@/assets/img/jihyeon.jpg" /> -->
              </div>

              <!-- 좋아요, 댓글, 신고 -->
              <div class="reply-box">
                <div class="box-left">
                  <div class="like-article">
                    <div class="com-detail-like-count" v-if="isLogin">
                      <b-icon
                        icon="heart"
                        v-if="LIKE == 0"
                        @click="Like"
                      ></b-icon>
                      <b-icon
                        icon="heart-fill"
                        v-if="LIKE == 1"
                        @click="Like"
                      ></b-icon>
                    </div>
                    <span class="com-detail-like-count">좋아요</span>
                    <span class="com-detail-like-count">{{
                      community.c_like_cnt
                    }}</span>
                  </div>
                  <div class="button-comment">
                    <b-icon class="comm-detail-icon" icon="chat-left"></b-icon>
                    <span class="com-detail-comment-count">댓글</span>
                    <span class="com-detail-comment-count">{{
                      cnt_comment
                    }}</span>
                  </div>
                </div>
                <div class="box-right">
                  <div v-if="isLogin" class="report-article">
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
                          <md-button
                            class="md-danger md-simple"
                            @click="modaldel"
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
            <ul class="comdetail-comment-option" style="padding-bottom: 20px">
              <li class="comdetail-comment-item">
                <div
                  class="comdetail-comment-area"
                  v-for="(comment, idx) in comments"
                  :key="idx"
                >
                  <img
                    :src="comment.profile"
                    alt="Circle Image"
                    class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
                    width="42px"
                    height="42px"
                  />
                  <div class="comment-box">
                    <div class="comdetail-comment-nick-box">
                      {{ comment.nickname }}
                    </div>
                    <div class="comdetail-comment-text-box">
                      {{ comment.cc_content }}
                    </div>
                    <div class="comdetail-comment-info-box">
                      {{ comment.cc_date }}
                    </div>
                  </div>
                </div>
              </li>
            </ul>

            <!-- 댓글 작성 -->
            <div v-if="isLogin" class="comdetail-comment-writer">
              <div class="comdetail-comment-inbox">
                <span class="comdetail-comment-inbox-name">{{
                  loginInfo.nickname
                }}</span>
                <textarea
                  placeholder="댓글을 작성해주세요."
                  class="comdetail-comment-inbox-text"
                  rows="1"
                  @click="findWriter"
                  v-model="cccontent"
                ></textarea>

                <!-- 댓글등록버튼 -->
                <div class="comdetail-comment-attach">
                  <div class="comdetail-register-box">
                    <button @click="insert_comment">등록</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- 댓글 작성 end -->
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
import { getboard, letlike, letdelete, insert_comm } from '@/api/community.js';
import { mapState } from 'vuex';
import { getuidCookie } from '@/util/cookie.js';
import { profileByUid } from '@/api/user.js';
import swal from 'sweetalert';

export default {
  components: {
    Modal,
    // Comment,
  },
  bodyClass: 'profile-page',
  created() {
    this.initUser();
    this.name = this.$route.params.name;
    getboard(
      this.name,
      this.loginid,
      (response) => {
        this.LIKE = response.data.LIKE;
        this.community = response.data.Community;
        this.comments = response.data.commentList;
        this.cnt_comment = response.data.cnt_comment;
        this.check = response.data.check;
        this.message = response.data.message;
        // console.log(this.message);
        this.nowuid = this.community.uid;
        this.getInfo();
      },
      (error) => {
        swal(`${error}`);
      }
    );
  },

  data() {
    return {
      classicModal: false,
      name: '',
      nowuid: '',
      loginid: 'namu',
      community: {
        cid: 0,
        uid: '',
        c_title: '',
        c_content: '',
        c_date: '',
        is_delete: '',
        c_view: 0,
        c_like_cnt: 0,
        c_img: '',
      },
      comments: {
        ccid: 0,
        cid: 0,
        uid: '',
        cc_content: '',
        cc_date: '',
        is_delete: 0,
        nickname: '',
        profile: '',
      },
      cnt_comment: 0,
      check: false,
      LIKE: 0,
      writer: {
        uid: '',
        nickname: '',
        profile: '',
      },
      postlike: {
        uid: '',
        cid: 0,
      },
      cccontent: '',
      sendComment: {
        cid: 0,
        uid: 0,
        cc_content: '',
      },
      loginInfo: {},
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
    ...mapState(['isLogin', 'loggedInUserData']),
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
    modaldel() {
      // console.log('함수들왔쪄');
      this.classicModal = false;
      swal('신고 완료', '신고 완료되었습니다', 'success');
    },
    onResize: function () {
      this.isMobile = window.innerWidth <= 480;
    },
    classicModalHide() {
      this.classicModal = false;
    },
    initUser() {
      this.loginid = getuidCookie();
      //this.loginid = 'namu';
    },
    getInfo() {
      profileByUid(
        this.nowuid,
        (response) => {
          this.writer = response.data.info;
          // console.log(this.message);
          // console.log(this.writer);
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    Like: function () {
      this.postlike.uid = this.loginid;
      this.postlike.cid = this.community.cid;
      letlike(
        this.postlike,
        (response) => {
          this.LIKE = response.data.LIKE;
          // console.log(response.data.message);
          window.location.reload();
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    Delete: function () {
      // console.log('삭제 눌렸어/');
      letdelete(
        this.community.cid,
        (response) => {
          // console.log(response.data.message);
          this.$router.push('/community');
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    findWriter: function () {
      profileByUid(
        this.loginid,
        (response) => {
          this.loginInfo = response.data.info;
          // console.log(this.message);
          // console.log(this.loginInfo);
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    insert_comment: function () {
      this.sendComment.cid = this.name;
      this.sendComment.uid = this.loginid;
      this.sendComment.cc_content = this.cccontent;
      // console.log(this.sendComment);
      insert_comm(
        this.sendComment,
        (response) => {
          // console.log(response.data);
          window.location.reload();
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}
</style>
