<template>
  <table>
    <thead class="thead-css">
      <tr>
        <th width="5%" scope="col">No</th>
        <th width="60%" scope="col">Contents</th>
        <th width="15%" scope="col">Writer</th>
        <th width="20%" scope="col">Date</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td data-label="No">1</td>
        <td data-label="Contents">이건 나의 첫번째 게시글이야</td>
        <td data-label="Writer">Ariana Grande</td>
        <td data-label="Date">2020/03/25</td>
      </tr>
      <tr>
        <td scope="row" data-label="No">2</td>
        <td data-label="Contents">이건 내 두번째 게시글</td>
        <td data-label="Writer">Ariana Grande</td>
        <td data-label="Date">2020/03/25</td>
      </tr>
      <tr>
        <td scope="row" data-label="No">3</td>
        <td data-label="Contents">이제 난 더이상 게시글을 작성하지 않을랭</td>
        <td data-label="Writer">Ariana Grande</td>
        <td data-label="Date">2020/03/25</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import '../assets/css/article.css';
export default {};
</script>
