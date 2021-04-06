<template>
  <div class="cards-container">
    <div v-for="(word, idx) in wordList" :key="idx" class="card card-css">
      <div v-if="word != null" class="card-question">
        {{word.namu_title}}
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
