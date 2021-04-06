import { createInstance } from "./index.js";

const instance = createInstance();

function getboard(cid, uid, success, fail){
    instance
        .get(`/community/${cid}?uid=${uid}`)
        .then(success)
        .catch(fail);
}

function getlist(page, success, fail){
    instance
    .get(`/community/list/${page}`)
    .then(success)
    .catch(fail);
}
function letlike(articlelike, success,fail){
    instance
    .post(`/community/like`, articlelike)
    .then(success)
    .catch(fail);
}

function insert(FormData, success, fail){
    instance
    .post(`/community/insert`, FormData,{
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
    .then(success)
    .catch(fail);
}

function insert_nopic(article, success, fail){
    instance
    .post(`/community/insert/np`, article)
    .then(success)
    .catch(fail);
}

function update_nopic(uid, c_content, c_title, success, fail){
    instance
    .put(`/community/update`, uid, c_content, c_title)
    .then(success)
    .catch(fail);
}
function search_list(c_title, success, fail){
    instance
    .get(`/community/${c_title}`)
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

};