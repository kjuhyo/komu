<template>
  <div class="comment-container">
    <div class="comment-option">
      <h3>댓글</h3>
    </div>
    <!-- 댓글 리스트 -->
    <ul class="comment-option">
      <li class="comment-item">
        <div class="comment-area">
          <img
            :src="img"
            alt="Circle Image"
            class="img-raised rounded-circle img-fluid userprofile-image comment-thumb"
            width="42px"
            height="42px"
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
</template>

<script>
export default {
  props: {
    img: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
  },
};
</script>
