import { createInstance } from "./index.js";

const instance = createInstance();

/*
*   유저 정보 읽어오기
*/
function profileByUid(uid, success, fail){
    instance
        .get(`/user/profile/${uid}`)
        .then(success)
        .catch(fail);
}

/*
*   유저 정보 읽어오기
*/
function getSingerName(uid, success, fail){
    instance
        .get(`/user/nickname/${uid}`)
        .then(success)
        .catch(fail);
}

export{
    profileByUid,
    getSingerName
};