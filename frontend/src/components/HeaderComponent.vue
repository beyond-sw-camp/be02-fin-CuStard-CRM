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
                        <div class="css-w444a2 e1493ofl1">
                          <input
                            id="gnb_search"
                            placeholder="검색어를 입력해주세요"
                            required=""
                            class="css-11ntk83 e1493ofl3"
                            v-model="searchInput"
                          >
                          <a :href= "'/search/' + searchInput">
                            <button id="submit" aria-label="submit" class="css-ywxmlw e1493ofl0"></button>
                          </a>
                        </div>
                    </div>

                    <div class="css-c4pbxv e15sbxqa0">
                        <div class="coupondiv">
                          <div class="css-c4pbxv e15sbxqa0">
                            <div class="coupondiv" @mouseenter="fetchCoupons" @mouseleave="showDropdown = false">
                              보유 쿠폰
                              <!-- 드롭다운 메뉴 -->
                              <transition name="fade">
                                <div v-if="showDropdown" class="css-14vnom0 e1n3mt0d1 coupon dropdown">
                                  <ul>
                                    <li v-for="coupon in coupons" :key="coupon.idx">[{{ getCategoryName(coupon.couponCategory) }}]            {{coupon.discount}}% 할인쿠폰</li>
                                  </ul>
                                </div>
                              </transition>
                            </div>
                          </div>
                        </div>
                    </div>
                </div>
            </header>
</template>

<script setup>
import { ref } from 'vue';
import { computed } from 'vue';
import { useRouter } from 'vue-router';


const coupons = ref([]);
const showDropdown = ref(false);
const router = useRouter();
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

const fetchCoupons = async () => {
  const url = "http://localhost:8080/coupon/list"; // 예: 'http://localhost:8080/api/coupons'
  try {
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    const data = await response.json();
    coupons.value = data; // 응답 데이터를 coupons 상태에 저장
    showDropdown.value = true;
  } catch (error) {
    console.error("Fetching coupons failed:", error);
    showDropdown.value = false;
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

// const search = async (keyword) => {
//   this.$router.push(`/search/${keyword}`);
// }


</script>

<style scoped>
ul{
  padding: 0;
  margin: 0;}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
/* header */
ul{
  list-style: none;
}
a{
    text-decoration-line: none;
  color: black;
}
.divgnb{
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
.css-1lrerrk {
    width: 8px;
    height: 5px;
    background-image: url(https://res.kurly.com/pc/ico/1908/ico_down_16x10.png);
    background-size: cover;
    background-position: center center;
    display: inline-block;
    margin-left: 5px;
    margin-bottom: 1px;
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
.login{
    margin-right: 20px;
}
.gnb{
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
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzYiIGhlaWdodD0iMzYiIHZpZXdCb3g9IjAgMCAzNiAzNiIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZmlsbD0ibm9uZSIgZD0iTTAgMGgzNnYzNkgweiIvPgogICAgICAgIDxnIHN0cm9rZT0iIzVGMDA4MCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2Utd2lkdGg9IjEuNyI+CiAgICAgICAgICAgIDxwYXRoIGQ9Im0yNi4wODEgMjYuMDgxLTQuMTItNC4xMk0xNi40NjcgMjMuMzM0YTYuODY3IDYuODY3IDAgMSAwIDAtMTMuNzM0IDYuODY3IDYuODY3IDAgMCAwIDAgMTMuNzM0eiIvPgogICAgICAgIDwvZz4KICAgIDwvZz4KPC9zdmc+Cg==) 0px 0px no-repeat;
    border: none;
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

.css-17tqugj {
    min-width: 1050px;
    letter-spacing: -0.3px;
    background-color: rgb(255, 255, 255);
    position: relative;
    box-shadow: rgba(0, 0, 0, 0.07) 0px 3px 4px 0px;
    width: 100%;
    z-index: 300;
}
/* 쿠폰 아이콘 */
.css-14vnom0 {
    //width: 32px;
    //height: 50px;
    margin-right: 5px;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    //background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'%3E%3Ctitle%3Eticket-percent-outline%3C/title%3E%3Cpath d='M14.8 8L16 9.2L9.2 16L8 14.8L14.8 8M4 4H20C21.11 4 22 4.89 22 6V10C20.9 10 20 10.9 20 12C20 13.11 20.9 14 22 14V18C22 19.11 21.11 20 20 20H4C2.9 20 2 19.11 2 18V14C3.11 14 4 13.11 4 12C4 10.9 3.11 10 2 10V6C2 4.89 2.9 4 4 4M4 6V8.54C5.24 9.26 6 10.57 6 12C6 13.43 5.24 14.75 4 15.46V18H20V15.46C18.76 14.75 18 13.43 18 12C18 10.57 18.76 9.26 20 8.54V6H4M9.5 8C10.33 8 11 8.67 11 9.5C11 10.33 10.33 11 9.5 11C8.67 11 8 10.33 8 9.5C8 8.67 8.67 8 9.5 8M14.5 13C15.33 13 16 13.67 16 14.5C16 15.33 15.33 16 14.5 16C13.67 16 13 15.33 13 14.5C13 13.67 13.67 13 14.5 13Z' /%3E%3C/svg%3E");
    cursor: pointer;
}
/* 찜 호버 했을 때 리스트 */


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
}

.css-ff2aah {
    position: relative;
    margin-left: 20px;
}

.css-mlddcv {
    position: relative;
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    width: 1050px;
    height: 56px;
    margin: 0px auto;
}
.css-axtlq9 {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    height: 100%;
}
.css-1k5gn9s {
    width: 16px;
    height: 14px;
    margin-right: 14px;
    background: url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMTYiIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAxNiAxNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0wIDBoMTZ2MS43SDBWMHptMCA2LjE1aDE2djEuN0gwdi0xLjd6bTAgNi4xNWgxNlYxNEgwdi0xLjd6IiBmaWxsPSIjMzMzIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiLz4KPC9zdmc+Cg==) 0px 0px / 16px 14px no-repeat;
}
.css-t75x7c {
    font-size: 16px;
    font-weight: 500;
    line-height: 20px;
    letter-spacing: -0.3px;
    color: rgb(51, 51, 51);
}

/* 쿠폰 드롭다운 */
.dropdown {
  position: absolute;
  top: 22px;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
}
.dropdown::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}
.coupondiv{
    /* border: 1px solid red; */
    display: flex;
    transition: all 0.5s ease 0s;
    margin-right: 46px;
}
.amount{
    margin-top: 10px;
}
.dropdown > li{
    margin: 9px 0px;
}

/* 로고 */
.logo{
    width: 50px;
    height: 50px;
}
</style>
