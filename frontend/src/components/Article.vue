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
      <tr v-for="(komu,idx) in list" :key="idx">
        <td data-label="No">{{komu.namu_id}}</td>
        <td data-label="Contents">
          <router-link :to="`/komuwikidetail/${komu.namu_id}`">
          {{komu.namu_title}}
          </router-link>
          </td>
        <td data-label="Writer">{{komu.nickname}}</td>
        <td data-label="Date">{{komu.namu_date}}</td>
      </tr>
      </tbody>
  </table>
</template>

<script>
import '../assets/css/article.css';
export default {
  namu:"article",
  props:{
    list:{type:Object},
  },
  data:function(){
    return{
    }

  },
  created() {
    //console.log('리스트');
    //console.log(list.uid);
    /*getSingerName(

    )*/
  },
};
</script>
