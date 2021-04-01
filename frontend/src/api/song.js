import { createInstance } from "./index.js";

const instance = createInstance();

/*
*  노래 id로 노래 검색
*/
function get_song(id, uid, success, fail){
    instance
        .get(`/song/info/?id=${id}&uid=${uid}`)
        .then(success)
        .catch(fail);
}

export{
    get_song,
};