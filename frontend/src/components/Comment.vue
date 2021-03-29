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
            </div>
          </div>
        </div>
      </li>
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
            </div>
          </div>
        </div>
      </li>
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
export default {
  props: {
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
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
</style>