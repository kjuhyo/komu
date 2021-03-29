<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container" v-click-outside="closeModal">
          <div class="modal-header">
            <slot name="header">
              <Login/>
            </slot>
          </div>

        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import Login from '../components/user/Login.vue'

export default {


  name: 'LoginModal',
  components: {
    Login,
    
  },
  data () {
      return {
        tab: null,
        items: [
          'Login', 'Register',
        ],
        // closeOnContentClick: true,
      }
    },
  methods: {
      closeModal: function() {
      this.$emit("close");
    }
  },
}
</script>

<style lang="scss">
/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

</style>
