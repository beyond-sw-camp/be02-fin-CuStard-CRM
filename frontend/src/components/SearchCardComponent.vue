<template>
  <div class="product-info">
    <a :href="`/product/` + product.idx">
    <div clas="product-image">
      <img class="image" :src="product.productImage" width="300px" height="300px" alt=""/>
    </div>
    <div class="product-name" style="font-size: 20px"> {{ product.productName }}</div>
    <div class="product-price"> {{ product.productPrice}} </div>
   <div class="product-price"> {{ product.idx}} </div>
    </a>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      type: Object,
      required: true
    }
  }
}
</script>

<style>
.product-info{
  margin-right: 60px;
  width: 320px;
}

.image{
  width: 300px;
  height:300px;
}
</style>