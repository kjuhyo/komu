<template>
  <div>
    <div class="mypost-article" v-if="this.songList.length == 0">
      좋아요한 노래가 없어요!
    </div>
    <ul v-else class="cards">
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
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');

.mypost-article {
  font-family: 'Nanum Gothic', sans-serif;
  text-align: center;
  /* margin: auto; */
  padding: 20px;
  font-size: 18px;
  margin: 10px;
}
</style>
