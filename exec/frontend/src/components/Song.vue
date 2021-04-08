<template>
  <div>
    <ul class="cards">
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
              <div class="r-card__title">{{ song.song_name }}</div>
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
