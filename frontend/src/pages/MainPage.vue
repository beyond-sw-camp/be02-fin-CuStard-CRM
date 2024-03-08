<template>
  <div>
    <section v-for="(categorySlides, category) in categoriesWithSlides" :key="category">
      <h2>{{ category }}</h2>
      <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="(product) in categorySlides" :key="product.idx">
          <div class="carousel__slide product">
            <div class="carousel__item-column">
              <img :src="product.productImage" alt="Slide Image">
            </div>
            <div class="carousel__item-column">
              <div class="carousel__item-details">
                <div class="carousel__item-title">{{ product.productName }}</div>
                <div class="carousel__item-price">{{ product.productPrice }}</div>
              </div>
            </div>
          </div>
        </Slide>
        <template #addons>
          <Navigation />
          <Pagination />
        </template>
      </Carousel>
    </section>
  </div>
</template>
<script>
import { ref, defineComponent } from 'vue';
import axios from 'axios';
import { Carousel, Slide, Pagination, Navigation } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
export default defineComponent({
  name: 'CategoryCarousels',
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation
  },
  setup() {
    const categoriesWithSlides = ref({});
    const fetchCategorySlides = async () => {
      try {
        const response = await axios.get('http://localhost:8080/product/list');
        const products = response.data;
        // 카테고리 별로 제품을 그룹화
        products.forEach(product => {
          if (!categoriesWithSlides.value[product.category]) {
            categoriesWithSlides.value[product.category] = [];
          }
          categoriesWithSlides.value[product.category].push(product);
        });
        for (const category in categoriesWithSlides.value) {
          switch (category) {
            case '1':
              categoriesWithSlides.value[category].categoryName = '식품';
              break;
            case '2':
              categoriesWithSlides.value[category].categoryName = '가전';
              break;
            case '3':
              categoriesWithSlides.value[category].categoryName = '패션';
              break;
            default:
              categoriesWithSlides.value[category].categoryName = '기타';
              break;
          }
        }
      } catch (error) {
        console.error('Error fetching categories and products:', error);
      }
    };
    fetchCategorySlides();
    return {
      categoriesWithSlides
    };
  },
});
</script>


  
  <style>
  .div{
    display: flex;
    flex-direction: column;
    align-items: center;
    align-content: stretch;

  }
  .div2{
    display: flex;
    flex-direction: column;
    justify-content: left;
  }
 .carousel__icon {
    width: var(--vc-icn-width);
    height: var(--vc-icn-width);
    fill: currentColor;
    border: 2px solid black;
    background-color: black;
    border-radius: 10px;
    color: white;
}
  .carousel__slide {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }
  
  
  .carousel__item-column {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    -webkit-line-clamp: 1;

  }
  
  .carousel__item {
    min-height: 200px;
    width: 100%;
    background-color: blue;
    color: var(--vc-clr-white);
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .carousel__slide {
    padding: 10px;
    width: 150px;
    height: 200px;
  }
  
  .carousel__item-details {
    text-align: center;
    margin-top: 10px;
    text-align: justify;
    /* border: 1px solid black; */
    width: 120px;
    height: 100px;
    overflow: hidden;  	
  text-overflow: ellipsis;  	
  white-space: nowrap; 	
  word-break:break-all;

  }
  
  .carousel__item-title {
    font-weight: bold;
  }
  
  .carousel__item-price {
    margin-top: 5px;
    font-size: 17px;
    font-weight: lighter;
  }

  .carousel__pagination-button{
    width: 17px;
  }
  img {
    width: 100px;
    height: 100px;
    object-fit: contain;
}

  </style>
  