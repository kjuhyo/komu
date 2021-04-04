import { createInstance } from "./index.js";

const instance = createInstance();

function getboard(namu_title, uid, success, fail){
    instance
        .get(`/namu/word/${namu_title}?loginid=${uid}`)
        .then(success)
        .catch(fail);
}

function getlist(page, success, fail){
    instance
    .get(`/namu/list/${page}`)
    .then(success)
    .catch(fail);
}
function letlike(wordlike, success,fail){
    instance
    .post(`/namu/like`,wordlike)
    .then(success)
    .catch(fail);
}


export{
    getboard,
    getlist,
    letlike,
};