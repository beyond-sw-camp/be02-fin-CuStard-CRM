<template>
  <div class="body">
    <h3 class="result"> 카테고리 {{route.params.category}} </h3>
    <div class="searched-product">
      <div>
        <div class="product-row" v-for="(row, index) in productRows" :key="index">
          <div class="product-item" v-for="product in row" :key="product.idx">
            <CategoryCardComponent :product="product"></CategoryCardComponent>
          </div>
        </div>
      </div>
    </div>
    <div class="btn">
      <button  @click="goToPage(0)"> 처음으로 </button>
      <button @click="goToPage(+route.params.page-1)"> 이전 페이지 </button>
      <button  @click="goToPage(+route.params.page+1)"> 다음 페이지 </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import {useRoute, useRouter} from 'vue-router';
import CategoryCardComponent from "@/components/CategoryCardComponent.vue";

const products = ref([]);

const route = useRoute();
const router = useRouter();
let backend = "http://192.168.0.31:80/api";
// const backend = "http://localhost:8080";

onMounted(async () => {
  const category = route.params.category;
  const page = route.params.page;
  await fetchSearchResults(category, page);
});

async function fetchSearchResults(category, page) {
  try {
    const response = await fetch(backend + `/product/category/${category}?page=${page}`);
    if (!response.ok) {
      throw new Error('검색 결과를 가져오는 데 실패했습니다.');
    }
    const data = await response.json();
    products.value = data.result;
  } catch (error) {
    console.error('검색 결과를 가져오는 중 오류가 발생했습니다:', error);
  }
}

const productRows = computed(() => {
  const rows = [];
  for (let i = 0; i < products.value.length; i += 5) {
    rows.push(products.value.slice(i, i + 4));
  }
  return rows;
});

async function goToPage(idx) {
  if(idx < 0) alert("첫 페이지입니다.");
  else await router.push(`/category/${route.params.category}/${idx}`);
}
</script>

<style scoped>
.body{
  width: 100%;
}

.searched-product {
  width: 75%;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  //overflow: hidde n;
}

.product-row {
  display: flex;
}

.product-item {
  flex: 1;
}

.result{
  padding-left: 14%;
}

.btn{
  display: flex;
  justify-content: center;
}

</style>