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

/*
*  페이지번호 받아서 최신순 노래 리스트 가져오기
*/
function getlist_new(page, success, fail){
    instance
        .get(`/song/newestlist/${page}`)
        .then(success)
        .catch(fail);
}

/*
*  페이지번호 받아서 인기순 노래 리스트 가져오기
*/
function getlist_pop(page, success, fail){
    instance
        .get(`/song/default/${page}`)
        .then(success)
        .catch(fail);
}

/*
*  페이지번호 받아서 장르별 노래 리스트 가져오기(최신순)
*/
function getlist_genre(genre, page, success, fail){
    instance
        .get(`/song/genre/${page}?genre=${genre}`)
        .then(success)
        .catch(fail);
}

/*
*  페이지번호 받아서 장르별 노래 리스트 가져오기(인기순)
*/
function getListPopularGenre(genre, page, success, fail){
    instance
        .get(`/song/genrePopular/${page}?genre=${genre}`)
        .then(success)
        .catch(fail);
}

/*
*  페이지번호 받아서 장르별 노래 리스트 가져오기(인기순)
*/
function do_like(songlike, success, fail){
    instance
        .post(`/song/like` , songlike)
        .then(success)
        .catch(fail);
}

/*
*  단어 등록 하기
*/
function search_word(sw, uid, success, fail){
    instance
        .post(`/song/insert/word?uid=${uid}` , sw)
        .then(success)
        .catch(fail);
}

/*
*  나무타이틀로 나무 아이디 가져오기
*/
function getNamuId(namu_title, success, fail){
    instance
        .get(`/song/namuId/${namu_title}`)
        .then(success)
        .catch(fail);
}

export{
    get_song,
    getlist_new,
    getlist_pop,
    getlist_genre,
    getListPopularGenre,
    do_like,
    search_word,
    getNamuId,
};