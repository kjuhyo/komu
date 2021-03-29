
function saveUserToCookie(uid) {
    const date = new Date();
    date.setTime(date.getTime() + 1*24*60*60*1000);
      document.cookie = `uid=${uid};expires=${date.toUTCString()};path=/`;
    
  }

function getuidCookie() {
return document.cookie.replace(
    /(?:(?:^|.*;\s*)uid\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
);
}

  
  function deleteCookie() {
      document.cookie = `uid=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  }
  
  export {
    saveUserToCookie,
    getuidCookie,
    deleteCookie,
  };