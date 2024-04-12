<template>
  <div class="product-info">
    <a :href="`/product/` + product.idx">
      <div clas="product-image">
        <img class="image" :src="product.productImage" width="300px" height="300px" alt=""/>
      </div>
      <div class="product-name" style="font-size: 20px"> {{ product.productName }}</div>
      <div class="product-price"> {{ formatNumber(product.productPrice)}}원 </div>
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
  },
  methods: {
    formatNumber(value) {
      return new Intl.NumberFormat().format(value);
    },
  }
}
</script>

<style>
.product-info{
  //margin-right: 60px;
  width: 300px;
}

.image{
  width: 280px;
  height:280px;
}



</style>
