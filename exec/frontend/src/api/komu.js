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

function insert(FormData, success,fail){
    instance
    .post(`/namu/insert`, FormData,{
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
    .then(success)
    .catch(fail);
}

function insert_nopic(komu, success,fail){
    instance
    .post(`/namu/insert/np`, komu)
    .then(success)
    .catch(fail);
}

function update(FormData, success,fail){
    instance
    .put(`/namu/update`, FormData,{
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
    .then(success)
    .catch(fail);
}

function update_nopic(namu, success, fail){
    instance
    .put(`/namu/update/np`, namu)
    .then(success)
    .catch(fail);
}
function search_list(page, word, success,fail){
    instance
    .get(`namu/search/${page}?word=${word}`)
    .then(success)
    .catch(fail);
}

export{
    getboard,
    getlist,
    letlike,
    insert,
    insert_nopic,
    update_nopic,
    search_list,
    update,

};