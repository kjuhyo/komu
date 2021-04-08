<template>
  <div>
    <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
      <div v-if="isMobile">
        <b-button-group class="editor-buttons-first">
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold"
          >
            <b-icon icon="type-bold"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic"
          >
            <b-icon icon="type-italic"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.underline() }"
            @click="commands.underline"
          >
            <b-icon icon="type-underline"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.strike() }"
            @click="commands.strike"
          >
            <b-icon icon="type-strikethrough"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.bullet_list() }"
            @click="commands.bullet_list"
          >
            <b-icon icon="list-ul"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.ordered_list() }"
            @click="commands.ordered_list"
          >
            <b-icon icon="list-ol"></b-icon>
          </b-button>
        </b-button-group>
        <b-button-group class="editor-buttons-second">
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 1 }) }"
            @click="commands.heading({ level: 1 })"
          >
            H1
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 2 }) }"
            @click="commands.heading({ level: 2 })"
          >
            H2
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 3 }) }"
            @click="commands.heading({ level: 3 })"
          >
            H3
          </b-button>
          <b-button class="editor-button" @click="commands.undo">
            <b-icon icon="arrow-left"></b-icon>
          </b-button>
          <b-button class="editor-button" @click="commands.redo">
            <b-icon icon="arrow-right"></b-icon>
          </b-button>
        </b-button-group>
      </div>
      <div v-else>
        <b-button-group class="editor-buttons-first">
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold"
          >
            <b-icon icon="type-bold"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic"
          >
            <b-icon icon="type-italic"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.underline() }"
            @click="commands.underline"
          >
            <b-icon icon="type-underline"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.strike() }"
            @click="commands.strike"
          >
            <b-icon icon="type-strikethrough"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.bullet_list() }"
            @click="commands.bullet_list"
          >
            <b-icon icon="list-ul"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.ordered_list() }"
            @click="commands.ordered_list"
          >
            <b-icon icon="list-ol"></b-icon>
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 1 }) }"
            @click="commands.heading({ level: 1 })"
          >
            H1
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 2 }) }"
            @click="commands.heading({ level: 2 })"
          >
            H2
          </b-button>
          <b-button
            class="editor-button"
            :class="{ 'is-active': isActive.heading({ level: 3 }) }"
            @click="commands.heading({ level: 3 })"
          >
            H3
          </b-button>
          <b-button class="editor-button" @click="commands.undo">
            <b-icon icon="arrow-left"></b-icon>
          </b-button>
          <b-button class="editor-button" @click="commands.redo">
            <b-icon icon="arrow-right"></b-icon>
          </b-button>
        </b-button-group>
      </div>
    </editor-menu-bar>
    <editor-content :blur="send()" :editor="editor"/>
  </div>
</template>

<script>
import '../assets/css/texteditor.css';
import { Editor, EditorContent, EditorMenuBar } from 'tiptap';
import {
  Bold,
  Italic,
  Underline,
  Strike,
  Heading,
  History,
  OrderedList,
  BulletList,
  ListItem,
} from 'tiptap-extensions';

export default {
  components: { EditorMenuBar, EditorContent },
  props: {
    props_content: String,
    props_change: Boolean,
  },
  data() {
    return {
      isMobile: false,
      editor: new Editor({
        extensions: [
          new Heading({ levels: [1, 2, 3] }),
          new BulletList(),
          new OrderedList(),
          new ListItem(),
          new Bold(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History(),
        ],
      }),
    };
  },
  created() {
    this.editor.setContent(this.props_content);
  },
  watch: {
    props_change: function() {
      this.editor.setContent(this.props_content);
    },
  },
  methods: {
    send() {
      this.$emit('input', this.editor.getHTML());
    },
    // 화면 너비에 따른 모바일 여부 판단
    handleResize: function() {
      this.isMobile = window.innerWidth <= 480;
    },
  },
  mounted() {
    // 화면 너비 측정 이벤트 추가/
    this.handleResize();
    window.addEventListener('resize', this.handleResize);
  },

  beforeDestroy() {
    this.editor.destroy();
  },
};
</script>
