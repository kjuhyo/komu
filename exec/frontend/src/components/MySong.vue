<template>
  <div>
    <ul class="cards">
      <h4 class="textalign" v-if="this.songList.length == 0">
        좋아요한 노래가 없어요!
      </h4>
      <li v-for="(song, idx) in songList" :key="idx" class="cards__item">
        <div v-if="song != null" class="song-card">
          <router-link
            :to="{
              name: 'songdetail',
              query: {
                id: song.id,
              },
            }"
          >
            <img :src="song.album_cover" class="card__image" />
            <div class="card__content">
              <div class="card__title">{{ song.song_name }}</div>
              <p class="card__text">{{ song.singer_name }}</p>
            </div>
          </router-link>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import '../assets/css/song.scss';

export default {
  name: 'song',
  props: {
    songList: { type: Object },
  },
  data() {
    return;
  },
  created() {
    console.log(this.songList);
  },
};
</script>

<style scoped>
.textalign {
  text-align: center;
}
</style>
