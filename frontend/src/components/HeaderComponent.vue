<template>

  <header>
    <div class="css-1xfyvd1 eo7pjfk4">
      <div class="divgnb">
        <a v-show="!isLoggedIn" href="/signup" class="css-xygizb eo7pjfk2">회원가입</a>
        <div class="css-1qgm48u eo7pjfk0"></div>
        <a v-show="!isLoggedIn" href="/login" class="css-oyffzd eo7pjfk2">로그인</a>
        <a v-show="isLoggedIn" @click="logout" class="css-oyffzd eo7pjfk2">로그아웃</a>
        <div class="css-1qgm48u eo7pjfk0"></div>
        <!-- <div class="css-1qolcqm eo7pjfk3"> </div> -->
      </div>

    </div>
    <div class="gnb">
      <a href="/" class="css-boc80u ekdqe1a1">
        <img  href="/" src="../../public/img/logo.png" alt="커스타드 로고" class="css-17mnrrx e1s3pt0j0 logo">
        <div class="active css-mxd3pm ekdqe1a0">Cus+ard</div>
      </a>
      <div class="css-pqw0uk e1493ofl4">
        <div class="css-w444a2 e1493ofl1">
          <input
            id="gnb_search"
            placeholder="검색어를 입력해주세요."
            required=""
            class="css-11ntk83 e1493ofl3"
            value=""
            v-model="searchInput"
          >
          <a :href= "'/search/' + searchInput">
            <button id="submit" aria-label="submit" class="css-ywxmlw e1493ofl0"></button>
          </a>
        </div>
      </div>

      <div class="answer">
        <router-link to="/qna/write" >1:1문의 내역</router-link>
      </div>

      <div class="css-c4pbxv e15sbxqa0">
        <div class="coupondiv" @click="fetchCoupons" @mouseleave="showDropdown = false">
          보유 쿠폰
          <transition name="fade">
            <div v-if="showDropdown" class="css-14vnom0 e1n3mt0d1 coupon dropdown">
              <ul>
                <li v-for="haveCoupon in coupons" :key="haveCoupon.idx">
                  [{{ getCategoryName(haveCoupon.count) }}]  {{ haveCoupon.discount }} % 할인쿠폰 {{ haveCoupon.count }}개
                </li>
              </ul>
            </div>
          </transition>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { ref, computed } from 'vue';
// import { useRouter } from 'vue-router';
import axios from 'axios';
let backend = "http://192.168.0.31:80/api";
// let backend = "http://localhost:8080";

const coupons = ref([]);
const showDropdown = ref(false);
// const router = useRouter();
const searchInput = ref('');

// 카테고리 번호에 따른 이름을 반환하는 메서드
function getCategoryName(categoryId) {
  switch(categoryId) {
    case 1:
      return '패션';
    case 2:
      return '뷰티';
    case 3:
      return '식품';
    case 4:
      return '스포츠/레저';
    case 5:
      return '가전';

      // 추가 카테고리에 대한 케이스
    default:
      return '기타';
  }
}

// 쿠폰 데이터를 불러오는 함수
  const fetchCoupons = async () => {
    // 로컬 스토리지에서 사용자 idx 가져오기
    const customerIdx = sessionStorage.getItem('customerIdx');

    if (!customerIdx) {
      alert('로그인이 필요합니다.');
      return;
    }

    const url = backend + `/have/list?customerIdx=${customerIdx}`;

    try {
      const response = await axios.get(url);
      coupons.value = response.data.result; // 응답 데이터 저장
      showDropdown.value = true; // 드롭다운 표시

      console.log(coupons);
    } catch (error) {
      console.error("Fetching coupons failed:", error);
      showDropdown.value = false; // 실패 시 드롭다운 숨김
    }
  };

const logout = () => {
  localStorage.removeItem("accessToken");
  localStorage.removeItem("customerIdx");
  localStorage.removeItem("notified");
  localStorage.setItem('notified', 'false');
  location.reload();
};



const isLoggedIn = computed(() => {
  return localStorage.getItem("accessToken") !== null;
});
// const search = async (keyword) => {
//   this.$router.push(`/search/${keyword}`);
// }


</script>

<style scoped>
ul {
  padding: 0;
  margin: 0;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s;
}

.fade-enter-from, .fade-leave-to {
  opacity: 0;
}

/* header */
ul {
  list-style: none;
}

a {
  text-decoration-line: none;
  color: #333;
}

.divgnb {
  display: flex;
  margin-right: 45px;
}

.css-t79vuj {
  position: relative;
  width: 1050px;
  height: 100px;
  margin: 0px auto;
  letter-spacing: -0.3px;
}
.css-c4pbxv {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  /* right: -200px;
  top: -49px; */
  /* border: 1px solid red; */
  width: 200px;
  justify-content: flex-end;
  right: -81px;
}

.css-1xfyvd1 {
  display: flex;
  -webkit-box-align: center;
  font-size: 13px;
  justify-content: flex-end;
  /* border: 1px solid red; */
  margin-bottom: 10px;

}

.css-xygizb {
  display: block;
  color: #99154e;
  cursor: pointer;
}

.css-1qgm48u:first-of-type {
  width: 1px;
  height: 13px;
  margin: 0px 12px;
  background-color: rgb(217, 217, 217);
}

.css-oyffzd {
  display: block;
  cursor: pointer;
  color: #333;
}

.css-1qolcqm {
  line-height: 35px;
  position: relative;
}

.css-oyffzd {
  display: block;
  cursor: pointer;
}


.css-1ho29iy {
  position: absolute;
  right: 0px;
  top: 34px;
  width: 130px;
  padding: 3px 9px;
  border: 1px solid rgb(221, 221, 221);
  background-color: rgb(255, 255, 255);
}

.css-1qolcqm .menu {
  display: none;
}

.css-12olpw6 {
  display: block;
  height: 24px;
  line-height: 24px;
  cursor: pointer;
}

.css-r7wmjj {
  height: 100px;
  padding-top: 14px;
}

.css-boc80u {
  display: flex;
  /* -webkit-box-align: center; */
  align-items: center;
  height: 63px;
  width: 156px;
  /* border: 1px solid red; */
  position: relative;
  left: 35px;
}

.login {
  margin-right: 20px;
}

.gnb {
  /* background-color: blue; */
  display: flex;
  align-items: center;
}

.css-ywxmlw {
  position: relative;
  width: 30px;
  height: 30px;
  margin: 10px;
  bottom: 3px;
  background-image: url("../assets/search.png") ;
  background-repeat: no-repeat;
  background-size : 60%;
  background-position : 5px 9px;
  border: none;
  background-color: #FFF;
}

.css-mxd3pm {
  flex-shrink: 0;
  margin-left: 10px;
  margin-bottom: -8px;
  font-size: 35px;
  font-weight: normal;
  line-height: 1.33;
  letter-spacing: normal;
  color: rgb(181, 181, 181);
  cursor: pointer;
  border: none;
  background: none;
}

.css-mxd3pm.active, .css-mxd3pm:hover {
  font-weight: 500;
  color: rgb(255 190 14);
  border: none;
  background: none;
  margin-top: -10px;
}

.css-11ntk83 {
  width: 300px;
  background-color: inherit;
  border: none;
  outline: none;
  font-size: 16px;
  letter-spacing: -0.33px;
  color: 33380;

}

.css-pqw0uk {
  position: relative;
  width: 1050px;
  margin: 0px auto;
  right: 100px;
  display: contents;
}

.css-w444a2 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  margin: 0 160px;
  /* top: -55px; */
  width: 465px;
  height: 48px;
  padding-left: 14px;
  border: 2px solid #99154e;
  border-radius: 6px;
  background-color: rgb(255, 255, 255);
  box-shadow: rgb(247, 247, 247) 0px 0px 0px 1px inset;
}
.css-11ntk83::placeholder {
  color: 33380; /* Change this to the desired color */
}

/* 쿠폰 드롭다운 */
.dropdown {
  position: absolute;
  top: 22px;
  right: 20px;
  width: 200px;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  padding: 12px 16px;
  z-index: 1;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}

.dropdown::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}

.coupondiv {
  /* border: 1px solid red; */
  display: flex;
  transition: all 0.5s ease 0s;
  margin-right: 46px;
  color:#333;
}

.amount {
  margin-top: 10px;
}

.dropdown > li {
  margin: 9px 0px;
}

/* 로고 */
.logo {
  width: 50px;
  height: 50px;
}
.answer{
  width: 120px;
  color: #333;
  right: -167px;
  position: relative;
  //border: 1px solid red;
}
</style>
