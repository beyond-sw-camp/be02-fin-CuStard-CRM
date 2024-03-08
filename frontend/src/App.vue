<template>
  <HeaderComponent />
  <router-view></router-view>
  <FooterComponent />
</template>


<script>
import HeaderComponent from "./components/HeaderComponent.vue"
import FooterComponent from "./components/FooterComponent.vue"

export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent,
  }
}
</script>

<style>
body {
  font-family: 'IBM Plex Sans KR', sans-serif;
  font-weight: bold;
}
</style>
