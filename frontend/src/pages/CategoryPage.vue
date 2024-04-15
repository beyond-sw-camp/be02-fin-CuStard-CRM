<template>
  <div class="body">
    <h3 class="result">{{categoryName}} </h3>
    <div class="searched-product">
      <div>
        <div class="product-row" v-for="(row, index) in productRows" :key="index">
          <div class="product-item" v-for="product in row" :key="product.idx">
            <CategoryCardComponent :product="product"></CategoryCardComponent>
          </div>
        </div>
      </div>
    </div>
    <div class="button-container">
<!--      <button  @click="goToPage(0)"> 첫 페이지 </button>-->
      <button @click="goToPage(-1)"> 이전 페이지 </button>
      <button @click="goToPage(1)"> 다음 페이지 </button>

<!--      <button  @click="goToPage(0)"> 마지막 페이지 </button>-->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted,watch, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
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

watch(() => route.params.page, async (newPage) => {
  await fetchSearchResults(route.params.category, newPage);
});

async function fetchSearchResults(category, page) {
  try {
    const response = await fetch(`${backend}/product/category/${category}?page=${page}`);
    if (!response.ok) {
      throw new Error('검색 결과를 가져오는 데 실패했습니다.');
    }
    const data = await response.json();
    products.value = data.result;
  } catch (error) {
    console.error('검색 결과를 가져오는 중 오류가 발생했습니다:', error);
  }
}

const categoryName = computed(() => {
  const category = route.params.category;
  switch(category) {
    case '1': return '의류';
    case '2': return '뷰티';
    case '3': return '식품';
    case '4': return '스포츠/레저';
    case '5': return '가전';
    default: return '기타';
  }
});

const productRows = computed(() => {
  const rows = [];
  for (let i = 0; i < products.value.length; i += 4) {
    rows.push(products.value.slice(i, i + 4));
  }
  return rows;
});

// async function goToPage(idx) {
//   if(idx < 0) alert("첫 페이지입니다.");
//   else await router.push(`/category/${route.params.category}/${idx}`);
// }

async function goToPage(pageNumber) {
  // 페이지 번호가 숫자인지 확인하고 숫자로 변환
  const currentPage = parseInt(route.params.page, 10);
  // 이동할 페이지 번호 계산
  const targetPage = currentPage + pageNumber;

  // 첫 페이지 이하로 가지 않도록 체크 (0은 첫 페이지를 가정)
  if (targetPage < 0) {
    alert("첫 페이지입니다.");
    return;
  }
  await router.push(`/category/${route.params.category}/${targetPage}`);
}
</script>


<style scoped>
.body {
  width: 100%;
}

.searched-product {
  width: 87%;
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

.result {
  padding-left: 8%;
  margin-top:  -12px;
}

.btn {
  display: flex;
  justify-content: center;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}

.button-container button {
  margin: 20px 10px;
  padding: 10px 20px;
  background-color: #58283d; /* Green */
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  border-radius: 10px
}

.button-container button:hover {
  background-color: #99154e; /* Darker Green */
}
</style>
