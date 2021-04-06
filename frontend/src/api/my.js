import { createInstance } from "./index.js";

const instance = createInstance();

/*
*  내가 쓴 게시글
*/
function myPostList(uid, success, fail){
    instance
        .get(`my/community/${uid}`)
        .then(success)
        .catch(fail);
}

/*
*  내가 좋아요한 가수
*/
function mySongList(uid, success, fail){
    instance
        .get(`my/song/${uid}`)
        .then(success)
        .catch(fail);
}

/*
*  내가 좋아요한 단어
*/
function myWordList(uid, success, fail){
    instance
        .get(`my/word/${uid}`)
        .then(success)
        .catch(fail);
}



export{
    myPostList,
    mySongList,
    myWordList,
};