<template>
  <table>
    <thead class="thead-css">
      <tr>
        <th width="5%" scope="col">No</th>
        <th width="50%" scope="col">Title</th>
        <th width="25%" scope="col">Writer</th>
        <th width="20%" scope="col">Date</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(article,idx) in list" :key="idx">
        <td data-label="No">{{article.cid}}</td>
        <td data-label="Contents">
          <router-link :to="`/communitydetail/${article.cid}`">
          {{article.c_title}}
          </router-link>
          </td>
        <td data-label="Writer">{{article.nickname}}</td>
        <td data-label="Date">{{article.c_date}}</td>
      </tr>
      </tbody>
  </table>
</template>

<script>
import '../assets/css/article.css';
export default {
  namu:"communityarticle",
  props:{
    list:{type:Object},
  },
  data:function(){
    return{
    }

  },

};
</script>
