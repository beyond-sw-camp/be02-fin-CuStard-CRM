<template>
  <div class="production-selling">
    <div class="product-image-box">
      <img class="product-image" :src="this.productDetail.productImage" width="500px" height="500px">
    </div>
    <div class="production-selling-overview__content">
      <div class="production-selling-header">
        <h1 class="production-selling-header__title">
          <div class="production-selling-header__title__name-wrap">
            <div class="product-name">{{this.productDetail.productName}}</div>
            <span class="production-selling-header__title__name">{{this.productDetail.productDetails}}</span>
          </div>
        </h1>
        <div class="production-selling-header__price">
          <span class="number">{{this.productDetail.productPrice}}</span>
          <span class="won">원</span>
        </div>
      </div>
      <div class="production-selling-header__info-wrap">
        <span class="delivery">배송</span>
        <div class="production-selling-header__delivery">
          <b>무료배송</b>
          <div class="production-selling-header__delivery__expectation">
            지금 주문시 <b>내일 출발</b>
          </div>
          <span>일반택배</span>
          <div class="production-selling-header__delivery__disclaimer">
            제주도/도서산간 지역 20,000원
          </div>
          <span class="date">1/29(월) 도착 예정 <b>87%</b></span>

        </div>
      </div>
      <div class="production-selling-option-form">
        <p>주문금액 <span class="price">{{this.productDetail.productPrice}}</span>원</p>
      </div>
      <div class="production-selling-option-form__footer">
        <button class="button--color-blue button--size-55" type="button" @click="ordersCreate">바로 구매</button>
      </div>
    </div>
  </div>
</template>

<script>
// 상세 페이지 컴포넌트의 스크립트 부분
import axios from 'axios';
import {useRoute} from "vue-router";

// let backend = "http://192.168.0.53:80/api";
let backend = "http://localhost:8080";

export default {
  // props: {
  //   id: {
  //     type: String,
  //     required: true
  //   }
  // },
  data() {
    return {
      productDetail: "",
      productIdx: "",
      customData: []
    };
  },
  async created() {
    try {
      const route = useRoute()
      console.log(route.params.productIdx);
      this.productIdx = route.params.productIdx;

      const response = await axios.get(backend + `/product/read/${this.productIdx}`, {
        headers: {
          Authorization: localStorage.getItem("accessToken"),
        }});
      console.log(response);
      this.productDetail = response.data.result;
      console.log(this.productDetail);

      this.customData.push({"id": this.productIdx, "name": this.productDetail.productName, "price":this.productDetail.productPrice});
      console.log(this.customData);

    } catch (error) {
      console.error("상품 상세 정보를 불러오는데 실패했습니다.", error);
    }
  },
  methods: {
    async ordersCreate() {
      const { IMP } = window;
      IMP.init('imp62836256');

      let today = new Date();
      let hours = today.getHours(); // 시
      let minutes = today.getMinutes();  // 분
      let seconds = today.getSeconds();  // 초
      let milliseconds = today.getMilliseconds();
      let makeMerchantUid = hours + minutes + seconds + milliseconds;

      let product_name = "[cus+ard] " + this.productDetail.productName;

      IMP.request_pay({ // param
            pg: "kakaopay.TC0ONETIME",
            pay_method: "card",
            merchant_uid: "IMP" + makeMerchantUid,
            name: product_name,
            amount: this.productDetail.productPrice,
            buyer_email: "gildong@gmail.com",
            buyer_name: "홍길동",
            buyer_tel: "010-3333-3333",
            buyer_addr: "서울특별시 강남구 신사동",
            buyer_postcode: "01181",
            custom_data: this.customData
          }, async rsp => { // callback
            if (rsp.success) {
              // 결제 성공 시 로직,
              console.log(rsp.imp_uid);
              let response = await axios.get(backend + "/orders/validation?impUid=" + rsp.imp_uid, {
                    headers: {
                      Authorization: localStorage.getItem("accessToken"),
                    },
                  }
              )
              console.log(response.data)
              // window.location.href = "http://localhost:8081/order/complete"

              this.$router.push({
                name: 'orderCompletePage',
                query: { impUid: rsp.imp_uid.split("imp_")[1],
                  today: today.getFullYear() +"년 "+ today.getMonth()+ "월 "+today.getDate() +"일" ,
                  name: this.productDetail.productName,
                  price: this.productDetail.productPrice,
                },
              });
            }
          }
      )
    }
  }
};

</script>

<style scoped>
/* header start */
*{
  font-family: 'GmarketSans';
}

.production-selling {
  position: relative;
  width: 100%;
  min-height: 1px;
  justify-content : center;
  margin-top: 5%;
  display: flex;
  /* border: 1px solid red; */
}

div {
  display: block;
}

ol, ul {
  list-style: none;
}

ol {
  display: block;
  list-style-type: decimal;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  padding-inline-start: 40px;
}

.commerce-category-breadcrumb__entry {
  display: inline-block;
  margin-right: 2px;
  color: #757575;
  font-size: 13px;
  line-height: 1.2;
}

a {
  color: inherit;
  text-decoration: none;
}


.production-selling-cover-image-container {
  position: relative;
}

.production-selling-overview__cover-image {
  margin: 0 -15px;
}

.production-selling-cover-image__entry {
  position: relative;
  padding-bottom: 100%;
  /* background-color: #ededed; */
}



.production-selling-overview__content{
  width: 90%;
  /* padding-right:5%; */
  padding: 30px;
  height: auto;
  flex: right;
  /* background-color: blanchedalmond; */
}

.production-selling-header {
  position: relative;
}

h1 {
  display: block;
  font-size: 2em;
  margin-block-start: 0.67em;
  margin-block-end: 0.67em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}

.production-selling-header__title__brand-wrap {
  display: block;
  margin: 19px 0 4px;
  font-size: 14px;
  font-weight: 400;
  line-height: 19px;
  color: #757575;
  margin-bottom: 25px;
}

p {
  display: block;
}


.production-selling-header__title__name-wrap {
  position: relative;
}

.production-selling-header__title__name {
  display: block;
  font-size: 17px;
  font-weight: 400;
  line-height: 20px;
  color: #000;
  overflow-wrap: break-all;
  margin-right: 36px;
}

.production-selling-header__action {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0 -6px;
}

.production-selling-header__action {
  position: absolute;
  top: 0;
  right: 0;
  margin: 0 -6px;
}

.production-selling-header__action__button-scrap {
  display: none;
}
.production-selling-header__action__button {
  display: inline-block;
  margin: 0 3px;
  padding: 5px 4px 0;
  background: none;
  border: none;
  text-align: center;
  transition: opacity .1s;
  line-height: 0;
  z-index: 2;
  vertical-align: top;
}

.production-selling-header__content {
  min-height: 26px;
}

.production-selling-header__price__original {
  font-size: 20px;
  line-height: 17px;
  color: #bdbdbd;
  text-decoration: strike-through;
}

.production-selling-header__price__price-value-wrap {
  margin-top: 5px;
  color: #424242;
}

.production-selling-header__price__price {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  line-height: 1;
  font-size: 40px;
  font-weight: 700;
  margin: -4px 0 0 -6px;

}
.production-selling-header__price{
  font-size: 20px;
  font-weight: bold;
}
.production-selling-header__price__price>* {
  margin: 4px 0 0 6px;

}

.production-selling-header__price__price>.won {
  margin-left: 0;
}

.production-selling-header__info-wrap {
  padding: 16px 0;
  border-bottom: 1px solid #ededed;
  display: flex;
  flex-direction: row;
  /* background-color: red; */
}


.production-selling-header__delivery {
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
  display: flex;
  font-size: 14px;
  line-height: 21px;
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}

.production-selling-header__delivery__title-wrap {
  -webkit-flex-basis: 42px;
  -moz-flex-basis: 42px;
  -ms-flex-preferred-size: 42px;
  flex-basis: 42px;
}

.production-selling-header__delivery__content-wrap {
  -webkit-flex-basis: auto;
  -moz-flex-basis: auto;
  -ms-flex-preferred-size: auto;
  flex-basis: auto;
  -webkit-box-flex: 1;
  -webkit-flex-grow: 1;
  -moz-flex-grow: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  width: 42px;
}

.production-selling-header__delivery__fee {
  display: block;
  margin-bottom: 4px;
  color: #292929;
}

.production-selling-header__delivery__today-departure__dropdown {
  display: block;
  margin-bottom: 4px;
}

._2SUYq {
  position: relative;
  display: inline-block
}

.production-selling-header__delivery__today-departure__header {
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -ms-flex-align: center;
  -webkit-align-items: center;
  -moz-align-items: center;
  align-items: center;
}

.production-selling-header__delivery__today-departure__header .text {
  color: #2f3438;
}

.production-selling-header__delivery__today-departure__header .text .active {
  color: #a34610;
  font-weight: 700;
}

.production-selling-header__delivery__type {
  display: block;
  margin-bottom: 4px;
  color: #292929;
}

.production-selling-header__delivery__type>span:first-of-type:before {
  margin: 0;
  border-left: 0;
}
.production-selling-header__delivery__type>span:before {
  content: "";
  height: 15px;
  display: inline-block;
  margin: 0 6px;
  border-left: 1px solid #292929;
  vertical-align: -2px;
}
.production-selling-header__delivery__expectation-wrap {
  display: block;
  border-radius: 4px;
  background-color: #f7f8fa;
  height: 40px;
  padding-top: 5px;
}

.production-selling-header__delivery__expectation-section {
  display: block;
  margin-top: 12px;
}

.production-selling-header__delivery {
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
  display: flex;
  font-size: 14px;
  line-height: 21px;
}


.css-49v6aj {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-align-items: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  margin-top: 16px;
  color: #2F3438;
  font-weight: 700;
  margin-top: 40px;
}



.production-selling-option-form__footer {
  -webkit-box-flex: 0;
  -webkit-flex: 0 0 auto;
  -moz-box-flex: 0;
  -moz-flex: 0 0 auto;
  -ms-flex: 0 0 auto;
  flex: 0 0 auto;
  display: -webkit-box;
  display: -webkit-flex;
  display: -moz-flex;
  display: -ms-flexbox;
  display: flex;
  margin: 20px -3px 0;
}
.production-selling-option-form{
  font-size: 20px;
}

.production-selling-option-form__footer>.button, .production-selling-option-form__footer__sold-out {
  -webkit-box-flex: 1;
  -webkit-flex: 1 0 0px;
  -moz-box-flex: 1;
  -moz-flex: 1 0 0px;
  -ms-flex: 1 0 0px;
  flex: 1 0 0px;
  margin: 0 3px;
}

.button--size-55 {
  padding: 13px 10px 14px;
  font-size: 17px;
  line-height: 26px;
}
.button--color-blue-inverted {
  background-color: #fff;
  border-color: #d99b3f;
  color: #35c5f0;
}
.button {
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
  display: inline-block;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  border: 1px solid transparent;
  background: none;
  font-family: inherit;
  font-weight: 700;
  text-decoration: none;
  text-align: center;
  transition: color .1s,background-color .1s,border-color .1s;
  border-radius: 4px;
  cursor: pointer;
}

.button--color-blue {
  background-color: #d99b3f;
  border-color: #d99b3f;
  color: #fff;
  height: 50px;
  width: 60%;
  margin-top: 20px;
}

.price{
  margin-left: 350px;
}

.product-details{
  width: 80%;
  padding-left:10%;
  margin-top: 60px;
  float:right;
}

.product-image-box{
  width: 500px;
  height: 500px;
  float: left;
  padding-left: 20%;

}

.product-image{
  width:100%;
  height:100%;
  object-fit:cover;
}

.product-name{
  height: 60px;
}


</style>
