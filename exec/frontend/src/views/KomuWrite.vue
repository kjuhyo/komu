<template>
  <div class="wrapper">
    <parallax
      class="section page-header header-filter"
      :style="headerStyle"
    ></parallax>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="md-layout">
            <div class="md-layout-item md-size-50 mx-auto"></div>
          </div>
          <div class="writecom_container">
            <!-- 글 제목 -->
            <div class="write_title">
              <span class="title_css">제목 </span>
              <input class="content_title" v-model="title" />
            </div>
            <br />
            <!-- 텍스트에디터 -->
            <text-editor
              @input="(data) => GetEditorContent(data)"
            ></text-editor>

            <!-- 이미지 -->
            <b-form-group class="b-form-container">
              <b-form-file
                id="photo"
                v-model="form.file"
                placeholder="이미지를 업로드해주세요"
                required
                accept="image/*"
                @change="previewImage"
                class="community-image-upload"
              ></b-form-file>

              <div class="community-photo-img" v-if="previewImageData != null">
                <div class="centered">
                  <b-img
                    class="community-upload-image"
                    :src="previewImageData"
                  ></b-img>
                </div>
              </div>

              <!-- 제출 -->
              <div class="align-center">
                <b-button
                  v-if="previewImageData != null"
                  type="submit"
                  class="community-submit"
                  aria-disabled="true"
                  @click="UploadCertification"
                  >업로드</b-button
                >
                <b-button
                  v-if="previewImageData == null"
                  type="submit"
                  class="community-submit"
                  aria-disabled="true"
                  @click="Upload"
                  >업로드</b-button
                >
              </div>
            </b-form-group>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/css/writearticle.scss';
import TextEditor from '../components/TextEditor.vue';
import { insert, insert_nopic } from '@/api/komu.js';
import { profileByUid, getSingerName } from '@/api/user.js';
import { getuidCookie } from '@/util/cookie.js';
import swal from 'sweetalert';

export default {
  components: { TextEditor },
  data() {
    return {
      form: {
        petName: '',
        file: null,
        //   desc: ''
        content: '',
      },
      namu: {
        uid: '',
        namu_title: '',
        namu_content: '',
      },
      nick: '',
      loginid: '',
      show: true,
      previewImageData: null,
      isMobile: false,
      content: '',
      title: '',
    };
  },

  bodyClass: 'profile-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
    },
  },
  created() {
    this.initUser();
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    onResize: function () {
      this.isMobile = window.innerWidth <= 480;
    },

    GetEditorContent: function (data) {
      this.content = data;
    },

    onSubmit(evt) {
      evt.preventDefault();
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset
      this.form.petName = '';
      this.form.file = '';
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    previewImage() {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImageData = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImageData = null;
      }
    },
    setDto: function () {
      this.namu.uid = this.loginid;
      this.namu.namu_title = this.title;
      this.namu.namu_content = this.content;
    },
    UploadCertification: function () {
      this.setDto();
      const formData = new FormData();
      formData.append(
        'namu',
        new Blob([JSON.stringify(this.namu)], { type: 'application/json' })
      );
      formData.append('file', this.form.file);
      insert(
        formData,
        (response) => {
          // console.log(response.data.message);
          // console.log('SUCCESS');
          swal(`${response.data.message}`);
          this.$router.push('/komuwiki');
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    Upload: function () {
      this.setDto();
      insert_nopic(
        this.namu,
        (response) => {
          swal(`${response.data.message}`);
          this.$router.push('/komuwiki');
        },
        (error) => {
          swal(`${error}`);
        }
      );
    },
    initUser() {
      this.loginid = getuidCookie();
      // this.loginid = '65D7CRpy4vBA';
    },
  },
};
</script>
