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
let backend = "http://192.168.0.31:80/api";
// let backend = "http://localhost:8080";

onMounted(async () => {
  const keyword = route.params.keyword;
  console.log(keyword);
  // 검색 결과를 받아오는 비동기 함수 호출
  await fetchSearchResults(keyword);
});

async function fetchSearchResults(keyword) {
  try {
    // 서버로부터 검색 결과를 가져오는 요청 보내기
    const response = await fetch(backend + `/product/search/${keyword}`);
    console.log(response);
    if (!response.ok) {
      throw new Error('검색 결과를 가져오는 데 실패했습니다.');
    }
    // JSON 형식으로 응답 데이터 변환
    const data = await response.json();
    // 가져온 검색 결과를 상태에 저장
    console.log(data);
    products.value = data.result;
    console.log(products.value);

  } catch (error) {
    console.error('검색 결과를 가져오는 중 오류가 발생했습니다:', error);
  }
}
</script>

<style>
.searched-product {
  display: grid;
  grid-template-columns: repeat(4, minmax(250px, 1fr));
  gap: 20px; /* 각 아이템 사이의 간격을 설정합니다 */
  padding-top: 20px;
  margin-left: 30px;
  font-size: 16px;
  font-weight: normal;
}

.result {
  padding-top: 60px;
  margin-left: 30px;
}


</style>
