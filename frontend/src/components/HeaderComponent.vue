<template>
  <header>
    <div class="css-1xfyvd1 eo7pjfk4">
      <div class="divgnb">
        <a v-show="!isLoggedIn" href="/signup" class="css-xygizb eo7pjfk2">회원가입</a>
        <div class="css-1qgm48u eo7pjfk0"></div>
        <a v-show="!isLoggedIn" href="/login" class="css-oyffzd eo7pjfk2">로그인</a>
        <div class="css-1qgm48u eo7pjfk0 login"></div>
        <a @click="logout()" class="css-oyffzd eo7pjfk2">로그아웃</a>
        <div class="css-1qgm48u eo7pjfk0"></div>
        <!-- <div class="css-1qolcqm eo7pjfk3"> </div> -->
      </div>

    </div>
    <div class="gnb">
      <a href="/" class="css-boc80u ekdqe1a1">
        <img  href="/" src="../../public/img/logo.png" alt="커스타드 로고" class="css-17mnrrx e1s3pt0j0 logo">
        <div class="active css-mxd3pm ekdqe1a0">CUSTARD</div>
      </a>
      <div class="css-pqw0uk e1493ofl4">
        <div class="css-w444a2 e1493ofl1"><input
            id="gnb_search"
            placeholder="검색어를 입력해주세요"
            required=""
            class="css-11ntk83 e1493ofl3"
            value="">
          <button id="submit" aria-label="submit" class="css-ywxmlw e1493ofl0"></button>
        </div>
      </div>

      <div>
        <router-link to="/qna/write" >1:1문의 내역</router-link>
      </div>

      <div class="css-c4pbxv e15sbxqa0">
        <div class="coupondiv" @mouseenter="fetchCoupons" @mouseleave="showDropdown = false">
          보유 쿠폰
          <transition name="fade">
            <div v-if="showDropdown" class="css-14vnom0 e1n3mt0d1 coupon dropdown">
              <ul>
                <li v-for="haveCoupon in coupons" :key="haveCoupon.idx">
                  [{{ getCategoryName(haveCoupon.count) }}]  {{ haveCoupon.coupon }} % 할인쿠폰 {{ haveCoupon.count }}개
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
import { ref } from 'vue';
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';


const coupons = ref([]);
const showDropdown = ref(false);
const router = useRouter();

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

// const fetchCoupons = async () => {
//   const url = "http://localhost:8080/coupon/list"; // 예: 'http://localhost:8080/api/coupons'
//   try {
//     const response = await fetch(url);
//     if (!response.ok) {
//       throw new Error(`HTTP error! status: ${response.status}`);
//     }
//     const data = await response.json();
//     coupons.value = data; // 응답 데이터를 coupons 상태에 저장
//     showDropdown.value = true;
//   } catch (error) {
//     console.error("Fetching coupons failed:", error);
//     showDropdown.value = false;
//   }
// };

// 쿠폰 데이터를 불러오는 함수
const fetchCoupons = async () => {
  // 로컬 스토리지에서 사용자 idx 가져오기
  const customerIdx = sessionStorage.getItem('customerIdx');

  if (!customerIdx) {
    alert('로그인이 필요합니다.');
    return;
  }

  const url = `http://localhost:8080/have/list?customerIdx=${customerIdx}`;

  try {
    const response = await axios.get(url);
    coupons.value = response.data; // 응답 데이터 저장
    showDropdown.value = true; // 드롭다운 표시
  } catch (error) {
    console.error("Fetching coupons failed:", error);
    showDropdown.value = false; // 실패 시 드롭다운 숨김
  }
};


const logout = () => {
  sessionStorage.removeItem("atoken");
  router.push("");
  router.go();
}

const isLoggedIn = computed(() => {
  if (sessionStorage.getItem("atoken") !== null) {
    return true;
  } else {
    return false;
  }

});
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
  color: black;
}

.divgnb {
  display: flex;
}

.css-t79vuj {
  position: relative;
  width: 1050px;
  height: 100px;
  margin: 0px auto;
  letter-spacing: -0.3px;
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
  color: rgb(95, 0, 128);
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
}

.css-mxd3pm {
  flex-shrink: 0;
  margin-left: 10px;
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

}

.css-11ntk83 {
  width: 300px;
  background-color: inherit;
  border: none;
  outline: none;
  font-size: 16px;
  letter-spacing: -0.33px;
}

.css-pqw0uk {
  position: relative;
  width: 1050px;
  margin: 0px auto;
}

.css-w444a2 {
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  justify-content: space-between;
  position: relative;
  left: 300px;
  /* top: -55px; */
  width: 465px;
  height: 48px;
  padding-left: 14px;
  border: 2px solid #99154e;
  border-radius: 6px;
  background-color: rgb(255, 255, 255);
  box-shadow: rgb(247, 247, 247) 0px 0px 0px 1px inset;
}


/* 쿠폰 드롭다운 */
.dropdown {
  position: absolute;
  top: 22px;
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
</style>
