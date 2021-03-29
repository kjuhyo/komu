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

export{
    profileByUid,
};