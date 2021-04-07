<template>
  <div class="cards-container">
    <h4 v-if="this.wordList.length == 0">좋아요한 단어가 없어요!</h4>
    <div v-for="(word, idx) in wordList" :key="idx" class="card card-css">
      <div v-if="word != null" class="card-question">
         <router-link
          :to="`/komuwikidetail/${word.namu_id}`">
          {{word.namu_title}}
         </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/myword.scss';
export default {
  name: "myword",
  props:{
    wordList:{type:Object},
  },
  data() {
    return
  },
  created() {
    console.log(this.songList)
  }
};
</script>
