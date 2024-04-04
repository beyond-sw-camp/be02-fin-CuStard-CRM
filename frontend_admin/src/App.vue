<template>
  <nav-component v-if="!$route.meta.hideNav" />
  <side-component v-if="!$route.meta.hideNav" />
  <router-view></router-view>
</template>

<script>
import NavComponent from "@/components/NavComponent.vue";
import SideComponent from "@/components/SideComponent.vue";

export default {
  name: 'App',
  components: {
    NavComponent,
    SideComponent
  }
}
</script>



<style>
#app {
  display: flex;
  flex-direction: row; /* 가로 방향으로 요소들을 정렬 */
}

/* 사이드 컴포넌트와 네비게이션 컴포넌트의 너비를 조정하고 싶다면 추가적인 CSS가 필요할 수 있습니다. */
.side-component {
  /* 예: 고정된 너비 설정 */
  width: 200px;
}
.nav-component {
  /* 필요한 경우 스타일링 */
}
</style>
