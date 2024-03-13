
<template>
  <div>
    <h3 class="result">검색 결과</h3>
    <div class="searched-product">
      <SearchCardComponent v-for="product in products" :key="product.idx" :product="product"></SearchCardComponent>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import SearchCardComponent from "@/components/SearchCardComponent.vue";

const products = ref([]);

const route = useRoute();

onMounted(async () => {
  const keyword = route.params.keyword;
  console.log(keyword);
  // 검색 결과를 받아오는 비동기 함수 호출
  await fetchSearchResults(keyword);
});

async function fetchSearchResults(keyword) {
  try {
    // 서버로부터 검색 결과를 가져오는 요청 보내기
    const response = await fetch(`http://localhost:8080/product/search/${keyword}`);
    if (!response.ok) {
      throw new Error('검색 결과를 가져오는 데 실패했습니다.');
    }
    // JSON 형식으로 응답 데이터 변환
    const data = await response.json();
    // 가져온 검색 결과를 상태에 저장
    products.value = data;
    console.log(products.value);

  } catch (error) {
    console.error('검색 결과를 가져오는 중 오류가 발생했습니다:', error);
  }
}
</script>

<style>
  .searched-product{
    display: flex;
    padding-top: 20px;
  }

  .result{
    padding-top: 60px;
  }
</style>