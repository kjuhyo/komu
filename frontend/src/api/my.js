import { createInstance } from "./index.js";

const instance = createInstance();

/*
*  내가 쓴 게시글
*/
function myPostList(uid, success, fail){
    instance
        .get(`my/community/uid=${uid}`)
        .then(success)
        .catch(fail);
}

/*
*  내가 좋아요한 가수
*/
function mySingerList(page, success, fail){
    instance
        .get(`/song/newestlist/${page}`)
        .then(success)
        .catch(fail);
}

/*
*  내가 좋아요한 단어
*/
function myWordList(page, success, fail){
    instance
        .get(`/song/default/${page}`)
        .then(success)
        .catch(fail);
}



export{
    myPostList,
    mySingerList,
    myWordList,
};