import { createInstance } from "./index.js";

const instance = createInstance();

function genreRecommend(uid, success, fail){
    instance
        .get(`/recommend/genre?uid=${uid}`)
        .then(success)
        .catch(fail);
}

function lyricRecommend(uid, success, fail){
    instance
        .get(`/recommend/lyric?uid=${uid}`)
        .then(success)
        .catch(fail);
}

export{
    genreRecommend,
    lyricRecommend,
};