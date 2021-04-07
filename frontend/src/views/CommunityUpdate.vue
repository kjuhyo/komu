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
              <input
                class="content_title"
                v-model="community.c_title"
              />
            </div>
            <br />
            <!-- 텍스트에디터 -->
            <text-editor
              :props_content="community.c_content"
              :props_change="getComm"
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
              <!-- :disabled="form.file == null" -->
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
import { getboard, update_nopic, update } from '@/api/community.js';
import { getuidCookie } from '@/util/cookie.js';

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
      name: '',
      community: {
        cid: 0,
        uid: '',
        c_title: '',
        c_content: '',
        c_date: '',
        is_delete: '',
        c_view: 0,
        c_like_cnt: 0,
        c_img: '',
      },
      getComu: false,
      message: '',
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
    this.name = this.$route.params.name; //cid얌
    getboard(
      this.name,
      this.loginid,
      (response) => {
        this.community = response.data.Community;
        this.message = response.data.message;
        this.getComm = !this.getComm;
        console.log(this.message);
      },
      (error) => {
        console.log(error);
      }
    );
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
    getboard: function() {},
    onResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },

    GetEditorContent: function(data) {
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
      //   this.form.desc = '';
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
    setDto: function() {
      this.community.c_content = this.content;
    },
    UploadCertification: function() {
      this.setDto();
      const formData = new FormData();
      formData.append(
        'community',
        new Blob([JSON.stringify(this.community)], { type: 'application/json' })
      );
      formData.append('file', this.form.file);
      update(
        formData,
        (response) => {
          console.log(response.data.message);
          console.log('SUCCESS');
          alert(response.data.message);
          this.$router.push('/communitydetail/' + this.community.cid);
        },
        (error) => {
          console.log(error.data);
        }
      );
    },
    Upload: function() {
      this.setDto();
      update_nopic(
        this.community,
        (response) => {
          console.log(response.data);
          this.$router.push('/communitydetail/' + this.community.cid);
        },
        (error) => {
          console.log('FAIL');
          console.log(error);
        }
      );
    },
    initUser() {
      this.loginid = getuidCookie();
      // this.loginid = 'namu';
    },
  },
};
</script>
