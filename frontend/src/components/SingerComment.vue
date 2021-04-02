<template>
  <div class="comment-container">
    <div class="comment-option">
      <h3>댓글</h3>
    </div>
    <ul class="comment-option">
      <li class="comment-item">
        <div class="comment-area">
          <img
            :src="img"
            alt="Circle Image"
            class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
            width="42px" height="42px"
          />
          <div class="comment-box">
            <div class="comment-nick-box">
              <a href="#">닉네임</a>
            </div>
            <div class="comment-text-box">
              <p>댓글입니다.</p>
            </div>
            <div class="comment-info-box">
              <span>2021.03.28</span>
              <div>
                <template>
                  <!-- class="md-primary md-round classic-modal" -->
                  <md-button class="md-danger comment-alert-modal" @click="classicModal = true">신고</md-button>
                  <modal v-if="classicModal" @close="classicModalHide">
                    <template slot="header">
                      <h4 class="modal-title">신고하기</h4>
                      <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                        <md-icon>clear</md-icon>
                      </md-button>
                    </template>

                    <template slot="body">
                      <p>정말 이 댓글을 신고하겠습니까?</p>
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
        </div>
      </li>
      <hr>
      <li class="comment-item">
        <div class="comment-area">
          <img
            :src="img"
            alt="Circle Image"
            class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
            width="42px" height="42px"
          />
          <div class="comment-box">
            <div class="comment-nick-box">
              <a href="#">닉네임</a>
            </div>
            <div class="comment-text-box">
              <p>댓글입니다.</p>
            </div>
            <div class="comment-info-box">
              <span>2021.03.28</span>
              <div>
                <template>
                  <!-- class="md-primary md-round classic-modal" -->
                  <md-button class="md-danger comment-alert-modal" @click="classicModal = true">신고</md-button>
                  <modal v-if="classicModal" @close="classicModalHide">
                    <template slot="header">
                      <h4 class="modal-title">신고하기</h4>
                      <md-button class="md-simple md-just-icon md-round modal-default-button" @click="classicModalHide">
                        <md-icon>clear</md-icon>
                      </md-button>
                    </template>

                    <template slot="body">
                      <p>정말 이 댓글을 신고하겠습니까?</p>
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
        </div>
      </li>
      <hr>
    </ul>
    <div class="comment-writer">
      <div class="comment-inbox">
        <span class="comment-inbox-name">닉네임</span>
        <textarea placeholder="댓글을 남겨보세요." class="comment-inbox-text" rows="1"></textarea>
      </div>
      <div class="comment-attach">
        <div class="register-box">
          <a href="#" role="button">등록</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {Modal} from '@/components'

export default {
  props: {
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
  data() {
    return {
      classicModal: false
    };
  },
  components: {
    Modal,
  },
  methods: {
    classicModalHide() {
      this.classicModal = false
    }
  }
}
</script>

<style lang="scss">
  ul {
    list-style: none;
  }
  .comment_option {
    position: relative;
    padding-top: 16px;
    margin-bottom: 11px;
  }
  .comment-area {
    position: relative;
    padding: 12px 23px 10px 0;
  }
  .comment-thumb {
    position: absolute;
    top: 12px;
    left: 0;
  }
  .comment-box {
    padding-left: 46px;
  }
  .comment-writer {
    margin: 12px 0 29px;
    padding: 16px 10px 10px 18px;
    border: 2px solid gray;
    box-sizing: border-box;
    border-radius: 6px;
  }
  .comment-inbox {
    position: relative;
    margin-bottom: 10px;
  }
  .comment-inbox-name {
    display: block;
    margin-bottom: 10px;
    font-weight: 700;
    font-size: 13px;
  }
  .comment-inbox-text {
    overflow-x: hidden;
    overflow-y: auto;
    display: block;
    width: 100%;
    min-height: 17px;
    padding-right: 1px;
    border: 0;
    font-size: 13px;
    -webkit-appearance: none;
    resize: none;
    box-sizing: border-box;
    background: transparent;
    color: var(--skinTextColor);
    outline: 0;
  }
  .comment-attach {
    position: relative;
    top: -15px;
  }
  .register-box {
    float: right;
    color: gray;
  }
  .comment-info-box {
    display: flex;
    justify-content: space-between;
    // line-height: 40px;
  }
  .comment-alert-modal {
    // width: 5px;
    height: 30px;
    // padding: 0;
  }
</style>