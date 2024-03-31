<template>
  <div class="product-info">
    <a :href="`/product/` + product.idx">
    <div clas="product-image">
      <img class="image" :src="product.productImage" width="300px" height="300px" alt=""/>
    </div>
    <div class="product-name" style="font-size: 15px"> {{ product.productName }}</div>
    <div class="product-price" style="font-size: 15px"> {{ formatNumber(product.productPrice)}}원</div>
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
  //width: 270px;
  height: 400px;
  margin-left: 15px;
}

.image{
  width:90%;
  height:90%;
  overflow-block: hidden;
}

/*.image:nth-child(5){
  height: 10px;
  width: 10px;
}*/

</style>